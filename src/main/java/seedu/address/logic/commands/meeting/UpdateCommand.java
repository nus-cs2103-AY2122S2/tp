package seedu.address.logic.commands.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_MEETING;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AGENDA;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDEES_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLACE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MEETINGS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.Agenda;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingPlace;
import seedu.address.model.meeting.MeetingTime;
import seedu.address.model.meeting.UpdateMeetingDescriptor;
import seedu.address.model.person.Id;
import seedu.address.model.person.Person;

public class UpdateCommand extends Command {
    public static final String COMMAND_WORD = "update";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the meeting identified "
            + "by the index number used in the displayed meeting list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_ATTENDEES_INDEX + "LIST_OF_ATTENDEES_INDEX] "
            + "[" + PREFIX_AGENDA + "AGENDA] "
            + "[" + PREFIX_TIME + "MEETING_TIME] "
            + "[" + PREFIX_PLACE + "MEETING PLACE] ";

    public static final String MESSAGE_UPDATE_MEETING_SUCCESS = "Meeting has been updated";
    public static final String MESSAGE_NOT_UPDATED = "At least one field to edit must be provided.";

    private final Index targetIndex;
    private final UpdateMeetingDescriptor updateMeetingDescriptor;

    /**
     * @param targetIndex of the meeting in the filtered meeting list to edit
     * @param updateMeetingDescriptor details to edit the meeting with
     */
    public UpdateCommand(Index targetIndex, UpdateMeetingDescriptor updateMeetingDescriptor) {
        requireNonNull(targetIndex);
        requireNonNull(updateMeetingDescriptor);

        this.targetIndex = targetIndex;
        this.updateMeetingDescriptor = new UpdateMeetingDescriptor(updateMeetingDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Meeting> lastShownMeetingList = model.getFilteredMeetingList();
        List<Person> lastShownPersonList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownMeetingList.size()) {
            throw new CommandException(MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
        }

        Meeting meetingToEdit = lastShownMeetingList.get(targetIndex.getZeroBased());
        Meeting editedMeeting = createEditedMeeting(meetingToEdit, updateMeetingDescriptor, lastShownPersonList);

        if (!meetingToEdit.isSameMeeting(editedMeeting) && model.hasMeeting(editedMeeting)) {
            throw new CommandException(MESSAGE_DUPLICATE_MEETING);
        }

        model.setMeeting(meetingToEdit, editedMeeting);
        model.updateFilteredMeetingList(PREDICATE_SHOW_ALL_MEETINGS);
        return new CommandResult(MESSAGE_UPDATE_MEETING_SUCCESS);
    }

    /**
     * Creates and returns a {@code Meeting} with the details of {@code meetingToEdit}
     * edited with {@code updateMeetingDescriptor}.
     */
    private static Meeting createEditedMeeting(Meeting meetingToEdit, UpdateMeetingDescriptor updateMeetingDescriptor,
                                               List<Person> lastShownPersonList) throws CommandException {
        requireNonNull(meetingToEdit);

        Agenda updatedAgenda = updateMeetingDescriptor.getAgenda().orElse(meetingToEdit.getAgenda());
        MeetingPlace updatedPlace = updateMeetingDescriptor.getMeetingPlace().orElse(meetingToEdit.getPlace());
        MeetingTime updatedTime = updateMeetingDescriptor.getMeetingTime().orElse(meetingToEdit.getTime());
        Set<Index> updatedAttendeesIndexes = meetingToEdit.getIndexes();
        Set<Id> updatedAttendees = meetingToEdit.getAttendees();

        if (updateMeetingDescriptor.areAttendeesChanged()) {
            updatedAttendeesIndexes = updateMeetingDescriptor.getIndexes()
                    .orElse(meetingToEdit.getIndexes());
            updatedAttendees = new HashSet<>();
            for (Index index : updatedAttendeesIndexes) {
                if (index.getZeroBased() >= lastShownPersonList.size()) {
                    throw new CommandException(String.format(MESSAGE_INVALID_PERSON_DISPLAYED_INDEX,
                            index.getOneBased()));
                } else { //only add if valid
                    updatedAttendees.add(lastShownPersonList.get(index.getZeroBased()).getId());
                }
            }
        }

        return new Meeting(updatedAgenda, updatedPlace, updatedTime, updatedAttendeesIndexes, updatedAttendees);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }
        // instanceof handles nulls
        if (!(other instanceof UpdateCommand)) {
            return false;
        }

        // state check
        UpdateCommand u = (UpdateCommand) other;
        return targetIndex.equals(u.targetIndex)
                && updateMeetingDescriptor.equals(u.updateMeetingDescriptor);
    }
}

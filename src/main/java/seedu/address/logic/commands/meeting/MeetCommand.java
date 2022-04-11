package seedu.address.logic.commands.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_MEETING;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AGENDA;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDEES_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLACE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Id;
import seedu.address.model.person.Person;

/**
 * Creates a new meeting for the user.
 */
public class MeetCommand extends Command {

    public static final String COMMAND_WORD = "meet";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":Adds a new meeting.\n"
            + "Required Parameters: "
            + PREFIX_ATTENDEES_INDEX + "[INDEX OF PERSON IN REACHE] [MORE INDICES OF PEOPLE IN REACHE]... "
            + PREFIX_AGENDA + "AGENDA "
            + PREFIX_PLACE + "MEETING PLACE "
            + PREFIX_TIME + "MEETING TIME\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ATTENDEES_INDEX + "1 3 5 67 "
            + PREFIX_AGENDA + "Product Demo with Client "
            + PREFIX_PLACE + "Conference Room 5A "
            + PREFIX_TIME + "05-04-2022 15:44";

    public static final String INDEX_CANNOT_BE_EMPTY_MESSAGE = "At least one participant is required!";

    public static final String AGENDA_CANNOT_BE_EMPTY_MESSAGE = "Please add an agenda to the meeting!";

    public static final String MESSAGE_SUCCESS = "Created a new meeting: %1$s";

    private final Meeting toMeet;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public MeetCommand(Meeting meeting) {
        requireNonNull(meeting);
        toMeet = meeting;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        Set<Id> attendees = new HashSet<>();
        for (Index index : toMeet.getIndexes()) {
            if (index.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(String.format(MESSAGE_INVALID_PERSON_DISPLAYED_INDEX,
                        index.getOneBased()));
            }
            attendees.add(lastShownList.get(index.getZeroBased()).getId());
        }

        Meeting meetingWithAttendeesAdded = toMeet.setAttendees(attendees);
        if (model.hasMeeting(meetingWithAttendeesAdded)) {
            throw new CommandException(MESSAGE_DUPLICATE_MEETING);
        }
        model.addMeeting(meetingWithAttendeesAdded);
        return new CommandResult(String.format(MESSAGE_SUCCESS, meetingWithAttendeesAdded), false, false,
                true, false, false, false, null);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MeetCommand // instanceof handles nulls
                && toMeet.equals(((MeetCommand) other).toMeet));
    }

    @Override
    public String toString() {
        return "New meeting: " + toMeet.getAgenda().toString();
    }
}

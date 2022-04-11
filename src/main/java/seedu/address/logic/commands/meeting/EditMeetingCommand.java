package seedu.address.logic.commands.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LABEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATETIME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MEETINGS;

import java.time.LocalDateTime;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;


/**
 * Edits the details of an existing meeting in the address book.
 */
public class EditMeetingCommand extends Command {

    public static final String COMMAND_WORD = "editMeeting";

    public static final String MESSAGE_USAGE = "Parameters:\n• INDEX (must be a positive integer) "
            + "[" + PREFIX_START_DATETIME + "START_DATETIME] "
            + "[" + PREFIX_END_DATETIME + "END_DATETIME] "
            + "[" + PREFIX_LABEL + "LABEL]\n"
            + "(Existing values will be overwritten by the input values)\n"
            + "Example:\n• " + COMMAND_WORD + " 1 "
            + PREFIX_START_DATETIME + "01-01-2022 18:00 "
            + PREFIX_END_DATETIME + "01-01-2022 20:00 "
            + PREFIX_LABEL + "Dinner";

    public static final String MESSAGE_EDIT_MEETING_SUCCESS = "Edited Meeting: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_OVERLAPPING_MEETING =
            "The new timing specified overlaps with at least one existing meeting.";
    public static final String MESSAGE_INVALID_DATETIME =
            "The new timing specified is invalid. The start date time cannot be after the end date time";

    private final Index index;
    private final EditMeetingDescriptor editMeetingDescriptor;

    /**
     * @param index of the meeting in the filtered meeting list to edit
     * @param editMeetingDescriptor details to edit the meeting with
     */
    public EditMeetingCommand(Index index, EditMeetingDescriptor editMeetingDescriptor) {
        requireNonNull(index);
        requireNonNull(editMeetingDescriptor);

        this.index = index;
        this.editMeetingDescriptor = new EditMeetingDescriptor(editMeetingDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Meeting> lastShownList = model.getFilteredMeetingList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
        }

        Meeting meetingToEdit = lastShownList.get(index.getZeroBased());
        Meeting editedMeeting = createEditedMeeting(meetingToEdit, editMeetingDescriptor);

        if (model.isOverlappingExcept(editedMeeting, meetingToEdit)) {
            throw new CommandException(MESSAGE_OVERLAPPING_MEETING);
        }

        if (!Meeting.isValidMeeting(editedMeeting.getStartDateTime(), editedMeeting.getEndDateTime())) {
            throw new CommandException(MESSAGE_INVALID_DATETIME);
        }

        model.setMeeting(meetingToEdit, editedMeeting);
        model.updateFilteredMeetingList(PREDICATE_SHOW_ALL_MEETINGS, model.isShowAllMeetings());
        return new CommandResult(String.format(MESSAGE_EDIT_MEETING_SUCCESS, editedMeeting));
    }

    /**
     * Creates and returns a {@code Client} with the details of {@code clientToEdit}
     * edited with {@code editClientDescriptor}.
     */
    private static Meeting createEditedMeeting(Meeting meetingToEdit, EditMeetingDescriptor editMeetingDescriptor) {
        assert meetingToEdit != null;

        LocalDateTime startDateTime = editMeetingDescriptor.getStartDateTime() != null
                ? editMeetingDescriptor.getStartDateTime()
                : meetingToEdit.getStartDateTime();
        LocalDateTime endDateTime = editMeetingDescriptor.getEndDateTime() != null
                ? editMeetingDescriptor.getEndDateTime()
                : meetingToEdit.getEndDateTime();
        String label = editMeetingDescriptor.getLabel() != null
                ? editMeetingDescriptor.getLabel()
                : meetingToEdit.getLabel();

        return new Meeting(startDateTime, endDateTime, meetingToEdit.getClient(), label);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditMeetingCommand)) {
            return false;
        }

        // state check
        EditMeetingCommand e = (EditMeetingCommand) other;
        return index.equals(e.index)
                && editMeetingDescriptor.equals(e.editMeetingDescriptor);
    }

    /**
     * Stores the details to edit the meeting with. Each non-empty field value will replace the
     * corresponding field value of the meeting.
     */
    public static class EditMeetingDescriptor {
        private LocalDateTime startDateTime;
        private LocalDateTime endDateTime;
        private String label;

        public EditMeetingDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditMeetingDescriptor(EditMeetingDescriptor toCopy) {
            setStartDateTime(toCopy.startDateTime);
            setEndDateTime(toCopy.endDateTime);
            setLabel(toCopy.label);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(startDateTime, endDateTime, label);
        }

        public void setStartDateTime(LocalDateTime startDateTime) {
            this.startDateTime = startDateTime;
        }

        public void setEndDateTime(LocalDateTime endDateTime) {
            this.endDateTime = endDateTime;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public LocalDateTime getStartDateTime() {
            return startDateTime;
        }

        public LocalDateTime getEndDateTime() {
            return endDateTime;
        }

        public String getLabel() {
            return label;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditMeetingDescriptor)) {
                return false;
            }

            // state check
            EditMeetingDescriptor e = (EditMeetingDescriptor) other;

            return getStartDateTime().equals(e.getStartDateTime())
                    && getEndDateTime().equals(e.getEndDateTime())
                    && getLabel().equals(e.getLabel());
        }
    }
}

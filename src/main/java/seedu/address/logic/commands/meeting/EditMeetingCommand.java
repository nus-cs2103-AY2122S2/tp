package seedu.address.logic.commands.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECURRING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_URL;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.IsRecurring;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingDateTime;
import seedu.address.model.meeting.MeetingDuration;
import seedu.address.model.meeting.MeetingName;
import seedu.address.model.meeting.MeetingUrl;
import seedu.address.model.module.Module;
import seedu.address.model.tag.Tag;

/**
 * Edits a meeting in LinkyTime.
 */
public class EditMeetingCommand extends Command {
    public static final String COMMAND_WORD = "edit";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the meeting identified "
            + "by the index number used in the displayed list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_URL + "URL] "
            + "[" + PREFIX_DATETIME + "DATETIME] "
            + "[" + PREFIX_DURATION + "DURATION] "
            + "[" + PREFIX_MODULE + "MODULE_INDEX] "
            + "[" + PREFIX_RECURRING + "IS_RECURRING] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME + "Tutorial "
            + PREFIX_DURATION + "1.0";

    public static final String MESSAGE_EDIT_MEETING_SUCCESS = "Edited meeting: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_MEETING = "This meeting already exists in LinkyTime.";

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
        final List<Meeting> lastShownMeetingList = model.getFilteredMeetingList();

        if (index.getZeroBased() >= lastShownMeetingList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
        }

        final List<Module> lastShownModuleList = model.getFilteredModuleList();
        final Optional<Index> moduleIndex = editMeetingDescriptor.getModuleIndex();
        final Optional<Module> editedModule;

        if (moduleIndex.isEmpty()) {
            editedModule = Optional.empty();
        } else if (moduleIndex.get().getZeroBased() >= lastShownModuleList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
        } else {
            editedModule = Optional.ofNullable(lastShownModuleList.get(moduleIndex.get().getZeroBased()));
        }

        final Meeting meetingToEdit = lastShownMeetingList.get(index.getZeroBased());
        final Meeting editedMeeting = createEditedMeeting(meetingToEdit, editedModule, editMeetingDescriptor);

        if (!meetingToEdit.equals(editedMeeting) && model.hasMeeting(editedMeeting)) {
            throw new CommandException(MESSAGE_DUPLICATE_MEETING);
        }

        model.setMeeting(meetingToEdit, editedMeeting);
        return new CommandResult(String.format(MESSAGE_EDIT_MEETING_SUCCESS, editedMeeting));
    }

    /**
     * Creates and returns a {@code Meeting} with the details of {@code meetingToEdit}
     * edited with {@code editMeetingDescriptor} and {@code editedModule}.
     */
    private static Meeting createEditedMeeting(Meeting meetingToEdit, Optional<Module> editedModule,
            EditMeetingDescriptor editMeetingDescriptor) {
        assert meetingToEdit != null;

        final MeetingName updatedName = editMeetingDescriptor.getName().orElse(meetingToEdit.getName());
        final MeetingUrl updatedUrl = editMeetingDescriptor.getUrl().orElse(meetingToEdit.getUrl());
        final MeetingDateTime updatedDateTime = editMeetingDescriptor.getDateTime().orElse(meetingToEdit
                .getStartDateTime());
        final MeetingDuration updatedDuration = editMeetingDescriptor.getDuration().orElse(meetingToEdit.getDuration());
        final Module updatedModule = editedModule.orElse(meetingToEdit.getModule());
        final IsRecurring updatedIsRecurring = editMeetingDescriptor.getIsRecurring().orElse(meetingToEdit
                .getIsRecurring());
        final Set<Tag> updatedTags = editMeetingDescriptor.getTags().orElse(meetingToEdit.getTags());

        return new Meeting(updatedName, updatedUrl, updatedDateTime, updatedDuration, updatedModule,
                updatedIsRecurring, updatedTags);
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
        final EditMeetingCommand e = (EditMeetingCommand) other;
        return index.equals(e.index)
                && editMeetingDescriptor.equals(e.editMeetingDescriptor);
    }

    /**
     * Stores the details to edit the meeting with. Each non-empty field value will replace the
     * corresponding field value of the meeting.
     */
    public static class EditMeetingDescriptor {
        private MeetingName name;
        private MeetingUrl url;
        private MeetingDateTime dateTime;
        private MeetingDuration duration;
        private Index moduleIndex;
        private IsRecurring isRecurring;
        private Set<Tag> tags;

        public EditMeetingDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditMeetingDescriptor(EditMeetingDescriptor toCopy) {
            setName(toCopy.name);
            setUrl(toCopy.url);
            setDateTime(toCopy.dateTime);
            setDuration(toCopy.duration);
            setModuleIndex(toCopy.moduleIndex);
            setIsRecurring(toCopy.isRecurring);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, url, dateTime, duration, moduleIndex, isRecurring, tags);
        }

        public void setName(MeetingName name) {
            this.name = name;
        }

        public Optional<MeetingName> getName() {
            return Optional.ofNullable(name);
        }

        public void setUrl(MeetingUrl url) {
            this.url = url;
        }

        public Optional<MeetingUrl> getUrl() {
            return Optional.ofNullable(url);
        }

        public void setDateTime(MeetingDateTime dateTime) {
            this.dateTime = dateTime;
        }

        public Optional<MeetingDateTime> getDateTime() {
            return Optional.ofNullable(dateTime);
        }

        public void setDuration(MeetingDuration duration) {
            this.duration = duration;
        }

        public Optional<MeetingDuration> getDuration() {
            return Optional.ofNullable(duration);
        }

        public void setModuleIndex(Index moduleIndex) {
            this.moduleIndex = moduleIndex;
        }

        public Optional<Index> getModuleIndex() {
            return Optional.ofNullable(moduleIndex);
        }

        public void setIsRecurring(IsRecurring isRecurring) {
            this.isRecurring = isRecurring;
        }

        public Optional<IsRecurring> getIsRecurring() {
            return Optional.ofNullable(isRecurring);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
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
            final EditMeetingDescriptor e = (EditMeetingDescriptor) other;

            return getName().equals(e.getName())
                    && getUrl().equals(e.getUrl())
                    && getDateTime().equals(e.getDateTime())
                    && getDuration().equals(e.getDuration())
                    && getModuleIndex().equals(e.getModuleIndex())
                    && getIsRecurring().equals(e.getIsRecurring())
                    && getTags().equals(e.getTags());
        }
    }

}

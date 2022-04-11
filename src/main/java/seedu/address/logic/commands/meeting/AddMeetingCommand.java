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
 * Adds a meeting to LinkyTime.
 */
public class AddMeetingCommand extends Command {
    public static final String COMMAND_WORD = "add";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a meeting to LinkyTime. "
            + "Parameters: "
            + PREFIX_NAME + "MEETING_NAME "
            + PREFIX_URL + "URL "
            + PREFIX_DATETIME + "DATETIME "
            + PREFIX_DURATION + "DURATION "
            + PREFIX_MODULE + "MODULE_INDEX "
            + PREFIX_RECURRING + "IS_RECURRING "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Tutorial "
            + PREFIX_URL + "https://www.zoom.com "
            + PREFIX_DATETIME + "30-04-2022 1400 "
            + PREFIX_DURATION + "2 "
            + PREFIX_MODULE + "1 "
            + PREFIX_RECURRING + "Y "
            + PREFIX_TAG + "Boring";

    public static final String MESSAGE_SUCCESS = "New meeting added: %1$s";
    public static final String MESSAGE_DUPLICATE_MEETING = "This meeting already exists in LinkyTime";

    private final AddMeetingDescriptor addMeetingDescriptor;

    /**
     * Creates an AddMeetingCommand to add the specified {@code Meeting}
     */
    public AddMeetingCommand(AddMeetingDescriptor addMeetingDescriptor) {
        requireNonNull(addMeetingDescriptor);
        this.addMeetingDescriptor = addMeetingDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        final List<Module> lastShownModuleList = model.getFilteredModuleList();
        final Index moduleIndex = addMeetingDescriptor.getModuleIndex();

        if (moduleIndex.getZeroBased() >= lastShownModuleList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
        }

        final Module module = lastShownModuleList.get(moduleIndex.getZeroBased());
        final Meeting meetingToAdd = AddMeetingDescriptor.createMeeting(addMeetingDescriptor, module);

        if (model.hasMeeting(meetingToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_MEETING);
        }

        model.addMeeting(meetingToAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, meetingToAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddMeetingCommand // instanceof handles nulls
                && addMeetingDescriptor.equals(((AddMeetingCommand) other).addMeetingDescriptor));
    }

    public static class AddMeetingDescriptor {
        private MeetingName name;
        private MeetingUrl url;
        private MeetingDateTime dateTime;
        private MeetingDuration duration;
        private Index moduleIndex;
        private IsRecurring isRecurring;
        private Set<Tag> tags;

        public AddMeetingDescriptor() {}

        /**
         * Constructs a AddMeetingDescriptor.
         * Used in AddMeetingCommandParser.
         */
        public AddMeetingDescriptor(MeetingName name, MeetingUrl url, MeetingDateTime dateTime,
                                    MeetingDuration duration, Index moduleIndex, IsRecurring isRecurring,
                                    Set<Tag> tags) {
            this.name = name;
            this.url = url;
            this.dateTime = dateTime;
            this.duration = duration;
            this.moduleIndex = moduleIndex;
            this.isRecurring = isRecurring;
            this.tags = tags;
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public AddMeetingDescriptor(AddMeetingDescriptor toCopy) {
            this.name = toCopy.name;
            this.url = toCopy.url;
            this.dateTime = toCopy.dateTime;
            this.duration = toCopy.duration;
            this.moduleIndex = toCopy.moduleIndex;
            this.isRecurring = toCopy.isRecurring;
            this.tags = toCopy.tags;
        }

        public MeetingName getName() {
            return name;
        }

        public void setName(MeetingName name) {
            this.name = name;
        }

        public MeetingUrl getUrl() {
            return url;
        }

        public void setUrl(MeetingUrl url) {
            this.url = url;
        }

        public MeetingDateTime getDateTime() {
            return dateTime;
        }

        public void setDateTime(MeetingDateTime dateTime) {
            this.dateTime = dateTime;
        }

        public MeetingDuration getDuration() {
            return duration;
        }

        public void setDuration(MeetingDuration duration) {
            this.duration = duration;
        }

        public Index getModuleIndex() {
            return moduleIndex;
        }

        public void setModuleIndex(Index moduleIndex) {
            this.moduleIndex = moduleIndex;
        }

        public IsRecurring getIsRecurring() {
            return isRecurring;
        }

        public void setIsRecurring(IsRecurring isRecurring) {
            this.isRecurring = isRecurring;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Creates and returns a {@code Meeting} with the given {@code addMeetingDescriptor} and {@code module}.
         */
        public static Meeting createMeeting(AddMeetingDescriptor addMeetingDescriptor, Module module) {
            assert module != null;

            final MeetingName name = addMeetingDescriptor.getName();
            final MeetingUrl url = addMeetingDescriptor.getUrl();
            final MeetingDateTime dateTime = addMeetingDescriptor.getDateTime();
            final MeetingDuration duration = addMeetingDescriptor.getDuration();
            final IsRecurring isRecurring = addMeetingDescriptor.getIsRecurring();
            final Set<Tag> tags = addMeetingDescriptor.getTags().get();

            return new Meeting(name, url, dateTime, duration, module, isRecurring, tags);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof AddMeetingDescriptor)) {
                return false;
            }

            // state check
            final AddMeetingDescriptor a = (AddMeetingDescriptor) other;

            return getName().equals(a.getName())
                    && getUrl().equals(a.getUrl())
                    && getDateTime().equals(a.getDateTime())
                    && getDuration().equals(a.getDuration())
                    && getModuleIndex().equals(a.getModuleIndex())
                    && getIsRecurring().equals(a.getIsRecurring())
                    && getTags().equals(a.getTags());
        }
    }
}

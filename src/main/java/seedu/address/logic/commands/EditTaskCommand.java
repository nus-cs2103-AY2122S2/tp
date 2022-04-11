package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DATE_RANGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LINK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASKNAME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.TagUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Link;
import seedu.address.model.task.Task;

/**
 * Edits the details of an existing task in the task list.
 */
public class EditTaskCommand extends Command {

    public static final String COMMAND_WORD = "editt";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edit and update the details of the task identified"
            + " by the index number used in the displayed task list. \n"
            + "Existing values will be overwritten by the input values. Index must be a positive integer\n"
            + "Usage: "
            + COMMAND_WORD + " "
            + "INDEX "
            + "[" + PREFIX_TASKNAME + "TASK NAME] "
            + "[" + PREFIX_DATETIME + "DATETIME(dd-mm-yyyy hhmm)] "
            + "[" + PREFIX_LINK + "LINK] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TASKNAME + "CS2103T Lecture "
            + PREFIX_DATETIME + "12-03-2023 1330 "
            + PREFIX_LINK + "https://...  "
            + PREFIX_TAG + "Lecture";

    public static final String MESSAGE_EDIT_TASK_SUCCESS = "Updated Task: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.\n%1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in your task list.";

    private final Index index;
    private final EditTaskDescriptor editTaskDescriptor;

    /**
     * Constructs EditTaskCommand that takes in a task index and editTaskDescriptor.
     * @param index of the task in the filtered task list to edit
     * @param editTaskDescriptor details to edit the task with
     */
    public EditTaskCommand(Index index, EditTaskDescriptor editTaskDescriptor) {
        requireNonNull(index);
        requireNonNull(editTaskDescriptor);

        this.index = index;
        this.editTaskDescriptor = new EditTaskDescriptor(editTaskDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToEdit = lastShownList.get(index.getZeroBased());
        Task editedTask = createEditedTask(taskToEdit, editTaskDescriptor);

        String checkTagLength = TagUtil.checkTagLength(editedTask.getTags());

        //null value represents no tags are too long.
        if (checkTagLength != null) {
            throw new CommandException(checkTagLength);
        }

        if (!taskToEdit.isSameTask(editedTask) && model.hasTask(editedTask)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        if (editedTask.hasInvalidDateRange()) {
            throw new CommandException(MESSAGE_INVALID_DATE_RANGE);
        }

        model.setTask(taskToEdit, editedTask);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(String.format(MESSAGE_EDIT_TASK_SUCCESS, editedTask));
    }

    /**
     * Creates and returns a {@code Task} with the details of {@code taskToEdit}
     * edited with {@code editTaskDescriptor}.
     */
    private static Task createEditedTask(Task taskToEdit, EditTaskDescriptor editTaskDescriptor) {
        requireNonNull(taskToEdit);
        requireNonNull(editTaskDescriptor);

        String editName = editTaskDescriptor.getName().orElse(taskToEdit.getName());
        LocalDateTime editDate = editTaskDescriptor.getDate().orElse(taskToEdit.getDateTime());
        LocalDateTime editEndDate = editTaskDescriptor.getEndDate();
        Set<Tag> editTag = editTaskDescriptor.getTags().orElse(taskToEdit.getTags());
        Link link = editTaskDescriptor.getLink().orElse(taskToEdit.getLink());
        boolean isTaskMarkDone = taskToEdit.isTaskMark();

        return new Task(editName, editDate, editEndDate, editTag, link, isTaskMarkDone);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditTaskCommand)) {
            return false;
        }

        // state check
        EditTaskCommand e = (EditTaskCommand) other;
        return index.equals(e.index)
                && editTaskDescriptor.equals(e.editTaskDescriptor);
    }

    /**
     * Stores the details to edit the task with. Each non-empty field value will replace the
     * corresponding field value of the task.
     */
    public static class EditTaskDescriptor {
        private String name;
        private LocalDateTime dateTime;
        private LocalDateTime endDateTime;
        private Set<Tag> tags;
        private Link link;

        public EditTaskDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditTaskDescriptor(EditTaskDescriptor toCopy) {
            requireNonNull(toCopy);
            setName(toCopy.name);
            setDate(toCopy.dateTime);
            setEndDate(toCopy.endDateTime);
            setTags(toCopy.tags);
            setLink(toCopy.link);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, dateTime, tags, link);
        }

        public void setName(String name) {
            this.name = name;
        }

        public Optional<String> getName() {
            return Optional.ofNullable(name);
        }

        public void setDate(LocalDateTime dateTime) {
            this.dateTime = dateTime;
        }

        public void setEndDate(LocalDateTime endDateTime) {
            this.endDateTime = endDateTime;
        }

        public void setLink(Link link) {
            this.link = link;
        }

        public Optional<LocalDateTime> getDate() {
            return Optional.ofNullable(dateTime);
        }

        public LocalDateTime getEndDate() {
            return endDateTime;
        }

        public Optional<Link> getLink() {
            return Optional.ofNullable(link);
        }

        /**
         * Sets {@code tag} to this object's {@code tag}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return Optional.ofNullable(tags);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditTaskDescriptor)) {
                return false;
            }

            // state check
            EditTaskDescriptor e = (EditTaskDescriptor) other;

            return getName().equals(e.getName())
                    && getDate().equals(e.getDate())
                    && getTags().equals(e.getTags());
        }
    }
}

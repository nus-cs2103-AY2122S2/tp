package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HEADER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INFORMATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.logic.parser.Type.TASK;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Type;
import seedu.address.model.Date;
import seedu.address.model.Model;
import seedu.address.model.Time;
import seedu.address.model.tasks.Header;
import seedu.address.model.tasks.Information;
import seedu.address.model.tasks.Task;

public class EditTaskCommand extends EditCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + " [t] : Edits the details of the task identified "
            + "by the index number used in the displayed task list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "<" + PREFIX_HEADER + "HEADER> "
            + "<" + PREFIX_DATE + "DATE> "
            + "<" + PREFIX_TIME + "TIME> "
            + "<" + PREFIX_INFORMATION + "INFORMATION> \n"
            + "Example: " + COMMAND_WORD + " [t] 1 "
            + PREFIX_HEADER + "Update interview statuses "
            + PREFIX_DATE + "2021-05-06 ";

    public static final String MESSAGE_EDIT_TASK_SUCCESS = "Edited Task: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the address book.";

    private final Index index;
    private final EditTaskDescriptor editTaskDescriptor;

    /**
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

        if (!taskToEdit.isSameTask(editedTask) && model.hasTask(editedTask)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        model.setTask(taskToEdit, editedTask);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(String.format(MESSAGE_EDIT_TASK_SUCCESS, editedTask), getType());
    }

    /**
     * Creates and returns a {@code Task} with the details of {@code taskToEdit}
     * edited with {@code editTaskDescriptor}.
     */
    private static Task createEditedTask(
            Task taskToEdit, EditTaskDescriptor editTaskDescriptor) {
        assert taskToEdit != null;

        Header updatedHeader = editTaskDescriptor.getHeader().orElse(taskToEdit.getHeader());
        Information updatedInformation = editTaskDescriptor.getInformation().orElse(taskToEdit.getInformation());
        Date updatedDate = editTaskDescriptor.getDate().orElse(taskToEdit.getDate());
        Time updatedTime = editTaskDescriptor.getTime().orElse(taskToEdit.getTime());

        return new Task(updatedHeader, updatedDate, updatedTime, updatedInformation);
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

    @Override
    public Type getType() {
        return TASK;
    }

    /**
     * Stores the details to edit the task with. Each non-empty field value will replace the
     * corresponding field value of the task.
     */
    public static class EditTaskDescriptor {
        private Header header;
        private Information information;
        private Date date;
        private Time time;

        public EditTaskDescriptor() {}

        /**
         * Copy constructor.
         *
         */
        public EditTaskDescriptor(EditTaskDescriptor toCopy) {
            setHeader(toCopy.header);
            setInformation(toCopy.information);
            setDate(toCopy.date);
            setTime(toCopy.time);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(header, information, date, time);
        }

        public void setHeader(Header header) {
            this.header = header;
        }

        public Optional<Header> getHeader() {
            return Optional.ofNullable(header);
        }

        public void setInformation(Information information) {
            this.information = information;
        }

        public Optional<Information> getInformation() {
            return Optional.ofNullable(information);
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public Optional<Date> getDate() {
            return Optional.ofNullable(date);
        }

        public void setTime(Time time) {
            this.time = time;
        }

        public Optional<Time> getTime() {
            return Optional.ofNullable(time);
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

            return getHeader().equals(e.getHeader())
                    && getInformation().equals(e.getInformation())
                    && getDate().equals(e.getDate())
                    && getTime().equals(e.getTime());
        }
    }
}


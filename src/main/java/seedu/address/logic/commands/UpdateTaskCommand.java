package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADD_TASK_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADD_TASK_DESCRIPTION;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.TaskList;
import seedu.address.model.task.Task;


public class UpdateTaskCommand extends Command {
    public static final String COMMAND_WORD = "updt";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Updates the details of the chosen task"
        + " by the task ID used in the displayed task list. "
        + "Existing values will be overwritten by the input values.\n"
        + "At least one field must be updated "
        + "Parameters: Task ID (must be a positive integer) "
        + "[" + PREFIX_ADD_TASK_DESCRIPTION + "DESCRIPTION] "
        + "[" + PREFIX_ADD_TASK_DEADLINE + "DEADLINE]...\n"
        + "Example: " + COMMAND_WORD + " 1 "
        + PREFIX_ADD_TASK_DESCRIPTION + "Running Lesson "
        + PREFIX_ADD_TASK_DEADLINE + "04/05/2022";

    public static final String MESSAGE_EMPTY_PARAMETERS = "Parameter given cannot be blank";
    public static final String MESSAGE_UPDATE_TASK_SUCCESS = "Updated Task: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_INVALID_TASK_DISPLAYED_ID = "The task id provided is invalid";

    private final Integer taskId;
    private final UpdateTaskDescriptor updateTaskDescriptor;

    /**
     * @param taskId of the person in the filtered person list to edit
     * @param updateTaskDescriptor details to edit the person with
     */
    public UpdateTaskCommand(Integer taskId, UpdateTaskDescriptor updateTaskDescriptor) {
        requireNonNull(taskId);
        requireNonNull(updateTaskDescriptor);

        this.taskId = taskId;
        this.updateTaskDescriptor = new UpdateTaskDescriptor(updateTaskDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        TaskList lastShownList = model.getTaskList();

        if (taskId > lastShownList.size()) {
            throw new CommandException(MESSAGE_INVALID_TASK_DISPLAYED_ID);
        }

        Task taskToUpdate = lastShownList.getTask(taskId);
        Task updatedTask = createUpdatedTask(taskToUpdate, updateTaskDescriptor);

        model.updateTask(updatedTask, taskId);

        return new CommandResult(String.format(MESSAGE_UPDATE_TASK_SUCCESS, updatedTask));
    }

    /**
     * Creates and returns a {@code Task} with the details of {@code taskToEdit}
     * edited with {@code updateTaskDescriptor}.
     */
    public static Task createUpdatedTask(Task taskToUpdate, UpdateTaskDescriptor updateTaskDescriptor) {
        assert taskToUpdate != null;

        String updatedDescription = "";
        String updatedDeadline = "";

        if (updateTaskDescriptor.getDescription() == null) {
            updatedDescription = taskToUpdate.getDescription();
        } else {
            updatedDescription = updateTaskDescriptor.getDescription();
        }

        if (updateTaskDescriptor.getDeadline() == null) {
            updatedDeadline = taskToUpdate.getDeadline();
        } else {
            updatedDeadline = updateTaskDescriptor.getDeadline();
        }

        return new Task(updatedDescription, updatedDeadline);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UpdateTaskCommand)) {
            return false;
        }

        // state check
        UpdateTaskCommand e = (UpdateTaskCommand) other;
        return taskId.equals(e.taskId)
            && updateTaskDescriptor.equals(e.updateTaskDescriptor);
    }

    /**
     * Stores the details to edit the task with. Each non-empty field value will replace the
     * corresponding field value of the task.
     */
    public static class UpdateTaskDescriptor {
        private String description;
        private String deadline;

        public UpdateTaskDescriptor() {

        }

        /**
         * Constructor for updateTaskDescriptor object
         *
         */
        public UpdateTaskDescriptor(UpdateTaskDescriptor updateTaskDescriptor) {
            setDeadline(updateTaskDescriptor.deadline);
            setDescription(updateTaskDescriptor.description);
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDescription() {
            return this.description;
        }

        public void setDeadline(String deadline) {
            this.deadline = deadline;
        }

        public String getDeadline() {
            return this.deadline;
        }


        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof UpdateTaskDescriptor)) {
                return false;
            }

            // state check
            UpdateTaskDescriptor e = (UpdateTaskDescriptor) other;

            return getDescription().equals(e.getDescription())
                && getDeadline().equals(e.getDeadline());
        }
    }
}

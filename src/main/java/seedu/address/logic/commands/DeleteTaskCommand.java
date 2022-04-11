package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Delete a task from the system
 */
public class DeleteTaskCommand extends Command {
    public static final String COMMAND_WORD = "delt";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Delete a task number from current tasklist using given index. \n"
            + "Format: delt <tasknumber>. \n"
            + "Example: " + COMMAND_WORD + " " + "1";

    public static final String MESSAGE_ARGUMENTS = "Task %1$s deleted";

    public static final String MESSAGE_NO_INDEX = "Task number not found!";

    public static final String MESSAGE_INDEX_OUT_OF_BOUNDS = "Task number does not exist in current task list!";

    private final Integer taskNumber;

    /**
     * Initializes a DeleteTaskCommand with the given taskNumber
     * @param taskNumber index to be deleted
     */
    public DeleteTaskCommand(Integer taskNumber) {
        requireAllNonNull(taskNumber);
        this.taskNumber = taskNumber;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireAllNonNull(model);

        if (this.taskNumber <= 0 || this.taskNumber > model.getTaskList().size()) {
            throw new CommandException(MESSAGE_INDEX_OUT_OF_BOUNDS + "\n" + MESSAGE_USAGE);
        }

        model.deleteTask(taskNumber);

        // Printing out current items in list to ensure item is deleted
        System.out.println(model.getTaskList().size());

        return new CommandResult(String.format(MESSAGE_ARGUMENTS, taskNumber));

    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteTaskCommand)) {
            return false;
        }

        //state check
        DeleteTaskCommand e = (DeleteTaskCommand) other;
        return taskNumber.equals(e.taskNumber);
    }



}

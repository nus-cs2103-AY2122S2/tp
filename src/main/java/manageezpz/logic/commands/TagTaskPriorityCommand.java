package manageezpz.logic.commands;

import static java.util.Objects.requireNonNull;
import static manageezpz.logic.parser.CliSyntax.PREFIX_PRIORITY;

import manageezpz.logic.commands.exceptions.CommandException;
import manageezpz.model.Model;
import manageezpz.model.task.Task;

public class TagTaskPriorityCommand extends Command {
    public static final String COMMAND_WORD = "tagPriority";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Tag the Task to your specified Priority "
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_PRIORITY + "LOW/MEDIUM/HIGH" + " (This must be strictly followed!)"
            + "\r\n"
            + "Example: " + COMMAND_WORD + " "
            + "1 "
            + PREFIX_PRIORITY + "HIGH";
    public static final String MESSAGE_SUCCESS = "Task has been tagged with the appropriate priority!";
    public static final String MESSAGE_DUPLICATE_TASK = "This Task has already been assigned to the same person!";

    private int index;
    private String priority;

    /**
     * Constructor for TagTaskCommand
     * @param index index of the Task to be tagged.
     * @param priority Priority level to be set for the Task.
     */
    public TagTaskPriorityCommand(int index, String priority) {
        this.index = index;
        this.priority = priority;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Task task;
        try {
            task = model.getFilteredTaskList().get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException("This Task Number is invalid. \r\n"
                    + MESSAGE_USAGE);
        }

        String currTaskPriority = task.getPriority().name();
        if(currTaskPriority.equals(Task.Priority.NONE.name())) {
            try {
                task.setPriority(priority);
                return new CommandResult(String.format(MESSAGE_SUCCESS, task));
            } catch(NullPointerException e) {
                throw new CommandException("Priority cannot be NULL! \r\n"
                        + MESSAGE_USAGE);
            } catch(IllegalArgumentException e) {
                throw new CommandException("Priority is invalid, Please use one of the three Priority! \r\n"
                        + MESSAGE_USAGE);
            }
        }
        return new CommandResult("Tag Priority Command did not go through, try again :( \r\n" + MESSAGE_USAGE);
    }

}

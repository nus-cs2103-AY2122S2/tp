package manageezpz.logic.commands;

import static java.util.Objects.requireNonNull;
import static manageezpz.logic.parser.CliSyntax.PREFIX_ASSIGNEES;
import static manageezpz.logic.parser.CliSyntax.PREFIX_DATE;
import static manageezpz.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static manageezpz.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static manageezpz.logic.parser.CliSyntax.PREFIX_EVENT;
import static manageezpz.logic.parser.CliSyntax.PREFIX_IS_MARKED;
import static manageezpz.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static manageezpz.logic.parser.CliSyntax.PREFIX_TODO;

import java.util.function.Predicate;

import manageezpz.commons.core.Messages;
import manageezpz.logic.commands.exceptions.CommandException;
import manageezpz.model.Model;
import manageezpz.model.person.Name;
import manageezpz.model.task.Date;
import manageezpz.model.task.Description;
import manageezpz.model.task.TaskMultiplePredicate;


/**
 * A subclass for find command to find all the tasks.
 */
public class FindTaskCommand extends Command {

    public static final String COMMAND_WORD = "findTask";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all tasks that contains the properties specified.\n"
            + "Task Types:\n"
            + PREFIX_TODO.getPrefix() + ": Todos\n"
            + PREFIX_DEADLINE.getPrefix() + ": Deadlines\n"
            + PREFIX_EVENT.getPrefix() + ": Events\n"
            + "Options:\n"
            + PREFIX_DESCRIPTION.getPrefix() + ": Description of the tasks\n"
            + PREFIX_DATE.getPrefix() + ": Date of the task in YYYY-MM-DD (Only for deadline and event)\n"
            + PREFIX_PRIORITY.getPrefix() + ": Priority of task. Only HIGH, MEDIUM, LOW and NONE\n"
            + PREFIX_ASSIGNEES.getPrefix()
            + ": The assignees that was assigned to the task (Only one full name of assignee allowed)\n"
            + PREFIX_IS_MARKED.getPrefix() + ": Whether the task is marked. Only true or false."
            + "Format:\n"
            + COMMAND_WORD + " " + PREFIX_TODO.getPrefix() + "\n"
            + COMMAND_WORD + " " + PREFIX_DEADLINE.getPrefix() + "\n"
            + COMMAND_WORD + " " + PREFIX_EVENT.getPrefix() + "\n"
            + COMMAND_WORD + " " + PREFIX_DESCRIPTION.getPrefix() + "[LIST OF WORDS]\n"
            + COMMAND_WORD + " " + PREFIX_DATE.getPrefix() + "YYYY-MM-DD\n"
            + COMMAND_WORD + " " + PREFIX_PRIORITY.getPrefix() + "PRIORITY"
            + COMMAND_WORD + " " + PREFIX_ASSIGNEES.getPrefix() + "Assignee's full name\n"
            + COMMAND_WORD + " " + PREFIX_IS_MARKED.getPrefix() + "BOOLEAN\n"
            + "Example:\n"
            + COMMAND_WORD + " " + PREFIX_DESCRIPTION.getPrefix() + "homework\n"
            + COMMAND_WORD + " " + PREFIX_DATE.getPrefix() + "2022-01-01\n"
            + COMMAND_WORD + " " + PREFIX_PRIORITY.getPrefix() + "HIGH\n"
            + COMMAND_WORD + " " + PREFIX_ASSIGNEES.getPrefix() + "Sam Leong\n"
            + COMMAND_WORD + " " + PREFIX_IS_MARKED.getPrefix() + "true\n"
            + COMMAND_WORD + " " + PREFIX_DESCRIPTION.getPrefix() + "Capstone project " + PREFIX_DATE.getPrefix()
            + "2022-05-01 " + PREFIX_PRIORITY.getPrefix() + "HIGH " + PREFIX_ASSIGNEES.getPrefix() + "Max Leong"
            + PREFIX_IS_MARKED.getPrefix() + "true";

    public static final String NO_OPTIONS = COMMAND_WORD + " needs at least 1 valid option\n";

    public static final String INVALID_DESCRIPTION = Description.MESSAGE_CONSTRAINTS + "\n";

    public static final String INVALID_DATE = Date.MESSAGE_CONSTRAINTS + "\n";

    public static final String INVALID_PRIORITY = "Property should be NONE, LOW, MEDIUM, HIGH\n";

    public static final String TODO_AND_DATE_OPTION_TOGETHER = "Todo and Date option are together\n";

    public static final String INVALID_BOOLEAN = "Boolean should be true or false\n";

    public static final String INVALID_ASSIGNEE = Name.MESSAGE_CONSTRAINTS + "\n";

    public static final String MORE_THAN_ONE_TASK_TYPE = "Only one task type is allowed\n";

    private final TaskMultiplePredicate predicate;

    /**
     * The constructor for find task command.
     * @param predicate The predicate as search terms to find task
     */
    public FindTaskCommand(TaskMultiplePredicate predicate) {
        requireNonNull(predicate);
        this.predicate = predicate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredTaskList(predicate);
        int latestNumberOfTasks = model.getFilteredTaskList().size();
        String commandResultMessage = String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW, latestNumberOfTasks);
        return new CommandResult(commandResultMessage);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof FindTaskCommand) {
            Predicate otherPredicate = ((FindTaskCommand) obj).predicate;
            boolean isOtherPredicateEqual = predicate.equals(otherPredicate);
            return isOtherPredicateEqual;
        }
        return false;
    }
}

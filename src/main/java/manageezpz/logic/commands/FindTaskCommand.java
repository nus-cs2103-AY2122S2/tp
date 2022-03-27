package manageezpz.logic.commands;

import static java.util.Objects.requireNonNull;
import static manageezpz.logic.parser.CliSyntax.PREFIX_ASSIGNEES;
import static manageezpz.logic.parser.CliSyntax.PREFIX_DATE;
import static manageezpz.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static manageezpz.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static manageezpz.logic.parser.CliSyntax.PREFIX_EVENT;
import static manageezpz.logic.parser.CliSyntax.PREFIX_IS_MARKED;
import static manageezpz.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static manageezpz.logic.parser.CliSyntax.PREFIX_TASK;
import static manageezpz.logic.parser.CliSyntax.PREFIX_TODO;

import java.util.function.Predicate;

import manageezpz.commons.core.Messages;
import manageezpz.logic.commands.exceptions.CommandException;
import manageezpz.model.Model;
import manageezpz.model.task.Date;
import manageezpz.model.task.TaskMultiplePredicate;


/**
 * A subclass for find command to find all the tasks.
 */
public class FindTaskCommand extends Command {
    public static final String COMMAND_WORD = "findTask";

    private static final String TASK_OPTIONS = String.join(", ", PREFIX_TODO.toString(),
            PREFIX_DEADLINE.toString(), PREFIX_EVENT.toString());
    private static final String TASK_PROPERTIES = String.join(", ", PREFIX_DESCRIPTION.toString(),
            PREFIX_DATE.toString(), PREFIX_PRIORITY.toString(), PREFIX_ASSIGNEES.toString(),
            PREFIX_IS_MARKED.toString());
    private static final String NOTE = "NOTE: All task properties option must be filled";
    private static final String EXAMPLE = String.join(" ", COMMAND_WORD,
            PREFIX_DEADLINE.toString(), "Finish TP", PREFIX_DATE.toString(), "2022-01-01");
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all tasks that contains the properties specified.\n"
            + "Task type options: " + TASK_OPTIONS + "\n"
            + "Task properties " + TASK_PROPERTIES + "\n"
            + NOTE + "\n"
            + EXAMPLE;

    public static final String EMPTY_KEYWORD = "Keyword is empty\n";
    public static final String INVALID_DATE = Date.MESSAGE_CONSTRAINTS + "\n";
    public static final String INVALID_PRIORITY = "Property should be NONE, LOW, MEDIUM, HIGH\n";
    public static final String TODO_AND_DATE_OPTION_TOGETHER = "Together and Date option are together\n";
    public static final String INVALID_BOOLEAN = "Boolean should be true or false\n";
    public static final String EMPTY_ASSIGNEE = "Assignee is empty\n";

    private TaskMultiplePredicate predicate;

    /**
     * The constructor for find task command.
     * @param taskContainsDescriptionKeywordsTagPredicate The predicate as search terms to find task
     */
    public FindTaskCommand(
            TaskMultiplePredicate taskContainsDescriptionKeywordsTagPredicate) {
        this.predicate = taskContainsDescriptionKeywordsTagPredicate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredTaskList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW, model.getFilteredTaskList().size()));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof FindTaskCommand) {
            Predicate otherPredicate = ((FindTaskCommand) obj).predicate;
            return predicate.equals(otherPredicate);
        }
        return false;
    }
}

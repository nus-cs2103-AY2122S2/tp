package manageezpz.logic.commands;

import static java.util.Objects.requireNonNull;

import manageezpz.commons.core.Messages;
import manageezpz.logic.commands.exceptions.CommandException;
import manageezpz.model.Model;
import manageezpz.model.task.Date;
import manageezpz.model.task.TaskMultiplePredicate;


/**
 * A subclass for find command to find all the tasks.
 */
public class FindTaskCommand extends FindCommand {
    public static final String EMPTY_KEYWORD = "Keyword is empty\n";
    public static final String INVALID_DATE = Date.MESSAGE_CONSTRAINTS + "\n";
    public static final String INVALID_PRIORITY = "Property should be NONE, LOW, MEDIUM, HIGH\n";
    public static final String TODO_AND_DATE_OPTION_TOGETHER = "Together and Date option are together\n";
    public static final String INVALID_BOOLEAN = "Boolean should be true or false\n";
    public static final String EMPTY_ASSIGNEE = "Assignee is empty\n";

    /**
     * The constructor for find task command.
     * @param taskContainsDescriptionKeywordsTagPredicate The predicate as search terms to find task
     */
    public FindTaskCommand(
            TaskMultiplePredicate taskContainsDescriptionKeywordsTagPredicate) {
        super(taskContainsDescriptionKeywordsTagPredicate);
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
}

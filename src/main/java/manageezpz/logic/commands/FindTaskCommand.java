package manageezpz.logic.commands;

import static java.util.Objects.requireNonNull;

import manageezpz.commons.core.Messages;
import manageezpz.logic.commands.exceptions.CommandException;
import manageezpz.model.Model;
import manageezpz.model.task.Date;
import manageezpz.model.task.TaskMultiplePredicate;

public class FindTaskCommand extends FindCommand {
    public static final String EMPTY_KEYWORD = "Keyword is empty\n";
    public static final String EMPTY_DATE = "Date is empty\n";
    public static final String INVALID_DATE = Date.MESSAGE_CONSTRAINTS + "\n";
    public static final String EMPTY_PRIORITY = "Priority is empty\n";
    public static final String INVALID_PRIORITY = "Invalid Priority\n";
    public static final String TODO_AND_DATE_OPTION_TOGETHER = "Together and Date option are together\n";
    public static final String EMPTY_BOOLEAN = "Boolean is Empty\n";
    public static final String INVALID_BOOLEAN = "Is Marked Boolean is invalid\n";
    public static final String EMPTY_ASSIGNEE = "Assignee is empty\n";

    public FindTaskCommand(
            TaskMultiplePredicate taskContainsDescriptionKeywordsTagPredicate) {
        super(taskContainsDescriptionKeywordsTagPredicate);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredTaskList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_TASK_LISTED_OVERVIEW, model.getFilteredTaskList().size()));
    }
}

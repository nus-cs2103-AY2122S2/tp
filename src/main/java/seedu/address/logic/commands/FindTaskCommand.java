package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.Type.TASK;

import seedu.address.commons.core.Messages;
import seedu.address.logic.parser.Type;
import seedu.address.model.Model;
import seedu.address.model.tasks.TaskContainsKeywordPredicate;

public class FindTaskCommand extends FindCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " [t] : Finds all tasks that meet search criteria.\n"
            + "Only accepts: g/, h/, d/, t/, and i/ flags\n"
            + "Example: " + COMMAND_WORD + " [t] g/h/update d/2022-01-19 g/i/add";

    private TaskContainsKeywordPredicate predicate;

    /**
     * Constructor for FindTaskCommand
     *
     * @param predicate Search criteria to find tasks
     */
    public FindTaskCommand(TaskContainsKeywordPredicate predicate) {
        requireNonNull(predicate);
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTaskList(predicate);
        return new CommandResult(String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW,
                model.getFilteredTaskList().size()), getType());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindTaskCommand)) {
            return false;
        }

        // state check
        FindTaskCommand e = (FindTaskCommand) other;
        return predicate.equals(e.predicate);
    }

    @Override
    public Type getType() {
        return TASK;
    }
}

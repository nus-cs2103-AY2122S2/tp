package seedu.address.logic.commands.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.name.TaskNameContainsKeywordsPredicate;
import seedu.address.model.tag.TaskTagContainsKeywordsPredicate;

/**
 * Finds and lists all tasks in task book whose name or tag contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindTaskCommand extends Command {

    public static final String COMMAND_WORD = "find-t";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all tasks whose names or tags contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: "
            + "[" + PREFIX_NAME + "NAME]... "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_NAME + "Meeting " + PREFIX_NAME + "Dinner "
            + PREFIX_TAG + "friends";

    private final TaskNameContainsKeywordsPredicate namePredicate;
    private final TaskTagContainsKeywordsPredicate tagPredicate;

    /**
     * Initialises new object using {@code namePredicate} and {@code tagPredicate}
     */
    public FindTaskCommand(TaskNameContainsKeywordsPredicate namePredicate,
                           TaskTagContainsKeywordsPredicate tagPredicate) {
        this.namePredicate = namePredicate;
        this.tagPredicate = tagPredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTaskList(namePredicate.or(tagPredicate));
        return new CommandResult(
                String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW, model.getFilteredTaskList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindTaskCommand // instanceof handles nulls
                && namePredicate.equals(((FindTaskCommand) other).namePredicate)
                && tagPredicate.equals(((FindTaskCommand) other).tagPredicate)); // state check
    }
}

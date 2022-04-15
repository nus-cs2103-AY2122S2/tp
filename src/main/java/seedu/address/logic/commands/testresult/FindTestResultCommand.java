package seedu.address.logic.commands.testresult;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.logic.commands.ViewedNric;
import seedu.address.model.Model;
import seedu.address.model.testresult.TestResultContainsKeywordsPredicate;

/**
 * Finds and lists all test results in MedBook belonging to the currently viewed patient whose information contains any
 * of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindTestResultCommand extends Command {

    public static final String COMMAND_WORD = "find";
    public static final CommandType COMMAND_TYPE = CommandType.TEST;

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all test results belonging to the currently viewed patient whose information contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " CT Scan";

    private final TestResultContainsKeywordsPredicate predicate;

    public FindTestResultCommand(TestResultContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTestResultList(predicate);
        return new CommandResult(String.format(Messages.MESSAGE_TEST_RESULTS_LISTED_OVERVIEW,
                        model.getFilteredTestResultList().size(), ViewedNric.getViewedNric().toString()), COMMAND_TYPE);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindTestResultCommand // instanceof handles nulls
                && predicate.equals(((FindTestResultCommand) other).predicate)); // state check
    }
}

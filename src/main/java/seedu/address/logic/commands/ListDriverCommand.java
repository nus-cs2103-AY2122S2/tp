package seedu.address.logic.commands;


import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.driver.StatusContainsKeywordsPredicate;

/**
 * Finds and lists all drivers whose status contains any of the argument keywords.
 */
public class ListDriverCommand extends Command {
    public static final String COMMAND_WORD = "listdriver";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all free drivers who are not deliver any orders "
            + "now and displays them as a list with index numbers.\n"
            + "The status of driver: FREE, BUSY, ABSENT.\n"
            + "Parameters: STATUS...\n"
            + "Example: " + COMMAND_WORD + " free";

    private final StatusContainsKeywordsPredicate predicate;

    public ListDriverCommand(StatusContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (predicate.getKeywords().get(0).equalsIgnoreCase("all")) {
            model.updateFilteredDriverList(Model.PREDICATE_SHOW_ALL_DRIVERS);
        } else {
            model.updateFilteredDriverList(predicate);
        }
        return new CommandResult(
                String.format(Messages.MESSAGE_DRIVERS_LISTED_OVERVIEW,
                        model.getFilteredDriverList().size()), false,
                false, false, true, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListDriverCommand // instanceof handles nulls
                && predicate.equals(((ListDriverCommand) other).predicate)); // state check
    }
}

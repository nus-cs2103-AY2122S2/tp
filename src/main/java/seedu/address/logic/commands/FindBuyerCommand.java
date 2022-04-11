package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.buyer.Buyer;
import seedu.address.model.buyer.BuyerHouseTypeContainsKeywordsPredicate;
import seedu.address.model.buyer.BuyerLocationContainsKeywordsPredicate;
import seedu.address.model.buyer.BuyerNameContainsKeywordsPredicate;
import seedu.address.model.buyer.BuyerPhoneContainsKeywordsPredicate;
import seedu.address.model.buyer.BuyerTagsContainsKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindBuyerCommand extends Command {

    public static final String COMMAND_WORD = "find-b";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all buyers whose specified field matches "
            + "the given keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: DELIMITER/KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " n/alice bob charlie";

    private final Predicate<Buyer> predicateBuyer;

    public FindBuyerCommand(BuyerNameContainsKeywordsPredicate predicate) {
        this.predicateBuyer = predicate;
    }

    public FindBuyerCommand(BuyerPhoneContainsKeywordsPredicate predicate) {
        this.predicateBuyer = predicate;
    }

    public FindBuyerCommand(BuyerTagsContainsKeywordsPredicate predicate) {
        this.predicateBuyer = predicate;
    }

    public FindBuyerCommand(BuyerHouseTypeContainsKeywordsPredicate predicate) {
        this.predicateBuyer = predicate;
    }

    public FindBuyerCommand(BuyerLocationContainsKeywordsPredicate predicate) {
        this.predicateBuyer = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredBuyerList(predicateBuyer);
        return new CommandResult(
                String.format(Messages.MESSAGE_BUYERS_LISTED_OVERVIEW, model.getFilteredBuyerList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindBuyerCommand // instanceof handles nulls
                && predicateBuyer.equals(((FindBuyerCommand) other).predicateBuyer)); // state check
    }
}

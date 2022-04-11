package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.seller.Seller;
import seedu.address.model.seller.SellerHouseTypeContainsKeywordsPredicate;
import seedu.address.model.seller.SellerLocationContainsKeywordsPredicate;
import seedu.address.model.seller.SellerNameContainsKeywordsPredicate;
import seedu.address.model.seller.SellerPhoneContainsKeywordsPredicate;
import seedu.address.model.seller.SellerTagsContainsKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindSellerCommand extends Command {

    public static final String COMMAND_WORD = "find-s";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all sellers whose specified field matches "
            + "the given keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: DELIMITER/KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " n/alice bob charlie";

    private final Predicate<Seller> predicateSeller;

    public FindSellerCommand(SellerNameContainsKeywordsPredicate predicate) {
        this.predicateSeller = predicate;
    }

    public FindSellerCommand(SellerPhoneContainsKeywordsPredicate predicate) {
        this.predicateSeller = predicate;
    }

    public FindSellerCommand(SellerTagsContainsKeywordsPredicate predicate) {
        this.predicateSeller = predicate;
    }

    public FindSellerCommand(SellerHouseTypeContainsKeywordsPredicate predicate) {
        this.predicateSeller = predicate;
    }

    public FindSellerCommand(SellerLocationContainsKeywordsPredicate predicate) {
        this.predicateSeller = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredSellerList(predicateSeller);
        return new CommandResult(
                String.format(Messages.MESSAGE_SELLERS_LISTED_OVERVIEW, model.getFilteredSellerList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindSellerCommand // instanceof handles nulls
                && predicateSeller.equals(((FindSellerCommand) other).predicateSeller)); // state check
    }
}

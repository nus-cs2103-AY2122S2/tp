package seedu.address.model.seller;


import seedu.address.commons.core.index.Index;
import seedu.address.model.buyer.Buyer;

import java.util.function.Predicate;

/**
 * Tests that a {@code Seller}'s {@code PropertyToSell} matches the demand
 *  of a given {@code Buyer}'s {@code PropertyToBuy}.
 */
public class hasMatchWithBuyerPredicate implements Predicate<Buyer> {

    /**
     * Index of Buyer in UniqueBuyerList.
     */
    private final Buyer buyer;

    public hasMatchWithBuyerPredicate(Buyer buyer) {
        this.buyer = buyer;
    }

    /**
     * Tests whether a Buyer (provided by the index), matches with a Seller.
     * @param index the index of the Buyer in the UniqueBuyerList we are comparing with.
     * @return True if {@code Seller}'s {@code PropertyToSell} matches the demand
     * of the {@code Buyer}'s {@code PropertyToBuy}.
     */
    @Override
    public boolean test(Buyer buyer) {

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof hasMatchWithBuyerPredicate // instanceof handles nulls
            && buyer.equals(((hasMatchWithBuyerPredicate) other).buyer)); // state check
    }
}

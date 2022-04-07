package seedu.address.model.seller;

import java.util.function.Predicate;

import seedu.address.model.buyer.Buyer;
import seedu.address.model.property.NullPropertyToSell;
import seedu.address.model.property.PriceRange;
import seedu.address.model.property.PropertyToBuy;
import seedu.address.model.property.PropertyToSell;

/**
 * Tests that a {@code Seller}'s {@code PriceRange} matches the demand
 *  of a given {@code Buyer}'s {@code PriceRange}.
 */
public class PriceRangeMatchBuyerPredicate implements Predicate<Seller> {
    /**
     * Index of Buyer in UniqueBuyerList to match with.
     */
    private final Buyer buyer;

    public PriceRangeMatchBuyerPredicate(Buyer buyer) {
        this.buyer = buyer;
    }

    /**
     * Tests whether the Buyer (provided by the index) matches with a Seller by the PriceRange.
     *
     * @param seller the seller we are matching the Buyer with.
     * @return True if {@code Seller}'s {@code PropertyToSell PriceRange} has a price that can match
     * that of the {@code Buyer}.
     */
    @Override
    public boolean test(Seller seller) {
        PropertyToBuy propertyToBuy = getPropertyToBuy();
        PropertyToSell propertyToSell = getPropertyToSell(seller);

        PriceRange buyRange = propertyToBuy.getPriceRange();
        PriceRange sellRange = propertyToSell.getPriceRange();

        boolean isNotNullProperty = !(propertyToSell instanceof NullPropertyToSell);

        return PriceRange.canMatchPrice(buyRange, sellRange) && isNotNullProperty;
    }

    public PropertyToBuy getPropertyToBuy() {
        return buyer.getPropertyToBuy();
    }

    public PropertyToSell getPropertyToSell(Seller seller) {
        return seller.getPropertyToSell();
    }
}

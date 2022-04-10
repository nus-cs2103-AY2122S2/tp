package seedu.address.model.seller;

import java.util.function.Predicate;

import seedu.address.model.buyer.Buyer;
import seedu.address.model.property.House;
import seedu.address.model.property.Location;
import seedu.address.model.property.NullPropertyToSell;
import seedu.address.model.property.PropertyToBuy;
import seedu.address.model.property.PropertyToSell;

/**
 * Tests that a {@code Seller}'s {@code Location} matches the demand
 *  of a given {@code Buyer}'s {@code Location}.
 */
public class LocationMatchBuyerPredicate implements Predicate<Seller> {

    /**
     * Index of Buyer in UniqueBuyerList to match with.
     */
    private final Buyer buyer;

    public LocationMatchBuyerPredicate(Buyer buyer) {
        this.buyer = buyer;
    }

    /**
     * Tests whether the Buyer (provided by the index) matches with a Seller by the Location.
     *
     * @param seller the seller we are matching the Buyer with.
     * @return True if {@code Seller}'s {@code PropertyToSell Location} is the same as
     * that of the {@code Buyer}.
     */
    @Override
    public boolean test(Seller seller) {
        PropertyToBuy propertyToBuy = getPropertyToBuy();
        PropertyToSell propertyToSell = getPropertyToSell(seller);

        House houseToBuy = propertyToBuy.getHouse();
        House houseToSell = propertyToSell.getHouse();

        Location buyLocation = houseToBuy.getLocation();
        Location sellLocation = houseToSell.getLocation();

        boolean isNotNullProperty = !(propertyToSell instanceof NullPropertyToSell);

        return buyLocation.equals(sellLocation) && isNotNullProperty;
    }

    public PropertyToBuy getPropertyToBuy() {
        return buyer.getPropertyToBuy();
    }

    public PropertyToSell getPropertyToSell(Seller seller) {
        return seller.getPropertyToSell();
    }
}

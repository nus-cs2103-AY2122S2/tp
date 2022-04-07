package seedu.address.model.seller;

import java.util.function.Predicate;

import seedu.address.model.buyer.Buyer;
import seedu.address.model.property.House;
import seedu.address.model.property.HouseType;
import seedu.address.model.property.NullPropertyToSell;
import seedu.address.model.property.PropertyToBuy;
import seedu.address.model.property.PropertyToSell;


/**
 * Tests that a {@code Seller}'s {@code HouseType} matches the demand
 *  of a given {@code Buyer}'s {@code HouseType}.
 */
public class HouseTypeMatchBuyerPredicate implements Predicate<Seller> {

    /**
     * Index of Buyer in UniqueBuyerList to match with.
     */
    private final Buyer buyer;

    public HouseTypeMatchBuyerPredicate(Buyer buyer) {
        this.buyer = buyer;
    }

    /**
     * Tests whether the Buyer (provided by the index) matches with a Seller by the HouseType.
     *
     * @param seller the seller we are matching the Buyer with.
     * @return True if {@code Seller}'s {@code PropertyToSell HouseType} is the same as
     * that of the {@code Buyer}, or
     * the {@code Buyer}'s {@code PropertyToBuy} has an unspecified house type.
     */
    @Override
    public boolean test(Seller seller) {
        PropertyToBuy propertyToBuy = getPropertyToBuy();
        PropertyToSell propertyToSell = getPropertyToSell(seller);

        House houseToBuy = propertyToBuy.getHouse();
        House houseToSell = propertyToSell.getHouse();

        HouseType buyType = houseToBuy.getHouseType();
        HouseType sellType = houseToSell.getHouseType();

        boolean isNotNullProperty = !(propertyToSell instanceof NullPropertyToSell);

        return (buyType.equals(sellType) || buyType.equals(HouseType.UNSPECIFIED)) && isNotNullProperty;
    }

    public PropertyToBuy getPropertyToBuy() {
        return buyer.getPropertyToBuy();
    }

    public PropertyToSell getPropertyToSell(Seller seller) {
        return seller.getPropertyToSell();
    }

}

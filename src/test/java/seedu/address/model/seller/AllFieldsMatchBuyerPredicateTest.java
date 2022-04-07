package seedu.address.model.seller;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.buyer.Buyer;
import seedu.address.model.property.Address;
import seedu.address.model.property.House;
import seedu.address.model.property.HouseType;
import seedu.address.model.property.Location;
import seedu.address.model.property.PriceRange;
import seedu.address.model.property.PropertyToBuy;
import seedu.address.model.property.PropertyToSell;
import seedu.address.testutil.BuyerBuilder;
import seedu.address.testutil.SellerBuilder;

class AllFieldsMatchBuyerPredicateTest {

    private PropertyToBuy propertyToBuyStub1 = new PropertyToBuy(
        new House(HouseType.BUNGALOW, new Location("Kranji")),
        new PriceRange(100, 500));

    private PropertyToBuy propertyToBuyStub2 = new PropertyToBuy(
        new House(HouseType.UNSPECIFIED, new Location("Kranji")),
        new PriceRange(100, 500));

    //same fields
    private PropertyToSell propertyToSellStub1 = new PropertyToSell(
        new House(HouseType.BUNGALOW, new Location("Kranji")),
        new PriceRange(100, 500), new Address("hey"));

    private PropertyToSell propertyToSellStub2 = new PropertyToSell(
        new House(HouseType.HDB_FLAT, new Location("Kranji")),
        new PriceRange(0, 600), new Address("hey"));

    //Another location
    private PropertyToSell propertyToSellStub3 = new PropertyToSell(
        new House(HouseType.BUNGALOW, new Location("Another location")),
        new PriceRange(100, 500), new Address("hey"));

    //abit different price range but within range
    private PropertyToSell propertyToSellStub4 = new PropertyToSell(
        new House(HouseType.BUNGALOW, new Location("Kranji")),
        new PriceRange(499, 501), new Address("hey"));

    //out of bounds PriceRange
    private PropertyToSell propertyToSellStub5 = new PropertyToSell(
        new House(HouseType.BUNGALOW, new Location("Kranji")),
        new PriceRange(501, 600), new Address("hey"));

    //unspecified housetype but different location
    private PropertyToSell propertyToSellStub6 = new PropertyToSell(
        new House(HouseType.UNSPECIFIED, new Location("Kranji")),
        new PriceRange(100, 500), new Address("hey"));

    private Buyer buyer1 = new BuyerBuilder().withProperty(propertyToBuyStub1).build();
    private Buyer buyer2 = new BuyerBuilder().withProperty(propertyToBuyStub2).build();

    private Seller seller1 = new SellerBuilder().withProperty(propertyToSellStub1).build();
    private Seller seller2 = new SellerBuilder().withProperty(propertyToSellStub2).build();
    private Seller seller3 = new SellerBuilder().withProperty(propertyToSellStub3).build();
    private Seller seller4 = new SellerBuilder().withProperty(propertyToSellStub4).build();
    private Seller seller5 = new SellerBuilder().withProperty(propertyToSellStub5).build();
    private Seller seller6 = new SellerBuilder().withProperty(propertyToSellStub6).build();

    private AllFieldsMatchBuyerPredicate predicate1 = new AllFieldsMatchBuyerPredicate(buyer1);
    private AllFieldsMatchBuyerPredicate predicate2 = new AllFieldsMatchBuyerPredicate(buyer2);

    @Test
    void test1() {
        //same fields
        assertTrue(predicate1.test(seller1));

        //UNSPECIFIED housetype but same location, should
        // match with seller with same fields other than housetype
        assertTrue(predicate2.test(seller1));

        //within price range and unspecified house type
        assertTrue(predicate2.test(seller1));
        assertTrue(predicate2.test(seller4));

        //same housetype but another location
        assertFalse(predicate1.test(seller3));

        //out of bounds PriceRange
        assertFalse(predicate1.test(seller5));

        //UNSPECIFIED housetype but diff location
        assertFalse(predicate1.test(seller5));
    }

    @Test
    void getPropertyToBuy() {
        assertTrue(predicate1.getPropertyToBuy().equals(new PropertyToBuy(
            new House(HouseType.BUNGALOW, new Location("Kranji")),
            new PriceRange(100, 500))));

        assertFalse(predicate1.getPropertyToBuy().equals(new PropertyToBuy(
            new House(HouseType.BUNGALOW, new Location("Crunchy")),
            new PriceRange(100, 500))));
    }

    @Test
    void getPropertyToSell() {
        assertTrue(predicate1.getPropertyToSell(seller1).equals(new PropertyToSell(
            new House(HouseType.BUNGALOW, new Location("Kranji")),
            new PriceRange(100, 500), new Address("hey"))));
    }

    @Test
    void getBuyRange() {
        assertTrue(predicate1.getBuyRange(propertyToBuyStub1).equals(new PriceRange(100, 500)));
    }

    @Test
    void getSellRange() {
        assertTrue(predicate1.getSellRange(propertyToSellStub1).equals(new PriceRange(100, 500)));
    }

}

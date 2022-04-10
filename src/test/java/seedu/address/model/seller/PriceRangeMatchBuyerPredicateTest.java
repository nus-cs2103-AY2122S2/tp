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
import seedu.address.testutil.HouseBuilder;
import seedu.address.testutil.PropertyToBuyBuilder;
import seedu.address.testutil.PropertyToSellBuilder;
import seedu.address.testutil.SellerBuilder;

class PriceRangeMatchBuyerPredicateTest {

    //property to buy
    private PropertyToBuy ptbStub1 = new PropertyToBuyBuilder()
        .withHouse(new HouseBuilder().withHouseType(HouseType.BUNGALOW).withLocation("Kranji").build())
        .withPriceRange(new PriceRange(20, 100)).build();

    private PropertyToSell ptsStub1 = new PropertyToSellBuilder()
        .withHouse(new HouseBuilder().withHouseType(HouseType.BUNGALOW).withLocation("Kranji").build())
        .withPriceRange(new PriceRange(20, 100)).withAddress("ANY ADDRESS").build();

    private PropertyToSell ptsStub2 = new PropertyToSellBuilder()
        .withHouse(new HouseBuilder().withHouseType(HouseType.BUNGALOW).withLocation("Kranji").build())
        .withPriceRange(new PriceRange(0, 200)).withAddress("ANY ADDRESS").build();

    private PropertyToSell ptsStub3 = new PropertyToSellBuilder()
        .withHouse(new HouseBuilder().withHouseType(HouseType.BUNGALOW).withLocation("Kranji").build())
        .withPriceRange(new PriceRange(50, 50)).withAddress("ANY ADDRESS").build();

    private PropertyToSell ptsStub4 = new PropertyToSellBuilder()
        .withHouse(new HouseBuilder().withHouseType(HouseType.BUNGALOW).withLocation("Kranji").build())
        .withPriceRange(new PriceRange(18, 20)).withAddress("ANY ADDRESS").build();

    private PropertyToSell ptsStub5 = new PropertyToSellBuilder()
        .withHouse(new HouseBuilder().withHouseType(HouseType.BUNGALOW).withLocation("Kranji").build())
        .withPriceRange(new PriceRange(100, 100)).withAddress("ANY ADDRESS").build();

    private PropertyToSell ptsStub6 = new PropertyToSellBuilder()
        .withHouse(new HouseBuilder().withHouseType(HouseType.BUNGALOW).withLocation("Kranji").build())
        .withPriceRange(new PriceRange(0, 19)).withAddress("ANY ADDRESS").build();

    private PropertyToSell ptsStub7 = new PropertyToSellBuilder()
        .withHouse(new HouseBuilder().withHouseType(HouseType.BUNGALOW).withLocation("Kranji").build())
        .withPriceRange(new PriceRange(101, 200)).withAddress("ANY ADDRESS").build();

    private Buyer buyer1 = new BuyerBuilder().withProperty(ptbStub1).build();

    private Seller seller1 = new SellerBuilder().withProperty(ptsStub1).build();
    private Seller seller2 = new SellerBuilder().withProperty(ptsStub2).build();
    private Seller seller3 = new SellerBuilder().withProperty(ptsStub3).build();
    private Seller seller4 = new SellerBuilder().withProperty(ptsStub4).build();
    private Seller seller5 = new SellerBuilder().withProperty(ptsStub5).build();
    private Seller seller6 = new SellerBuilder().withProperty(ptsStub6).build();
    private Seller seller7 = new SellerBuilder().withProperty(ptsStub7).build();

    private PriceRangeMatchBuyerPredicate predicate1 = new PriceRangeMatchBuyerPredicate(buyer1);

    @Test
    void test1() {
        //price range matches
        assertTrue(predicate1.test(seller1));
        assertTrue(predicate1.test(seller2));
        assertTrue(predicate1.test(seller3));
        //boundary values should work as match is inclusive
        assertTrue(predicate1.test(seller4));
        assertTrue(predicate1.test(seller5));

        //price range does not match
        assertFalse(predicate1.test(seller6));
        assertFalse(predicate1.test(seller7));
    }

    @Test
    void getPropertyToBuy() {
        assertTrue(predicate1.getPropertyToBuy().equals(new PropertyToBuy(
            new House(HouseType.BUNGALOW, new Location("Kranji")),
            new PriceRange(20, 100))));

        assertFalse(predicate1.getPropertyToBuy().equals(new PropertyToBuy(
            new House(HouseType.BUNGALOW, new Location("Kranji")),
            new PriceRange(0, 200))));

        assertFalse(predicate1.getPropertyToBuy().equals(new PropertyToBuy(
            new House(HouseType.BUNGALOW, new Location("Kranji")),
            new PriceRange(0, 100))));

        assertFalse(predicate1.getPropertyToBuy().equals(new PropertyToBuy(
            new House(HouseType.BUNGALOW, new Location("Kranji")),
            new PriceRange(0, 20))));
    }

    @Test
    void getPropertyToSell() {
        assertTrue(predicate1.getPropertyToSell(seller1).equals(new PropertyToSell(
            new House(HouseType.BUNGALOW, new Location("Kranji")),
            new PriceRange(20, 100), new Address("ANY ADDRESS"))));
    }
}

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

class HouseTypeMatchBuyerPredicateTest {

    //property to buy
    private PropertyToBuy ptbStub1 = new PropertyToBuyBuilder()
            .withHouse(new HouseBuilder().withHouseType(HouseType.BUNGALOW).withLocation("Kranji").build())
            .withPriceRange(new PriceRange(0, 100)).build();

    private PropertyToBuy ptbStub2 = new PropertyToBuyBuilder()
        .withHouse(new HouseBuilder().withHouseType(HouseType.UNSPECIFIED).withLocation("Kranji").build())
        .withPriceRange(new PriceRange(0, 100)).build();

    private PropertyToBuy ptbStub3 = new PropertyToBuyBuilder()
        .withHouse(new HouseBuilder().withHouseType(HouseType.APARTMENT).withLocation("Kranji").build())
        .withPriceRange(new PriceRange(0, 100)).build();

    //property to sell
    private PropertyToSell ptsStub1 = new PropertyToSellBuilder()
        .withHouse(new HouseBuilder().withHouseType(HouseType.BUNGALOW).withLocation("Kranji").build())
        .withPriceRange(new PriceRange(0, 100)).withAddress("ANY ADDRESS").build();

    private PropertyToSell ptsStub2 = new PropertyToSellBuilder()
        .withHouse(new HouseBuilder().withHouseType(HouseType.UNSPECIFIED).withLocation("Kranji").build())
        .withPriceRange(new PriceRange(0, 100)).withAddress("ANY ADDRESS").build();

    private PropertyToSell ptsStub3 = new PropertyToSellBuilder()
        .withHouse(new HouseBuilder().withHouseType(HouseType.APARTMENT).withLocation("Kranji").build())
        .withPriceRange(new PriceRange(0, 100)).withAddress("ANY ADDRESS").build();

    private PropertyToSell ptsStub4 = new PropertyToSellBuilder()
        .withHouse(new HouseBuilder().withHouseType(HouseType.COLONIA).withLocation("Kranji").build())
        .withPriceRange(new PriceRange(0, 100)).withAddress("ANY ADDRESS").build();

    private PropertyToSell ptsStub5 = new PropertyToSellBuilder()
        .withHouse(new HouseBuilder().withHouseType(HouseType.CONDOMINIUM).withLocation("Kranji").build())
        .withPriceRange(new PriceRange(0, 100)).withAddress("ANY ADDRESS").build();

    private Buyer buyer1 = new BuyerBuilder().withProperty(ptbStub1).build();
    private Buyer buyer2 = new BuyerBuilder().withProperty(ptbStub2).build();
    private Buyer buyer3 = new BuyerBuilder().withProperty(ptbStub3).build();

    private Seller seller1 = new SellerBuilder().withProperty(ptsStub1).build();
    private Seller seller2 = new SellerBuilder().withProperty(ptsStub2).build();
    private Seller seller3 = new SellerBuilder().withProperty(ptsStub3).build();
    private Seller seller4 = new SellerBuilder().withProperty(ptsStub4).build();
    private Seller seller5 = new SellerBuilder().withProperty(ptsStub5).build();

    private HouseTypeMatchBuyerPredicate predicate1 = new HouseTypeMatchBuyerPredicate(buyer1);
    private HouseTypeMatchBuyerPredicate predicate2 = new HouseTypeMatchBuyerPredicate(buyer2);
    private HouseTypeMatchBuyerPredicate predicate3 = new HouseTypeMatchBuyerPredicate(buyer3);

    @Test
    void test1() {
        //exactly the same.
        assertTrue(predicate1.test(seller1));
        assertTrue(predicate3.test(seller3));

        //buyer is unspecified house type
        assertTrue(predicate2.test(seller1));
        assertTrue(predicate2.test(seller2));
        assertTrue(predicate2.test(seller3));
        assertTrue(predicate2.test(seller4));
        assertTrue(predicate2.test(seller5));

        //wrong house type
        assertFalse(predicate1.test(seller3));
        assertFalse(predicate1.test(seller4));
        assertFalse(predicate1.test(seller5));

        assertFalse(predicate3.test(seller1));
        assertFalse(predicate3.test(seller2));
        assertFalse(predicate3.test(seller4));
        assertFalse(predicate3.test(seller5));
    }

    @Test
    void getPropertyToBuy() {
        assertTrue(predicate1.getPropertyToBuy().equals(new PropertyToBuy(
            new House(HouseType.BUNGALOW, new Location("Kranji")),
            new PriceRange(0, 100))));

        assertFalse(predicate1.getPropertyToBuy().equals(new PropertyToBuy(
            new House(HouseType.BUNGALOW, new Location("Kranji")),
            new PriceRange(0, 200))));

        assertFalse(predicate1.getPropertyToBuy().equals(new PropertyToBuy(
            new House(HouseType.BUNGALOW, new Location("Crunchy")),
            new PriceRange(0, 100))));

        assertFalse(predicate1.getPropertyToBuy().equals(new PropertyToBuy(
            new House(HouseType.COLONIA, new Location("Kranji")),
            new PriceRange(0, 100))));
    }

    @Test
    void getPropertyToSell() {
        assertTrue(predicate1.getPropertyToSell(seller1).equals(new PropertyToSell(
            new House(HouseType.BUNGALOW, new Location("Kranji")),
            new PriceRange(0, 100), new Address("ANY ADDRESS"))));
    }
}

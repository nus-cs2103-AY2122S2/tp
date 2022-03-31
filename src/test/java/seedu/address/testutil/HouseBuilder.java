//package seedu.address.testutil;
//
//import seedu.address.model.property.House;
//import seedu.address.model.property.HouseType;
//import seedu.address.model.property.Location;
//import seedu.address.model.seller.Seller;
//
//
//public class HouseBuilder {
//
//    public static final HouseType DEFAULT_HOUSE_TYPE= HouseType.UNSPECIFIED;
//    public static final String DEFAULT_LOCATION = "Clementi";
//
//    private HouseType houseType;
//    private Location location;
//
//    /**
//     * Creates a {@code houseBuilder} with the default details.
//     */
//     public HouseBuilder() {
//        houseType = new HouseType(DEFAULT_HOUSE_TYPE);
//        location = new Location(DEFAULT_LOCATION);
//     }
//
//    /**
//     * Initializes the houseBuilder with the data of {@code clientToCopy}.
//     */
//
//    public HouseBuilder(House houseToCopy) {
//        houseType = houseToCopy.getHouseType();
//        location = houseToCopy.getLocation();
//    }
//
//    public House build() {
//        return new House(houseType, location);
//    }
//}

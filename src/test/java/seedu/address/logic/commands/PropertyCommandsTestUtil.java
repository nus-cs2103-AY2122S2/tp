package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOUSE_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE_RANGE;

import seedu.address.model.property.HouseType;
import seedu.address.model.property.PriceRange;

public class PropertyCommandsTestUtil {

    public static final String VALID_HOUSE_TYPE_1 = "Condominium";
    public static final String VALID_HOUSE_TYPE_2 = "Colonia";
    public static final String VALID_HOUSE_TYPE_3 = "HDB";
    public static final String VALID_HOUSE_TYPE_4 = "Mansion";
    public static final String VALID_HOUSE_TYPE_5 = "Unspecified";

    public static final HouseType VALID_HOUSE_TYPE_CONDO = HouseType.CONDOMINIUM;
    public static final HouseType VALID_HOUSE_TYPE_COL = HouseType.COLONIA;
    public static final HouseType VALID_HOUSE_TYPE_HDB = HouseType.HDB_FLAT;
    public static final HouseType VALID_HOUSE_TYPE_MAN = HouseType.MANSION;
    public static final HouseType VALID_HOUSE_TYPE_UND = HouseType.UNSPECIFIED;

    public static final String VALID_LOCATION_1 = "Bishan";
    public static final String VALID_LOCATION_2 = "AMK Block 4";
    public static final String VALID_LOCATION_3 = "Heartbeat@Bedok";
    public static final String VALID_LOCATION_4 = "Heartbeat/Bedok";

    public static final String VALID_ADDRESS_1 = "Clementi Block 4";
    public static final String VALID_ADDRESS_2 = "Sembawang unit #04-1212";
    public static final String VALID_ADDRESS_3 = "Istana";
    public static final String VALID_ADDRESS_4 = "Kranji, Joo chiat place";

    public static final String VALID_PR_1 = "0,0";
    public static final String VALID_PR_2 = "10000,20000";
    public static final String VALID_PR_3 = "0,2147483647";
    public static final String VALID_PR_4 = "2147483647,2147483647";

    public static final PriceRange VALID_PR_ZERO = new PriceRange("0,0");
    public static final PriceRange VALID_PR_NORMAL = new PriceRange("10000,20000");
    public static final PriceRange VALID_PR_BIG = new PriceRange("0,2147483647");
    public static final PriceRange VALID_PR_MAX = new PriceRange("2147483647,2147483647");

    public static final String INVALID_HOUSE_TYPE_1 = "sandbox";
    public static final String INVALID_HOUSE_TYPE_2 = "hdb123";

    public static final String INVALID_HOUSE_TYPE_DESC_1 = " " + PREFIX_HOUSE_TYPE + INVALID_HOUSE_TYPE_1;
    public static final String INVALID_HOUSE_TYPE_DESC_2 = " " + PREFIX_HOUSE_TYPE + INVALID_HOUSE_TYPE_2;

    public static final String INVALID_LOCATION_1 = "";

    public static final String INVALID_ADDRESS_1 = "";

    public static final String INVALID_PR_1 = "-1,1000";
    public static final String INVALID_PR_2 = "-1,-3";
    public static final String INVALID_PR_3 = "40,39";
    public static final String INVALID_PR_4 = "2147483647,2147483648";
    public static final String INVALID_PR_5 = "2147483648,2147483650";

    public static final String INVALID_PR_DESC_1 = " " + PREFIX_PRICE_RANGE + "-1,1000";
    public static final String INVALID_PR_DESC_2 = " " + PREFIX_PRICE_RANGE + "-1,-3";
    public static final String INVALID_PR_DESC_3 = " " + PREFIX_PRICE_RANGE + "40,39";
    public static final String INVALID_PR_DESC_4 = " " + PREFIX_PRICE_RANGE + "2147483647,2147483648";
    public static final String INVALID_PR_DESC_5 = " " + PREFIX_PRICE_RANGE + "2147483648,2147483650";

    public static final String HOUSE_TYPE_DESC_1 = " " + PREFIX_HOUSE_TYPE + VALID_HOUSE_TYPE_1;
    public static final String HOUSE_TYPE_DESC_2 = " " + PREFIX_HOUSE_TYPE + VALID_HOUSE_TYPE_2;
    public static final String HOUSE_TYPE_DESC_3 = " " + PREFIX_HOUSE_TYPE + VALID_HOUSE_TYPE_3;
    public static final String HOUSE_TYPE_DESC_4 = " " + PREFIX_HOUSE_TYPE + VALID_HOUSE_TYPE_4;
    public static final String HOUSE_TYPE_DESC_5 = " " + PREFIX_HOUSE_TYPE + VALID_HOUSE_TYPE_5;

    public static final String LOCATION_DESC_1 = " " + PREFIX_LOCATION + VALID_LOCATION_1;
    public static final String LOCATION_DESC_2 = " " + PREFIX_LOCATION + VALID_LOCATION_2;
    public static final String LOCATION_DESC_3 = " " + PREFIX_LOCATION + VALID_LOCATION_3;
    public static final String LOCATION_DESC_4 = " " + PREFIX_LOCATION + VALID_LOCATION_4;

    public static final String ADDRESS_DESC_1 = " " + PREFIX_ADDRESS + VALID_ADDRESS_1;
    public static final String ADDRESS_DESC_2 = " " + PREFIX_ADDRESS + VALID_ADDRESS_2;
    public static final String ADDRESS_DESC_3 = " " + PREFIX_ADDRESS + VALID_ADDRESS_3;
    public static final String ADDRESS_DESC_4 = " " + PREFIX_ADDRESS + VALID_ADDRESS_4;

    public static final String PR_DESC_1 = " " + PREFIX_PRICE_RANGE + VALID_PR_1;
    public static final String PR_DESC_2 = " " + PREFIX_PRICE_RANGE + VALID_PR_2;
    public static final String PR_DESC_3 = " " + PREFIX_PRICE_RANGE + VALID_PR_3;
    public static final String PR_DESC_4 = " " + PREFIX_PRICE_RANGE + VALID_PR_4;


}

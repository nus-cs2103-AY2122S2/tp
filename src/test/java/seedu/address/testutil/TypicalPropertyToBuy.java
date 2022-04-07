package seedu.address.testutil;

import static seedu.address.logic.commands.PropertyCommandsTestUtil.VALID_HOUSE_TYPE_COL;
import static seedu.address.logic.commands.PropertyCommandsTestUtil.VALID_HOUSE_TYPE_CONDO;
import static seedu.address.logic.commands.PropertyCommandsTestUtil.VALID_HOUSE_TYPE_HDB;
import static seedu.address.logic.commands.PropertyCommandsTestUtil.VALID_HOUSE_TYPE_MAN;
import static seedu.address.logic.commands.PropertyCommandsTestUtil.VALID_HOUSE_TYPE_UND;
import static seedu.address.logic.commands.PropertyCommandsTestUtil.VALID_LOCATION_1;
import static seedu.address.logic.commands.PropertyCommandsTestUtil.VALID_LOCATION_2;
import static seedu.address.logic.commands.PropertyCommandsTestUtil.VALID_LOCATION_3;
import static seedu.address.logic.commands.PropertyCommandsTestUtil.VALID_LOCATION_4;
import static seedu.address.logic.commands.PropertyCommandsTestUtil.VALID_PR_BIG;
import static seedu.address.logic.commands.PropertyCommandsTestUtil.VALID_PR_MAX;
import static seedu.address.logic.commands.PropertyCommandsTestUtil.VALID_PR_NORMAL;
import static seedu.address.logic.commands.PropertyCommandsTestUtil.VALID_PR_ZERO;

import seedu.address.model.property.House;
import seedu.address.model.property.PropertyToBuy;

public class TypicalPropertyToBuy {

    public static final House HOUSE_ONE = new HouseBuilder().withHouseType(VALID_HOUSE_TYPE_CONDO)
            .withLocation(VALID_LOCATION_1).build();
    public static final House HOUSE_TWO = new HouseBuilder().withHouseType(VALID_HOUSE_TYPE_COL)
            .withLocation(VALID_LOCATION_2).build();
    public static final House HOUSE_THREE = new HouseBuilder().withHouseType(VALID_HOUSE_TYPE_HDB)
            .withLocation(VALID_LOCATION_3).build();
    public static final House HOUSE_FOUR = new HouseBuilder().withHouseType(VALID_HOUSE_TYPE_MAN)
            .withLocation(VALID_LOCATION_4).build();
    public static final House HOUSE_FIVE = new HouseBuilder().withHouseType(VALID_HOUSE_TYPE_UND)
            .withLocation(VALID_LOCATION_1).build();

    public static final PropertyToBuy PROPERTY_TO_BUY_ONE = new PropertyToBuyBuilder().withHouse(HOUSE_ONE)
            .withPriceRange(VALID_PR_ZERO).build();
    public static final PropertyToBuy PROPERTY_TO_BUY_TWO = new PropertyToBuyBuilder().withHouse(HOUSE_TWO)
            .withPriceRange(VALID_PR_NORMAL).build();
    public static final PropertyToBuy PROPERTY_TO_BUY_THREE = new PropertyToBuyBuilder().withHouse(HOUSE_THREE)
            .withPriceRange(VALID_PR_NORMAL).build();
    public static final PropertyToBuy PROPERTY_TO_BUY_FOUR = new PropertyToBuyBuilder().withHouse(HOUSE_FOUR)
            .withPriceRange(VALID_PR_BIG).build();
    public static final PropertyToBuy PROPERTY_TO_BUY_FIVE = new PropertyToBuyBuilder().withHouse(HOUSE_FIVE)
            .withPriceRange(VALID_PR_MAX).build();
}

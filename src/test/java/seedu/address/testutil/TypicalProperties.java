package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRICE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRICE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REGION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REGION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SIZE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SIZE_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.property.Property;

/**
 * A utility class containing a list of {@code Property} objects to be used in tests.
 */
public class TypicalProperties {

    public static final Property PROPERTY_ALICE = new PropertyBuilder().withAddress("123, Jurong West Ave 6, #08-111")
            .withRegion("West").withSize("1-room").withPrice("$300000").build();
    public static final Property PROPERTY_BENSON = new PropertyBuilder().withAddress("311, Clementi Ave 2, #02-25")
            .withRegion("West").withSize("2-room").withPrice("$400000").build();
    public static final Property PROPERTY_CARL = new PropertyBuilder().withAddress("wall street")
            .withRegion("East").withSize("3-room").withPrice("$500000").build();
    public static final Property PROPERTY_DANIEL = new PropertyBuilder().withAddress("10th street")
            .withRegion("South").withSize("4-room").withPrice("$600000").build();
    public static final Property PROPERTY_ELLE = new PropertyBuilder().withAddress("michegan ave")
            .withRegion("North").withSize("5-room").withPrice("$700000").build();
    public static final Property PROPERTY_FIONA = new PropertyBuilder().withAddress("little tokyo")
            .withRegion("East").withSize("5-room").withPrice("$800000").build();
    public static final Property PROPERTY_GEORGE = new PropertyBuilder().withAddress("4th street")
            .withRegion("Central").withSize("5-room").withPrice("$900000").build();

    // Manually added - Property's details found in {@code CommandTestUtil}
    public static final Property PROPERTY_AMY = new PropertyBuilder().withAddress(VALID_ADDRESS_AMY)
            .withRegion(VALID_REGION_AMY).withSize(VALID_SIZE_AMY).withPrice(VALID_PRICE_AMY).build();
    public static final Property PROPERTY_BOB = new PropertyBuilder().withAddress(VALID_ADDRESS_BOB)
            .withRegion(VALID_REGION_BOB).withSize(VALID_SIZE_BOB).withPrice(VALID_PRICE_BOB).build();

    private TypicalProperties() {} // prevents instantiation

    public static List<Property> getTypicalProperties() {
        return new ArrayList<>(Arrays.asList(PROPERTY_ALICE, PROPERTY_BENSON, PROPERTY_CARL, PROPERTY_DANIEL,
                        PROPERTY_ELLE, PROPERTY_FIONA, PROPERTY_GEORGE));
    }
}

package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.property.Property;

/**
 * A utility class containing a list of {@code Property} objects to be used in tests.
 */
public class TypicalProperties {

    public static final Property NORTH_1_ROOM = new PropertyBuilder().withAddress("123, Woodlands Ave 6, #08-111")
            .withRegion("North").withSize("1-room").withPrice("$300000").build();
    public static final Property SOUTH_2_ROOM = new PropertyBuilder().withAddress("123, Pasir Panjang Ave 6, #08-111")
            .withRegion("South").withSize("2-room").withPrice("$400000").build();
    public static final Property EAST_3_ROOM = new PropertyBuilder().withAddress("123, Changi Ave 6, #08-111")
            .withRegion("East").withSize("3-room").withPrice("$500000").build();
    public static final Property WEST_4_ROOM = new PropertyBuilder().withAddress("123, Jurong West Ave 6, #08-111")
            .withRegion("West").withSize("4-room").withPrice("$600000").build();
    public static final Property CENTRAL_5_ROOM = new PropertyBuilder().withAddress("123, Bukit Timah Ave 6, #08-111")
            .withRegion("West").withSize("5-room").withPrice("$700000").build();

    private TypicalProperties() {} // prevents instantiation

    public static List<Property> getTypicalProperties() {
        return new ArrayList<>(Arrays.asList(NORTH_1_ROOM, SOUTH_2_ROOM, EAST_3_ROOM, WEST_4_ROOM, CENTRAL_5_ROOM));
    }
}

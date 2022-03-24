package seedu.ibook.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.ibook.model.item.Item;

public class TypicalItems {
    public static final String QUANTITY_5 = "5";
    public static final String QUANTITY_10 = "10";

    public static final Item KAYA_BREAD_1_Q5 =
        new ItemBuilder().withExpiryDate("2022-03-01").withQuantity(QUANTITY_5).build();
    public static final Item KAYA_BREAD_1_Q10 =
        new ItemBuilder().withExpiryDate("2022-03-01").withQuantity(QUANTITY_10).build();
    public static final Item PEANUT_BUTTER_BREAD_1_Q5 =
        new ItemBuilder().withExpiryDate("2022-03-01").withQuantity(QUANTITY_5).build();

    // Manually added - Item's details found in {@code CommandTestUtil}
    public static final Item ITEM_A_1 =
        new ItemBuilder().withExpiryDate("2022-03-01").withQuantity("5").build();
    public static final Item ITEM_B_1 =
        new ItemBuilder().withExpiryDate("2022-03-01").withQuantity("5").build();

    private TypicalItems() {} // prevents instantiation

    public static List<Item> getTypicalItems() {
        return new ArrayList<>(Arrays.asList(KAYA_BREAD_1_Q5, KAYA_BREAD_1_Q10, PEANUT_BUTTER_BREAD_1_Q5));
    }
}

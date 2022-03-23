package seedu.ibook.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.ibook.model.item.Item;

public class TypicalItems {
    public static final String QUANTITY_5 = "5";
    public static final String QUANTITY_10 = "10";

    public static final Item Q5_2022_03_01 =
        new ItemBuilder().withExpiryDate("2022-03-01").withQuantity(QUANTITY_5).build();
    public static final Item Q10_2022_03_01 =
        new ItemBuilder().withExpiryDate("2022-03-01").withQuantity(QUANTITY_10).build();
    public static final Item Q5_2022_03_02 =
        new ItemBuilder().withExpiryDate("2022-03-02").withQuantity(QUANTITY_5).build();

    private TypicalItems() {} // prevents instantiation

    public static List<Item> getTypicalItems() {
        return new ArrayList<>(Arrays.asList(Q5_2022_03_01, Q10_2022_03_01, Q5_2022_03_02));
    }
}

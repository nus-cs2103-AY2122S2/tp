package seedu.ibook.testutil;

import static seedu.ibook.logic.commands.CommandTestUtil.VALID_EXPIRY_DATE_A;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_EXPIRY_DATE_B;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_QUANTITY_A;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_QUANTITY_B;

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

    public static final Item ITEM_A =
        new ItemBuilder().withExpiryDate(VALID_EXPIRY_DATE_A).withQuantity(VALID_QUANTITY_A).build();

    public static final Item ITEM_B =
        new ItemBuilder().withExpiryDate(VALID_EXPIRY_DATE_B).withQuantity(VALID_QUANTITY_B).build();

    private TypicalItems() {} // prevents instantiation

    public static List<Item> getTypicalItems() {
        return new ArrayList<>(Arrays.asList(Q5_2022_03_01, Q10_2022_03_01, Q5_2022_03_02));
    }
}

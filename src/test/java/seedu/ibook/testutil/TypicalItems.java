package seedu.ibook.testutil;

import static seedu.ibook.logic.commands.CommandTestUtil.VALID_EXPIRY_DATE_A;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_EXPIRY_DATE_B;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_QUANTITY_A;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_QUANTITY_B;
import static seedu.ibook.testutil.TypicalProducts.KAYA_BREAD;
import static seedu.ibook.testutil.TypicalProducts.PRODUCT_A;
import static seedu.ibook.testutil.TypicalProducts.PRODUCT_B;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.ibook.model.item.Item;
import seedu.ibook.model.item.ItemDescriptor;

public class TypicalItems {
    public static final String QUANTITY_0 = "0";
    public static final String QUANTITY_5 = "5";
    public static final String QUANTITY_10 = "10";

    public static final ItemDescriptor Q5_2022_03_01 =
            new ItemBuilder().withExpiryDate("2022-03-01").withQuantity(QUANTITY_5).buildItemDescriptor();

    public static final Item Q5_2022_03_01_KAYA =
            new ItemBuilder().withExpiryDate("2022-03-01").withQuantity(QUANTITY_5).build(KAYA_BREAD);
    public static final Item Q10_2022_03_01_KAYA =
            new ItemBuilder().withExpiryDate("2022-03-01").withQuantity(QUANTITY_10).build(KAYA_BREAD);
    public static final Item Q5_2020_01_01_KAYA =
            new ItemBuilder().withExpiryDate("2020-01-01").withQuantity(QUANTITY_5).build(KAYA_BREAD);
    public static final Item Q5_2200_01_01_KAYA =
            new ItemBuilder().withExpiryDate("2200-01-01").withQuantity(QUANTITY_5).build(KAYA_BREAD);
    public static final Item Q5_2022_03_02_KAYA =
            new ItemBuilder().withExpiryDate("2022-03-02").withQuantity(QUANTITY_5).build(KAYA_BREAD);
    public static final Item Q0_2022_03_01_KAYA =
            new ItemBuilder().withExpiryDate("2022-03-01").withQuantity(QUANTITY_0).build(KAYA_BREAD);
    public static final Item Q0_2022_03_02_KAYA =
            new ItemBuilder().withExpiryDate("2022-03-02").withQuantity(QUANTITY_0).build(KAYA_BREAD);
    public static final Item Q5_TODAY_KAYA =
            new ItemBuilder().withExpiryDate(LocalDate.now().toString()).withQuantity(QUANTITY_5).build(KAYA_BREAD);
    public static final Item Q5_TEN_DAYS_KAYA =
            new ItemBuilder().withExpiryDate(
                    LocalDate.now().plusDays(10).toString()).withQuantity(QUANTITY_5).build(KAYA_BREAD);

    public static final Item ITEM_A =
            new ItemBuilder().withExpiryDate(VALID_EXPIRY_DATE_A).withQuantity(VALID_QUANTITY_A).build(PRODUCT_A);

    public static final Item ITEM_B =
            new ItemBuilder().withExpiryDate(VALID_EXPIRY_DATE_B).withQuantity(VALID_QUANTITY_B).build(PRODUCT_B);

    private TypicalItems() {} // prevents instantiation

    public static List<Item> getTypicalItems() {
        return new ArrayList<>(Arrays.asList(Q5_2020_01_01_KAYA, Q5_2022_03_01_KAYA, Q5_2200_01_01_KAYA));
    }

    public static List<Item> getOnlyExpiredItems() {
        return new ArrayList<>(Arrays.asList(Q5_2020_01_01_KAYA, Q5_2022_03_01_KAYA));
    }

    public static List<Item> getOnlyNonExpiredItems() {
        return new ArrayList<>(Arrays.asList(Q5_2200_01_01_KAYA));
    }

    public static List<Item> getAllItemsOut() {
        return new ArrayList<>(Arrays.asList(Q0_2022_03_01_KAYA, Q0_2022_03_02_KAYA, Q5_2020_01_01_KAYA));
    }

    public static List<Item> getZeroItems() {
        return new ArrayList<>(Arrays.asList(Q0_2022_03_01_KAYA, Q0_2022_03_02_KAYA));
    }

    public static List<Item> getNonZeroItems() {
        return new ArrayList<>(Arrays.asList(Q5_2020_01_01_KAYA));
    }

    public static List<Item> getAllItemsRemind() {
        return new ArrayList<>(Arrays.asList(Q5_2020_01_01_KAYA, Q5_TODAY_KAYA, Q5_2200_01_01_KAYA));
    }

    public static List<Item> getOnlyExpiringItems() {
        return new ArrayList<>(Arrays.asList(Q5_TODAY_KAYA));
    }

    public static List<Item> getOnlyNonExpiringItems() {
        return new ArrayList<>(Arrays.asList(Q5_2020_01_01_KAYA, Q5_2200_01_01_KAYA));
    }

    public static List<Item> getItemToday() {
        return new ArrayList<>(Arrays.asList(Q5_TODAY_KAYA));
    }

    public static List<Item> getItemTenDays() {
        return new ArrayList<>(Arrays.asList(Q5_TEN_DAYS_KAYA));
    }

}

package seedu.ibook.testutil;

import static seedu.ibook.testutil.TypicalItems.ITEM_A;
import static seedu.ibook.testutil.TypicalItems.ITEM_B;
import static seedu.ibook.testutil.TypicalItems.Q5_2022_03_01_KAYA;
import static seedu.ibook.testutil.TypicalItems.Q5_2200_01_01_KAYA;
import static seedu.ibook.testutil.TypicalProducts.CHOCOLATE_BREAD;
import static seedu.ibook.testutil.TypicalProducts.KAYA_BREAD;
import static seedu.ibook.testutil.TypicalProducts.PEANUT_BUTTER_BREAD;
import static seedu.ibook.testutil.TypicalProducts.PRODUCT_A;
import static seedu.ibook.testutil.TypicalProducts.PRODUCT_B;
import static seedu.ibook.testutil.TypicalProducts.VANILLA_CAKE;
import static seedu.ibook.testutil.TypicalProducts.WAFFLES;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.ibook.model.actions.ReversibleAddItemAction;
import seedu.ibook.model.actions.ReversibleAddProductAction;
import seedu.ibook.model.actions.ReversibleIBookAction;
import seedu.ibook.model.actions.ReversibleRemoveItemAction;
import seedu.ibook.model.actions.ReversibleRemoveProductAction;
import seedu.ibook.model.actions.ReversibleSetItemAction;
import seedu.ibook.model.actions.ReversibleSetProductAction;

public class TypicalReversibleIBookActions {
    public static final ReversibleAddProductAction REVERSIBLE_ADD_PRODUCT_ACTION =
            new ReversibleAddProductAction(PRODUCT_A);
    public static final ReversibleSetProductAction REVERSIBLE_SET_PRODUCT_ACTION =
            new ReversibleSetProductAction(KAYA_BREAD, PRODUCT_B);
    public static final ReversibleRemoveProductAction REVERSIBLE_REMOVE_PRODUCT_ACTION =
            new ReversibleRemoveProductAction(VANILLA_CAKE);
    public static final ReversibleAddItemAction REVERSIBLE_ADD_ITEM_ACTION =
            new ReversibleAddItemAction(WAFFLES, ITEM_A);
    public static final ReversibleSetItemAction REVERSIBLE_SET_ITEM_ACTION =
            new ReversibleSetItemAction(CHOCOLATE_BREAD, Q5_2200_01_01_KAYA, ITEM_B);
    public static final ReversibleRemoveItemAction REVERSIBLE_REMOVE_ITEM_ACTION =
            new ReversibleRemoveItemAction(PEANUT_BUTTER_BREAD, Q5_2022_03_01_KAYA);

    private TypicalReversibleIBookActions() {} // prevent instantiation

    public static List<ReversibleIBookAction> getTypicalReversibleIBookActions() {
        return new ArrayList<>(Arrays.asList(
                REVERSIBLE_ADD_PRODUCT_ACTION,
                REVERSIBLE_ADD_ITEM_ACTION,
                REVERSIBLE_SET_ITEM_ACTION,
                REVERSIBLE_REMOVE_ITEM_ACTION,
                REVERSIBLE_SET_PRODUCT_ACTION,
                REVERSIBLE_REMOVE_PRODUCT_ACTION,
                REVERSIBLE_ADD_PRODUCT_ACTION));
    }
}

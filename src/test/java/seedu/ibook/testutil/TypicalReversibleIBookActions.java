package seedu.ibook.testutil;

import static seedu.ibook.testutil.TypicalItems.ITEM_A;
import static seedu.ibook.testutil.TypicalItems.ITEM_B;
import static seedu.ibook.testutil.TypicalProducts.PRODUCT_A;
import static seedu.ibook.testutil.TypicalProducts.PRODUCT_B;
import static seedu.ibook.testutil.TypicalProducts.getTypicalIBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.ibook.model.IBook;
import seedu.ibook.model.ReversibleAddItemAction;
import seedu.ibook.model.ReversibleAddProductAction;
import seedu.ibook.model.ReversibleIBookAction;
import seedu.ibook.model.ReversibleRemoveItemAction;
import seedu.ibook.model.ReversibleRemoveProductAction;
import seedu.ibook.model.ReversibleResetDataAction;
import seedu.ibook.model.ReversibleSetItemAction;
import seedu.ibook.model.ReversibleSetProductAction;

public class TypicalReversibleIBookActions {
    public static final ReversibleAddProductAction REVERSIBLE_ADD_PRODUCT_ACTION =
            new ReversibleAddProductAction(PRODUCT_A);
    public static final ReversibleSetProductAction REVERSIBLE_SET_PRODUCT_ACTION =
            new ReversibleSetProductAction(PRODUCT_A, PRODUCT_B);
    public static final ReversibleRemoveProductAction REVERSIBLE_REMOVE_PRODUCT_ACTION =
            new ReversibleRemoveProductAction(PRODUCT_B);
    public static final ReversibleAddItemAction REVERSIBLE_ADD_ITEM_ACTION =
            new ReversibleAddItemAction(PRODUCT_A, ITEM_A);
    public static final ReversibleSetItemAction REVERSIBLE_SET_ITEM_ACTION =
            new ReversibleSetItemAction(PRODUCT_B, ITEM_B, ITEM_A);
    public static final ReversibleRemoveItemAction REVERSIBLE_REMOVE_ITEM_ACTION =
            new ReversibleRemoveItemAction(PRODUCT_A, ITEM_A);
    public static final ReversibleResetDataAction REVERSIBLE_RESET_DATA_ACTION =
            new ReversibleResetDataAction(getTypicalIBook(), new IBook());

    private TypicalReversibleIBookActions() {} // prevent instantiation

    public static List<ReversibleIBookAction> getTypicalReversibleIBookActions() {
        return new ArrayList<>(Arrays.asList(
                REVERSIBLE_ADD_PRODUCT_ACTION,
                REVERSIBLE_ADD_ITEM_ACTION,
                REVERSIBLE_SET_PRODUCT_ACTION,
                REVERSIBLE_REMOVE_PRODUCT_ACTION,
                REVERSIBLE_ADD_PRODUCT_ACTION,
                REVERSIBLE_RESET_DATA_ACTION));
    }
}

package seedu.ibook.testutil;

import static seedu.ibook.testutil.TypicalItems.ITEM_A;
import static seedu.ibook.testutil.TypicalItems.ITEM_B;
import static seedu.ibook.testutil.TypicalProducts.PRODUCT_A;
import static seedu.ibook.testutil.TypicalProducts.PRODUCT_B;

import seedu.ibook.model.ReversibleAddItemAction;
import seedu.ibook.model.ReversibleAddProductAction;
import seedu.ibook.model.ReversibleRemoveItemAction;
import seedu.ibook.model.ReversibleRemoveProductAction;
import seedu.ibook.model.ReversibleSetItemAction;
import seedu.ibook.model.ReversibleSetProductAction;

public class TypicalReversibleIBookAction {
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
}

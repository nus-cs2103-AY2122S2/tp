package seedu.ibook.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.ibook.testutil.TypicalProducts.WAFFLES;
import static seedu.ibook.testutil.TypicalProducts.getTypicalIBook;
import static seedu.ibook.testutil.TypicalReversibleIBookActions.REVERSIBLE_ADD_PRODUCT_ACTION;
import static seedu.ibook.testutil.TypicalReversibleIBookActions.REVERSIBLE_SET_PRODUCT_ACTION;

import org.junit.jupiter.api.Test;

import seedu.ibook.model.actions.ReversibleAddProductAction;
import seedu.ibook.model.actions.ReversibleRemoveProductAction;
import seedu.ibook.model.actions.ReversibleResetDataAction;
import seedu.ibook.model.actions.ReversibleSetProductAction;

public class ReversibleIBookActionsTest {
    private final IBook emptyIBook = new IBook();
    private final IBook typicalIBook = getTypicalIBook();

    private IBook initiallyEmptyIBook = new IBook();
    private IBook initiallyNonEmptyIBook = getTypicalIBook();

    @Test
    public void testReversibleAddProductAction() {
        ReversibleAddProductAction reversibleAddProductAction = REVERSIBLE_ADD_PRODUCT_ACTION;

        reversibleAddProductAction.performForwardAction(initiallyEmptyIBook);
        reversibleAddProductAction.performBackwardAction(initiallyEmptyIBook);
        assertEquals(initiallyEmptyIBook, emptyIBook);

        reversibleAddProductAction.performForwardAction(initiallyNonEmptyIBook);
        reversibleAddProductAction.performBackwardAction(initiallyNonEmptyIBook);
        assertEquals(initiallyNonEmptyIBook, typicalIBook);
    }

    @Test
    public void testReversibleSetProductAction() {
        ReversibleSetProductAction reversibleSetProductAction = REVERSIBLE_SET_PRODUCT_ACTION;

        reversibleSetProductAction.performForwardAction(initiallyNonEmptyIBook);
        reversibleSetProductAction.performBackwardAction(initiallyNonEmptyIBook);
        assertEquals(initiallyNonEmptyIBook, typicalIBook);
    }

    @Test
    public void testReversibleRemoveProductAction() {
        ReversibleRemoveProductAction reversibleRemoveProductAction =
                new ReversibleRemoveProductAction(WAFFLES);

        reversibleRemoveProductAction.performForwardAction(initiallyNonEmptyIBook);
        assertFalse(new ModelManager(initiallyNonEmptyIBook, new UserPrefs()).hasProduct(WAFFLES));
        reversibleRemoveProductAction.performBackwardAction(initiallyNonEmptyIBook);
        assertTrue(new ModelManager(initiallyNonEmptyIBook, new UserPrefs()).hasProduct(WAFFLES));
    }

    @Test
    public void testReversibleResetDataAction() {
        ReversibleResetDataAction resetEmptyIBook =
                new ReversibleResetDataAction(initiallyEmptyIBook, typicalIBook);
        ReversibleResetDataAction resetNonEmptyIBook =
                new ReversibleResetDataAction(new IBook(initiallyNonEmptyIBook), emptyIBook);

        resetEmptyIBook.performForwardAction(initiallyEmptyIBook);
        resetEmptyIBook.performBackwardAction(initiallyEmptyIBook);
        assertEquals(initiallyEmptyIBook, emptyIBook);

        resetNonEmptyIBook.performForwardAction(initiallyNonEmptyIBook);
        resetNonEmptyIBook.performBackwardAction(initiallyNonEmptyIBook);
        assertEquals(initiallyNonEmptyIBook, typicalIBook);
    }
}

package seedu.address.logic.commands.sort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.testutil.Assert.assertAllFalse;
import static seedu.address.testutil.Assert.assertAllThrows;
import static seedu.address.testutil.Assert.assertAllTrue;
import static seedu.address.testutil.TypicalStrings.SORT_ORDER_ASCENDING;
import static seedu.address.testutil.TypicalStrings.SORT_ORDER_ASC_STRING;
import static seedu.address.testutil.TypicalStrings.SORT_ORDER_DESCENDING;
import static seedu.address.testutil.TypicalStrings.SORT_ORDER_DESC_STRING;
import static seedu.address.testutil.TypicalStrings.getInvalidStringsForSortOrder;
import static seedu.address.testutil.TypicalStrings.getValidStringsForSortOrder;

import org.junit.jupiter.api.Test;

public class SortOrderTest {

    @Test
    public void isValidSortOrder() {

        // null sort label
        assertFalse(SortOrder.isValidSortOrder(null));

        // invalid sort order
        assertAllFalse(SortOrder::isValidSortOrder, getInvalidStringsForSortOrder());

        // valid sort order
        assertAllTrue(SortOrder::isValidSortOrder, getValidStringsForSortOrder());
    }


    @Test
    public void valueOfLabel_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> SortOrder.valueOfLabel(null));
    }

    @Test
    public void valueOfLabel_invalidSortOrderLabel_throwsIllegalArgumentException() {
        assertAllThrows(IllegalArgumentException.class, SortOrder::valueOfLabel, getInvalidStringsForSortOrder());
    }

    @Test
    public void valueOfLabel_validSortOrderLabel_returnsValidSortOrder() {
        String ascendingLabel = SORT_ORDER_ASCENDING;
        assertEquals(SortOrder.ASCENDING, SortOrder.valueOfLabel(ascendingLabel));

        String descendingLabel = SORT_ORDER_DESCENDING;
        assertEquals(SortOrder.DESCENDING, SortOrder.valueOfLabel(descendingLabel));
    }

    @Test
    public void toString_validSortKey_validLabel() {
        String ascendingExpected = SORT_ORDER_ASC_STRING;
        assertEquals(SortOrder.ASCENDING.toString(), ascendingExpected);

        String descendingExpected = SORT_ORDER_DESC_STRING;
        assertEquals(SortOrder.DESCENDING.toString(), descendingExpected);
    }
}

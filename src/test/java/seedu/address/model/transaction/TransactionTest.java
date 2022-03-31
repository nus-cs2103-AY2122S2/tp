package seedu.address.model.transaction;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TransactionUtil.*;
import static seedu.address.testutil.TransactionUtil.VALID_DUE_DATE_TWO;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;

public class TransactionTest {

    @Test
    public void constructor_null_throwsNullArgumentException() {
        assertThrows(NullPointerException.class, () -> new Transaction((Collection<TransactionField>) null, VALID_ID));
    }

    @Test
    public void constructor_missingRequiredField_throwsIllegalArgumentException() {
        // Missing Transaction Date Field
        List<TransactionField> incompleteArgumentsOne = List.of(
                new Amount(VALID_AMOUNT_ONE)
        );
        assertThrows(IllegalArgumentException.class, () -> new Transaction(incompleteArgumentsOne, VALID_ID));

        // Missing Amount Field
        List<TransactionField> incompleteArgumentsTwo = List.of(
                    new TransactionDate(VALID_TRANSACTION_DATE_ONE)
        );
        assertThrows(IllegalArgumentException.class, () -> new Transaction(incompleteArgumentsTwo, VALID_ID));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Transaction transactionCopy = new Transaction(TRANSACTION_ONE);
        assertEquals(TRANSACTION_ONE, transactionCopy);

        // same object -> returns true
        assertEquals(TRANSACTION_ONE, TRANSACTION_ONE);

        // null -> returns false
        assertNotEquals(null, TRANSACTION_ONE);

        // different type -> returns false
        assertNotEquals("TRANSACTION_ONE", TRANSACTION_ONE);

        // different Transaction -> returns false
        assertNotEquals(TRANSACTION_ONE, TRANSACTION_TWO);

        // some fields are missing -> return false
        assertNotEquals(TRANSACTION_ONE, TRANSACTION_ONE_INCOMPLETE);
    }

}

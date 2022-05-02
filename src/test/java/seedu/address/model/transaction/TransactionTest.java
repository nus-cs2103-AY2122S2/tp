package seedu.address.model.transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TransactionUtil.INVALID_TRANSACTION;
import static seedu.address.testutil.TransactionUtil.TRANSACTION_ONE;
import static seedu.address.testutil.TransactionUtil.TRANSACTION_ONE_INCOMPLETE;
import static seedu.address.testutil.TransactionUtil.TRANSACTION_TWO;
import static seedu.address.testutil.TransactionUtil.VALID_AMOUNT_ONE;
import static seedu.address.testutil.TransactionUtil.VALID_DUE_DATE_ONE;
import static seedu.address.testutil.TransactionUtil.VALID_ID;
import static seedu.address.testutil.TransactionUtil.VALID_TRANSACTION_DATE_ONE;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.Command;
import seedu.address.model.transaction.util.StatusFactoryInterface;

public class TransactionTest {
    private Amount amount;
    private TransactionDate transactionDate;
    private DueDate dueDate;
    private Transaction transaction;
    private long validId;

    @BeforeEach
    public void setUp() {
        amount = new Amount(VALID_AMOUNT_ONE);
        transactionDate = new TransactionDate(VALID_TRANSACTION_DATE_ONE);
        dueDate = new DueDate(VALID_DUE_DATE_ONE);
        validId = VALID_ID;

        Collection<TransactionField> fields = List.of(
                amount,
                transactionDate,
                dueDate
        );
        transaction = new Transaction(
                fields,
                validId
        );
    }

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
    public void isValidTest() {
        Transaction validTransaction = TRANSACTION_ONE;
        Transaction invalidTransaction = INVALID_TRANSACTION;

        // valid transaction -> returns True
        assertTrue(validTransaction.isValid());

        // invalid transaction (due date < transaction date) -> returns false
        assertFalse(invalidTransaction.isValid());
    }

    @Test
    public void removeFieldTest() {
        DueDate toRemove = dueDate;
        Collection<TransactionField> fieldsOne = List.of(
                amount,
                transactionDate,
                dueDate
        );

        Collection<TransactionField> fieldCopy = new ArrayList<>(fieldsOne);

        Transaction transactionOne = new Transaction(fieldsOne, VALID_ID);
        fieldCopy.remove(toRemove);
        Transaction expectedTransaction = new Transaction(transactionOne.getTransactionId(), fieldCopy, VALID_ID);

        assertEquals(expectedTransaction, transactionOne.removeField(DueDate.PREFIX));
    }

    @Test
    public void addFieldTest() {
        DueDate toAdd = dueDate;

        Collection<TransactionField> fieldsOne = List.of(
                amount,
                transactionDate,
                dueDate
        );

        Collection<TransactionField> fieldCopy = new ArrayList<>(fieldsOne);

        Transaction transactionOne = new Transaction(fieldsOne, VALID_ID);
        fieldCopy.add(toAdd);
        Transaction expectedTransaction = new Transaction(transactionOne.getTransactionId(), fieldCopy, VALID_ID);

        assertEquals(expectedTransaction, transactionOne.addField(toAdd));
    }

    @Test
    public void setStatusToTest() {
        Status statusPaid = new Status("true");

        Collection<TransactionField> fields = new ArrayList<>(transaction.getFields());
        fields.add(statusPaid);

        Transaction expectedTransaction = new Transaction(transaction.getTransactionId(),
                fields,
                validId
        );

        assertEquals(expectedTransaction, transaction.setStatusTo(Command.class, new StatusFactoryAlwaysReturnsTrue()));
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

    private class StatusFactoryAlwaysReturnsTrue implements StatusFactoryInterface {

        @Override
        public Status getStatus(Class<? extends Command> command) {
            return new Status("True");
        }
    }
}

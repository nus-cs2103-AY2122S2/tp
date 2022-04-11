package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEMO_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEMO_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.MemoUtil.MAXIMUM_MEMO_STRING;
import static seedu.address.testutil.MemoUtil.ONE_LESS_THAN_MAXIMUM_MEMO_STRING;
import static seedu.address.testutil.MemoUtil.ONE_MORE_THAN_MAXIMUM_MEMO_STRING;

import org.junit.jupiter.api.Test;

/**
 * Contains tests for {@code Memo}.
 */
public class MemoTest {

    private final Memo validMemoAmy = new Memo(VALID_MEMO_AMY);

    // EP: null
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Memo(null));
    }

    // EP: invalid length string, 1 more than maximum length
    @Test
    public void constructor_invalidMemo_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Memo(ONE_MORE_THAN_MAXIMUM_MEMO_STRING));
    }

    // EP: empty string
    @Test
    public void isValidMemo_validEmptyMemo_returnsTrue() {
        assertTrue(Memo.isValidMemo("")); // Boundary value
    }

    // EP: white space string
    @Test
    public void isValidMemo_validWhiteSpaceMemo_returnsTrue() {
        assertTrue(Memo.isValidMemo(" "));
    }

    // EP: valid length string
    @Test
    public void isValidMemo_validShortLengthMemo_returnsTrue() {
        assertTrue(Memo.isValidMemo(VALID_MEMO_AMY));
    }

    // EP: valid length string
    @Test
    public void isValidMemo_validOneLessThanMaximumLengthMemo_returnsTrue() {
        assertTrue(Memo.isValidMemo(ONE_LESS_THAN_MAXIMUM_MEMO_STRING));
    }

    // EP: valid length string
    @Test
    public void isValidMemo_validMaximumLengthMemo_returnsTrue() {
        assertTrue(Memo.isValidMemo(MAXIMUM_MEMO_STRING)); // Boundary value
    }

    // EP: invalid length string, 1 more than maximum length
    @Test
    public void isValidMemo_invalidOneMoreThanMaximumLengthMemo_returnsFalse() {
        assertFalse(Memo.isValidMemo(ONE_MORE_THAN_MAXIMUM_MEMO_STRING));
    }

    // EP: not empty memo
    @Test
    public void isEmpty_notEmptyValidMemo_returnsFalse() {
        assertFalse(validMemoAmy.isEmpty());
    }

    // EP: empty memo
    @Test
    public void isEmpty_emptyValidMemo_returnsTrue() {
        assertTrue(Memo.EMPTY_MEMO.isEmpty());
    }

    @Test
    public void exactEquals() {
        // same object -> returns true
        assertTrue(validMemoAmy.exactEquals(validMemoAmy));

        // same values -> returns true
        Memo validMemoCopy = new Memo(validMemoAmy.memo);
        assertTrue(validMemoAmy.exactEquals(validMemoCopy));

        // different capitalization -> returns false
        Memo validMemoAmyAllCaps = new Memo(VALID_MEMO_AMY.toUpperCase());
        assertFalse(validMemoAmy.exactEquals(validMemoAmyAllCaps));

        // null -> returns false
        assertFalse(validMemoAmy.exactEquals(null));

        // different memo -> returns false
        Memo differentMemo = new Memo(VALID_MEMO_BOB);
        assertFalse(validMemoAmy.exactEquals(differentMemo));
    }

    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(validMemoAmy.equals(validMemoAmy));

        // same values -> returns true
        Memo validMemoCopy = new Memo(validMemoAmy.memo);
        assertTrue(validMemoAmy.equals(validMemoCopy));

        // different capitalization -> returns true
        Memo validMemoAmyAllCaps = new Memo(VALID_MEMO_AMY.toUpperCase());
        assertTrue(validMemoAmy.equals(validMemoAmyAllCaps));

        // different types -> returns false
        assertFalse(validMemoAmy.equals(1));

        // null -> returns false
        assertFalse(validMemoAmy.equals(null));

        // different memo -> returns false
        Memo differentMemo = new Memo(VALID_MEMO_BOB);
        assertFalse(validMemoAmy.equals(differentMemo));
    }
}

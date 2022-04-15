package seedu.address.logic.commands.sort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.testutil.Assert.assertAllFalse;
import static seedu.address.testutil.Assert.assertAllThrows;
import static seedu.address.testutil.Assert.assertAllTrue;
import static seedu.address.testutil.TypicalStrings.SORT_KEY_DEADLINE;
import static seedu.address.testutil.TypicalStrings.SORT_KEY_DEADLINE_STRING;
import static seedu.address.testutil.TypicalStrings.SORT_KEY_NAME;
import static seedu.address.testutil.TypicalStrings.SORT_KEY_NAME_STRING;
import static seedu.address.testutil.TypicalStrings.SORT_KEY_PRIORITY;
import static seedu.address.testutil.TypicalStrings.SORT_KEY_PRIORITY_STRING;
import static seedu.address.testutil.TypicalStrings.getInvalidStringsForSortKey;
import static seedu.address.testutil.TypicalStrings.getValidStringsForSortKey;

import org.junit.jupiter.api.Test;

public class SortKeyTest {

    @Test
    public void isValidSortKey() {

        // null sort label
        assertFalse(SortKey.isValidSortKey(null));

        // invalid sort key
        assertAllFalse(SortKey::isValidSortKey, getInvalidStringsForSortKey());

        // valid sort key
        assertAllTrue(SortKey::isValidSortKey, getValidStringsForSortKey());
    }


    @Test
    public void valueOfLabel_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> SortKey.valueOfLabel(null));
    }

    @Test
    public void valueOfLabel_invalidSortKeyLabel_throwsIllegalArgumentException() {
        assertAllThrows(IllegalArgumentException.class, SortKey::valueOfLabel, getInvalidStringsForSortKey());
    }

    @Test
    public void valueOfLabel_validSortKeyLabel_returnsValidSortKey() {
        String deadlineLabel = SORT_KEY_DEADLINE;
        assertEquals(SortKey.DEADLINE, SortKey.valueOfLabel(deadlineLabel));

        String nameLabel = SORT_KEY_NAME;
        assertEquals(SortKey.NAME, SortKey.valueOfLabel(nameLabel));

        String priorityLabel = SORT_KEY_PRIORITY;
        assertEquals(SortKey.PRIORITY, SortKey.valueOfLabel(priorityLabel));
    }

    @Test
    public void toString_validSortKey_validLabel() {
        String deadlineExpected = SORT_KEY_DEADLINE_STRING;
        assertEquals(SortKey.DEADLINE.toString(), deadlineExpected);

        String nameExpected = SORT_KEY_NAME_STRING;
        assertEquals(SortKey.NAME.toString(), nameExpected);

        String priorityExpected = SORT_KEY_PRIORITY_STRING;
        assertEquals(SortKey.PRIORITY.toString(), priorityExpected);
    }
}

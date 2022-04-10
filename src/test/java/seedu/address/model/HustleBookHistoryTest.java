package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.HustleBookBuilder;

public class HustleBookHistoryTest {
    private static final HustleBookHistory hustleBookHistory = HustleBookHistory.getInstance();
    private static final HustleBook HUSTLEBOOK_EMPTY = new HustleBookBuilder().build();
    private static final HustleBook HUSTLEBOOK_WITH_PERSON_ALICE = new HustleBookBuilder().withPerson(ALICE).build();
    private static final HustleBook HUSTLEBOOK_WITH_PERSON_BOB = new HustleBookBuilder().withPerson(BOB).build();
    private static final HustleBook HUSTLEBOOK_WITH_PERSON_CARL = new HustleBookBuilder().withPerson(CARL).build();
    private static final HustleBook HUSTLEBOOK_WITH_PERSON_DANIEL = new HustleBookBuilder().withPerson(DANIEL).build();

    // Check to ensure all instances are the same
    @Test
    public void getInstance_checkInstancesSame_success() {
        HustleBookHistory firstObject = HustleBookHistory.getInstance();
        HustleBookHistory secondObject = HustleBookHistory.getInstance();
        assertSame(firstObject, secondObject); // Check if there are the same instance
    }

    @Test
    public void getInstance_checkUpdatedInstancesSame_success() {
        HustleBookHistory firstObject = HustleBookHistory.getInstance();
        hustleBookHistory.update(HUSTLEBOOK_WITH_PERSON_ALICE);
        HustleBookHistory secondObject = HustleBookHistory.getInstance();
        assertSame(firstObject, secondObject); // Check if there are the same instance
    }

    @Test
    public void getCurrState_noHistory_throwsIndexOutOfBoundsException() {
        hustleBookHistory.clear();
        assertThrows(IndexOutOfBoundsException.class, () -> hustleBookHistory.getCurrState());
    }

    @Test
    public void getPrevState_noPrevState_throwsIndexOutOfBoundsException() {
        hustleBookHistory.clear();
        hustleBookHistory.update(HUSTLEBOOK_WITH_PERSON_ALICE);
        assertThrows(IndexOutOfBoundsException.class, () -> hustleBookHistory.getPrevState());
    }

    @Test
    public void getPrevState_checkStateWithPerson_success() {
        hustleBookHistory.update(HUSTLEBOOK_WITH_PERSON_ALICE);
        hustleBookHistory.update(HUSTLEBOOK_WITH_PERSON_BOB);
        assertEquals(HUSTLEBOOK_WITH_PERSON_ALICE, hustleBookHistory.getPrevState());
    }

    // Check ensure that even empty HustleBook is returned
    @Test
    public void getPrevState_checkStateWithoutPerson_success() {
        hustleBookHistory.update(HUSTLEBOOK_EMPTY);
        hustleBookHistory.update(HUSTLEBOOK_WITH_PERSON_ALICE);
        assertEquals(HUSTLEBOOK_EMPTY, hustleBookHistory.getPrevState());
    }

    @Test
    public void getPrevState_multipleGetPrevStateExecuted_success() {
        // Simulates (In order): Update, Update, Update, Update, Undo, Undo
        hustleBookHistory.update(HUSTLEBOOK_WITH_PERSON_ALICE);
        hustleBookHistory.update(HUSTLEBOOK_WITH_PERSON_BOB);
        hustleBookHistory.update(HUSTLEBOOK_WITH_PERSON_CARL);
        hustleBookHistory.update(HUSTLEBOOK_WITH_PERSON_DANIEL);
        hustleBookHistory.getPrevState();
        hustleBookHistory.getPrevState();
        assertEquals(HUSTLEBOOK_WITH_PERSON_BOB, hustleBookHistory.getCurrState());
    }

    @Test
    public void getPrevState_getPrevStateExecutedAndStateUpdated_success() {
        // Simulates (In order): Update, Update, Undo, Update
        hustleBookHistory.update(HUSTLEBOOK_WITH_PERSON_ALICE);
        hustleBookHistory.update(HUSTLEBOOK_WITH_PERSON_BOB);
        hustleBookHistory.getPrevState();
        hustleBookHistory.update(HUSTLEBOOK_WITH_PERSON_CARL);
        assertEquals(HUSTLEBOOK_WITH_PERSON_CARL, hustleBookHistory.getCurrState());
        assertEquals(HUSTLEBOOK_WITH_PERSON_ALICE, hustleBookHistory.getPrevState());
    }

    @Test
    public void getPrevState_noHistory_throwsIndexOutOfBoundsException() {
        hustleBookHistory.clear();
        assertThrows(IndexOutOfBoundsException.class, () -> hustleBookHistory.getPrevState());
    }

    @Test
    public void getNextState_noFutureState_throwsIndexOutOfBoundsException() {
        hustleBookHistory.clear();
        hustleBookHistory.update(HUSTLEBOOK_WITH_PERSON_ALICE);
        assertThrows(IndexOutOfBoundsException.class, () -> hustleBookHistory.getNextState());
    }

    // Check to ensure redo possible after undo
    @Test
    public void getNextState_checkState_success() {
        hustleBookHistory.update(HUSTLEBOOK_WITH_PERSON_ALICE);
        hustleBookHistory.update(HUSTLEBOOK_WITH_PERSON_BOB);
        hustleBookHistory.getPrevState();
        assertEquals(HUSTLEBOOK_WITH_PERSON_BOB, hustleBookHistory.getNextState());
    }

    @Test
    public void getNextState_multipleGetNextStateExecuted_success() {
        // Simulates (In order): Update, Update, Update, Update, Undo, Undo, Redo, Redo
        hustleBookHistory.clear();
        hustleBookHistory.update(HUSTLEBOOK_WITH_PERSON_ALICE);
        hustleBookHistory.update(HUSTLEBOOK_WITH_PERSON_BOB);
        hustleBookHistory.update(HUSTLEBOOK_WITH_PERSON_CARL);
        hustleBookHistory.update(HUSTLEBOOK_WITH_PERSON_DANIEL);
        hustleBookHistory.getPrevState();
        hustleBookHistory.getPrevState();
        hustleBookHistory.getNextState();
        hustleBookHistory.getNextState();
        assertEquals(HUSTLEBOOK_WITH_PERSON_DANIEL, hustleBookHistory.getCurrState());
    }

    // Check to ensure redo not possible after update
    @Test
    public void getNextState_getPrevStateExecutedAndStateUpdated_throwsIndexOutOfBoundsException() {
        // Simulates (In order): Update, Update, Undo, Update
        hustleBookHistory.update(HUSTLEBOOK_WITH_PERSON_ALICE);
        hustleBookHistory.update(HUSTLEBOOK_WITH_PERSON_BOB);
        hustleBookHistory.getPrevState();
        hustleBookHistory.update(HUSTLEBOOK_WITH_PERSON_CARL);
        assertThrows(IndexOutOfBoundsException.class, () -> hustleBookHistory.getNextState());
    }

    @Test
    public void getNextState_noHistory_throwsIndexOutOfBoundsException() {
        hustleBookHistory.clear();
        assertThrows(IndexOutOfBoundsException.class, () -> hustleBookHistory.getNextState());
    }

    @Test
    public void update_addEmptyHustleBook_success() {
        hustleBookHistory.update(HUSTLEBOOK_EMPTY);
        assertEquals(HUSTLEBOOK_EMPTY, hustleBookHistory.getCurrState());
    }

    @Test
    public void update_addHustleBookWithPerson_success() {
        hustleBookHistory.update(HUSTLEBOOK_WITH_PERSON_ALICE);
        assertEquals(HUSTLEBOOK_WITH_PERSON_ALICE, hustleBookHistory.getCurrState());
    }

    @Test
    public void update_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> hustleBookHistory.update(null));
    }

    @Test
    public void testClear() {
        hustleBookHistory.clear();
        assertTrue(hustleBookHistory.isEmpty());
    }

    @Test
    public void clear_clearAfterUpdating_success() {
        hustleBookHistory.update(HUSTLEBOOK_WITH_PERSON_ALICE);
        hustleBookHistory.clear();
        assertTrue(hustleBookHistory.isEmpty());
    }

    @Test
    public void testIsEmpty() {

        // History empty (No states)
        hustleBookHistory.clear();
        assertTrue(hustleBookHistory.isEmpty());

        // Single entry in history (1 state)
        hustleBookHistory.clear();
        hustleBookHistory.update(HUSTLEBOOK_WITH_PERSON_ALICE);
        assertFalse(hustleBookHistory.isEmpty());

        // Multiple entry in history (Multiple states)
        hustleBookHistory.update(HUSTLEBOOK_WITH_PERSON_ALICE);
        hustleBookHistory.update(HUSTLEBOOK_WITH_PERSON_BOB);
        assertFalse(hustleBookHistory.isEmpty());

    }
}

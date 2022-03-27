package seedu.ibook.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.ibook.testutil.Assert.assertThrows;
import static seedu.ibook.testutil.TypicalReversibleIBookActions.REVERSIBLE_ADD_ITEM_ACTION;
import static seedu.ibook.testutil.TypicalReversibleIBookActions.REVERSIBLE_ADD_PRODUCT_ACTION;
import static seedu.ibook.testutil.TypicalReversibleIBookActions.REVERSIBLE_REMOVE_ITEM_ACTION;
import static seedu.ibook.testutil.TypicalReversibleIBookActions.REVERSIBLE_SET_ITEM_ACTION;
import static seedu.ibook.testutil.TypicalReversibleIBookActions.REVERSIBLE_SET_PRODUCT_ACTION;

import java.util.List;

import org.junit.jupiter.api.Test;

public class StateChangeRecorderTest {
    private StateChangeRecorder recorderWithNoRecord = getStateChangeRecorderWithNoRecord();
    private StateChangeRecorder recorderWithOneRecord = getStateChangeRecorderWithOneRecord();
    private StateChangeRecorder recorderWithTwoRecords = getStateChangeRecorderWithTwoRecords();

    private StateChangeRecorder getStateChangeRecorderWithNoRecord() {
        return new StateChangeRecorder();
    }

    private StateChangeRecorder getStateChangeRecorderWithOneRecord() {
        StateChangeRecorder stateChangeRecorder = new StateChangeRecorder();

        stateChangeRecorder.recordAction(REVERSIBLE_ADD_ITEM_ACTION);
        stateChangeRecorder.saveStateChange();

        return stateChangeRecorder;
    }

    private StateChangeRecorder getStateChangeRecorderWithTwoRecords() {
        StateChangeRecorder stateChangeRecorder = new StateChangeRecorder();

        stateChangeRecorder.recordAction(REVERSIBLE_ADD_ITEM_ACTION);
        stateChangeRecorder.saveStateChange();

        stateChangeRecorder.recordAction(REVERSIBLE_ADD_ITEM_ACTION);
        stateChangeRecorder.recordAction(REVERSIBLE_REMOVE_ITEM_ACTION);
        stateChangeRecorder.recordAction(REVERSIBLE_SET_PRODUCT_ACTION);
        stateChangeRecorder.saveStateChange();

        return stateChangeRecorder;
    }

    private void initialize() {
        recorderWithNoRecord = getStateChangeRecorderWithNoRecord();
        recorderWithOneRecord = getStateChangeRecorderWithOneRecord();
        recorderWithTwoRecords = getStateChangeRecorderWithTwoRecords();
    }

    @Test
    public void recordAction() {
        // test null argument
        assertThrows(NullPointerException.class, () -> recorderWithNoRecord.recordAction(null));

        try {
            recorderWithNoRecord.recordAction(REVERSIBLE_ADD_PRODUCT_ACTION);
        } catch (NullPointerException e) {
            // should not fail to add a valid action
            assert false;
        }
    }

    @Test
    public void getCurrentActionList() {
        recorderWithOneRecord = getStateChangeRecorderWithOneRecord();
        List<ReversibleIBookAction> actionList = recorderWithOneRecord.getCurrentActionList();
        List<ReversibleIBookAction> anotherActionList = recorderWithOneRecord.getCurrentActionList();

        assertEquals(actionList, anotherActionList);

        recorderWithOneRecord.prepareNewStateChange();
        anotherActionList = recorderWithOneRecord.getCurrentActionList();
        assertEquals(actionList, anotherActionList);

        recorderWithOneRecord.recordAction(REVERSIBLE_SET_ITEM_ACTION);
        anotherActionList = recorderWithOneRecord.getCurrentActionList();
        assertEquals(actionList, anotherActionList);

        recorderWithOneRecord.saveStateChange();
        anotherActionList = recorderWithOneRecord.getCurrentActionList();
        assertNotEquals(actionList, anotherActionList);
    }

    @Test
    public void hasOlderStateChange() {
        initialize();
        assertFalse(recorderWithNoRecord.hasOlderStateChange());
        assertTrue(recorderWithOneRecord.hasOlderStateChange());
        assertTrue(recorderWithTwoRecords.hasOlderStateChange());
    }

    @Test
    public void hasNewerStateChange() {
        initialize();
        assertFalse(recorderWithNoRecord.hasNewerStateChange());
        assertFalse(recorderWithOneRecord.hasNewerStateChange());
        assertFalse(recorderWithTwoRecords.hasNewerStateChange());

        assertThrows(AssertionError.class, () -> recorderWithNoRecord.revertStateChange());
        recorderWithOneRecord.revertStateChange();
        recorderWithTwoRecords.revertStateChange();

        assertTrue(recorderWithOneRecord.hasNewerStateChange());
        assertTrue(recorderWithTwoRecords.hasNewerStateChange());
    }

    @Test
    public void equals() {
        // same values -> returns true
        recorderWithOneRecord = getStateChangeRecorderWithOneRecord();
        StateChangeRecorder recorderCopy = getStateChangeRecorderWithOneRecord();
        assertTrue(recorderWithOneRecord.equals(recorderCopy));

        // same object -> returns true
        assertTrue(recorderWithOneRecord.equals(recorderWithOneRecord));

        // null -> returns false
        assertFalse(recorderWithOneRecord.equals(null));

        // different types -> returns false
        assertFalse(recorderWithOneRecord.equals(5));

        // different curStatePointer -> returns false
        recorderWithOneRecord.revertStateChange();
        assertFalse(recorderWithOneRecord.equals(recorderCopy));
        recorderWithOneRecord.restoreStateChange();

        // different stateChanges -> returns false
        recorderWithTwoRecords = getStateChangeRecorderWithTwoRecords();
        recorderWithTwoRecords.revertStateChange();
        assertFalse(recorderWithOneRecord.equals(recorderWithTwoRecords));

        // different newActionList -> returns false
        recorderCopy.recordAction(REVERSIBLE_ADD_ITEM_ACTION);
        assertFalse(recorderWithOneRecord.equals(recorderCopy));
    }
}

package seedu.ibook.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that keeps records of changes to iBook.
 */
public class StateChangeRecorder {

    /**
     * A class that represents a single state change.
     */
    private static class StateChange {
        private final List<ReversibleIBookAction> actionList;

        public StateChange(List<ReversibleIBookAction> actionList) {
            this.actionList = actionList;
        }

        @Override
        public String toString() {
            return String.format("A state change comprising %d actions", actionList.size());
        }

        @Override
        public boolean equals(Object other) {
            return other == this
                    || (other instanceof StateChange && actionList.equals(((StateChange) other).actionList));
        }
    }

    private static final int INVALID_STATE_CHANGE = -1;
    private static final int LIST_OFFSET = 1;

    private final List<StateChange> stateChanges;

    // A variable acting as a pointer to the most recent state change.
    private int curStateChange;

    // List of new actions to be performed.
    private List<ReversibleIBookAction> newActionList;

    /**
     * Class constructor.
     */
    public StateChangeRecorder() {
        stateChanges = new ArrayList<>();
        curStateChange = INVALID_STATE_CHANGE;

        prepareNewStateChange();
    }

    /**
     * Gets a new workspace for recording next possible state change.
     */
    public void prepareNewStateChange() {
        newActionList = new ArrayList<>();
    }

    /**
     * Records down the action to be performed.
     * @param action the action to be performed.
     */
    public void recordAction(ReversibleIBookAction action) {
        newActionList.add(action);
    }

    /**
     * Gets the list of actions in the {@code stateChange} indicated by {@code curStateChange}.
     * @return a copy of action list in the {@code stateChange} indicated by {@code curStateChange}.
     */
    public List<ReversibleIBookAction> getCurrentActionList() {
        return new ArrayList<>(stateChanges.get(curStateChange).actionList);
    }

    /**
     * Saves the changes made to iBook.
     */
    public void saveStateChange() {
        StateChange nextStateChange = new StateChange(newActionList);

        stateChanges.subList(curStateChange + LIST_OFFSET, stateChanges.size()).clear();
        stateChanges.add(nextStateChange);
        curStateChange++;
    }

    /**
     * Checks if there is any older changes.
     * @return true if there is one or more older changes.
     */
    public boolean hasOlderStateChange() {
        return curStateChange != INVALID_STATE_CHANGE;
    }

    /**
     * Checks if there is any newer changes.
     * @return true if there is one or more newer changes.
     */
    public boolean hasNewerStateChange() {
        return curStateChange != stateChanges.size() - LIST_OFFSET;
    }

    /**
     * Reverts most recently changes to IBook.
     */
    public void revertStateChange() {
        curStateChange--;
    }

    /**
     * Restores most recently reverted changes to IBook.
     */
    public void restoreStateChange() {
        curStateChange++;
    }

    @Override
    public String toString() {
        return String.format("A state change recorder having %d records of state change", stateChanges.size());
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof StateChangeRecorder)) {
            return false;
        }

        StateChangeRecorder o = (StateChangeRecorder) other;
        return curStateChange == o.curStateChange
                && stateChanges.equals(o.stateChanges)
                && newActionList.equals(o.newActionList);
    }
}

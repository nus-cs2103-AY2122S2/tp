package seedu.address.logic.inputhistory;

public class UserInputIndex {
    private int index;

    public UserInputIndex() {
        // Index -1 represents not in history mode
        index = -1;
    }

    /**
     * Resets index back to -1, not in history mode
     */
    protected void reset() {
        index = -1;
    }

    protected int currentIndex() {
        return index;
    }

    protected boolean isInHistoryMode() {
        return index != -1;
    }

    protected void increment() {
        index++;
    }

    protected void decrement() {
        if (!isInHistoryMode()) { // Do nothing if it's -1
            return;
        }
        index--;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other instanceof UserInputIndex) {
            UserInputIndex otherObj = (UserInputIndex) other;
            return index == otherObj.index;
        }
        return false;
    }
}

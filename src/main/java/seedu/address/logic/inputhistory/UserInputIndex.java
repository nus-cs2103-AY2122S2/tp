package seedu.address.logic.inputhistory;

/**
 * Represents an index used when browsing {@code UserInputHistory}.
 *
 * The index stored in {@code UserInputIndex} does not allow the integer to drop below -1. -1 is reserved to indicate
 * if the current viewing index of the history is {@code null}, which is not in history mode.
 */
public class UserInputIndex {
    private int index;

    /**
     * Creates a new {@code UserInputIndex} with starting index -1, which refers to not in history mode.
     */
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

package seedu.address.logic.inputhistory;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;

/**
 * Contains the selected {@code UserInputString}
 */
public class InputHistoryResult {
    private final UserInputString userInputString;
    private final boolean isChanged;

    private final Logger logger = LogsCenter.getLogger(getClass());

    /**
     * Creates a {@code InputHistoryResult} with the accompanying {@code UserInputString}.
     *
     * @param userInputString {@code UserInputString} retrieved from {@code UserInputHistory}.
     */
    public InputHistoryResult(UserInputString userInputString) {
        this.userInputString = userInputString;
        isChanged = true;
    }

    /**
     * Creates a {@code InputHistoryResult} with no accompanying {@code UserInputString}.
     *
     * {@code isChanged} will be set to {@code false} and the UI component check for this should not update any text
     * boxes.
     */
    public InputHistoryResult() {
        this.userInputString = null;
        isChanged = false;
    }

    /**
     * Get the retrieved {@code UserInputString}.
     *
     * Any method using this method should first check if {@code isChanged} is {@code true}. If {@code isChanged} is
     * true, an accompanying {@code UserInputString} exists and the relevant UI element should be updated. If
     * {@code isChanged} is {@code false}, there is no accompanying {@code UserInputString} and no UI elements should
     * be updated.
     *
     * @return {@code UserInputString} retrieved from {@code UserInputHistory}.
     */
    public UserInputString getUserInputString() {
        if (!isChanged) {
            // Should only be accessed when isChanged is true
            logger.severe("Tried to access InputHistory even though no history is available");
            assert false;
            return new UserInputString("");
        }
        return userInputString;
    }

    public boolean isChanged() {
        return isChanged;
    }
}

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

    public InputHistoryResult(UserInputString userInputString) {
        this.userInputString = userInputString;
        isChanged = true;
    }

    public InputHistoryResult() {
        this.userInputString = null;
        isChanged = false;
    }

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

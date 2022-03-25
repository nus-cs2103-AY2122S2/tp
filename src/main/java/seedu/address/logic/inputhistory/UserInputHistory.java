package seedu.address.logic.inputhistory;

import java.util.ArrayList;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;

/**
 * Contains past user history and also the logic of displaying previous user inputs
 */
public class UserInputHistory {
    private final ArrayList<UserInputString> inputHistory;
    private final Logger logger = LogsCenter.getLogger(getClass());
    private final boolean isLogging = false;
    private final UserInputIndex selectedIndex;

    /**
     * Creates a {@code UserInputHistory} object that holds past user inputs
     */
    public UserInputHistory() {
        inputHistory = new ArrayList<>();
        selectedIndex = new UserInputIndex();
    }

    /**
     * Add a new user input to the history.
     *
     * @param userInput The new user input.
     */
    public void addToHistory(String userInput) {
        selectedIndex.reset();
        UserInputString newUserInput = new UserInputString(userInput);
        inputHistory.add(newUserInput);
        getLog();
    }

    /**
     * Retrieve the previous user's input based on the stored index.
     * If there is no previous input, this will return a {@code InputHistoryResult} object with a boolean indicating
     * that no previous history is available.
     *
     * @return The previous user's input as a {@code InputHistoryResult} object.
     */
    public InputHistoryResult getPreviousInput() { // Up arrow
        boolean historyAvailable = selectedIndex.currentIndex() < inputHistory.size() - 1;
        if (!historyAvailable) {
            getLog();
            // Return a result with no changes
            return new InputHistoryResult();
        }
        assert selectedIndex.currentIndex() >= -1; // Sanity check
        selectedIndex.increment();
        int calculatedIndex = inputHistory.size() - 1 - selectedIndex.currentIndex();
        UserInputString selectedInput = inputHistory.get(calculatedIndex);
        getLog();
        return new InputHistoryResult(selectedInput);
    }

    /**
     * Retrieve the following user's input based on the stored index.
     * If there is no following input, this will return a {@code InputHistoryResult} object with a boolean indicating
     * that no following input is available.
     *
     * @return The following user's input as a {@code InputHistoryResult} object.
     */
    public InputHistoryResult getNextInput() { // Down arrow
        boolean forwardHistoryAvailable = selectedIndex.currentIndex() > 0;
        if (!forwardHistoryAvailable) {
            getLog();
            // Return a result with no changes
            return new InputHistoryResult();
        }
        assert selectedIndex.currentIndex() >= -1; // Sanity check
        selectedIndex.decrement();
        int calculatedIndex = inputHistory.size() - 1 - selectedIndex.currentIndex();
        UserInputString selectedInput = inputHistory.get(calculatedIndex);
        getLog();
        return new InputHistoryResult(selectedInput);
    }

    private void getLog() {
        if (isLogging) {
            logger.info(getCurrentStatus());
        }
    }

    private String getCurrentStatus() {
        return String.format("UserInputHistory - Index %d - HistoryLength %d", selectedIndex.currentIndex(),
                inputHistory.size());
    }
}

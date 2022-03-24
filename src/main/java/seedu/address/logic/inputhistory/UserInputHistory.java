package seedu.address.logic.inputhistory;

import java.util.ArrayList;

/**
 * Contains past user history and also the logic of displaying previous user inputs
 */
public class UserInputHistory {
    private final ArrayList<UserInputString> inputHistory;
    private UserInputIndex selectedIndex;


    /**
     * Creates a {@code UserInputHistory} object that holds past user inputs
     */
    public UserInputHistory() {
        inputHistory = new ArrayList<>();
    }

    /**
     * Add a new user input to the history
     *
     * @param userInput The new user input
     */
    public void addToHistory(String userInput) {
        selectedIndex.reset();
        UserInputString newUserInput = new UserInputString(userInput);
        inputHistory.add(newUserInput);
    }

    /**
     * Retrieve the previous user's input based on the stored index.
     * If there is no previous input, this will return a {@code InputHistoryResult} object with a boolean indicating
     * that no previous history is available.
     *
     * @return The previous user's input as a {@code InputHistoryResult} object.
     */
    public InputHistoryResult previousInput() { // Up arrow
        boolean historyAvailable = selectedIndex.currentIndex() < inputHistory.size();
        if (!historyAvailable) {
            // Return a result with no changes
            return new InputHistoryResult();
        }
        assert selectedIndex.currentIndex() > -1; // Sanity check
        selectedIndex.increment();
        UserInputString selectedInput = inputHistory.get(selectedIndex.currentIndex());
        return new InputHistoryResult(selectedInput);
    }

    /**
     * Retrieve the following user's input based on the stored index.
     * If there is no following input, this will return a {@code InputHistoryResult} object with a boolean indicating
     * that no following input is available.
     *
     * @return The following user's input as a {@code InputHistoryResult} object.
     */
    public InputHistoryResult nextInput() { // Down arrow
        boolean forwardHistoryAvailable = selectedIndex.currentIndex() > 0;
        if (!forwardHistoryAvailable) {
            // Return a result with no changes
            return new InputHistoryResult();
        }
        assert selectedIndex.currentIndex() > -1; // Sanity check
        selectedIndex.decrement();
        UserInputString selectedInput = inputHistory.get(selectedIndex.currentIndex());
        return new InputHistoryResult(selectedInput);
    }
}

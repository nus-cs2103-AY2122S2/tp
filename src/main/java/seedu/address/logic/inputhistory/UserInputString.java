package seedu.address.logic.inputhistory;

import static java.util.Objects.requireNonNull;

/**
 * Immutable String containing a previous user input
 */
public class UserInputString {
    private final String userInput;

    /**
     * Creates a {@code UserInputString} to hold a previous user input
     *
     * @param userInput The previous user input
     */
    public UserInputString(String userInput) {
        requireNonNull(userInput);
        this.userInput = userInput;
    }

    @Override
    public String toString() {
        return userInput;
    }

    public boolean isEmpty() {
        return userInput.isEmpty();
    }
}

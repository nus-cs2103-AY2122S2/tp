package seedu.address.history;

/**
 * API of the InputHistory component
 */
public interface InputHistory {
    /**
     * Stores the input entered by the user.
     * @param input the user input.
     */
    void storeInput(String input);

    /**
     * Returns a previous input by the user.
     */
    String getPreviousUserInput();

    /**
     * Returns a next input by the user.
     */
    String getNextUserInput();

    /**
     * Returns a boolean that indicates if a next input exists.
     */
    boolean canGetNextInput();

    /**
     * Returns a boolean that indicates if a prev input exists.
     */
    boolean canGetPrevInput();
}

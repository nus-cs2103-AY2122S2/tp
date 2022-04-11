package seedu.address.history;

import java.util.ArrayList;
import java.util.List;

/**
 * A class to record the commands entered by the user
 */
public class InputHistoryManager implements InputHistory {
    private static final int INIT_INPUT_INDEX_VALUE = 0;

    private List<String> previousInputs;
    private int indexPointer;

    /**
     * Constructs an {@code InputHistoryManager}
     */
    public InputHistoryManager() {
        this.previousInputs = new ArrayList<>();
        this.indexPointer = INIT_INPUT_INDEX_VALUE;
    }

    /**
     * Stores the most user input.
     * @param input the user input.
     */
    @Override
    public void storeInput(String input) {
        this.previousInputs.add(input);
        indexPointer = maxInputIndex() + 1;
    }

    /**
     * Returns the previous user input with respect to the pointer.
     */
    @Override
    public String getPreviousUserInput() {
        // Return the first input provided by the user at app launch.
        if (indexPointer == INIT_INPUT_INDEX_VALUE) {
            return previousInputs.get(0);
        }

        indexPointer--;
        return previousInputs.get(indexPointer);
    }

    /**
     * Returns the next user input with respect to the pointer.
     */
    @Override
    public String getNextUserInput() {
        // Return the most recent input provided by the user.
        if (indexPointer == maxInputIndex()) {
            return previousInputs.get(indexPointer);
        }

        indexPointer++;
        return previousInputs.get(indexPointer);
    }

    @Override
    public boolean canGetNextInput() {
        return indexPointer != previousInputs.size();
    }

    @Override
    public boolean canGetPrevInput() {
        return indexPointer != 0;
    }

    private int maxInputIndex() {
        return previousInputs.size() - 1;
    }

}

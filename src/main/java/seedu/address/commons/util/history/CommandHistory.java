package seedu.address.commons.util.history;

import java.util.ArrayList;
import java.util.List;

public class CommandHistory {

    private static final int LIMIT = 50;

    private List<String> history;
    private int pointer;

    /**
     * Construct a {@code CommandHistory}
     */
    public CommandHistory() {
        history = new ArrayList<>();
        pointer = 0;
    }
    /**
     * Save the @code text into command history.
     * @param text The command to be saved
     * */
    public void save(String text) {
        if (history.size() == LIMIT) {
            history.remove(0);
        }
        history.add(text);
        pointer = history.size();
    }
    /**
     * Navigate to previous command history by one step.
     * */
    public String previous() {
        if (history.size() == 0) {
            return "";
        } else if (pointer == 0) {
            return history.get(pointer);
        } else {
            pointer--;
            return history.get(pointer);
        }
    }
    /**
     * Navigate to next command history by one step.
     * */
    public String next() {
        if (pointer == history.size() - 1) {
            pointer++;
            return "";
        } else if (pointer > history.size() - 1) {
            return "";
        } else {
            pointer++;
            return history.get(pointer);
        }
    }
}

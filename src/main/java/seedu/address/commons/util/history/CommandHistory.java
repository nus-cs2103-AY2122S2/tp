package seedu.address.commons.util.history;

import java.util.ArrayList;
import java.util.List;

public class CommandHistory {

    private static final int LIMIT = 50;

    private List<String> history;
    private int pointer;

    public CommandHistory() {
        history = new ArrayList<>();
        pointer = 0;
    }

    public void save(String text) {
        if (history.size() == LIMIT) {
            history.remove(0);
        }
        history.add(text);
        pointer = history.size();
    }

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

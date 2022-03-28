package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;

public class UserInputHistory {

    private final List<String> list;
    private int index;

    /**
     * Creates a new TextFieldStorage.
     */
    public UserInputHistory() {
        this.list = new ArrayList<>();
        this.list.add("");
        this.index = 0;
    }

    /**
     * Adds user inputs to list.
     *
     * @param userInput String input from the user.
     */
    public void add(String userInput) {
        this.index = this.list.size() - 1;
        this.list.add(this.index, userInput);
        this.index++;
    }

    /**
     * Returns stored user input from list at current index.
     */
    public String get() {
        return this.list.get(this.index);
    }

    /**
     * Decrements current index. Does nothing if index is at 0.
     */
    public void up() {
        if (this.index > 0) {
            this.index--;
        }
    }

    /**
     * Increments current index. Does nothing if index is at max list size - 1.
     */
    public void down() {
        if (this.index < this.list.size() - 1) {
            this.index++;
        }
    }
}

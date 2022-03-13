package seedu.address.storage;

import java.util.ArrayList;

import seedu.address.commons.exceptions.IllegalValueException;

/**
 * A class that stores the history of entered commands.
 */
public class CommandStorage {

    private ArrayList<String> history;
    private int currentCommandNum = -1;

    public CommandStorage() {
        history = new ArrayList<>();
    }

    /**
     * Adds an entered command into the saved history.
     * @param command The command input entered by the user.
     */
    public void addCommand(String command) {
        history.add(command);
        currentCommandNum = history.size() - 1;
    }

    /**
     * Gets the previous command, depending on the current state.
     * @return The right command in history.
     */
    public String getPreviousCommand() {
        if (currentCommandNum == -1) { // nothing typed
            return "";
        }
        if (currentCommandNum == 0) { // already at the oldest String
            return history.get(0);
        }
        currentCommandNum--;
        return history.get(currentCommandNum);
    }
    /**
     * Gets the next command, depending on the current state.
     * @return The right command in history.
     */
    public String getNextCommand() {
        if (currentCommandNum == -1) { // nothing typed
            return "";
        }
        if (currentCommandNum == history.size() - 1) { // already at the newest String
            return history.get(currentCommandNum);
        }
        currentCommandNum++;
        return history.get(currentCommandNum);
    }

    /**
     * Retrieves the right command given the command number in history.
     *
     * @param commandNum The position of the command to retrieve.
     * @return The command at that position.
     * @throws IllegalValueException If the command number is invalid.
     */
    public String getCommand(int commandNum) throws IllegalValueException {
        if (commandNum < 0 || commandNum >= history.size()) {
            throw new IllegalValueException("Invalid command number!");
        }
        return history.get(commandNum);
    }

    /**
     * Retrieves the command at a particular point in history.
     *
     * @return The command at this point.
     */
    public String getCurrentCommand() {
        return history.get(currentCommandNum);
    }
}

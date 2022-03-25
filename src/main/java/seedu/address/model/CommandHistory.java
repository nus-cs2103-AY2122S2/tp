package seedu.address.model;

import java.util.ArrayList;

/**
 * Stores a list of previously executed commands.
 */
public class CommandHistory {
    private final ArrayList<String> commands = new ArrayList<>();

    public CommandHistory() {}

    /**
     * Returns true if command history does not contain any commands.
     * @return true if command history does not contain any commands
     */
    public boolean isEmpty() {
        return commands.isEmpty();
    }

    /**
     * Adds the specified command to command history.
     * @param str command to be added to command history
     */
    public void addToHistory(String str) {
        commands.add(str);
    }

    /**
     * Returns the latest command used and removes it from history.
     */
    public String popPreviousCommand() {
        int previousCommandIndex = commands.size() - 1;
        String previousCommand = commands.remove(previousCommandIndex);
        return previousCommand;
    }

    /**
     * Clears the command history.
     */
    public void clearHistory() {
        commands.clear();
    }

    /**
     * Displays the commands in command history as a String.
     * @return String representing the commands in command history
     */
    public String display() {
        StringBuilder sb = new StringBuilder();
        int index = 0;
        for (String command : commands) {
            index++;
            sb.append(index + ". ");
            sb.append(command);
            sb.append("\n");
        }
        return sb.toString();
    }
}

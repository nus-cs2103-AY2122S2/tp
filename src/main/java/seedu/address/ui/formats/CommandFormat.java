package seedu.address.ui.formats;

/**
 * Format for populating command list table
 */
public class CommandFormat {
    private String commandType;
    private String commandFormat;

    /**
     * constructs an object to represent a type of command and its format
     *
     * @param type type of command
     * @param format format of command
     */
    public CommandFormat(String type, String format) {
        commandType = type;
        commandFormat = format;
    }

    /**
     * Returns command type
     */
    public String getCommandType() {
        return commandType;
    }

    /**
     * return command format
     */
    public String getCommandFormat() {
        return commandFormat;
    }
}

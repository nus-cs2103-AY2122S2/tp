package seedu.address.ui.formats;

/**
 * Format for populating command list table
 */
public class CommandFormat {
    private String commandType;
    private String commandFormat;

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

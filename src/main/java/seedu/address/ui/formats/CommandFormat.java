package seedu.address.ui.formats;

public class CommandFormat {
    private String commandType;
    private String commandFormat;

    public CommandFormat(String type, String format) {
        commandType = type;
        commandFormat = format;
    }

    public String getCommandType() {
        return commandType;
    }

    public String getCommandFormat() {
        return commandFormat;
    }
}

package seedu.address.model.commandhistory;

import static java.util.Objects.requireNonNull;

import seedu.address.model.commandhistory.exceptions.HistoryDoesNotExistException;

public class CommandHistoryEntry {

    private static final CommandHistoryEntry EMPTY_COMMAND_HISTORY = new CommandHistoryEntrySentinel();

    private final String commandText;

    // hidden default constructor
    private CommandHistoryEntry() {
        commandText = "";
    }

    /**
     * Creates a command history entry. Encapsulates the information of a past command made by the user.
     *
     * @param commandText the raw input provided by the user.
     */
    public CommandHistoryEntry(String commandText) {
        requireNonNull(commandText);
        this.commandText = commandText;
    }

    public static CommandHistoryEntry getEmptyHistory() {
        return EMPTY_COMMAND_HISTORY;
    }

    public boolean exists() {
        return true;
    }

    public String getCommandText() {
        return this.commandText;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CommandHistoryEntry // instanceof handles nulls
                && commandText.equals(((CommandHistoryEntry) other).commandText));
    }

    private static class CommandHistoryEntrySentinel extends CommandHistoryEntry {

        @Override
        public boolean exists() {
            return false;
        }

        @Override
        public String getCommandText() {
            throw new HistoryDoesNotExistException();
        }

        @Override
        public boolean equals(Object other) {
            return other instanceof CommandHistoryEntrySentinel;
        }
    }
}

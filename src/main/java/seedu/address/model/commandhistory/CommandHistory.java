package seedu.address.model.commandhistory;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CommandHistory implements Iterable<CommandHistoryEntry> {

    private final ObservableList<CommandHistoryEntry> internalList = FXCollections.observableArrayList();

    /**
     * Caches the command into the history for future retrieval.
     * @param commandText the command text to be cached.
     */
    public void cacheCommand(String commandText) {
        requireNonNull(commandText);

        if (commandText.isBlank()) {
            return;
        }

        this.internalList.add(new CommandHistoryEntry(commandText));
    }

    /**
     * Retrieves the command at (size - offset) position.
     * If offset is out of bounds, returns an empty history entry.
     *
     * @param offset the number of commands to step back from
     * @return the resulting entry
     */
    public CommandHistoryEntry retrieveCommand(int offset) {
        if (offset > internalList.size() || offset <= 0) {
            return CommandHistoryEntry.getEmptyHistory();
        }
        return internalList.get(internalList.size() - offset);
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<CommandHistoryEntry> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CommandHistory // instanceof handles nulls
                && internalList.equals(((CommandHistory) other).internalList));
    }
}

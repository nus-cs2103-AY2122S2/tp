package seedu.address.model.strategy;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.strategy.exceptions.DuplicatePlayerException;
import seedu.address.model.strategy.exceptions.PlayerNotFoundException;

/**
 * A list of players that enforces uniqueness between its elements and does not allow nulls.
 *
 * Supports a minimal set of list operations.
 */
public class UniquePlayerList implements Iterable<Player> {
    private final ObservableList<Player> internalList = FXCollections.observableArrayList();
    private final ObservableList<Player> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent player as the given argument.
     */
    public boolean contains(Player toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a player to the list.
     * The player must not already exist in the list.
     */
    public void add(Player toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePlayerException();
        }
        internalList.add(toAdd);
    }

    /**
     * Removes the equivalent player from the list.
     * The player must exist in the list.
     */
    public void remove(Player toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PlayerNotFoundException();
        }
    }

    /**
     * Replaces the player {@code target} in the list with {@code editedPlayer}.
     * {@code target} must exist in the list.
     */
    public void setPlayer(Player target, Player editedPlayer) {
        requireNonNull(target);
        requireNonNull(editedPlayer);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PlayerNotFoundException();
        }

        internalList.set(index, editedPlayer);
    }

    /**
     * Replaces the contents of this list with {@code players}.
     * {@code players} must not contain duplicate players.
     */
    public void setPlayers(List<Player> players) {
        requireAllNonNull(players);
        if (!playersAreUnique(players)) {
            throw new DuplicatePlayerException();
        }

        internalList.setAll(players);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Player> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Player> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniquePlayerList // instanceof handles nulls
                && internalList.equals(((UniquePlayerList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code players} contains only unique players.
     */
    private boolean playersAreUnique(List<Player> players) {
        for (int i = 0; i < players.size() - 1; i++) {
            for (int j = i + 1; j < players.size(); j++) {
                if (players.get(i).equals(players.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}

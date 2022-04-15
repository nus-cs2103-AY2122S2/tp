package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.strategy.Player;
import seedu.address.model.strategy.UniquePlayerList;

/**
 * Wraps all data at the strategy-board level
 * Duplicates are not allowed (by .equals comparison)
 */
public class StrategyBoard implements ReadOnlyStrategyBoard {
    private final UniquePlayerList players;
    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        players = new UniquePlayerList();
    }

    public StrategyBoard() {}

    /**
     * Creates an StrategyBoard using the Players in the {@code toBeCopied}
     */
    public StrategyBoard(ReadOnlyStrategyBoard toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the player list with {@code players}.
     */
    public void setPlayers(List<Player> players) {
        this.players.setPlayers(players);
    }

    /**
     * Resets the existing data of this {@code StrategyBoard} with {@code newData}.
     */
    public void resetData(ReadOnlyStrategyBoard newData) {
        requireNonNull(newData);

        setPlayers(newData.getPlayerList());
    }

    //// player-level operations

    /**
     * Returns true if a player with the same identity as {@code player} exists in the address book.
     */
    public boolean hasPlayer(Player player) {
        requireNonNull(player);
        return players.contains(player);
    }

    /**
     * Adds a player to the Strategy Board.
     * The player must not already exist in the strategy board.
     */
    public void addPlayer(Player t) {
        players.add(t);
    }

    /**
     * Removes {@code key} from this {@code StrategyBoard}.
     * {@code key} must exist in the strategy board.
     */
    public void removePlayer(Player key) {
        players.remove(key);
    }

    public void setPlayer(Player target, Player editedPlayer) {
        requireNonNull(editedPlayer);

        players.setPlayer(target, editedPlayer);
    }
    //// util methods

    @Override
    public String toString() {
        return players.asUnmodifiableObservableList().size() + " players";
    }

    @Override
    public ObservableList<Player> getPlayerList() {
        return players.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StrategyBoard // instanceof handles nulls
                && players.equals(((StrategyBoard) other).players));
    }

    @Override
    public int hashCode() {
        return players.hashCode();
    }


}

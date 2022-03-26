package seedu.address.model.lineup;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;

/**
 * Represents a list of unique Teams
 */
public class UniqueLineupList {
    private final List<Lineup> list;

    /**
     * Constructs a {@code UniqueLineupList}
     */
    public UniqueLineupList() {
        this.list = new ArrayList<Lineup>();
    }

    /**
     * Adds a lineup to the list
     *
     * @param lineup The lineup to be added
     */
    public void addLineupToList(Lineup lineup) {
        requireNonNull(lineup);
        this.list.add(lineup);
    }

    /**
     * Deletes a lineup from the list
     *
     * @param lineup The lineup to be deleted
     */
    public void deleteLineupFromList(Lineup lineup) {
        requireNonNull(lineup);
        this.list.remove(lineup);
    }

    /**
     * Deletes a lineup from the list using lineup name
     *
     * @param lineupName The name of the lineup to be deleted
     */
    public void deleteLineupNameFromList(LineupName lineupName) {
        requireNonNull(lineupName);
        for (Lineup lineup : list) {
            if (lineup.sameLineupName(lineupName)) {
                this.list.remove(lineup);
            }
        }
    }

    /**
     * Checks for the list contains a lineup
     *
     * @param lineup The lineup to be checked
     * @return Boolean represents the existence of the lineup
     */
    public boolean containsLineup(Lineup lineup) {
        requireNonNull(lineup);
        return this.list.contains(lineup);
    }

    /**
     * Checks for any lineup in the UniqueLineupList has the same lineupName
     *
     * @param lineupName The lineupName to check
     * @return Boolean represents the existence of the lineup name
     */
    public boolean containsLineupName(LineupName lineupName) {
        requireNonNull(lineupName);
        for (Lineup lineup : list) {
            if (lineup.sameLineupName(lineupName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Getter of lineup using LineupName
     *
     * @param lineupName The name of the target lineup
     * @return The target lineup which has the same LineupName
     */
    public Lineup getLineup(LineupName lineupName) {
        if (!containsLineupName(lineupName)) {
            return null;
        } else {
            for (Lineup lineup: list) {
                if (lineup.sameLineupName(lineupName)) {
                    return lineup;
                }
            }
        }

        return null;
    }

    /**
     * Replaces a old lineup by a new lineup
     * @param target The old lineup to be replaced
     * @param editedLineup The new lineup to replace the old lineup
     */
    public void replaceLineup(Lineup target, Lineup editedLineup) {
        this.deleteLineupFromList(target);
        this.addLineupToList(editedLineup);
    }

    /**
     * Puts a player into a Lineup in the UniqueLineupList
     *
     * @param player The player to put
     * @param lineup The lineup to put at
     */
    public void putPlayerToLineup(Person player, Lineup lineup) {
        requireNonNull(lineup);
        requireNonNull(player);
        if (containsLineup(lineup)) {
            lineup.addPlayer(player);
        }
    }

    /**
     * Deletes a player from a Lineup in the UniqueLineupList
     *
     * @param player The player to delete
     * @param lineup The Lineup to delete from
     */
    public void deletePlayerFromLineup(Person player, Lineup lineup) {
        requireNonNull(lineup);
        requireNonNull(player);
        if (containsLineup(lineup)) {
            lineup.removePlayer(player);
        }
    }

    /**
     * Replaces an old player in a lineup by a new player
     *
     * @param removedPlayer The player to be removed from the lineup
     * @param addedPlayer The player to be added into the lineup
     * @param lineup The target lineup
     */
    public void replacePlayerInLineup(Person removedPlayer, Person addedPlayer, Lineup lineup) {
        if (containsLineup(lineup)) {
            deletePlayerFromLineup(removedPlayer, lineup);
            putPlayerToLineup(addedPlayer, lineup);
        }
    }

    /**
     * Deletes a player from all lineups
     *
     * @param removedPlayer The player to be deleted
     */
    public void deletePlayerFromALlLineups(Person removedPlayer) {
        for (Lineup lineup : list) {
            deletePlayerFromLineup(removedPlayer, lineup);
        }
    }

    /**
     * Replaces a player from all lineups
     *
     * @param removedPlayer The player to be replaced
     * @param addedPlayer The new player to replace the old player
     */
    public void replacePlayerInAllLineups(Person removedPlayer, Person addedPlayer) {
        for (Lineup lineup : list) {
            replacePlayerInLineup(removedPlayer, addedPlayer, lineup);
        }
    }

    public List<Lineup> getList() {
        return list;
    }

    public void setLineups(List<Lineup> lineups) {
        for (Lineup lineup : lineups) {
            addLineupToList(lineup);
        }
    }
}


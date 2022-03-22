package seedu.address.model;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.lineup.Lineup;
import seedu.address.model.lineup.LineupName;
import seedu.address.model.person.Person;


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

    public void addLineupToList(Lineup lineup) {
        this.list.add(lineup);
    }

    public void deleteLineupFromList(Lineup lineup) {
        this.list.remove(lineup);
    }

    public boolean containsLineup(Lineup lineup) {
        return this.list.contains(lineup);
    }

    /**
     * Checks for any lineup in the UniqueLineupList has the same lineupName
     *
     * @param lineupName The lineupName to check
     * @return Boolean represents the existence of the lineup name
     */
    public boolean containsLineupName(LineupName lineupName) {
        for (Lineup lineup : list) {
            if (lineup.sameLineupName(lineupName)) {
                return true;
            }
        }
        return false;
    }

    public Lineup getLineup(LineupName lineupName) {
        if (! containsLineupName(lineupName)) {
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
        if (containsLineup(lineup)) {
            lineup.removePlayer(player);
        }
    }

    public void replacePlayerInLineup(Person removedPlayer, Person addedPlayer, Lineup lineup) {
        if (containsLineup(lineup)) {
            deletePlayerFromLineup(removedPlayer, lineup);
            putPlayerToLineup(addedPlayer, lineup);
        }
    }

    public void deletePlayerFromALlLineups(Person removedPlayer) {
        for (Lineup lineup : list) {
            deletePlayerFromLineup(removedPlayer, lineup);
        }
    }

    public void replacePlayerInAllLineups(Person removedPlayer, Person addedPlayer) {
        for (Lineup lineup : list) {
            replacePlayerInLineup(removedPlayer, addedPlayer, lineup);
        }
    }

}


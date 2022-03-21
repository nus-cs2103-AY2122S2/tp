package seedu.address.model;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.lineup.Lineup;
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

}


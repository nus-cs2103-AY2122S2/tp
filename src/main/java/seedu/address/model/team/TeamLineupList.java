package seedu.address.model.team;

import javax.sound.sampled.Line;

import java.util.ArrayList;
import seedu.address.model.lineup.Lineup;
import seedu.address.model.person.Person;

public class TeamLineupList {
    ArrayList<Lineup> list;

    /**
     * Constructs a {@code TeamLineupList}
     */
    public TeamLineupList() {
        this.list = new ArrayList<Lineup>();
    }

    /**
     * Adds a lineup into TeamLineupList
     *
     * @param lineup The lineup to be added
     */
    public void addLineupToList(Lineup lineup) {
        this.list.add(lineup);
    }

    /**
     * Checks the existence of lineup
     *
     * @param lineup The lineup to check
     * @return Boolean represents the existence of lineup
     */
    public boolean containsLineup(Lineup lineup) {
        return this.list.contains(lineup);
    }

}

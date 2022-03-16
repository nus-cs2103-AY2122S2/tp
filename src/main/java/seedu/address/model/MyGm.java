package seedu.address.model;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.team.Team;

/**
 * Represents the root class of MyGM.
 */
public class MyGm {
    private final UniquePlayerList players;
    private final List<Team> teams;

    /**
     * Creates a new empty MyGm class.
     */
    public MyGm() {
        this.players = new UniquePlayerList();
        this.teams = new ArrayList<Team>();
    }

    // add more methods here to facilitate update of players and teams
}

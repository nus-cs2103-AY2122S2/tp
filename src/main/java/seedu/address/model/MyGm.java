package seedu.address.model;


import seedu.address.model.lineup.Lineup;
import seedu.address.model.person.Person;
import seedu.address.model.team.Team;

/**
 * Represents the root class of MyGM.
 */
public class MyGm {
    private final UniquePlayerList players;
    private final UniqueTeamList teams;

    /**
     * Creates a new empty MyGm class.
     */
    public MyGm() {
        this.players = new UniquePlayerList();
        this.teams = new UniqueTeamList();
    }

    /**
     * Puts a player into a team
     *
     * @param player Player to put
     * @param team Team to put
     */
    public void putPlayerToTeam(Person player, Team team) {
        this.players.addPersonToTeam(player, team);
    }

    /**
     * Puts a player into a lineup
     *
     * @param player Player to put
     * @param lineup Lineup to put
     */
    public void putPlayerToLineup(Person player, Lineup lineup) {
        this.players.addPersonToLineup(player, lineup);
    }

    // add more methods here to facilitate update of players and teams
}

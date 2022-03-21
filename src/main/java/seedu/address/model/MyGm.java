package seedu.address.model;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.lineup.Lineup;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.team.Team;
import seedu.address.model.team.TeamName;

/**
 * Represents the root class of MyGM.
 */
public class MyGm {
    private final UniquePlayerList players;
    //private final UniqueTeamList teams;
    private final List<Team> tempteams; // NOTE: TEMPORARY FOR TX'S FUNCTION

    /**
     * Creates a new empty MyGm class.
     */
    public MyGm() {
        this.players = new UniquePlayerList();
        //this.teams = new UniqueTeamList();
        this.tempteams = new ArrayList<>();
    }

    //// list overwrite operations
    public void setPersons(List<Person> persons) {
        //this.players.setPersons(persons);
    }

    public boolean isFull() {
        return players.isFull();
    }

    public void addPerson(Person player) {
        players.addPerson(player);
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

    public boolean hasPerson(Person player) {
        return players.containsPerson(player);
    }

    public boolean hasJerseyNumber(Person player) {
        return players.containsJerseyNumber(player.getJerseyNumber());
    }

    public Person getPerson(Name targetPersonName) {
        return players.getPerson(targetPersonName);
    }

    public String getAvailableJerseyNumber() {
        return players.getAvailableJerseyNumber();
    }

    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        players.setPerson(target, editedPerson);
    }

    /**
     * Checks if the system contains a player with the given {@code name}.
     *
     * @param name The player name to be checked.
     * @return True if the system contains a player with the given name.
     */
    public boolean containsPlayer(Name name) {
        return this.players.containsName(name);

    }

    // add more methods here to facilitate update of players and teams

    /**
     * Deletes a player.
     */
    public boolean deletePlayer(Name player) {
        if (!containsPlayer(player)) {
            return false;
        } else {
            Person currPlayer = this.players.getPerson(player);
            if (this.players.getPersonTeam(currPlayer) != null) {
                Team currTeam = this.players.getPersonTeam(currPlayer);

                this.players.removePersonFromTeam(currPlayer, currTeam);
                currTeam.removePlayer(currPlayer);
            }
            this.players.removePerson(currPlayer);
            return true;
        }
    }

    /**
     * Deletes a lineup from a team.
     */
    public boolean deleteLineupFromTeam(String lineup, String team) {
        for (Team tempTeam : tempteams) {
            if (tempTeam.getTeamName().equals(new TeamName(team))) {
                tempTeam.removeLineup(lineup);
                return true;
            }
        }
        // should also remove all players in the team
        return false;
    }

    /**
     * Deletes a team.
     */
    public boolean deleteTeamFromPool(String team) {
        return true;
    }
}

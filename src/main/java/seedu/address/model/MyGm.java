package seedu.address.model;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.lineup.Lineup;
import seedu.address.model.lineup.LineupName;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.team.Team;
import seedu.address.model.team.TeamName;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

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

    //final
    public boolean hasPerson(Name targetName) {
        return players.containsName(targetName);
    }

    public Person getPerson(Name targetPersonName) {
        return players.getPerson(targetPersonName);
    }

    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        players.setPerson(target, editedPerson);

    boolean containsPlayer(String name) {
        return this.players.containsName(new Name(name));

    }

    // add more methods here to facilitate update of players and teams

    /**
     * Deletes a player from a lineup.
     */
    public boolean deletePlayerFromLineup(String player, String lineup, String team) {
        if (!containsPlayer(player)) {
            return false;
        } else if (!(new TeamName(team)).equals(this.players.getPersonTeam(
                this.players.getPerson(new Name(player))).getTeamName())) {
            return false;
        } else {
            Person currPlayer = this.players.getPerson(new Name(player));
            Lineup currLineup = null;
            boolean containsLineup = false;
            for (Lineup tempLineup : this.players.getPersonLineups(this.players.getPerson(new Name(player)))) {
                if (tempLineup.getLineupName().equals(new LineupName(lineup))) {
                    currLineup = tempLineup;
                    containsLineup = true;
                    break;
                }
            }

            if (!containsLineup) {
                return false;
            }

            this.players.removePersonFromLineup(currPlayer, currLineup);
            currLineup.removePlayer(currPlayer);
            return true;
        }
    }

    /**
     * Deletes a player from a team.
     */
    public boolean deletePlayerFromTeam(String player, String team) {
        if (!containsPlayer(player)) {
            return false;
        } else if (!(new TeamName(team)).equals(this.players.getPersonTeam(
                this.players.getPerson(new Name(player))).getTeamName())) {
            return false;
        } else {
            Person currPlayer = this.players.getPerson(new Name(player));
            Team currTeam = this.players.getPersonTeam(currPlayer);

            this.players.removePersonFromTeam(currPlayer, currTeam);
            currTeam.removePlayer(currPlayer);
            return true;
        }
    }

    /**
     * Deletes a player.
     */
    public boolean deletePlayerFromPool(String player) {
        if (!containsPlayer(player)) {
            return false;
        } else {
            Person currPlayer = this.players.getPerson(new Name(player));
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
        for (Team tempTeam : this.teams) {
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
        return true; // assume only one team, cannot delete
    }

}

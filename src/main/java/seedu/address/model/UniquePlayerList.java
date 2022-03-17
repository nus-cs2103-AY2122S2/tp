package seedu.address.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import seedu.address.model.lineup.Lineup;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.team.Team;

/**
 * Represents all plauers contained in the system..
 * Contains the set of all players, a map from players to their teams,
 * and a map from players to their lineups.
 */
public class UniquePlayerList {
    private final Map<Name, Person> nameToPersonMap;
    private final Map<Person, Team> personToTeamMap;
    private final Map<Person, List<Lineup>> personToLineupMap;

    /**
     * Cretes a new MyGM object.
     */
    public UniquePlayerList() {
        this.nameToPersonMap = new HashMap<Name, Person>();
        this.personToTeamMap = new HashMap<Person, Team>();
        this.personToLineupMap = new HashMap<Person, List<Lineup>>();
    }

    /**
     * Checks if the given name is in the player pool.
     *
     * @param name
     * @return
     */
    public boolean containsName(Name name) {
        return this.nameToPersonMap.containsKey(name);
    }

    /**
     * Checks if the given person exists.
     */
    public boolean containsPerson(Person person) {
        return containsName(person.getName());
    }

    /**
     * Adds a person to the system.
     */
    public void addPerson(Person person) {
        Name name = person.getName();
        if (!this.nameToPersonMap.containsKey(name)) {
            this.nameToPersonMap.put(name, person);
        }
    }

    /**
     * Gets a person.
     */
    public Person getPerson(Name name) {
        return this.nameToPersonMap.getOrDefault(name, null);
    }

    /**
     * Gets a person's team.
     */
    public Team getPersonTeam(Person person) {
        return this.personToTeamMap.getOrDefault(person, null);
    }

    /**
     * Gets a person's lineups.
     */
    public List<Lineup> getPersonLineups(Person person) {
        return this.personToLineupMap.getOrDefault(person, null);
    }

    /**
     * Adds a person to team mapping to the system.
     */
    public void addPersonToTeam(Person person, Team team) {
        this.personToTeamMap.put(person, team);
    }

    /**
     * Adds a person to lineup mapping to the system.
     */
    public void addPersonToLineup(Person person, Lineup lineup) {
        if (this.personToLineupMap.containsKey(person)) {
            this.personToLineupMap.get(person).add(lineup);
        } else {
            this.personToLineupMap.put(person, new ArrayList<Lineup>());
            this.personToLineupMap.get(person).add(lineup);
        }
    }

    /**
     * Removes a person from the system.
     */
    public void removePerson(Person person) {
        if (!this.nameToPersonMap.containsKey(person.getName())) {
            this.nameToPersonMap.remove(person);
        }
    }

    /**
     * Remoes a person to team mapping from the system.
     */
    public void removePersonFromTeam(Person person, Team team) {
        if (this.personToTeamMap.get(person) == team) {
            // not .equal() here because they are supposed to be the same team
            this.personToTeamMap.remove(person, team);
        }
    }

    /**
     * Removes a person to lineup mapping from the system.
     */
    public void removePersonFromLineup(Person person, Lineup lineup) {
        if (this.personToLineupMap.get(person).contains(lineup)) {
            this.personToLineupMap.get(person).remove(lineup);
        }
    }

    public Person getPerson(Name targetPersonName) {
        return nameToPersonMap.get(targetPersonName);
    }

    public void setPerson(Person target, Person editedPerson) {
        Team targetTeam = personToTeamMap.get(target);
        List<Lineup> targetLineups = personToLineupMap.get(target);

        editPersonInTeam(targetTeam, target, editedPerson);
        editPersonInLineUp(targetLineups, target, editedPerson);
        updateMaps(target, editedPerson, targetTeam, targetLineups);
    }

    public void editPersonInTeam(Team targetTeam, Person target, Person editedPerson) {
        targetTeam.deletePersonFromTeam(target);
        targetTeam.putPersonToTeam(editedPerson);
    }

    public void editPersonInLineUp(List<Lineup> targetLineups, Person target, Person editedPerson) {
        for (Lineup lineup : targetLineups) {
            lineup.removePlayer(target);
            lineup.addPlayer(editedPerson);
        }
    }

    public void updateMaps(Person target, Person editedPerson, Team targetTeam, List<Lineup> targetLineups) {
        removePerson(target);
        addPerson(editedPerson);
        removePersonFromTeam(target);
        addPersonToTeam(editedPerson, targetTeam);
        removePersonFromLineUp(target);
        addPersonToLineup(editedPerson, targetLineups);
    }

    public void removePersonFromTeam(Person person) {
        if (personToTeamMap.containsKey(person)) {
            personToTeamMap.remove(person);
        }
    }

    public void removePersonFromLineUp(Person person) {
        if (personToLineupMap.containsKey(person)) {
            personToLineupMap.remove(person);
        }
    }

    public void addPersonToLineup(Person person, List<Lineup> lineups) {
        personToLineupMap.put(person, lineups);
    }
}

package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.model.person.Person;

/**
 * Represents the root class of MyGM.
 * Contains the set of all players, a map from players to their teams,
 * and a map from players to their lineups.
 */
public class MyGM {
    private final Set<Person> persons;
    private final Map<Person, Team> personToTeamMap;
    private final Map<Person, Lineup> personToLineupMap;

    public
}

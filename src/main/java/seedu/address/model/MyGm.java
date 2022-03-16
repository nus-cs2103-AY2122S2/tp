package seedu.address.model;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.team.Team;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

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

    public boolean hasPerson(Name targetName) {
        return players.containsName(targetName);
    }

    public Person getPerson(Name targetPersonName) {
        return players.getPerson(targetPersonName);
    }

    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        players.setPerson(target, editedPerson);
    }

    // add more methods here to facilitate update of players and teams

}

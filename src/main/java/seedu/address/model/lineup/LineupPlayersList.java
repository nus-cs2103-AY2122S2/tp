package seedu.address.model.lineup;

import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

import seedu.address.model.person.Person;

/**
 * Represents a list contains all the players in the lineup
 */
public class LineupPlayersList {
    private ArrayList<Person> playersList;

    /**
     * Constructs a {@code LineupPlayersList}
     */
    public LineupPlayersList() {
        this.playersList = new ArrayList<>();
    }

    public LineupPlayersList(ArrayList<Person> players) {
        this.playersList = players;
    }

    public int size() {
        return playersList.size();
    }

    public Person get(int index) {
        return playersList.get(index);
    }

    public void add(Person player) {
        playersList.add(player);
    }

    public void remove(Person player) {
        playersList.remove(player);
    }

    public ArrayList<Person> getPlayersList() {
        return this.playersList;
    }

    /**
     * Updates the lineupName for every player in the lineup
     *
     * @param oldName The old lineup name
     * @param newName The new lineup name
     */
    public void replaceLineup(LineupName oldName, LineupName newName) {
        for (Person person : playersList) {
            person.replaceLineupName(oldName, newName);
        }
    }

    public boolean hasPlayer(Person player) {
        return playersList.contains(player);
    }

    @Override
    public String toString() {
        return (String) playersList.stream()
                .map(Person::getName)
                .map(Objects::toString)
                .collect(Collectors.joining(", "));
    }
}

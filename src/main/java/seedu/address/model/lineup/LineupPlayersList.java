package seedu.address.model.lineup;

import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

import seedu.address.model.person.Person;

public class LineupPlayersList {
    private ArrayList<Person> playersList;

    public LineupPlayersList() {
        this.playersList = new ArrayList<>();
    }

    public LineupPlayersList(ArrayList<Person> players) {
        this.playersList = players;
    }

    public Person get(int index) {
        return playersList.get(index);
    }

    public void add(Person player) {
        playersList.add(player);
    }

    public void remove(int index) {
        playersList.remove(index);
    }

    @Override
    public String toString() {
        return (String) playersList.stream()
                .map(Person::getName)
                .map(Objects::toString)
                .collect(Collectors.joining(", "));
    }
}

package seedu.address.model.lineup;

import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

import seedu.address.model.person.Person;

public class LineupList {
    private ArrayList<Person> players;

    public LineupList() {
        this.players = new ArrayList<>();
    }

    public LineupList(ArrayList<Person> players) {
        this.players = players;
    }

    public Person get(int index) {
        return players.get(index);
    }

    public void add(Person player) {
        players.add(player);
    }

    public void remove(int index) {
        players.remove(index);
    }

    @Override
    public String toString() {
        return (String) players.stream()
                .map(Person::getName)
                .map(Objects::toString)
                .collect(Collectors.joining(", "));
    }
}

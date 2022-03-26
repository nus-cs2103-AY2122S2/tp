package seedu.address.model.lineup;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Objects;

import seedu.address.model.person.Person;

/**
 * Represents a lineup in MyGM.
 * Guarantees: lineupName is present and not null, up to 5 players per lineup.
 */
public class Lineup {

    private static final int MAXIMUM_CAPACITY = 5;

    private final LineupName lineupName;
    private final LineupPlayersList playersList;

    /**
     * Constructs a {@code LIneup}
     *
     * @param lineupName The name of the lineup
     */
    public Lineup(LineupName lineupName) {
        requireAllNonNull(lineupName);
        this.lineupName = lineupName;
        this.playersList = new LineupPlayersList();
    }

    /**
     * lineupName must be present and not null.
     */
    public Lineup(LineupName lineupName, LineupPlayersList playersList) {
        requireAllNonNull(lineupName);
        this.lineupName = lineupName;
        this.playersList = playersList;
    }

    public boolean hasPlayer(Person person) {
        return playersList.hasPlayer(person);
    }

    public LineupName getLineupName() {
        return lineupName;
    }

    public LineupPlayersList getPlayers() {
        return playersList;
    }

    /**
     * Get the player at specific index of the lineupList.
     */
    public Person getPlayer(int index) {
        return playersList.get(index);
    }

    public ArrayList<Person> getPlayerList() {
        return playersList.getPlayersList();
    }

    public void addPlayer(Person player) {
        playersList.add(player);
    }

    public void removePlayer(Person player) {
        playersList.remove(player);
    }

    public boolean reachMaximumCapacity() {
        return this.playersList.size() == MAXIMUM_CAPACITY;
    }

    public boolean sameLineupName(LineupName otherLineupName) {
        return this.lineupName.equals(otherLineupName);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(lineupName, playersList);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getLineupName())
                .append("; Players: ")
                .append(getPlayers());

        return builder.toString();
    }
}

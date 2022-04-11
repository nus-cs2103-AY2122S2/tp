package seedu.address.model.lineup;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.person.Name;
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
     * Constructs a {@code Lineup}
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

    public boolean containsPlayer(Name name) {
        return playersList.hasPlayer(name);
    }

    /**
     * Checks two lineup are same
     */
    public boolean isSameLineup(Lineup otherLineup) {
        if (otherLineup == this) {
            return true;
        }
        return otherLineup != null
                && sameLineupName(otherLineup.lineupName);
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

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Lineup)) {
            return false;
        }

        Lineup otherLineup = (Lineup) other;
        return sameLineupName(otherLineup.getLineupName());
    }
}

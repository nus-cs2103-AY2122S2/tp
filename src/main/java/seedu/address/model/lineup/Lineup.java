package seedu.address.model.lineup;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.person.Person;

/**
 * Represents a lineup in a team.
 * Guarantees: lineupName is present and not null, up to 5 players per lineup.
 */
public class Lineup {

    private final LineupName lineupName;
    private final LineupList players;

    /**
     * lineupName must be present and not null.
     */
    public Lineup(LineupName lineupName, LineupList players) {
        requireAllNonNull(lineupName);
        this.lineupName = lineupName;
        this.players = players;
    }

    public LineupName getLineupName() {
        return lineupName;
    }

    public LineupList getPlayers() {
        return players;
    }

    /**
     * Get the player at specific index of the lineupList.
     */
    public Person getPlayer(int index) {
        return players.get(index);
    }

    public void addPlayer(Person player) {
        players.add(player);
    }

    public void removePlayer(int index) {
        players.remove(index);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(lineupName, players);
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

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
    private final LineupPlayersList playersList;

    /**
     * lineupName must be present and not null.
     */
    public Lineup(LineupName lineupName, LineupPlayersList playersList) {
        requireAllNonNull(lineupName);
        this.lineupName = lineupName;
        this.playersList = playersList;
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

    public void addPlayer(Person player) {
        playersList.add(player);
    }

    public void removePlayer(int index) {
        playersList.remove(index);
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

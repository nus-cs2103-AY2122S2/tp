package seedu.address.model.lineup;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.LineupBuilder;
import seedu.address.testutil.PersonBuilder;

public class LineupTest {
    private static final Lineup VALID_LINEUP = new LineupBuilder().withLineupName("staring 5").build();
    private static final Lineup VALID_LINEUP_2 = new LineupBuilder().withLineupName("staring 5").build();
    private static final Lineup VALID_LINEUP_3 = new LineupBuilder().withLineupName("dummy").build();
    private static final Person VALID_PLAYER = new PersonBuilder().build();

    @Test
    public void isSameLineup() {
        //Same Lineup
        assertTrue(VALID_LINEUP.isSameLineup(VALID_LINEUP));

        //Same Lineup Name
        assertTrue(VALID_LINEUP.isSameLineup(VALID_LINEUP_2));

        //Different Lineup
        assertFalse(VALID_LINEUP.isSameLineup(VALID_LINEUP_3));

        //Same after adding player
        VALID_LINEUP_2.addPlayer(VALID_PLAYER);
        assertTrue(VALID_LINEUP.isSameLineup(VALID_LINEUP_2));

        //Same after deleting player
        VALID_LINEUP_2.removePlayer(VALID_PLAYER);
        assertTrue(VALID_LINEUP.isSameLineup(VALID_LINEUP_2));
    }

    @Test
    public void maxCapacity() {
        for (int i = 0; i < 5; i++) {
            VALID_LINEUP.addPlayer(new PersonBuilder().build());
        }
        assertTrue(VALID_LINEUP.reachMaximumCapacity());
    }
}

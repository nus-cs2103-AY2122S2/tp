package seedu.address.model.lineup;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class LineupPlayerListTest {
    private static final LineupPlayersList VALID_LIST = new LineupPlayersList();
    private static final Person VALID_PLAYER = new PersonBuilder().build();

    @Test
    public void hasPlayer() {
        assertFalse(VALID_LIST.hasPlayer(VALID_PLAYER));

        VALID_LIST.add(VALID_PLAYER);
        assertTrue(VALID_LIST.hasPlayer(VALID_PLAYER));
    }
}

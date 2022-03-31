package seedu.address.model.lineup;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.LineupBuilder;

public class UniqueLineupListTest {
    private static final UniqueLineupList VALID_LIST = new UniqueLineupList();
    private static final UniqueLineupList VALID_LIST_2 = new UniqueLineupList();
    private static final Lineup VALID_LINEUP = new LineupBuilder().build();
    private static final Lineup VALID_LINEUP_2 = new LineupBuilder().withLineupName("123").build();


    @Test
    public void sameUniqueLineupList() {
        //empty UniqueLineupList are same
        assertTrue(VALID_LIST.equals(VALID_LIST_2));

        VALID_LIST.addLineupToList(VALID_LINEUP);
        assertFalse(VALID_LIST.equals(VALID_LIST_2));

        VALID_LIST_2.addLineupToList(VALID_LINEUP);
        assertTrue(VALID_LIST.equals(VALID_LIST_2));

        VALID_LIST.replaceLineup(VALID_LINEUP, VALID_LINEUP_2);
        assertFalse(VALID_LIST.equals(VALID_LIST_2));

        VALID_LIST_2.deleteLineupFromList(VALID_LINEUP);
        assertFalse(VALID_LIST.equals(VALID_LIST_2));
    }
}

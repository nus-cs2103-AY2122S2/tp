package seedu.address.model.job;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PositionTest {
    private static final Position POSITION_FULLTIME = new Position("ft");
    private static final Position POSITION_PARTTIME = new Position("pt");

    @Test
    public void isValidPosition() {
        // null position
        assertThrows(NullPointerException.class, () -> new Position(null));

        // invalid position
        assertThrows(IllegalArgumentException.class, () -> new Position("ftpt"));

        // valid position
        assertTrue(Position.validPosition("ft"));

        // valid position
        assertTrue(Position.validPosition("fT"));

        // valid position
        assertTrue(Position.validPosition("Ft"));

        // valid position
        assertTrue(Position.validPosition("FT"));

        // valid position
        assertTrue(Position.validPosition("pt"));

        // valid position
        assertTrue(Position.validPosition("pT"));

        // valid position
        assertTrue(Position.validPosition("Pt"));

        // valid position
        assertTrue(Position.validPosition("PT"));
    }

    @Test
    public void position_toString() {
        // full-time
        assertEquals(POSITION_FULLTIME.toString(), "full-time");

        // part-time
        assertEquals(POSITION_PARTTIME.toString(), "part-time");
    }
}

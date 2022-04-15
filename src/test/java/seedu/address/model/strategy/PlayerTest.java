package seedu.address.model.strategy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class PlayerTest {

    private static final String VALID_NAME_1 = "caPs";
    private static final String VALID_NAME_2 = "Jankos";
    private static final String INVALID_NAME = "1234567890"
            + "1234567890"
            + "1234567890"
            + "1234567890"
            + "1234567890"
            + "1";

    @Test
    public void constructor_nullName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Player(null));
    }

    @Test
    public void constructor_emptyName_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Player(""));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Player(INVALID_NAME));
    }

    @Test
    public void isValidPlayer_nullName_returnsFalse() {
        assertThrows(NullPointerException.class, () -> Player.isValidPlayer(null));
    }

    @Test
    public void isValidPlayer_validName_true() {
        assertTrue(Player.isValidPlayer(VALID_NAME_1));
    }

    @Test
    public void isValidPlayer_invalidName_false() {
        assertFalse(Player.isValidPlayer(INVALID_NAME));
    }

    @Test
    public void isValidPlayer_emptyName_false() {
        assertFalse(Player.isValidPlayer(""));
    }

    @Test
    public void getName_validName_returnsName() {
        Player player = new Player(VALID_NAME_1);
        assertEquals(player.getName(), VALID_NAME_1);
    }

    @Test
    public void getXCoord_validCoord_returnsXCoord() {
        Player player = new Player(VALID_NAME_1, 123, 456);
        assertEquals(player.getXCoord(), 123);
    }

    @Test
    public void getYCoord_validCoord_returnsYCoord() {
        Player player = new Player(VALID_NAME_1, 123, 456);
        assertEquals(player.getYCoord(), 456);
    }

    @Test
    public void equals() {
        Player player1 = new Player(VALID_NAME_1);
        Player player1Copy = new Player(VALID_NAME_1);

        Player player2 = new Player(VALID_NAME_2);
        Player player1Case = new Player("CaPs");

        //same object reference
        assertTrue(player1.equals(player1));

        //same contents
        assertTrue(player1.equals(player1Copy));

        //case sensitive
        assertFalse(player1.equals(player1Case));

        //null
        assertFalse(player1.equals(null));

        //different contents
        assertFalse(player1.equals(player2));
    }
}

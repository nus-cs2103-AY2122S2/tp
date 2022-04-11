package seedu.address.model.team;


import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TeamTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Team(null));
    }

    @Test
    public void constructor_invalidTeamName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Team(invalidName));
    }

    @Test
    public void isValidTeamName() {
        // null team name
        assertThrows(NullPointerException.class, () -> Team.isValidTeamName(null));

        //team name with special characters
        Assertions.assertFalse(Team.isValidTeamName("Math tE@M"));

        //team name of 20 characters
        Assertions.assertTrue(Team.isValidTeamName("Math Olympiad team 1"));

        //empty team name
        Assertions.assertFalse(Team.isValidTeamName(""));

        //team name greater than 20 characters (21 characters)
        Assertions.assertFalse(Team.isValidTeamName("Math Olympiad team 15"));
    }

}

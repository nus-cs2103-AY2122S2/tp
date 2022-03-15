package seedu.address.model.team;

import static seedu.address.testutil.Assert.assertThrows;

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
        assertThrows(NullPointerException.class, () -> Team.isValidteamName(null));
    }

}

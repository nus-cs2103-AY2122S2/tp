package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class GithubUsernameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new GithubUsername(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidGithub = "";
        assertThrows(IllegalArgumentException.class, () -> new GithubUsername(invalidGithub));
    }

    @Test
    public void isValidGithubUsername() {
        // null name
        assertThrows(NullPointerException.class, () -> GithubUsername.isValidGithubUsername(null));

        // invalid name
        assertFalse(GithubUsername.isValidGithubUsername("")); // empty string
        assertFalse(GithubUsername.isValidGithubUsername(" ")); // spaces only
        assertFalse(GithubUsername.isValidGithubUsername("^")); // only non-alphanumeric characters
        assertFalse(GithubUsername.isValidGithubUsername("peter*")); // contains non-alphanumeric characters
        assertFalse(GithubUsername.isValidGithubUsername("-peter")); //starts with a hyphen
        assertFalse(GithubUsername.isValidGithubUsername("peter-")); //ends with a hyphen
        assertFalse(GithubUsername.isValidGithubUsername("pe--ter")); //contains consecutive hyphens

        // valid name
        assertTrue(GithubUsername.isValidGithubUsername("peterjack")); // alphabets only
        assertTrue(GithubUsername.isValidGithubUsername("12345")); // numbers only
        assertTrue(GithubUsername.isValidGithubUsername("peterthe2nd")); // alphanumeric characters
        assertTrue(GithubUsername.isValidGithubUsername("CapitalTan")); // with capital letters
        assertTrue(GithubUsername.isValidGithubUsername("DavidRogerJacksonRayJr2nd")); // long names
    }
}

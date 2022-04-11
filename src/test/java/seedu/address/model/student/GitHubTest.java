package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class GitHubTest {

    @Test
    public void constructor_invalidGitHub_throwsIllegalArgumentException() {
        String invalidGitHub = "";
        assertThrows(IllegalArgumentException.class, () -> new GitHub(invalidGitHub));
    }

    @Test
    public void isValidGitHub() {
        // null address
        assertThrows(NullPointerException.class, () -> GitHub.isValidGitHub(null));

        // invalid addresses
        assertFalse(GitHub.isValidGitHub("")); // empty string
        assertFalse(GitHub.isValidGitHub(" ")); // spaces only
        assertFalse(GitHub.isValidGitHub("a")); // only 1 character
        assertFalse(GitHub.isValidGitHub("-alexa")); //start with hyphen
        assertFalse(GitHub.isValidGitHub("alexa-")); //end with hyphen
        assertFalse(GitHub.isValidGitHub("alexa!")); //non-alphanumeric character
        assertFalse(GitHub.isValidGitHub("alexandra-rock-is-the-name-i-like-drinks")); // more than 39 character gitHub

        // valid addresses
        assertTrue(GitHub.isValidGitHub("alex-o"));
        assertTrue(GitHub.isValidGitHub("as")); // two character
        assertTrue(GitHub.isValidGitHub("alexandra-rock-is-the-name-i-like-drink")); // 39 character gitHub
    }
}

package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    public void constructor_invalidUsername_throwsIllegalArgumentException() {
        String invalidUsername = "";
        assertThrows(IllegalArgumentException.class, () -> new GithubUsername(invalidUsername));
    }

    @Test
    public void isValidGithubUsername() {
        String validLongName = "Tom-Alex-Doe-lee-Dr-steven-yeoh-Amos134";
        String invalidLongName = "Tom-Alex-Doe-lee-Dr-steven-yeoh-Amos1234";
        // null username
        assertThrows(NullPointerException.class, () -> GithubUsername.isValidUsername(null));

        // invalid usernames
        assertFalse(GithubUsername.isValidUsername("")); // empty string
        assertFalse(GithubUsername.isValidUsername(" ")); // spaces only
        assertFalse(GithubUsername.isValidUsername("alex_doe")); // contains illegal character
        assertFalse(GithubUsername.isValidUsername("-alex_doe")); //starts with hyphen
        assertFalse(GithubUsername.isValidUsername("alex_doe-")); //ends with hyphen
        assertFalse(GithubUsername.isValidUsername("alex_--doe")); //has consecutive hyphens
        assertFalse(GithubUsername.isValidUsername("alex--doe")); //has consecutive hyphens
        assertFalse(GithubUsername.isValidUsername(invalidLongName)); //40 character name


        // valid usernames
        assertTrue(GithubUsername.isValidUsername("johndoe")); // characters only
        assertTrue(GithubUsername.isValidUsername("amy-bal-ler-123")); // alphanumeric with hyphen
        assertTrue(GithubUsername.isValidUsername("12345")); // only numbers
        assertTrue(GithubUsername.isValidUsername(validLongName)); //39 character name
    }

    @Test void isValidUserNameLength() {
        String validLongName = "Tom-Alex-Doe-lee-Dr-steven-yeoh-Amos134";
        String invalidLongName = "Tom-Alex-Doe-lee-Dr-steven-yeoh-Amos1234";
        assertEquals(39, validLongName.length());
        assertEquals(40, invalidLongName.length());
        assertTrue(GithubUsername.isValidUsernameLength(validLongName)); //39 length username
        assertFalse(GithubUsername.isValidUsernameLength(invalidLongName)); //40 length username
    }

    @Test
    public void getGithubHandle() {
        GithubUsername firstUsername = new GithubUsername("johndoe");
        assertEquals(firstUsername.getGithubHandle(), "@johndoe");
        GithubUsername secondUsername = new GithubUsername("amy-123");
        assertEquals(secondUsername.getGithubHandle(), "@amy-123");
    }

    @Test
    public void getGithubProfileLink() {
        GithubUsername firstUsername = new GithubUsername("johndoe");
        assertEquals(firstUsername.getGithubProfileLink(), "https://github.com/johndoe");

        GithubUsername secondUsername = new GithubUsername("amy-123");
        assertEquals(secondUsername.getGithubProfileLink(), "https://github.com/amy-123");
    }
}

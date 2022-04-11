package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class EmailTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Email(null));
    }

    @Test
    public void constructor_invalidEmail_throwsIllegalArgumentException() {
        String invalidEmail = "";
        assertThrows(IllegalArgumentException.class, () -> new Email(invalidEmail));
    }

    @Test
    public void isValidEmail() {
        // null email
        assertThrows(NullPointerException.class, () -> Email.isValidEmail(null));

        // blank email
        assertFalse(Email.isValidEmail("")); // empty string
        assertFalse(Email.isValidEmail(" ")); // spaces only

        // missing parts
        assertFalse(Email.isValidEmail("@u.nus.edu")); // missing local part
        assertFalse(Email.isValidEmail("peterjacku.nus.edu")); // missing '@' symbol
        assertFalse(Email.isValidEmail("peterjack@")); // missing domain name

        // invalid parts
        assertFalse(Email.isValidEmail("peterjack@-")); // invalid domain name
        assertFalse(Email.isValidEmail("peterjack@u_nus.edu")); // underscore in domain name
        assertFalse(Email.isValidEmail("peter jack@u.nus.edu")); // spaces in local part
        assertFalse(Email.isValidEmail("peterjack@u nus.edu")); // spaces in domain name
        assertFalse(Email.isValidEmail(" peterjack@u.nus.edu")); // leading space
        assertFalse(Email.isValidEmail("peterjack@u.nus.edu ")); // trailing space
        assertFalse(Email.isValidEmail("peterjack@@u.nus.edu")); // double '@' symbol
        assertFalse(Email.isValidEmail("peter@jack@u.nus.edu")); // '@' symbol in local part
        assertFalse(Email.isValidEmail("-peterjack@u.nus.edu")); // local part starts with a hyphen
        assertFalse(Email.isValidEmail("peterjack-@u.nus.edu")); // local part ends with a hyphen
        assertFalse(Email.isValidEmail("peter..jack@u.nus.edu")); // local part has two consecutive periods
        assertFalse(Email.isValidEmail("peterjack@u.nus@edu")); // '@' symbol in domain name
        assertFalse(Email.isValidEmail("peterjack@.u.nus.edu")); // domain name starts with a period
        assertFalse(Email.isValidEmail("peterjack@u.nus.edu.")); // domain name ends with a period
        assertFalse(Email.isValidEmail("peterjack@-u.nus.edu")); // domain name starts with a hyphen
        assertFalse(Email.isValidEmail("peterjack@u.nus.edu-")); // domain name ends with a hyphen
        assertFalse(Email.isValidEmail("peterjack@u.nus.e")); // top level domain has less than two chars
        assertFalse(Email.isValidEmail("peterjack@email.com")); // Unaccepted domain name
        assertFalse(Email.isValidEmail("1234567890123456789012345678901234567890"
                + "1234567890123456789012345@gmail.com")); // 65 character long local name (too long)

        // valid email
        assertTrue(Email.isValidEmail("PeterJack_1190@u.nus.edu")); // underscore in local part
        assertTrue(Email.isValidEmail("PeterJack.1190@u.nus.edu")); // period in local part
        assertTrue(Email.isValidEmail("PeterJack+1190@u.nus.edu")); // '+' symbol in local part
        assertTrue(Email.isValidEmail("PeterJack-1190@u.nus.edu")); // hyphen in local part
        assertTrue(Email.isValidEmail("1234567890123456789012345678901234567890"
                + "123456789012345678901234@gmail.com")); // 64 character long local name

        // accepted domains
        assertTrue(Email.isValidEmail("peterjack@u.nus.edu"));
        assertTrue(Email.isValidEmail("peterjack@nus.edu.sg"));
        assertTrue(Email.isValidEmail("peterjack@gmail.com"));
        assertTrue(Email.isValidEmail("peterjack@yahoo.com"));
        assertTrue(Email.isValidEmail("peterjack@outlook.com"));
        assertTrue(Email.isValidEmail("peterjack@hotmail.com"));
    }
}

package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
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

        String edgeEmail = "";
        for (int i = 0; i < Email.CHARACTER_LIMIT - 4; i++) {
            if (i == (Email.CHARACTER_LIMIT - 4) / 2) {
                edgeEmail += "@";
                continue;
            }
            edgeEmail += "a";
        }
        edgeEmail += ".com";
        // blank email
        assertFalse(Email.isValidEmail("")); // empty string
        assertFalse(Email.isValidEmail(" ")); // spaces only

        // missing parts
        assertFalse(Email.isValidEmail("@example.com")); // missing local part
        assertFalse(Email.isValidEmail("peterjackexample.com")); // missing '@' symbol
        assertFalse(Email.isValidEmail("peterjack@")); // missing domain name
        assertFalse(Email.isValidEmail(edgeEmail + "a")); // missing domain name

        // invalid parts
        assertFalse(Email.isValidEmail("peterjack@-")); // invalid domain name
        assertFalse(Email.isValidEmail("peterjack@exam_ple.com")); // underscore in domain name
        assertFalse(Email.isValidEmail("peter jack@example.com")); // spaces in local part
        assertFalse(Email.isValidEmail("peterjack@exam ple.com")); // spaces in domain name
        assertFalse(Email.isValidEmail(" peterjack@example.com")); // leading space
        assertFalse(Email.isValidEmail("peterjack@example.com ")); // trailing space
        assertFalse(Email.isValidEmail("peterjack@@example.com")); // double '@' symbol
        assertFalse(Email.isValidEmail("peter@jack@example.com")); // '@' symbol in local part
        assertFalse(Email.isValidEmail("-peterjack@example.com")); // local part starts with a hyphen
        assertFalse(Email.isValidEmail("peterjack-@example.com")); // local part ends with a hyphen
        assertFalse(Email.isValidEmail("peter..jack@example.com")); // local part has two consecutive periods
        assertFalse(Email.isValidEmail("peterjack@example@com")); // '@' symbol in domain name
        assertFalse(Email.isValidEmail("peterjack@.example.com")); // domain name starts with a period
        assertFalse(Email.isValidEmail("peterjack@example.com.")); // domain name ends with a period
        assertFalse(Email.isValidEmail("peterjack@-example.com")); // domain name starts with a hyphen
        assertFalse(Email.isValidEmail("peterjack@example.com-")); // domain name ends with a hyphen
        assertFalse(Email.isValidEmail("peterjack@example.c")); // top level domain has less than two chars
        assertFalse(Email.isValidEmail("peterjack@examplecom")); // no period
        assertFalse(Email.isValidEmail("peter.jack@examplecom")); // no period in domain
        assertFalse(Email.isValidEmail(edgeEmail + "a")); // too long email

        // valid email
        assertTrue(Email.isValidEmail("PeterJack_1190@example.com")); // underscore in local part
        assertTrue(Email.isValidEmail("PeterJack.1190@example.com")); // period in local part
        assertTrue(Email.isValidEmail("PeterJack+1190@example.com")); // '+' symbol in local part
        assertTrue(Email.isValidEmail("PeterJack-1190@example.com")); // hyphen in local part
        assertTrue(Email.isValidEmail("a@bc.co")); // minimal
        assertTrue(Email.isValidEmail("123@145.cc")); // numeric local part and domain name
        assertTrue(Email.isValidEmail("a1+be.d@example1.com")); // mixture of alphanumeric and special characters
        assertTrue(Email.isValidEmail("peter_jack@very-very-very-long-example.com")); // long domain name
        assertTrue(Email.isValidEmail("if.you.dream.it_you.can.do.it@example.com")); // long local part
        assertTrue(Email.isValidEmail("e1234567@u.nus.edu")); // more than one period in domain
        assertTrue(Email.isValidEmail("peterjack@comp.nus.edu.sg")); // many periods
        assertTrue(Email.isValidEmail(edgeEmail)); // super long email
    }

    @Test
    public void exactEquals() {

        Email validEmailAmy = new Email(VALID_EMAIL_AMY);

        // same object -> returns true
        assertTrue(validEmailAmy.exactEquals(validEmailAmy));

        // same values -> returns true
        Email validEmailAmyCopy = new Email(VALID_EMAIL_AMY);
        assertTrue(validEmailAmy.exactEquals(validEmailAmyCopy));

        // different capitalization -> returns false
        Email validEmailAmyAllCaps = new Email(VALID_EMAIL_AMY.toUpperCase());
        assertFalse(validEmailAmy.exactEquals(validEmailAmyAllCaps));

        // null -> returns false
        assertFalse(validEmailAmy.exactEquals(null));

        // different email -> returns false
        Email validEmailBob = new Email(VALID_EMAIL_BOB);
        assertFalse(validEmailAmy.exactEquals(validEmailBob));
    }

    @Test
    public void equals() {

        Email validEmailAmy = new Email(VALID_EMAIL_AMY);
        Email validEmailBob = new Email(VALID_EMAIL_BOB);

        // same object -> returns true
        assertTrue(validEmailAmy.equals(validEmailAmy));

        // same values -> returns true
        Email validEmailAmyCopy = new Email(VALID_EMAIL_AMY);
        assertTrue(validEmailAmy.equals(validEmailAmyCopy));

        // different capitalization -> returns true
        Email validEmailAmyAllCaps = new Email(VALID_EMAIL_AMY.toUpperCase());
        assertTrue(validEmailAmy.equals(validEmailAmyAllCaps));

        // different types -> returns false
        assertFalse(validEmailAmy.equals(1));

        // null -> returns false
        assertFalse(validEmailAmy.equals(null));

        // different email -> returns false
        assertFalse(validEmailAmy.equals(validEmailBob));
    }

}

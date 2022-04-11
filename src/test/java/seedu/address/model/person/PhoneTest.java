package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PhoneTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Phone(null));
    }

    @Test
    public void constructor_invalidPhone_throwsIllegalArgumentException() {
        String invalidPhone = "";
        assertThrows(IllegalArgumentException.class, () -> new Phone(invalidPhone));
    }

    @Test
    public void isValidPhone() {
        // null phone number
        assertThrows(NullPointerException.class, () -> Phone.isValidPhone(null));
        String edgePhone = "";
        for (int i = 0; i < Phone.CHARACTER_LIMIT; i++) {
            edgePhone += "1";
        }
        // invalid phone numbers
        assertFalse(Phone.isValidPhone("")); // empty string
        assertFalse(Phone.isValidPhone(" ")); // spaces only
        assertFalse(Phone.isValidPhone("+ 12345")); // + followed by space
        assertFalse(Phone.isValidPhone("15")); // less than 3 numbers
        assertFalse(Phone.isValidPhone("+99")); // less than 3 numbers
        assertFalse(Phone.isValidPhone("++99999")); // two pluses
        assertFalse(Phone.isValidPhone("phone")); // non-numeric
        assertFalse(Phone.isValidPhone("9011p041")); // alphabets within digits
        assertFalse(Phone.isValidPhone(edgePhone + "1")); // too long

        // valid phone numbers
        assertTrue(Phone.isValidPhone("911")); // exactly 3 numbers
        assertTrue(Phone.isValidPhone("+65 93121534"));
        assertTrue(Phone.isValidPhone("9312 1534")); // spaces within digits
        assertTrue(Phone.isValidPhone("+86 124  293 842 033 123")); // many spaces
        assertTrue(Phone.isValidPhone(edgePhone)); // long
    }

    @Test
    public void exactEquals() {

        Phone validPhoneAmy = new Phone(VALID_PHONE_AMY);

        // same object -> returns true
        assertTrue(validPhoneAmy.exactEquals(validPhoneAmy));

        // same values -> returns true
        Phone validPhoneAmyCopy = new Phone(VALID_PHONE_AMY);
        assertTrue(validPhoneAmy.exactEquals(validPhoneAmyCopy));

        // null -> returns false
        assertFalse(validPhoneAmy.exactEquals(null));

        // different phone -> returns false
        Phone validPhoneBob = new Phone(VALID_PHONE_BOB);
        assertFalse(validPhoneAmy.exactEquals(validPhoneBob));

        // white space -> returns false
        int halfLengthPhoneAmy = VALID_PHONE_AMY.length() / 2;
        String phoneWithSpace = VALID_PHONE_AMY.substring(0, halfLengthPhoneAmy) + " "
                + VALID_PHONE_AMY.substring(halfLengthPhoneAmy); // add white space to the center of the phone number
        Phone validPhoneWithSpace = new Phone(phoneWithSpace);
        assertFalse(validPhoneAmy.exactEquals(validPhoneWithSpace));
    }

    @Test
    public void equals() {

        Phone validPhoneAmy = new Phone(VALID_PHONE_AMY);
        Phone validPhoneBob = new Phone(VALID_PHONE_BOB);

        // same object -> returns true
        assertTrue(validPhoneAmy.equals(validPhoneAmy));

        // same values -> returns true
        Phone validPhoneAmyCopy = new Phone(VALID_PHONE_AMY);
        assertTrue(validPhoneAmy.equals(validPhoneAmyCopy));

        // white space -> returns true
        int halfLengthPhoneAmy = VALID_PHONE_AMY.length() / 2;
        String phoneWithSpace = VALID_PHONE_AMY.substring(0, halfLengthPhoneAmy) + " "
                + VALID_PHONE_AMY.substring(halfLengthPhoneAmy); // add white space to the center of the phone number
        Phone validPhoneWithSpace = new Phone(phoneWithSpace);
        assertTrue(validPhoneAmy.equals(validPhoneWithSpace));

        // different types -> returns false
        assertFalse(validPhoneAmy.equals(1));

        // null -> returns false
        assertFalse(validPhoneAmy.equals(null));

        // different phone -> returns false
        assertFalse(validPhoneAmy.equals(validPhoneBob));
    }
}

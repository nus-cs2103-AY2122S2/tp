package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class MembershipTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Membership(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Membership(invalidName));
    }

    @Test
    public void getValue() {
        Membership member = new Membership("Test");

        assertEquals("Test", member.getValue());
        assertNotEquals("Fake", member.getValue());
    }

    @Test
    public void getDate() {
        LocalDate date = LocalDate.parse("1920-02-02");
        Membership member = new Membership("Test", date);

        assertEquals(date, member.getDate());
    }

    @Test
    public void isValidName() {
        // null address
        assertThrows(NullPointerException.class, () -> Membership.isValidName(null));

        // invalid addresses
        assertFalse(Membership.isValidName("")); // empty string
        assertFalse(Membership.isValidName(" ")); // spaces only
        assertFalse(Membership.isValidName("-")); // non-alphanumeric

        // valid addresses
        assertTrue(Membership.isValidName("Glee Club"));
        assertTrue(Membership.isValidName("I am the leader of all humans and you should bow before"));
    }

    @Test
    public void isValidDate() {
        String validDate = "1920-02-02";
        String invalidDate = "Jargon";

        assertTrue(Membership.isValidDate(validDate));

        assertFalse(Membership.isValidDate(invalidDate));
    }

    @Test
    public void toString_test() {
        LocalDate date = LocalDate.parse("1920-02-02");
        Membership member = new Membership("Test", date);
        String expectedResult = "Test since " + date.toString();

        assertEquals(expectedResult, member.toString());
    }

    @Test
    public void equals() {
        // null address
        Membership member1 = new Membership("Test", LocalDate.parse("1920-02-02"));
        Membership member2 = new Membership("Test", LocalDate.parse("1920-02-02"));
        Membership member3 = new Membership("Different");

        assertTrue(member1.equals(member1));
        assertTrue(member1.equals(member2));

        assertFalse(member1.equals(member3));
    }

    @Test
    public void hashCode_test() {
        Membership member = new Membership("Test");

        int expected = "Test".hashCode();

        assertEquals(expected, member.hashCode());
    }

    @Test
    public void compareTo() {
        Membership member1 = new Membership("Alpha");
        Membership member2 = new Membership("Beta");

        assertEquals(-1, member1.compareTo(null));
        assertEquals(-1, member1.compareTo(member2));
        assertEquals(1, member2.compareTo(member1));
    }
}

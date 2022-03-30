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
        Membership member = new Membership("bronze");

        assertEquals("Bronze member", member.getValue());
        assertNotEquals("Fake", member.getValue());

        member = new Membership("silver");

        assertEquals("Silver member", member.getValue());
        assertNotEquals("Fake", member.getValue());

        member = new Membership("gold");

        assertEquals("Gold member", member.getValue());
        assertNotEquals("Fake", member.getValue());
    }

    @Test
    public void getTierFromString() {
        assertEquals(Membership.Tier.GOLD, Membership.getTierFromString("gold"));
        assertEquals(Membership.Tier.SILVER, Membership.getTierFromString("silver"));
        assertEquals(Membership.Tier.BRONZE, Membership.getTierFromString("bronze"));
    }

    @Test
    public void getTier() {
        Membership member = new Membership("gold");
        assertEquals(Membership.Tier.GOLD, member.getTier());

        member = new Membership("silver");
        assertEquals(Membership.Tier.SILVER, member.getTier());

        member = new Membership("bronze");
        assertEquals(Membership.Tier.BRONZE, member.getTier());
    }

    @Test
    public void getDate() {
        LocalDate date = LocalDate.parse("1920-02-02");
        Membership member = new Membership("gold", date);

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
        assertTrue(Membership.isValidName("gOlD"));
        assertTrue(Membership.isValidName("bRoNZE"));
        assertTrue(Membership.isValidName("silver"));
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
        Membership member = new Membership("BRONZE", date);
        String expectedResult = "Bronze member since " + date.toString();

        assertEquals(expectedResult, member.toString());
    }

    @Test
    public void equals() {
        // null address
        Membership member1 = new Membership("GOLD", LocalDate.parse("1920-02-02"));
        Membership member2 = new Membership("GOLD", LocalDate.parse("1920-02-02"));
        Membership member3 = new Membership("SILVER");

        assertTrue(member1.equals(member1));
        assertTrue(member1.equals(member2));

        assertFalse(member1.equals(member3));
    }

    @Test
    public void hashCode_test() {
        Membership member = new Membership("Gold");

        int expected = Membership.Tier.GOLD.hashCode();

        assertEquals(expected, member.hashCode());
    }

    @Test
    public void compareTo() {
        Membership member1 = new Membership("gold");
        Membership member2 = new Membership("silver");

        assertEquals(-1, member1.compareTo(null));
        assertEquals(-1, member1.compareTo(member2));
        assertEquals(1, member2.compareTo(member1));
    }
}

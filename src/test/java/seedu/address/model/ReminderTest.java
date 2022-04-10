package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Name;
import seedu.address.model.property.Price;
import seedu.address.model.property.Size;

class ReminderTest {

    @Test
    public void creatingAReminder_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Reminder(null));
    }

    @Test
    void testEquals() {
        // Reminders with other Reminders of the same values
        String validTestString1 = "meet client";
        assertTrue(new Reminder(validTestString1).equals(new Reminder(validTestString1)));
        String validTestString2 = "arrange time for home viewing";
        assertTrue(new Reminder(validTestString2).equals(new Reminder(validTestString2)));
        String validTestString3 = "follow-up with client about home leasing";
        assertTrue(new Reminder(validTestString3).equals(new Reminder(validTestString3)));

        // Reminders with its own self
        String reminderTestString4 = "update client's contact number";
        Reminder reminderUsingTestString1 = new Reminder(reminderTestString4);
        assertTrue(reminderUsingTestString1.equals(reminderUsingTestString1));
        String reminderTestString5 = "do matching for client";
        Reminder reminderUsingTestString2 = new Reminder(reminderTestString5);
        assertTrue(reminderUsingTestString2.equals(reminderUsingTestString2));

        // Reminders against null objects
        assertFalse(new Reminder(validTestString1).equals(null));

        // Reminders against non-Reminder objects
        assertFalse(new Reminder(validTestString1).equals(new Price("$100000")));
        assertFalse(new Reminder(validTestString1).equals(Size.fromString("2-room")));
        assertFalse(new Reminder(validTestString1).equals(new Name("Sam Witwicky")));
    }

    @Test
    void isValidReminder() {
        // null Reminders
        assertThrows(NullPointerException.class, () -> new Reminder(null));

        // invalid Reminders
        assertFalse(Reminder.isValidReminder(""));
        assertFalse(Reminder.isValidReminder("   "));
        assertFalse(Reminder.isValidReminder("  "));

        // valid Reminders
        assertTrue(Reminder.isValidReminder("meet client"));
        assertTrue(Reminder.isValidReminder("arrange meeting with client"));
        assertTrue(Reminder.isValidReminder("update client's phone number"));
    }
}

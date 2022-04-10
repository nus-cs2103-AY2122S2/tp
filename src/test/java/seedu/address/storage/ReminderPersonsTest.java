package seedu.address.storage;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import seedu.address.model.Reminder;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

class ReminderPersonsTest {

    @Test
    void getInstance_success() {
        ReminderPersons reminderPersons = ReminderPersons.getInstance();

        assertEquals(ReminderPersons.class, reminderPersons.getClass());
    }

    @Test
    void addReminders_success() {
        // creating a plain copy of ReminderPersons for testing purposes
        ReminderPersons reminderPersons = ReminderPersons.getInstance();
        reminderPersons.setTestingInstance();

        // creating a new Reminder for model Persons with no previous Reminders
        Person alice = new PersonBuilder().withName("Alice").build();
        Reminder reminderForAlice = new Reminder("arrange home viewing");
        assertEquals(null, reminderPersons.add(alice, reminderForAlice));

        Person bob = new PersonBuilder().withName("Bob").build();
        Reminder reminderForBob = new Reminder("update client's phone number");
        assertEquals(null, reminderPersons.add(bob, reminderForBob));

    }

    @Test
    void editReminders_success() {
        // creating a plain copy of ReminderPersons for testing purposes
        ReminderPersons reminderPersons = ReminderPersons.getInstance();
        reminderPersons.setTestingInstance();

        // editing existing Reminders for model Persons
        Person alice = new PersonBuilder().withName("Alice").build();
        Reminder reminderForAlice = new Reminder("arrange home viewing");
        reminderPersons.add(alice, reminderForAlice);
        Reminder newReminderForAlice = new Reminder("call client later in 1 hour");
        assertEquals(reminderForAlice, reminderPersons.add(alice, newReminderForAlice));

        Person bob = new PersonBuilder().withName("Bob").build();
        Reminder reminderForBob = new Reminder("update client's phone number");
        reminderPersons.add(bob, reminderForBob);
        Reminder newReminderForBob = new Reminder("favourite this client");
        assertEquals(reminderForBob, reminderPersons.add(bob, newReminderForBob));
    }

    @Test
    void deleteReminders_success() {
        // creating a plain copy of ReminderPersons for testing purposes
        ReminderPersons reminderPersons = ReminderPersons.getInstance();
        reminderPersons.setTestingInstance();

        // deleting existing Reminders for model Persons
        Person alice = new PersonBuilder().withName("Alice").build();
        Reminder reminderForAlice = new Reminder("arrange home viewing");
        reminderPersons.add(alice, reminderForAlice);
        assertEquals(reminderForAlice, reminderPersons.remove(alice));

        Person bob = new PersonBuilder().withName("Bob").build();
        Reminder reminderForBob = new Reminder("update client's phone number");
        reminderPersons.add(bob, reminderForBob);
        assertEquals(reminderForBob, reminderPersons.remove(bob));
    }

    @Test
    void getReminders_success() {
        // creating a plain copy of ReminderPersons for testing purposes
        ReminderPersons reminderPersons = ReminderPersons.getInstance();
        reminderPersons.setTestingInstance();

        // adding new Reminders for model Persons
        Person alice = new PersonBuilder().withName("Alice").build();
        Reminder reminderForAlice = new Reminder("arrange home viewing");
        reminderPersons.add(alice, reminderForAlice);
        assertEquals(reminderForAlice, reminderPersons.get(alice));

        Person bob = new PersonBuilder().withName("Bob").build();
        Reminder reminderForBob = new Reminder("update client's phone number");
        reminderPersons.add(bob, reminderForBob);
        assertEquals(reminderForBob, reminderPersons.get(bob));
    }

    @Test
    void getKeySetAndContainsKeyAndClone_success() {
        // creating a plain copy of ReminderPersons for testing purposes
        ReminderPersons reminderPersons = ReminderPersons.getInstance();
        reminderPersons.setTestingInstance();

        // creating a standalone HashMap
        HashMap<Person, Reminder> personReminderHashMap = new HashMap<>();

        // adding Reminders for model Persons
        Person alice = new PersonBuilder().withName("Alice").build();
        Reminder reminderForAlice = new Reminder("arrange home viewing");
        reminderPersons.add(alice, reminderForAlice);

        Person bob = new PersonBuilder().withName("Bob").build();
        Reminder reminderForBob = new Reminder("update client's phone number");
        reminderPersons.add(bob, reminderForBob);

        // adding persons & their reminders to the HashMap
        personReminderHashMap.put(alice, reminderForAlice);
        personReminderHashMap.put(bob, reminderForBob);

        // check if ReminderPersons & the standalone HashMap return the same KeySet
        assertEquals(personReminderHashMap.keySet(), reminderPersons.getKeySet());

        // check if the ReminderPersons object contains the specified Persons added earlier
        assertTrue(reminderPersons.containsKey(alice));
        assertTrue(reminderPersons.containsKey(bob));

        // check if the HashMap in the ReminderPersons object and standalone HashMap are the same
        HashMap<Person, Reminder> reminderPersonsHashMapClone = reminderPersons.clone();
        assertTrue(reminderPersonsHashMapClone.equals(personReminderHashMap));
    }

    @Test
    void isEmpty_success() {
        // creating a plain copy of ReminderPersons for testing purposes
        ReminderPersons reminderPersons = ReminderPersons.getInstance();
        reminderPersons.setTestingInstance();

        // check if the HashMap in reminderPersons is empty
        assertTrue(reminderPersons.isEmpty());

        // add Reminders for model Persons
        Person alice = new PersonBuilder().withName("Alice").build();
        Reminder reminderForAlice = new Reminder("arrange home viewing");
        reminderPersons.add(alice, reminderForAlice);

        Person bob = new PersonBuilder().withName("Bob").build();
        Reminder reminderForBob = new Reminder("update client's phone number");
        reminderPersons.add(bob, reminderForBob);

        // check if the HashMap in reminderPersons is not empty
        assertFalse(reminderPersons.isEmpty());
    }

    @Test
    void toggleFavouriteForReminder_success() {
        // creating a plain copy of ReminderPersons for testing purposes
        ReminderPersons reminderPersons = ReminderPersons.getInstance();
        reminderPersons.setTestingInstance();

        // add Reminder for model Person
        Person alice = new PersonBuilder().withName("Alice").build();
        Reminder reminderForAlice = new Reminder("arrange home viewing");
        reminderPersons.add(alice, reminderForAlice);
        ReminderPersons.toggleFavouriteForReminder(alice);

        // favouriting a Person
        Person aliceFavourited = alice.toggleFavourite();
        assertTrue(reminderPersons.containsKey(aliceFavourited));
    }
}

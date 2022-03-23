package seedu.address.storage;

import java.util.HashSet;

import seedu.address.model.person.Person;

/**
 * A class to store all clients set for reminders.
 * This class is implemented as a singleton, as there can only be 1 list of Reminders.
 */
public class ReminderPersons {

    private static ReminderPersons reminderPersons;
    private static HashSet<Person> reminders;

    private ReminderPersons() {
        reminders = new HashSet<>();
    }

    public static ReminderPersons getInstance() {
        if (reminderPersons == null) {
            reminderPersons = new ReminderPersons();
        }

        return reminderPersons;
    }

    /**
     * Adds a person to the list of persons to be reminded of,
     * returns a boolean indicating if this person is in this list or not.
     * @param person The person to be reminded of.
     */
    public boolean add(Person person) {
        return reminders.add(person);
    }

    /**
     * Removes a person from the list of persons to be reminded of
     * @param person The person to remove from reminders.
     */
    public boolean remove(Person person) {
        return reminders.remove(person);
    }

    /**
     * Returns a shallow copy of the HashSet.
     */
    public Object clone() {
        return reminders.clone();
    }
}

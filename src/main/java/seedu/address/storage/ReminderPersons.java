package seedu.address.storage;

import java.util.HashMap;
import java.util.Set;

import seedu.address.model.Reminder;
import seedu.address.model.person.Person;

/**
 * A class to store all clients set for reminders.
 * This class is implemented as a singleton, as there can only be 1 list of Reminders.
 */
public class ReminderPersons {

    private static ReminderPersons reminderPersons;
    private static HashMap<Person, Reminder> reminders;

    private ReminderPersons() {
        reminders = new HashMap<>();
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
    public Reminder add(Person person, Reminder reminder) {
        return reminderPersons.reminders.put(person, reminder);
    }

    /**
     * Removes a person from the list of persons to be reminded of
     * @param person The person to remove from reminders.
     */
    public Reminder remove(Person person) {
        return reminderPersons.reminders.remove(person);
    }

    /**
     * Returns a shallow copy of the HashMap.
     */
    public HashMap<Person, Reminder> clone() {
        return new HashMap<>(reminderPersons.reminders);
    }

    public Set<Person> getKeySet() {
        return reminderPersons.reminders.keySet();
    }

    public boolean containsKey(Person person) {
        return reminderPersons.reminders.containsKey(person);
    }

    public boolean isEmpty() {
        return reminderPersons.reminders.isEmpty();
    }
}

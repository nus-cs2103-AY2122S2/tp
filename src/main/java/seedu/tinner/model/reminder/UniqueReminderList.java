package seedu.tinner.model.reminder;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.tinner.model.role.exceptions.DuplicateRoleException;

public class UniqueReminderList implements Iterable<Reminder> {

    private static UniqueReminderList reminderList;

    private final ObservableList<Reminder> internalList;
    private final ObservableList<Reminder> internalUnmodifiableList;
    private final HashMap<LocalDate, ArrayList<Reminder>> dateToReminderMap;

    /**
     * Constructor of UniqueReminderList
     */
    private UniqueReminderList() {
        internalList = FXCollections.observableArrayList();
        internalUnmodifiableList = FXCollections.unmodifiableObservableList(internalList);
        dateToReminderMap = new HashMap<>();
    }

    /**
     * Factory method of UniqueReminderList
     *
     * @return Produced UniqueReminderList object that is a Singleton.
     */
    public static UniqueReminderList getInstance() {
        if (reminderList == null) {
            reminderList = new UniqueReminderList();
        }
        return reminderList;
    }

    /**
     * Returns true if the list contains an equivalent role as the given argument.
     */
    public boolean contains(Reminder toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameReminder);
    }

    /**
     * Adds a role to the list.
     * The role must not already exist in the list.
     */
    public void add(Reminder toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateRoleException();
        }
        internalList.add(toAdd);

        LocalDateTime reminderDateTime = toAdd.getDeadline().value;
        LocalDate reminderDate = reminderDateTime.toLocalDate();
        dateToReminderMap.computeIfAbsent(reminderDate, k -> new ArrayList<>());
        dateToReminderMap.get(reminderDate).add(toAdd);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Reminder> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    public ObservableList<LocalDate> getReminderDates() {
        return FXCollections.unmodifiableObservableList(FXCollections
                .observableArrayList(new TreeSet<>(dateToReminderMap.keySet())));
    }

    public ObservableList<Reminder> getDateSpecificReminders(LocalDate date) {
        ObservableList<Reminder> reminderList = FXCollections.observableArrayList();
        ArrayList<Reminder> retrievedReminders = dateToReminderMap.get(date);
        if (retrievedReminders == null) {
            return reminderList;
        }
        Collections.sort(retrievedReminders);
        for (Reminder reminder : retrievedReminders) {
            reminderList.add(reminder);
        }
        return reminderList;
    }

    public Iterator<Reminder> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueReminderList // instanceof handles nulls
                && internalList.equals(((UniqueReminderList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code roles} contains only unique companies.
     */
    private boolean remindersAreUnique(List<Reminder> reminders) {
        for (int i = 0; i < reminders.size() - 1; i++) {
            for (int j = i + 1; j < reminders.size(); j++) {
                if (reminders.get(i).isSameReminder(reminders.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}

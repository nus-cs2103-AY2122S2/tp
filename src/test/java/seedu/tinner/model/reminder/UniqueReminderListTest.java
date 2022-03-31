package seedu.tinner.model.reminder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tinner.testutil.Assert.assertThrows;
import static seedu.tinner.testutil.TypicalReminders.AMAZON_DATA_ENGINEER;
import static seedu.tinner.testutil.TypicalReminders.APPLE_ML_ENGINEER;
import static seedu.tinner.testutil.TypicalReminders.GOOGLE_FRONTEND;
import static seedu.tinner.testutil.TypicalReminders.META_DATA_ANALYSIS;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.tinner.model.reminder.exceptions.DuplicateReminderException;
import seedu.tinner.testutil.ReminderBuilder;

public class UniqueReminderListTest {

    private final UniqueReminderList reminderList = UniqueReminderList.getInstance();

    @Test
    public void contains_nullReminder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> reminderList.contains(null));
    }

    @Test
    public void contains_reminderNotInList_returnsFalse() {
        Reminder test = new ReminderBuilder()
                .withCompanyName("No such company")
                .withRoleName("Nothing")
                .build();
        assertFalse(reminderList.contains(test));
    }

    @Test
    public void contains_existingReminder_returnsTrue() {
        Reminder test = new ReminderBuilder()
                .withCompanyName("Some Company")
                .withRoleName("Backend Developer")
                .build();
        reminderList.add(test);
        assertTrue(reminderList.contains(test));
    }

    @Test
    public void contains_reminderWithSameIdentity_returnsTrue() {
        Reminder test = new ReminderBuilder()
                .withCompanyName("Test Company")
                .withRoleName("Frontend Developer")
                .withStatus("complete")
                .withReminderDate("30-10-2022 23:59")
                .build();
        Reminder test2 = new ReminderBuilder()
                .withCompanyName("Test Company")
                .withRoleName("Frontend Developer")
                .withStatus("pending")
                .withReminderDate("30-11-2022 23:59")
                .build();
        reminderList.add(test);
        assertTrue(reminderList.contains(test2));
    }

    @Test
    public void add_nullReminder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> reminderList.add(null));
    }

    @Test
    public void add_duplicateReminder_throwsDuplicateReminderException() {
        reminderList.add(META_DATA_ANALYSIS);
        assertThrows(DuplicateReminderException.class, () -> reminderList.add(META_DATA_ANALYSIS));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> reminderList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void getDateSpecificReminders_existingReminders_returnAllMatching() {
        reminderList.add(APPLE_ML_ENGINEER);
        reminderList.add(GOOGLE_FRONTEND);
        reminderList.add(AMAZON_DATA_ENGINEER);
        ArrayList<Reminder> expectedList = new ArrayList<>();
        expectedList.add(APPLE_ML_ENGINEER);
        expectedList.add(GOOGLE_FRONTEND);
        LocalDate date = GOOGLE_FRONTEND.getReminderDate().value.toLocalDate();
        assertEquals(expectedList, reminderList.getDateSpecificReminders(date));
    }

    @Test
    public void getDateSpecificReminders_noRemindersOnDate_returnEmptyList() {
        Reminder test = new ReminderBuilder()
                .withCompanyName("Company C")
                .withRoleName("Frontend Developer")
                .withReminderDate("02-02-2024 00:00")
                .build();
        reminderList.add(test);
        ArrayList<Reminder> expectedList = new ArrayList<>();
        LocalDate date = LocalDate.of(2024, 01, 01);
        assertEquals(expectedList, reminderList.getDateSpecificReminders(date));
    }

}

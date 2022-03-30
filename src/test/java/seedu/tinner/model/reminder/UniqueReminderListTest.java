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



public class UniqueReminderListTest {


    private final UniqueReminderList reminderList = UniqueReminderList.getInstance();

    @Test
    public void contains_nullReminder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> reminderList.contains(null));
    }

    @Test
    public void contains_reminderNotInList_returnsFalse() {
        assertFalse(reminderList.contains(META_DATA_ANALYSIS));
    }

    @Test
    public void contains_existingReminder_returnsTrue() {
        reminderList.add(GOOGLE_FRONTEND);
        assertTrue(reminderList.contains(GOOGLE_FRONTEND));
    }

    @Test
    public void contains_reminderWithSameIdentity_returnsTrue() {
        reminderList.add(GOOGLE_FRONTEND);
        assertTrue(reminderList.contains(GOOGLE_FRONTEND));
    }

    @Test
    public void add_nullReminder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> reminderList.add(null));
    }

    @Test
    public void add_duplicateReminder_throwsDuplicateReminderException() {
        reminderList.add(AMAZON_DATA_ENGINEER);
        assertThrows(DuplicateReminderException.class, () -> reminderList.add(AMAZON_DATA_ENGINEER));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> reminderList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void getReminderDates_existingReminders_returnAscendingOrder() {
        reminderList.add(AMAZON_DATA_ENGINEER);
        reminderList.add(GOOGLE_FRONTEND);
        ArrayList<LocalDate> expectedList = new ArrayList<>();
        expectedList.add(GOOGLE_FRONTEND.getReminderDate().value.toLocalDate());
        expectedList.add(AMAZON_DATA_ENGINEER.getReminderDate().value.toLocalDate());
        assertEquals(expectedList, reminderList.getReminderDates());
    }

    @Test
    public void getReminderDates_duplicateReminders_returnOnlyOne() {
        reminderList.add(APPLE_ML_ENGINEER);
        reminderList.add(GOOGLE_FRONTEND);
        ArrayList<LocalDate> expectedList = new ArrayList<>();
        expectedList.add(GOOGLE_FRONTEND.getReminderDate().value.toLocalDate());
        assertEquals(expectedList, reminderList.getReminderDates());
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
        reminderList.add(APPLE_ML_ENGINEER);
        ArrayList<Reminder> expectedList = new ArrayList<>();
        LocalDate date = META_DATA_ANALYSIS.getReminderDate().value.toLocalDate();
        assertEquals(expectedList, reminderList.getDateSpecificReminders(date));
    }

}

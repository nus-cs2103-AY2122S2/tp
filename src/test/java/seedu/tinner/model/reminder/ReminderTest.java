package seedu.tinner.model.reminder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tinner.logic.commands.CommandTestUtil.VALID_NAME_APPLE;
import static seedu.tinner.logic.commands.CommandTestUtil.VALID_NAME_SOFTWARE_ENGINEER;
import static seedu.tinner.logic.commands.CommandTestUtil.VALID_NAME_WHATSAPP;
import static seedu.tinner.testutil.Assert.assertThrows;
import static seedu.tinner.testutil.TypicalReminders.AMAZON_DATA_ENGINEER;
import static seedu.tinner.testutil.TypicalReminders.APPLE_ML_ENGINEER;
import static seedu.tinner.testutil.TypicalReminders.GOOGLE_FRONTEND;
import static seedu.tinner.testutil.TypicalReminders.META_DATA_ANALYSIS;

import org.junit.jupiter.api.Test;

import seedu.tinner.testutil.ReminderBuilder;

public class ReminderTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new Reminder(null, null, null, null));
    }

    @Test
    public void isSameReminder() {
        // same object -> returns true
        assertTrue(GOOGLE_FRONTEND.isSameReminder(GOOGLE_FRONTEND));

        // null -> returns false
        assertFalse(GOOGLE_FRONTEND.isSameReminder(null));

        // same company and role names, all other attributes different -> return true
        Reminder diffStatus = new ReminderBuilder(APPLE_ML_ENGINEER)
                .withStatus("complete")
                .withReminderDate("01-05-2022 00:00")
                .build();
        assertTrue(APPLE_ML_ENGINEER.isSameReminder(diffStatus));

        // different company and role names, all other attributes same -> returns false
        Reminder diffNames = new ReminderBuilder(APPLE_ML_ENGINEER)
                .withCompanyName(VALID_NAME_WHATSAPP)
                .withRoleName(VALID_NAME_SOFTWARE_ENGINEER)
                .build();
        assertFalse(APPLE_ML_ENGINEER.isSameReminder(diffNames));

        // company name differs in case, all other attributes same -> returns true
        Reminder lowerCaseCompanyName = new ReminderBuilder(APPLE_ML_ENGINEER)
                .withCompanyName(VALID_NAME_APPLE.toLowerCase())
                .build();
        assertTrue(APPLE_ML_ENGINEER.isSameReminder(lowerCaseCompanyName));

        // company name has trailing spaces, all other attributes same -> returns true
        Reminder nameWithTrailingSpaces = new ReminderBuilder(APPLE_ML_ENGINEER)
                .withCompanyName(VALID_NAME_APPLE + " ")
                .build();
        assertTrue(APPLE_ML_ENGINEER.isSameReminder(nameWithTrailingSpaces));

        // same role name includes brackets -> return true
        Reminder reminderOne = new ReminderBuilder()
                .withRoleName(AMAZON_DATA_ENGINEER.getRoleName().toString() + "(Backend)")
                .build();
        Reminder reminderTwo = new ReminderBuilder()
                .withRoleName(AMAZON_DATA_ENGINEER.getRoleName().toString() + "(Backend)")
                .build();
        assertTrue(reminderOne.isSameReminder(reminderTwo));

        //same role name with brackets with uneven whitespaces -> return true
        reminderOne = new ReminderBuilder()
                .withRoleName(AMAZON_DATA_ENGINEER.getRoleName().toString() + "  (Backend  )")
                .build();
        reminderTwo = new ReminderBuilder()
                .withRoleName(AMAZON_DATA_ENGINEER.getRoleName().toString() + " (  Backend)")
                .build();
        assertTrue(reminderOne.isSameReminder(reminderTwo));
    }

    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(META_DATA_ANALYSIS.equals(META_DATA_ANALYSIS));

        // same values -> return true
        Reminder test = new ReminderBuilder(META_DATA_ANALYSIS).build();
        assertTrue(META_DATA_ANALYSIS.equals(test));

        // null -> returns false
        assertFalse(META_DATA_ANALYSIS.equals(null));

        // different type -> returns false
        assertFalse(META_DATA_ANALYSIS.equals(1));

        // different reminder -> returns false
        assertFalse(META_DATA_ANALYSIS.equals(AMAZON_DATA_ENGINEER));

        // different companyName attribute -> returns false
        test = new ReminderBuilder(META_DATA_ANALYSIS).withCompanyName("Facebook").build();
        assertFalse(META_DATA_ANALYSIS.equals(test));

        // different roleName attribute -> returns false
        test = new ReminderBuilder(META_DATA_ANALYSIS).withRoleName("Frontend Developer").build();
        assertFalse(META_DATA_ANALYSIS.equals(test));

        // different status attribute -> returns false
        test = new ReminderBuilder(META_DATA_ANALYSIS).withStatus("offered").build();
        assertFalse(META_DATA_ANALYSIS.equals(test));

        // different reminderDate attribute -> returns false
        test = new ReminderBuilder(META_DATA_ANALYSIS).withReminderDate("30-04-2022 00:00").build();
        assertFalse(META_DATA_ANALYSIS.equals(test));

    }
}

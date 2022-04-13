package seedu.tinner.testutil;

import seedu.tinner.model.reminder.Reminder;

public class TypicalReminders {
    public static final Reminder GOOGLE_FRONTEND = new ReminderBuilder()
            .withCompanyName("Google")
            .withRoleName("Frontend Developer")
            .withStatus("applying")
            .withReminderDate("30-06-2022 00:00")
            .build();
    public static final Reminder META_DATA_ANALYSIS = new ReminderBuilder()
            .withCompanyName("Meta")
            .withRoleName("Data Analysis")
            .withStatus("rejected")
            .withReminderDate("01-05-2022 00:00")
            .build();
    public static final Reminder AMAZON_DATA_ENGINEER = new ReminderBuilder()
            .withCompanyName("Amazon")
            .withRoleName("Data Engineer")
            .withStatus("offered")
            .withReminderDate("02-05-2022 00:00")
            .build();
    public static final Reminder APPLE_ML_ENGINEER = new ReminderBuilder()
            .withCompanyName("Apple")
            .withRoleName("ML Engineer")
            .withStatus("offered")
            .withReminderDate("30-06-2022 00:00")
            .build();
}

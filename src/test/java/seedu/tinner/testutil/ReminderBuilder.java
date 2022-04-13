package seedu.tinner.testutil;

import seedu.tinner.model.company.CompanyName;
import seedu.tinner.model.reminder.Reminder;
import seedu.tinner.model.role.ReminderDate;
import seedu.tinner.model.role.RoleName;
import seedu.tinner.model.role.Status;

/**
 * A utility class to help building Reminder objects.
 */

public class ReminderBuilder {

    public static final String DEFAULT_CNAME = "Meta";
    public static final String DEFAULT_RNAME = "Frontend Developer";
    public static final String DEFAULT_STATUS = "pending";
    public static final String DEFAULT_REMINDERDATE = "30-04-2022 23:59";

    private CompanyName companyName;
    private RoleName roleName;
    private Status status;
    private ReminderDate reminderDate;

    /**
     * Creates a {@code ReminderBuilder} with the default details.
     */
    public ReminderBuilder() {
        companyName = new CompanyName(DEFAULT_CNAME);
        roleName = new RoleName(DEFAULT_RNAME);
        status = new Status(DEFAULT_STATUS);
        reminderDate = new ReminderDate(DEFAULT_REMINDERDATE);
    }

    /**
     * Initializes the ReminderBuilder with the data of {@code reminderToCopy}.
     */
    public ReminderBuilder(Reminder reminderToCopy) {
        companyName = reminderToCopy.getCompanyName();
        roleName = reminderToCopy.getRoleName();
        status = reminderToCopy.getStatus();
        reminderDate = reminderToCopy.getReminderDate();
    }

    /**
     * Sets the {@code CompanyName} of the {@code Reminder} that we are building.
     */
    public ReminderBuilder withCompanyName(String name) {
        this.companyName = new CompanyName(name);
        return this;
    }

    /**
     * Sets the {@code RoleName} of the {@code Reminder} that we are building.
     */
    public ReminderBuilder withRoleName(String name) {
        this.roleName = new RoleName(name);
        return this;
    }


    /**
     * Sets the {@code Status} of the {@code Reminder} that we are building.
     */
    public ReminderBuilder withStatus(String status) {
        this.status = new Status(status);
        return this;
    }

    /**
     * Sets the {@code ReminderDate} of the {@code Reminder} that we are building.
     */
    public ReminderBuilder withReminderDate(String reminderDate) {
        this.reminderDate = new ReminderDate(reminderDate);
        return this;
    }

    public Reminder build() {
        return new Reminder(companyName, roleName, status, reminderDate);
    }
}

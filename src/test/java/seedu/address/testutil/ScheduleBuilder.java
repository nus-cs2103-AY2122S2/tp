package seedu.address.testutil;

import seedu.address.model.schedule.Schedule;
import seedu.address.model.schedule.ScheduleDateTime;
import seedu.address.model.schedule.ScheduleDescription;
import seedu.address.model.schedule.ScheduleName;

public class ScheduleBuilder {
    public static final String DEFAULT_SCHEDULE_NAME = "NBA FINALS";
    public static final String DEFAULT_DESCRIPTION = "Game1 Lakers vs Bucks";
    public static final String DEFAULT_DATETIME = "22/02/2022 1222";

    private ScheduleName name;
    private ScheduleDescription description;
    private ScheduleDateTime dateTime;

    /**
     * Creates a {@code ScheduleBuilder} with the default details.
     */
    public ScheduleBuilder() {
        name = new ScheduleName(DEFAULT_SCHEDULE_NAME);
        description = new ScheduleDescription(DEFAULT_DESCRIPTION);
        dateTime = new ScheduleDateTime(DEFAULT_DATETIME);
    }

    /**
     * Initializes the ScheduleBuilder with the data of {@code scheduleToCopy}.
     */
    public ScheduleBuilder(Schedule scheduleToCopy) {
        name = scheduleToCopy.getScheduleName();
        description = scheduleToCopy.getScheduleDescription();
        dateTime = scheduleToCopy.getScheduleDateTime();
    }

    /**
     * Sets the {@code ScheduleName} of the {@code Schedule} that we are building.
     */
    public ScheduleBuilder withScheduleName(String name) {
        this.name = new ScheduleName(name);
        return this;
    }

    /**
     * Sets the {@code ScheduleDescription} of the {@code Schedule} that we are building.
     */
    public ScheduleBuilder withScheduleDescription(String description) {
        this.description = new ScheduleDescription(description);
        return this;
    }

    /**
     * Sets the {@code ScheduleDateTime} of the {@code Schedule} that we are building.
     */
    public ScheduleBuilder withPhone(String dateTime) {
        this.dateTime = new ScheduleDateTime(dateTime);
        return this;
    }

    public Schedule build() {
        return new Schedule(name, description, dateTime);
    }
}

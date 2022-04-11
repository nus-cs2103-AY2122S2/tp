package manageezpz.testutil;

import manageezpz.model.task.Date;
import manageezpz.model.task.Description;
import manageezpz.model.task.Event;
import manageezpz.model.task.Time;


public class EventBuilder {

    public static final String TASK_DESCRIPTION = "read book";
    public static final String TASK_DATE = "2022-03-20";
    public static final String TASK_START_TIME = "1800";
    public static final String TASK_END_TIME = "2100";

    private Description description;
    private Date date;
    private Time startTime;
    private Time endTime;

    /**
     * Creates a {@code EventBuilder} with the default details.
     */
    public EventBuilder() {
        description = new Description(TASK_DESCRIPTION);
        date = new Date(TASK_DATE);
        startTime = new Time(TASK_START_TIME);
        endTime = new Time(TASK_END_TIME);
    }

    /**
     * Sets the {@code Description} of the {@code Task} that we are building.
     */
    public EventBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code Task} that we are building.
     */
    public EventBuilder withDate(String date) {
        this.date = new Date(date);
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code Task} that we are building.
     */
    public EventBuilder withStartTime(String startTime) {
        this.startTime = new Time(startTime);
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code Task} that we are building.
     */
    public EventBuilder withEndTime(String endTime) {
        this.endTime = new Time(endTime);
        return this;
    }

    public Event build() {
        return new Event(description, date, startTime, endTime);
    }
}

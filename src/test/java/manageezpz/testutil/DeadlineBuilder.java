package manageezpz.testutil;

import manageezpz.model.task.Date;
import manageezpz.model.task.Deadline;
import manageezpz.model.task.Description;
import manageezpz.model.task.Time;

public class DeadlineBuilder {
    public static final String TASK_DESCRIPTION = "read book";
    public static final String TASK_DATE = "2022-03-20";
    public static final String TASK_TIME = "2000";

    private Description description;
    private Date date;
    private Time time;

    /**
     * Creates a {@code DeadlineBuilder} with the default details.
     */
    public DeadlineBuilder() {
        description = new Description(TASK_DESCRIPTION);
        date = new Date(TASK_DATE);
        time = new Time(TASK_TIME);
    }

    /**
     * Sets the {@code Description} of the {@code Task} that we are building.
     */
    public DeadlineBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code Task} that we are building.
     */
    public DeadlineBuilder withDate(String date) {
        this.date = new Date(date);
        return this;
    }


    /**
     * Sets the {@code Time} of the {@code Task} that we are building.
     */
    public DeadlineBuilder withTime(String time) {
        this.time = new Time(time);
        return this;
    }

    public Deadline build() {
        return new Deadline(description, date, time);
    }
}

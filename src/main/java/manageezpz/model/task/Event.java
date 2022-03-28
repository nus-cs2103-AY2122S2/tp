package manageezpz.model.task;

import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected String type;
    protected Description description;
    private Date date;
    private Time startTime;
    private Time endTime;

    /**
     * Constructor for the Task class.
     *
     * @param taskDescription information about the task.
     */
    public Event(Description taskDescription, Date date, Time startTime, Time endTime) {
        this.description = taskDescription;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.type = "event";
    }

    public Date getDate() {
        return date;
    }

    public Time getStartTime() {
        return startTime;
    }

    public Time getEndTime() {
        return endTime;
    }


    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public Description getDescription() {
        return this.description;
    }

    @Override
    public String getDateTime() {
        return "at " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + " " + startTime.format(DateTimeFormatter.ofPattern("h:mm a")) + " to"
                + " " + endTime.format(DateTimeFormatter.ofPattern("h:mm a"));
    }

    /**
     * Returns the string representation of an event.
     * @return a string representation of the event, consisting of its description, formatted date,
     * starting time and ending time.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + getDescription()
                + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + " " + startTime.format(DateTimeFormatter.ofPattern("h:mm a")) + " to"
                + " " + endTime.format(DateTimeFormatter.ofPattern("h:mm a")) + ")";
    }
}

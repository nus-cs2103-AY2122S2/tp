package manageezpz.model.task;

import java.time.format.DateTimeFormatter;

/**
 * A class that represents an Event task.
 */
public class Event extends Task {
    protected String type;
    protected Description description;
    private Date date;
    private Time startTime;
    private Time endTime;

    /**
     * Constructor to initialize an instance of Event class with task
     * description, date, start time and end time.
     *
     * @param taskDescription Description of the task.
     * @param date Date at which the Event task is taking place.
     * @param startTime Start time of the Event task.
     * @param endTime End time of the Event task.
     */
    public Event(Description taskDescription, Date date, Time startTime, Time endTime) {
        this.type = "event";
        this.description = taskDescription;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Constructor to initialize an instance of Event class with an existing
     * Event object.
     *
     * @param event Event task
     */
    public Event(Event event) {
        this.type = event.getType();
        this.description = event.getDescription();
        this.date = event.getDate();
        this.startTime = event.getStartTime();
        this.endTime = event.getEndTime();
        this.isDone = event.isDone();
        this.priority = event.getPriority();
        this.assignees = event.getAssignees();
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

    public void setDescription(Description description) {
        this.description = description;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    @Override
    public String getDateTime() {
        return "at " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + " " + startTime.format(DateTimeFormatter.ofPattern("h:mm a")) + " to"
                + " " + endTime.format(DateTimeFormatter.ofPattern("h:mm a"));
    }

    /**
     * Returns the string representation of an Event task.
     *
     * @return The string representation of the event, consisting of its
     * description, formatted date and formatted start and end time.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + getDescription()
                + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + " " + startTime.format(DateTimeFormatter.ofPattern("h:mm a")) + " to"
                + " " + endTime.format(DateTimeFormatter.ofPattern("h:mm a")) + ")";
    }
}

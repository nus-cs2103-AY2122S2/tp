package manageezpz.model.task;

import java.time.format.DateTimeFormatter;

/**
 * A class that represents a Deadline task.
 */
public class Deadline extends Task {
    protected String type;
    protected Description description;
    private Date date;
    private Time time;

    /**
     * Constructor to initialize an instance of Deadline class with task
     * description, date and time.
     *
     * @param taskDescription Description of the task.
     * @param date Date by which the Deadline task needs to be completed.
     * @param time Time by which the Deadline task needs to be completed.
     */
    public Deadline(Description taskDescription, Date date, Time time) {
        this.type = "deadline";
        this.description = taskDescription;
        this.date = date;
        this.time = time;
    }

    /**
     * Constructor to initialize an instance of Deadline class with an existing
     * Deadline object.
     *
     * @param deadline Deadline task.
     */
    public Deadline(Deadline deadline) {
        this.type = deadline.getType();
        this.description = deadline.getDescription();
        this.date = deadline.getDate();
        this.time = deadline.getTime();
        this.isDone = deadline.isDone();
        this.priority = deadline.getPriority();
        this.assignees = deadline.getAssignees();
    }

    public Date getDate() {
        return this.date;
    }

    public Time getTime() {
        return this.time;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTime(Time time) {
        this.time = time;
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
        return "by " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + " " + time.format(DateTimeFormatter.ofPattern("h:mm a"));
    }

    /**
     * Returns the string representation of a Deadline task.
     *
     * @return The string representation of the Deadline task, consisting of its
     * description and formatted date and time.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + getDescription()
                + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + " " + time.format(DateTimeFormatter.ofPattern("h:mm a")) + ")";
    }
}

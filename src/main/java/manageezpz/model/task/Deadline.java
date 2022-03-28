package manageezpz.model.task;

import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected String type;
    private Date date;
    private Time time;

    /**
     * Constructor for the Task class.
     *
     * @param taskDescription information about the task.
     */
    public Deadline(Description taskDescription, Date date, Time time) {
        super(taskDescription);
        this.date = date;
        this.time = time;
        this.type = "deadline";
    }

    public Date getDate() {
        return this.date;
    }

    public Time getTime() {
        return this.time;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String getDateTime() {
        return "by " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + " " + time.format(DateTimeFormatter.ofPattern("h:mm a"));
    }

    /**
     * Returns the string representation of a deadline.
     * @return a string representation of the deadline, consisting of its description
     * and formatted date and time.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + " " + time.format(DateTimeFormatter.ofPattern("h:mm a")) + ")";
    }
}

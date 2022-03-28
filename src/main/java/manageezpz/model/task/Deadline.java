package manageezpz.model.task;

import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private Date date;
    private Time time;
    protected String type;

    /**
     * Constructor for the Task class.
     *
     * @param taskDescription information about the task.
     */
    public Deadline(Description taskDescription, Date date, Time time) {
        super(taskDescription);
        this.date = date;
        this.time = time;
        super.setType("deadline");
        //setType();
    }

    public Date getDate() {
        return this.date;
    }

    public Time getTime() {
        return this.time;
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

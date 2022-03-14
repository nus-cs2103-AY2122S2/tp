package manageezpz.model.task;

import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private Date date;
    private Time time;
    /**
     * Constructor for the Task class.
     *
     * @param taskDescription information about the task.
     */
    public Deadline(Description taskDescription, Date date, Time time) {
        super(taskDescription);
        super.setType("deadline");
        super.setDeadlineTime(time);
        super.setTaskDate(date);
        this.date = date;
        this.time = time;
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

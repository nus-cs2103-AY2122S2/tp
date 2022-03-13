package manageezpz.model.task;

import java.time.LocalTime;

import manageezpz.model.person.Date;

public class Deadline extends Task {
    private Date date;
    private LocalTime time;
    /**
     * Constructor for the Task class.
     *
     * @param taskDescription information about the task.
     */
    public Deadline(Description taskDescription, Date date, LocalTime time) {
        super(taskDescription);
        this.date = date;
        this.time = time;
    }
}

package manageezpz.model.task;

import manageezpz.model.person.Date;

import java.time.LocalTime;

public class Event extends Task {
    private Date date;
    private LocalTime startTime;
    private LocalTime endTime;

    /**
     * Constructor for the Task class.
     *
     * @param taskDescription information about the task.
     */
    public Event(Description taskDescription, Date date, LocalTime startTime, LocalTime endTime) {
        super(taskDescription);
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
}

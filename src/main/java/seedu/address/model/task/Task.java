package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class Task {

    private final String description;
    private final String deadline;

    /**
     * Initializes a Task with a description and deadline.
     */
    public Task(String description, String deadline) {
        requireNonNull(description);
        requireNonNull(deadline);
        this.description = description;
        this.deadline = deadline;
    }

    /**
     * Getter to obtain description of task
     * @return Description of task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Getter to obtain deadline of task if any
     * @return Deadline of task
     */
    public String getDeadline() {
        return this.deadline;
    }

    /**
     * Returns true if both task have the same description.
     * Weaker notion of equality between two tasks
     * @param otherTask Task object to be compared to
     * @return boolean if the tasks compared have the same description
     */
    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }
        return otherTask != null && otherTask.getDescription().equals(getDescription());
    }

    /**
     * Check if the task has valid description or deadline.
     *
     * @param task a Task object
     * @return true if task has valid description or deadline, false otherwise
     */
    public boolean isValidTask(Task task) {
        String deadline = task.getDeadline();
        String description = task.getDescription();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            if (description.contains("t/") || description.contains("d/")) {
                // if deadline token is used in the description
                throw new ParseException("You cannot have 't/' prefix in the description!");
            }

            dateTimeFormatter.parse(deadline);

            simpleDateFormat.setLenient(false);
            simpleDateFormat.parse(deadline);

            LocalDate today = LocalDate.now(ZoneId.systemDefault());
            LocalDate date = LocalDate.parse(deadline, dateTimeFormatter);
            if (date.isBefore(today)) {
                throw new ParseException("Deadline is before today's date!\n" + AddTaskCommand.MESSAGE_USAGE);
            }
        } catch (DateTimeParseException | ParseException | java.text.ParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Returns true if both task have the same description and deadline.
     *
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Task)) {
            return false;
        }

        Task otherTask = (Task) other;
        return otherTask.getDescription().equals(getDescription())
            && otherTask.getDeadline().equals(getDeadline());
    }

    @Override
    public String toString() {
        return "Task: " + this.description + " " + deadline;
    }
}

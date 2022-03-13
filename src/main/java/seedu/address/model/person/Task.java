package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Represents a Task assigned to a person.
 * Guarantees: Task Name is present and not null, the task is either complete or not.
 */
public class Task {

    public static final String MESSAGE_CONSTRAINTS =
            "Task name should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    private final String taskName;
    private boolean isComplete;

    /**
     * Constructs a {@code Task}.
     * Default constructor, to be used only by {@code JsonAdaptedPerson}.
     */
    public Task() {
        this.taskName = null;
    }

    /**
     * Constructs a {@code Task}.
     *
     * @param taskName a valid taskName.
     */
    public Task(String taskName) {
        requireNonNull(taskName);
        checkArgument(isValidTaskName(taskName), MESSAGE_CONSTRAINTS);
        this.taskName = convertToTitleCase(taskName);
        isComplete = false;
    }

    /**
     * Converts the given name to title case.
     *
     * @param name the name of the person.
     * @return the persons name in title case.
     */
    private static String convertToTitleCase(String name) {

        String delimiter = " ";
        int firstCharIdx = 0;
        int secondCharIdx = 1;
        int onlyOneChar = 1;

        if (name.isEmpty()) {
            return name;
        }

        // Solution adapted from https://www.baeldung.com/java-string-title-case.
        return Arrays.stream(name.split(delimiter))
                .map(x -> x.length() != onlyOneChar
                        ? (x.substring(firstCharIdx, secondCharIdx).toUpperCase()
                        + x.substring(secondCharIdx).toLowerCase())
                        : x.substring(firstCharIdx, secondCharIdx).toUpperCase())
                .collect(Collectors.joining(delimiter));
    }

    /**
     * Returns true if a given string is a valid task name.
     *
     * @param test the string that is being tested.
     * @return a boolean value (true/false) depending if the task name is valid.
     */
    public static boolean isValidTaskName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Marks the task as complete.
     */
    public void markComplete() {
        isComplete = true;
    }

    /**
     * Marks the task as not complete.
     */
    public void markNotComplete() {
        isComplete = false;
    }

    /**
     * Gets the name of the task.
     *
     * @return the task name
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Checks whether the student has completed this task.
     *
     * @return true if the task is complete; false otherwise.
     */
    public boolean isTaskComplete() {
        return isComplete;
    }

    /**
     * Checks whether the given argument equals {@code taskName}.
     *
     * @param otherTaskName the task name to be checked.
     * @return true if the task name is similar to that of the current instance; false otherwise.
     */
    public boolean isTaskNameEqual(String otherTaskName) {
        return taskName.equalsIgnoreCase(otherTaskName);
    }

    @Override
    public String toString() {
        return taskName;
    }

    @Override
    public boolean equals(Object other) {
        return this == other // short circuit if same object
                || (other instanceof Task
                && getTaskName().equals(((Task) other).getTaskName()));
    }

}

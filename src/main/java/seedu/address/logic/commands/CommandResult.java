package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;
import java.util.Optional;

import seedu.address.model.student.Student;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /**
     * Help information should be shown to the user.
     */
    private final boolean isShowHelp;

    /**
     * The application should exit.
     */
    private final boolean isExit;

    /**
     * {@code view} command is executed
     */
    private final boolean isView;

    private final Optional<Student> student;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean isShowHelp, boolean isExit, Student student) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.isShowHelp = isShowHelp;
        this.isExit = isExit;
        this.student = Optional.ofNullable(student);
        this.isView = this.student.isPresent();
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, null);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public Student getStudent() {
        return student.orElseThrow(IllegalStateException::new);
    }

    public boolean isShowHelp() {
        return isShowHelp;
    }

    public boolean isView() {
        return isView;
    }

    public boolean isExit() {
        return isExit;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;
        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                && isShowHelp == otherCommandResult.isShowHelp
                && isExit == otherCommandResult.isExit
                && isView == otherCommandResult.isView
                && student.equals(otherCommandResult.student);
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, isShowHelp, isExit, isView, student);
    }

}

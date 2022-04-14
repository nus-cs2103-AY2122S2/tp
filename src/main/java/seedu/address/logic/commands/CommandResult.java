package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The application should exit. */
    private final boolean exit;

    /** The application should confirm if the user wants to clear the storage. */
    private final boolean clearRequest;

    /** The application should verify if the user is requesting to undo the last command. */
    private final boolean undoRequest;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit,
                         boolean clearRequest, boolean undoRequest) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.clearRequest = clearRequest;
        this.undoRequest = undoRequest;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code showHelp}, {@code exit} and {@code clearRequest},
     * with {@code undoRequest} field set to false.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit, boolean clearRequest) {
        this(feedbackToUser, showHelp, exit, clearRequest, false);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code showHelp} and {@code exit},
     * with {@code clearRequest} field set to false.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit) {
        this(feedbackToUser, showHelp, exit, false);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code clearRequest},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, boolean clearRequest) {
        this(feedbackToUser, false, false, clearRequest);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isExit() {
        return exit;
    }

    public boolean isClearRequest() {
        return clearRequest;
    };

    public boolean isUndoRequest() {
        return undoRequest;
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
                && showHelp == otherCommandResult.showHelp
                && exit == otherCommandResult.exit
                && clearRequest == otherCommandResult.clearRequest
                && undoRequest == otherCommandResult.undoRequest;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit, clearRequest, undoRequest);
    }

}

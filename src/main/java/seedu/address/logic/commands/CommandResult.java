package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean isHelpCommand;

    /** The application should exit. */
    private final boolean isExitCommand;

    /** The command is related to orders */
    private final boolean isOrderCommand;

    /** The command is related to persons */
    private final boolean isPersonCommand;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean isOrderCommand, boolean isPersonCommand, boolean isHelpCommand,
                         boolean isExitCommand) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.isOrderCommand = isOrderCommand;
        this.isPersonCommand = isPersonCommand;
        this.isHelpCommand = isHelpCommand;
        this.isExitCommand = isExitCommand;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, boolean isOrderCommand, boolean isPersonCommand) {
        this(feedbackToUser, isOrderCommand, isPersonCommand, false, false);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isHelpCommand() {
        return isHelpCommand;
    }

    public boolean isExitCommand() {
        return isExitCommand;
    }

    public boolean isOrderCommand() {
        return isOrderCommand;
    }

    public boolean isPersonCommand() {
        return isPersonCommand;
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
                && isHelpCommand == otherCommandResult.isHelpCommand
                && isExitCommand == otherCommandResult.isExitCommand;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, isHelpCommand, isExitCommand);
    }

}

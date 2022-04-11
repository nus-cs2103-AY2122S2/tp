package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    public final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** Add window should be shown to the user */
    private final boolean showAdd;

    /** Edoit window should be shown to the user */
    private final boolean showEdit;

    /** The application should exit. */
    private final boolean exit;

    /** The application should copy. */
    private final boolean isCopyCommand;

    /** The application should switch addressbook */
    private final boolean isSwitchCommand;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean showAdd,
                         boolean showEdit, boolean exit, boolean isCopy, boolean isSwitch) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.showAdd = showAdd;
        this.showEdit = showEdit;
        this.exit = exit;
        this.isCopyCommand = isCopy;
        this.isSwitchCommand = isSwitch;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false, false, false, false);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isShowAdd() {
        return showAdd;
    }

    public boolean isShowEdit() {
        return showEdit;
    }

    public boolean isExit() {
        return exit;
    }

    public boolean isCopyCommand() {
        return isCopyCommand;
    }

    public boolean isSwitchCommand() {
        return isSwitchCommand;
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
                && showAdd == otherCommandResult.showAdd
                && showEdit == otherCommandResult.showEdit
                && exit == otherCommandResult.exit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit);
    }

}

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

    /** The application should show focus mode. */
    private final boolean showFocus;

    /** The int index to show on the list*/
    private final int focusIndex;

    /** Flag to show if command is edited*/
    private final boolean isEdit;

    /** Flag to show if command is edited*/
    private final int editIndex;
    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit, boolean showFocus, int focusIndex,
                         boolean isEdit, int editIndex) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.showFocus = showFocus;
        this.focusIndex = focusIndex;
        this.isEdit = isEdit;
        this.editIndex = editIndex;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false, -1, false, -1);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value for EditCommand.
     */
    public CommandResult(String feedbackToUser, boolean isEdit, int index) {
        this(feedbackToUser, false, false, false, -1, isEdit, index);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public int getIndexFocus() {
        return focusIndex;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isExit() {
        return exit;
    }

    public boolean isShowFocus() {
        return showFocus;
    }

    public boolean isEdit() {
        return isEdit;
    }

    public int getEditIndex() {
        return editIndex;
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
                && exit == otherCommandResult.exit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit);
    }

}

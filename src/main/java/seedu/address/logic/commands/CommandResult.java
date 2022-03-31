package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Result display window should be resized. */
    private final boolean resizeWindow;

    /** Pie Chart should be shown to the user. */
    private final boolean summarisePieChart;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The application should exit. */
    private final boolean exit;

    /** Email information should be shown to the user. */
    private final boolean showEmail;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit, boolean summarisePieChart,
                         boolean showEmail, boolean resizeWindow) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.resizeWindow = resizeWindow;
        this.summarisePieChart = summarisePieChart;
        this.showHelp = showHelp;
        this.showEmail = showEmail;
        this.exit = exit;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false, false, false);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isSummarise() {
        return summarisePieChart;
    }

    public boolean isResize() {
        return resizeWindow;
    }

    public boolean isShowEmail() {
        return showEmail;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isExit() {
        return exit;
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
                && summarisePieChart == otherCommandResult.summarisePieChart
                && showEmail == otherCommandResult.showEmail
                && resizeWindow == otherCommandResult.resizeWindow;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit, summarisePieChart, showEmail, resizeWindow);
    }

}

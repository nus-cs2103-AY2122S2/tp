package seedu.trackbeau.logic.commands;

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

    /** plot staff chart */
    private final boolean plotStaffChart;

    /** plot service chart */
    private final boolean plotServiceChart;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp,
                         boolean exit, boolean plotStaffChart, boolean plotServiceChart) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.plotStaffChart = plotStaffChart;
        this.plotServiceChart = plotServiceChart;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false,
                false, false, false);
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

    public boolean isPlotStaffChart() {
        return plotStaffChart;
    }

    public boolean isPlotServiceChart() {
        return plotServiceChart;
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
                && plotStaffChart == otherCommandResult.plotStaffChart
                && plotServiceChart == otherCommandResult.plotServiceChart;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit,
                plotStaffChart, plotServiceChart);
    }

}

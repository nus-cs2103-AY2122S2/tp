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

    /** The application should switch to applicant tab. */
    private final boolean tabApplicant;

    /** The application should switch to job tab. */
    private final boolean tabJob;

    /** The application should reset */
    private final boolean resetInfoPanel;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit,
                         boolean tabApplicant, boolean tabJob, boolean resetPanel) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.tabApplicant = tabApplicant;
        this.tabJob = tabJob;
        this.resetInfoPanel = resetPanel;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false, false, false);
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit) {
        this(feedbackToUser, showHelp, exit, false, false, false);
    }

    /**
     * Constructs a {@code CommandResult} that makes appropriate changes to UI
     */
    public CommandResult(String feedbackToUser, boolean tabApplicant, boolean tabJob, boolean resetPanel) {
        this(feedbackToUser, false, false, tabApplicant, tabJob, resetPanel);
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

    public boolean isTabApplicant() {
        return tabApplicant;
    }

    public boolean isTabJob() {
        return tabJob;
    }

    public boolean isResetInfoPanel() {
        return resetInfoPanel;
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
                && tabApplicant == otherCommandResult.tabApplicant
                && tabJob == otherCommandResult.tabJob
                && resetInfoPanel == otherCommandResult.resetInfoPanel;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit, tabApplicant, tabJob, resetInfoPanel);
    }
}

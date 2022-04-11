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

    /** Match information should be shown to the user. */
    private final boolean showMatch;

    /** Images associated to client should be shown to the user. */
    private final boolean showImage;

    /** Favourites information should be shown to the user. */
    private final boolean showFavourites;

    /** Reminder information should be shown to the user. */
    private final boolean showReminders;

    /** Statistics information should be shown to the user. */
    private final boolean showStatistics;

    /** The application should exit. */
    private final boolean exit;


    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean showMatch, boolean showImage,
                         boolean showFavourites, boolean showStatistics, boolean showReminders, boolean exit) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.showMatch = showMatch;
        this.showImage = showImage;
        this.showFavourites = showFavourites;
        this.showStatistics = showStatistics;
        this.showReminders = showReminders;
        this.exit = exit;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false, false, false, false, false);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isShowMatch() {
        return showMatch;
    }


    public boolean isShowImage() {
        return showImage;
    }

    public boolean isShowFavourites() {
        return showFavourites;
    }

    public boolean isShowReminders() {
        return showReminders;
    }

    public boolean isShowStatistics() {
        return showStatistics;
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
                && showMatch == otherCommandResult.showMatch
                && showImage == otherCommandResult.showImage
                && showFavourites == otherCommandResult.showFavourites
                && showReminders == otherCommandResult.showReminders
                && showStatistics == otherCommandResult.showStatistics
                && exit == otherCommandResult.exit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, showMatch, showImage, showFavourites,
                showStatistics, showReminders, exit);
    }

    @Override
    public String toString() {
        return ("Feedback to user: " + feedbackToUser + "\n"
                + "Show help: " + showHelp + "\n"
                + "Exit: " + exit + "\n");
    }

}

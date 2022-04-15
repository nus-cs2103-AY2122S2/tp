package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Tabs.DEFAULT;

import java.util.Objects;

import seedu.address.model.image.Image;


/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /**
     * Help information should be shown to the user.
     */
    private final boolean showHelp;

    /**
     * The application should exit.
     */
    private final boolean exit;

    private final int tabPane;

    /**
     * The background image of StrategyBoard should be changed
     */
    private final boolean isLoadImage;

    /**
     * The background image of StrategyBoard
     */
    private final Image image;

    /**
     * The application should export image in user-selected directory.
     */
    private final boolean isExportCommand;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     * <p>
     * Specifically used for load-court and export commands.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit, int tabPane,
                         boolean isLoadImage, Image image, boolean isExportCommand) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.isLoadImage = isLoadImage;
        this.image = image;
        this.exit = exit;
        this.tabPane = tabPane;
        this.isExportCommand = isExportCommand;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, DEFAULT, false, null, false);
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

    public int getTabPane() {
        return tabPane;
    }

    public boolean isLoadImageCommand() {
        return isLoadImage;
    }

    public Image getBackgroundImage() {
        return image;
    }

    public boolean isExportCommand() {
        return isExportCommand;
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
                && tabPane == otherCommandResult.tabPane
                && isLoadImage == otherCommandResult.isLoadImage
                && image == otherCommandResult.image;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit, tabPane, isLoadImage, image);
    }

    @Override
    public String toString() {
        return "CommandResult{"
                + "feedbackToUser='" + feedbackToUser + '\''
                + ", showHelp=" + showHelp
                + ", exit=" + exit
                + ", tabPane=" + tabPane
                + ", isLoadImage=" + isLoadImage
                + ", image=" + image
                + ", isExportCommand=" + isExportCommand
                + '}';
    }
}

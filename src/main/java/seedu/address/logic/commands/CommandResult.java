package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.logic.commands.exceptions.NoInfoPanelTypeException;
import seedu.address.logic.commands.misc.InfoPanelTypes;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** The application should switch tab to selected tab. **/
    private final ViewTab viewTab;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The application should exit. */
    private final boolean exit;

    /** If InfoPanel of the UI should be updated **/
    private final boolean updateInfoPanel;

    private final InfoPanelTypes infoPanelType;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.viewTab = ViewTab.NONE;
        this.updateInfoPanel = false;
        this.infoPanelType = null;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and with the intention of updating the UI with a new {@code InfoPanel}
     *
     * @param feedbackToUser Feedback given to user from the command.
     * @param updateInfoPanel Boolean indicating if the {@code InfoPanel} of the UI is updated.
     * @param infoPanelType {@code InfoPanelTypes} value representing the type of {@code InfoPanel} that is updated.
     * @param viewTab {@code viewTab} value representing the type of {@code ViewTab} that will be switched.
     */
    public CommandResult(String feedbackToUser, boolean updateInfoPanel,
                         InfoPanelTypes infoPanelType, ViewTab viewTab) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = false;
        this.exit = false;
        this.updateInfoPanel = updateInfoPanel;
        this.infoPanelType = infoPanelType;
        this.viewTab = viewTab;
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, ViewTab viewTab) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = false;
        this.exit = false;
        this.viewTab = viewTab;
        this.updateInfoPanel = false;
        this.infoPanelType = null;
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public ViewTab toggleTo() {
        return viewTab;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isExit() {
        return exit;
    }

    public boolean isUpdateInfoPanel() {
        return updateInfoPanel;
    }

    public InfoPanelTypes getInfoPanelType() {
        if (updateInfoPanel) {
            return infoPanelType;
        }
        throw new NoInfoPanelTypeException();
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

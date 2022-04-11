package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.commons.core.index.Index;
import seedu.address.model.client.Client;
import seedu.address.model.client.SortCriteria;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** Tutorial should be shown to the user. */
    private final boolean showTutorial;

    /** The application should exit. */
    private final boolean exit;

    /** Meetings should be shown. */
    private final boolean showMeetings;

    /** Clients should be shown. */
    private final boolean showClients;

    /** Clients should be sorted **/
    private final SortCriteria sortClients;

    /** Index to show. */
    private final Index indexToShow;

    /** Client to update. */
    private final Client clientToUpdate;

    /** Client to delete. */
    private final Client clientToDelete;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean showTutorial, boolean exit,
                         boolean showMeetings, boolean showClients, SortCriteria sortClients, Index indexToShow,
                         Client clientToUpdate, Client clientToDelete) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.showTutorial = showTutorial;
        this.exit = exit;
        this.showMeetings = showMeetings;
        this.showClients = showClients;
        this.sortClients = sortClients;
        this.indexToShow = indexToShow;
        this.clientToUpdate = clientToUpdate;
        this.clientToDelete = clientToDelete;
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields for non-sort commands.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean showTutorial, boolean exit,
                         boolean showMeetings, boolean showClients, Index indexToShow, Client clientToUpdate) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.showTutorial = showTutorial;
        this.exit = exit;
        this.showMeetings = showMeetings;
        this.showClients = showClients;
        this.indexToShow = indexToShow;
        this.sortClients = null;
        this.clientToUpdate = clientToUpdate;
        this.clientToDelete = null;
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields for non-sort commands.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean showTutorial, boolean exit,
                         boolean showMeetings, boolean showClients, Index indexToShow, Client clientToUpdate,
                         Client clientToDelete) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.showTutorial = showTutorial;
        this.exit = exit;
        this.showMeetings = showMeetings;
        this.showClients = showClients;
        this.indexToShow = indexToShow;
        this.sortClients = null;
        this.clientToUpdate = clientToUpdate;
        this.clientToDelete = clientToDelete;
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields for sort commands.
     */
    public CommandResult(String feedbackToUser, SortCriteria sortClients) {
        this(feedbackToUser, false, false, false,
                false, false, sortClients, null, null, null);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false,
                false, false, null, null);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser} and {@code indexToShow},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, Index indexToShow) {
        this(feedbackToUser, false, false, false,
                false, false, indexToShow, null);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isShowTutorial() {
        return showTutorial;
    }

    public boolean isExit() {
        return exit;
    }

    public boolean isShowMeetings() {
        return showMeetings;
    }

    public boolean isShowClients() {
        return showClients;
    }

    public boolean isShowClient() {
        return indexToShow != null;
    }

    public boolean isSortClients() {
        return sortClients != null;
    }

    public Index getIndexToShow() {
        return indexToShow;
    }

    public boolean isUpdateClient() {
        return clientToUpdate != null;
    }

    public boolean isDeleteClient() {
        return clientToDelete != null;
    }


    public Client getClientToUpdate() {
        return clientToUpdate;
    }

    public Client getClientToDelete() {
        return clientToDelete;
    }

    public SortCriteria getSortCriteria() {
        return sortClients;
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
                && showTutorial == otherCommandResult.showTutorial
                && sortClients == otherCommandResult.sortClients
                && exit == otherCommandResult.exit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit);
    }

    @Override
    public String toString() {
        return feedbackToUser;
    }
}

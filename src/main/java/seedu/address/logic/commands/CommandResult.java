package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.model.person.Person;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /**
     * The person to be displayed in the ContactDetailsPanel.
     */
    private final Person person;

    /**
     * Help information should be shown to the user.
     */
    private final boolean showHelp;

    /**
     * The application should exit.
     */
    private final boolean exit;

    /**
     * The application should display the PersonListPanel.
     */
    private final boolean loadPersonList;

    /**
     * The application should display the ContactDetailsPanel.
     */
    private final boolean loadContactDetails;

    /**
     * The application should display the confirmation window.
     */
    private final boolean loadConfirmWindow;

    /**
     * The command clears meetings only.
     */
    private final boolean meetingClear;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit, boolean loadPersonList,
                         boolean loadContactDetails, boolean loadConfirmWindow, boolean meetingClear, Person person) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.loadPersonList = loadPersonList;
        this.loadContactDetails = loadContactDetails;
        this.loadConfirmWindow = loadConfirmWindow;
        this.meetingClear = meetingClear;
        this.person = person;
    }


    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false,
                false, false, false, null);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser}, {@code showHelp}, and {@code exit},
     * and other fields set to their default value.
     */
    public CommandResult(String feedBackToUser, boolean showHelp, boolean exit) {
        this(feedBackToUser, showHelp, exit, false, false,
                false, false, null);
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

    public boolean isLoadPersonList() {
        return loadPersonList;
    }

    public boolean isLoadContactDetails() {
        return loadContactDetails;
    }

    public boolean requiresConfirmation() {
        return loadConfirmWindow;
    }

    public boolean isMeetingClear() {
        return meetingClear;
    }

    public Person getPerson() {
        return person;
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

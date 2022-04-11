package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    public enum MeetingListStatusResult {
        UNCHANGED,
        UPCOMING,
        ARCHIVE
    }

    private final String feedbackToUser;

    /**
     * Help information should be shown to the user.
     */
    private final boolean showHelp;

    /**
     * The application should exit.
     */
    private final boolean exit;

    /**
     * Status of the meeting list displayed to the user.
     */
    private final MeetingListStatusResult meetingListStatus;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit,
                         MeetingListStatusResult meetingListStatus) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.meetingListStatus = meetingListStatus;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, MeetingListStatusResult.UNCHANGED);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser} and {@code meetingListStatus}
     * and other fields set to their default value.
     * @param feedbackToUser Feedback of the command to be displayed to the user.
     * @param meetingListStatus Status of the meeting list to be displayed to the user.
     */
    public CommandResult(String feedbackToUser, MeetingListStatusResult meetingListStatus) {
        this(feedbackToUser, false, false, meetingListStatus);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser}, {@code showHelp} and
     * {@code meetingListStatus}. {@code meetListStatus} is set to its default value.
     * @param feedbackToUser Feedback of the command to be displayed to the user.
     * @param showHelp Whether the help window should be displayed to the user.
     * @param exit Whether the program should exit.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit) {
        this(feedbackToUser, showHelp, exit, MeetingListStatusResult.UNCHANGED);
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

    public MeetingListStatusResult getMeetingListStatus() {
        return meetingListStatus;
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

        final CommandResult otherCommandResult = (CommandResult) other;
        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                && showHelp == otherCommandResult.showHelp
                && exit == otherCommandResult.exit
                && meetingListStatus == otherCommandResult.meetingListStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit, meetingListStatus);
    }

    @Override
    public String toString() {
        return feedbackToUser;
    }
}

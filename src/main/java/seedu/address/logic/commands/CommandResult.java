package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.model.person.Person;
import seedu.address.model.theme.Theme;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The application should exit. */
    private final boolean exit;

    private final boolean showProfile;

    private final boolean showTagList;

    private final boolean removeProfile;

    private final boolean switchTheme;

    private final boolean showGrabResult;

    private final String grabResult;

    private final Person person;

    private final Theme theme;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit, boolean showProfile,
                         boolean showTagList, boolean removeProfile, boolean switchTheme, boolean showGrabResult,
                         Person person, Theme theme, String grabResult) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.showProfile = showProfile;
        this.person = person;
        this.showTagList = showTagList;
        this.switchTheme = switchTheme;
        this.removeProfile = removeProfile;
        this.showGrabResult = showGrabResult;
        this.theme = theme;
        this.grabResult = grabResult;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false,
                false, false, false,
                false, null, null, null);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser}, {@code showHelp}, {@code exit},
     * and other fields set to their default value. Only applicable to HelpCommand and ExitCommand.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit) {
        this(feedbackToUser, showHelp, exit,
                false, false, false,
                false, false, null, null, null);

    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser}, {@code showProfile},
     * {@code showTagList}, {@code removeProfile}, {@code person}, and other fields
     * set to their default value. Only applicable to AddCommand, AttachTagCommand, DetachTagCommand,
     * DeleteCommand, EditCommand, ProfileCommand and RemarkCommand.
     */
    public CommandResult(String feedbackToUser,
                         boolean showProfile, boolean showTagList, boolean removeProfile, Person person) {
        this(feedbackToUser, false, false,
                showProfile, showTagList, removeProfile,
                false, false, person, null, null);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser}, {@code switchTheme}, {@code theme},
     * and other fields set to their default value.Only applicable to SwitchThemeCommand.
     */
    public CommandResult(String feedbackToUser, boolean switchTheme, Theme theme) {
        this(feedbackToUser, false, false,
                false, false, false, switchTheme,
                false, null, theme, null);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser}, {@code showGrabResult},
     * {@code grabResult}, and other fields set to their default value.Only applicable to SwitchThemeCommand.
     */
    public CommandResult(String feedbackToUser, boolean showGrabResult, String grabResult) {
        this(feedbackToUser, false, false,
                false, false, false, false,
                showGrabResult, null, null, grabResult);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public Person getPerson() {
        return person;
    }

    public Theme getTheme() {
        return theme;
    }

    public String getGrabResult() {
        return grabResult;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isExit() {
        return exit;
    }

    public boolean isShowProfile() {
        return showProfile;
    }

    public boolean isShowTagList() {
        return showTagList;
    }

    public boolean isRemoveProfile() {
        return removeProfile;
    }

    public boolean isSwitchTheme() {
        return switchTheme;
    }

    public boolean isShowGrabResult() {
        return showGrabResult;
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

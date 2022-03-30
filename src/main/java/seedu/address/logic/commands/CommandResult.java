package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;
import java.util.Objects;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean isShowHelp;

    /** The application should exit. */
    private final boolean isExit;

    /** The application should display the window allowing the user to import a CSV file. */
    private final boolean isImportFromCsv;

    /** The application should display the window allowing the user to export to a CSV file. */
    private final boolean isExportToCsv;

    /** The application should display the window showing the insurance packages available */
    private final boolean isShowPackages;


    /**
     * Constructs a {@code CommandResult} with the specified fields.
     * The HashMap can have keys for "showHelp", "exit", "importFromCsv", "exportToCsv".
     * The default values are false.
     */
    public CommandResult(String feedbackToUser, HashMap<String, Boolean> settings) {
        this.feedbackToUser = requireNonNull(feedbackToUser);

        boolean hasSettings = Objects.nonNull(settings);

        this.isShowHelp = hasSettings && settings.getOrDefault("showHelp", false);
        this.isExit = hasSettings && settings.getOrDefault("exit", false);
        this.isImportFromCsv = hasSettings && settings.getOrDefault("importFromCsv", false);
        this.isExportToCsv = hasSettings && settings.getOrDefault("exportToCsv", false);
        this.isShowPackages = hasSettings && settings.getOrDefault("showPackages", false);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, null);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowHelp() {
        return isShowHelp;
    }

    public boolean isExit() {
        return isExit;
    }

    public boolean isImportFromCsv() {
        return isImportFromCsv;
    }

    public boolean isExportToCsv() {
        return isExportToCsv;
    }

    public boolean isShowPackages() {
        return isShowPackages;
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
                && isShowHelp == otherCommandResult.isShowHelp
                && isExit == otherCommandResult.isExit
                && isImportFromCsv == otherCommandResult.isImportFromCsv
                && isExportToCsv == otherCommandResult.isExportToCsv
                && isShowPackages == otherCommandResult.isShowPackages;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                feedbackToUser, isShowHelp, isExit, isImportFromCsv, isExportToCsv, isShowPackages);
    }

}

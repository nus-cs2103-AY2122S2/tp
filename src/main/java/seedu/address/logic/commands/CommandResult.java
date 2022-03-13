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
    private final boolean showHelp;

    /** The application should exit. */
    private final boolean exit;

    /** The application should display the window allowing the user to import a CSV file. */
    private final boolean importFromCsv;

    /** The application should display the window allowing the user to export to a CSV file. */
    private final boolean exportToCsv;


    /**
     * Constructs a {@code CommandResult} with the specified fields.
     * The HashMap can have keys for "showHelp", "exit", "importFromCsv", "exportToCsv".
     * The default values are false.
     */
    public CommandResult(String feedbackToUser, HashMap<String, Boolean> settings) {
        this.feedbackToUser = requireNonNull(feedbackToUser);

        boolean hasSettings = Objects.nonNull(settings);

        this.showHelp = hasSettings && settings.getOrDefault("showHelp", false);
        this.exit = hasSettings && settings.getOrDefault("exit", false);
        this.importFromCsv = hasSettings && settings.getOrDefault("importFromCsv", false);
        this.exportToCsv = hasSettings && settings.getOrDefault("exportToCsv", false);
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
        return showHelp;
    }

    public boolean isExit() {
        return exit;
    }

    public boolean isImportFromCsv() {
        return importFromCsv;
    }

    public boolean isExportToCsv() {
        return exportToCsv;
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

package seedu.tinner.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.tinner.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private static final int DEFAULT_REMINDER_WINDOW = 7; //days

    private GuiSettings guiSettings = new GuiSettings();
    private Path companyListFilePath = Paths.get("data" , "companyList.json");
    private int reminderWindow = DEFAULT_REMINDER_WINDOW;

    /**
     * Creates a {@code UserPrefs} with default values.
     */
    public UserPrefs() {}

    /**
     * Creates a {@code UserPrefs} with the prefs in {@code userPrefs}.
     */
    public UserPrefs(ReadOnlyUserPrefs userPrefs) {
        this();
        resetData(userPrefs);
    }

    /**
     * Resets the existing data of this {@code UserPrefs} with {@code newUserPrefs}.
     */
    public void resetData(ReadOnlyUserPrefs newUserPrefs) {
        requireNonNull(newUserPrefs);
        setGuiSettings(newUserPrefs.getGuiSettings());
        setCompanyListFilePath(newUserPrefs.getCompanyListFilePath());
        setReminderWindow(newUserPrefs.getReminderWindow());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getCompanyListFilePath() {
        return companyListFilePath;
    }

    public void setCompanyListFilePath(Path companyListFilePath) {
        requireNonNull(companyListFilePath);
        this.companyListFilePath = companyListFilePath;
    }

    public int getReminderWindow() {
        return reminderWindow;
    }

    public void setReminderWindow(int newWindow) {
        this.reminderWindow = newWindow;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof UserPrefs)) { //this handles null as well.
            return false;
        }

        UserPrefs o = (UserPrefs) other;

        return guiSettings.equals(o.guiSettings)
                && companyListFilePath.equals(o.companyListFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, companyListFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location : " + companyListFilePath);
        return sb.toString();
    }

}

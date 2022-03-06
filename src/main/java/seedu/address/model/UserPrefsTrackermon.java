package seedu.address.model;

import seedu.address.commons.core.GuiSettings;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class UserPrefsTrackermon implements ReadOnlyUserPrefsTrackermon {

    private GuiSettings guiSettings = new GuiSettings();
    private Path showListFilePath = Paths.get("data" , "addressbook.json");

    /**
     * Creates a {@code UserPrefs} with default values.
     */
    public UserPrefsTrackermon() {}

    /**
     * Creates a {@code UserPrefs} with the prefs in {@code userPrefs}.
     */
    public UserPrefsTrackermon(ReadOnlyUserPrefsTrackermon userPrefs) {
        this();
        resetData(userPrefs);
    }

    /**
     * Resets the existing data of this {@code UserPrefs} with {@code newUserPrefs}.
     */
    public void resetData(ReadOnlyUserPrefsTrackermon newUserPrefs) {
        requireNonNull(newUserPrefs);
        setGuiSettings(newUserPrefs.getGuiSettings());
        setShowListFilePath(newUserPrefs.getShowListFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getShowListFilePath() {
        return showListFilePath;
    }

    public void setShowListFilePath(Path showListFilePath) {
        requireNonNull(showListFilePath);
        this.showListFilePath = showListFilePath;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof UserPrefsTrackermon)) { //this handles null as well.
            return false;
        }

        UserPrefsTrackermon o = (UserPrefsTrackermon) other;

        return guiSettings.equals(o.guiSettings)
                && showListFilePath.equals(o.showListFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, showListFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location : " + showListFilePath);
        return sb.toString();
    }
}

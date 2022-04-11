package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.address.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {
    private GuiSettings guiSettings = new GuiSettings();
    private Path recipeBookFilePath = Paths.get("data" , "recipebook.json");

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
        setRecipeBookFilePath(newUserPrefs.getRecipeBookFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getRecipeBookFilePath() {
        return recipeBookFilePath;
    }

    public void setRecipeBookFilePath(Path recipeBookFilePath) {
        requireNonNull(recipeBookFilePath);
        this.recipeBookFilePath = recipeBookFilePath;
    }

    @Override
    public boolean equals(Object o) {

        if (!(o instanceof UserPrefs)) {
            return false;
        }

        if (o == this) {
            return true;
        }

        UserPrefs other = (UserPrefs) o;
        return this.guiSettings.equals(other.guiSettings)
                && this.recipeBookFilePath.equals(other.recipeBookFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, recipeBookFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("GUI Settings : \n").append(guiSettings);
        sb.append("Local data file location : ").append(recipeBookFilePath);
        return sb.toString();
    }

}

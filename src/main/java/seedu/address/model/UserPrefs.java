package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.util.StringBuilderUtil;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private Path contactListFilePath = Paths.get("data" , "contactlist.json");
    private Path taskListFilePath = Paths.get("data", "tasklist.json");
    private Path moduleListFilePath = Paths.get("data", "modulelist.json");

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
        setContactListFilePath(newUserPrefs.getContactListFilePath());
        setTaskListFilePath(newUserPrefs.getTaskListFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getContactListFilePath() {
        return contactListFilePath;
    }

    public Path getModuleListFilePath() {
        return moduleListFilePath;
    }

    public void setContactListFilePath(Path contactListFilePath) {
        requireNonNull(contactListFilePath);
        this.contactListFilePath = contactListFilePath;
    }

    public Path getTaskListFilePath() {
        return taskListFilePath;
    }

    public void setTaskListFilePath(Path taskListFilePath) {
        requireNonNull(taskListFilePath);
        this.taskListFilePath = taskListFilePath;
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
                && contactListFilePath.equals(o.contactListFilePath)
                && taskListFilePath.equals(o.taskListFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, contactListFilePath);
    }

    @Override
    public String toString() {
        StringBuilderUtil stringBuilderUtil = StringBuilderUtil.getInstance();
        stringBuilderUtil.appendAll("Gui Settings : ", guiSettings,
                "\nLocal contactlist file location : ", contactListFilePath,
                "\nLocal tasklist file location : ", taskListFilePath,
                "\nLocal modulelist file location : ", moduleListFilePath);
        return stringBuilderUtil.getFormattedOutput();
    }

}

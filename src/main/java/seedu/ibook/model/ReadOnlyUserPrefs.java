package seedu.ibook.model;

import java.nio.file.Path;

import seedu.ibook.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getIBookFilePath();

}

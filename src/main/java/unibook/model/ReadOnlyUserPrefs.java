package unibook.model;

import java.nio.file.Path;

import unibook.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getUniBookFilePath();

}

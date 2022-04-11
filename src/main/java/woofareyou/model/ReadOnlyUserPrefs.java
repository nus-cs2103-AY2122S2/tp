package woofareyou.model;

import java.nio.file.Path;

import woofareyou.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getPetBookFilePath();

}

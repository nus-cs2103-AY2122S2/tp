package seedu.unite.model;

import java.nio.file.Path;

import seedu.unite.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getUniteFilePath();

}

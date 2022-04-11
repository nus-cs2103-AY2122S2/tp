package seedu.trackbeau.model;

import java.nio.file.Path;

import seedu.trackbeau.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getTrackBeauFilePath();

}

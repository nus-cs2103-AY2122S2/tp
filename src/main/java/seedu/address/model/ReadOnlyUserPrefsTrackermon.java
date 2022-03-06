package seedu.address.model;

import java.nio.file.Path;

import seedu.address.commons.core.GuiSettings;


public interface ReadOnlyUserPrefsTrackermon {

    GuiSettings getGuiSettings();

    Path getShowListFilePath();
}

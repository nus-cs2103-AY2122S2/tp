package seedu.address.model;

import java.nio.file.Path;

import seedu.address.commons.core.GuiSettings;


public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getShowListFilePath();
}

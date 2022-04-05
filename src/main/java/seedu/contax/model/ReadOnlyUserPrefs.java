package seedu.contax.model;

import java.nio.file.Path;

import seedu.contax.commons.core.GuiSettings;

/**
 * Represents an unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    /** Returns the {@code GuiSettings} stored in this UserPrefs. */
    GuiSettings getGuiSettings();

    /** Returns the path of the address book data file stored in this UserPrefs. */
    Path getAddressBookFilePath();

    /** Returns the path of the schedule data file stored in this UserPrefs. */
    Path getScheduleFilePath();

}

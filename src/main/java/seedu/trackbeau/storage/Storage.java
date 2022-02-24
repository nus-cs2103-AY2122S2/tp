package seedu.trackbeau.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.trackbeau.commons.exceptions.DataConversionException;
import seedu.trackbeau.model.ReadOnlyTrackBeau;
import seedu.trackbeau.model.ReadOnlyUserPrefs;
import seedu.trackbeau.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends TrackBeauStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getAddressBookFilePath();

    @Override
    Optional<ReadOnlyTrackBeau> readAddressBook() throws DataConversionException, IOException;

    @Override
    void saveAddressBook(ReadOnlyTrackBeau addressBook) throws IOException;

}

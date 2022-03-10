package seedu.address.logic.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyShowList;
import seedu.address.model.ReadOnlyUserPrefsTrackermon;
import seedu.address.model.UserPrefsTrackermon;

/**
 * API of the Storage component
 */
public interface StorageTrackermon extends ShowListStorage, UserPrefsStorageTrackermon {

    @Override
    Optional<UserPrefsTrackermon> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefsTrackermon userPrefs) throws IOException;

    @Override
    Path getShowListFilePath();

    @Override
    Optional<ReadOnlyShowList> readShowList() throws DataConversionException, IOException;

    @Override
    void saveShowList(ReadOnlyShowList showList) throws IOException;

}

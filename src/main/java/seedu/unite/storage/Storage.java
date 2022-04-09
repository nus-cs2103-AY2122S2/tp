package seedu.unite.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.unite.commons.exceptions.DataConversionException;
import seedu.unite.model.ReadOnlyUnite;
import seedu.unite.model.ReadOnlyUserPrefs;
import seedu.unite.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends UniteStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getUniteFilePath();

    @Override
    Optional<ReadOnlyUnite> readUnite() throws DataConversionException, IOException;

    @Override
    void saveUnite(ReadOnlyUnite unite) throws IOException;

}

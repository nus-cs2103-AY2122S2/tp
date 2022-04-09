package woofareyou.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import woofareyou.commons.core.LogsCenter;
import woofareyou.commons.exceptions.DataConversionException;
import woofareyou.commons.exceptions.IllegalValueException;
import woofareyou.commons.util.FileUtil;
import woofareyou.commons.util.JsonUtil;
import woofareyou.model.ReadOnlyPetBook;

/**
 * A class to access WoofAreYou data stored as a json file on the hard disk.
 */
public class JsonPetBookStorage implements PetBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonPetBookStorage.class);

    private Path filePath;

    public JsonPetBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getPetBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyPetBook> readPetBook() throws DataConversionException {
        return readPetBook(filePath);
    }

    /**
     * Similar to {@link #readPetBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyPetBook> readPetBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializablePetBook> jsonPetBook = JsonUtil.readJsonFile(
            filePath, JsonSerializablePetBook.class);
        if (!jsonPetBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonPetBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void savePetBook(ReadOnlyPetBook petBook) throws IOException {
        savePetBook(petBook, filePath);
    }

    /**
     * Similar to {@link #savePetBook(ReadOnlyPetBook)}. Also creates an attendance file for each pet.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void savePetBook(ReadOnlyPetBook petBook, Path filePath) throws IOException {
        requireNonNull(petBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializablePetBook(petBook), filePath);
    }

}

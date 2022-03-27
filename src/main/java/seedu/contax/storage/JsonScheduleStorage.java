package seedu.contax.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.contax.commons.core.LogsCenter;
import seedu.contax.commons.exceptions.DataConversionException;
import seedu.contax.commons.exceptions.IllegalValueException;
import seedu.contax.commons.util.FileUtil;
import seedu.contax.commons.util.JsonUtil;
import seedu.contax.model.ReadOnlyAddressBook;
import seedu.contax.model.ReadOnlySchedule;

/**
 * A class to access Schedule data stored as a json file on the hard disk.
 */
public class JsonScheduleStorage implements ScheduleStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonScheduleStorage.class);

    private Path filePath;

    public JsonScheduleStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getScheduleFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlySchedule> readSchedule(ReadOnlyAddressBook addressBook)
            throws DataConversionException {
        requireNonNull(addressBook);
        return readSchedule(filePath, addressBook);
    }

    /**
     * Similar to {@link #readSchedule(ReadOnlyAddressBook)}.
     *
     * @param filePath The Location of the data. Cannot be null.
     * @param addressBook An up-to-date copy of the AddressBook for finding persons associated to
     *                    appointments.
     * @throws DataConversionException If the file is not in the correct format.
     */
    public Optional<ReadOnlySchedule> readSchedule(Path filePath, ReadOnlyAddressBook addressBook)
            throws DataConversionException {
        requireNonNull(filePath);
        requireNonNull(addressBook);

        Optional<JsonSerializableSchedule> jsonSchedule = JsonUtil.readJsonFile(
                filePath, JsonSerializableSchedule.class);
        if (jsonSchedule.isEmpty()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonSchedule.get().toModelType(addressBook.getPersonList()));
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveSchedule(ReadOnlySchedule schedule) throws IOException {
        saveSchedule(schedule, filePath);
    }

    /**
     * Similar to {@link #saveSchedule(ReadOnlySchedule)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveSchedule(ReadOnlySchedule schedule, Path filePath) throws IOException {
        requireNonNull(schedule);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableSchedule(schedule), filePath);
    }

}

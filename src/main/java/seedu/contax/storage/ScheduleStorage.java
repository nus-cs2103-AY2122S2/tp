package seedu.contax.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.contax.commons.exceptions.DataConversionException;
import seedu.contax.model.ReadOnlyAddressBook;
import seedu.contax.model.ReadOnlySchedule;

/**
 * Represents a storage for {@link seedu.contax.model.Schedule}.
 */
public interface ScheduleStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getScheduleFilePath();

    /**
     * Returns Schedule data as a {@link ReadOnlySchedule}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @param addressBook An up-to-date copy of the AddressBook for finding persons associated to
     *                    appointments.
     * @throws DataConversionException If the data in storage is not in the expected format.
     * @throws IOException If there was any problem when reading from the storage.
     */
    Optional<ReadOnlySchedule> readSchedule(ReadOnlyAddressBook addressBook)
            throws DataConversionException, IOException;

    /**
     * @see #getScheduleFilePath().
     */
    Optional<ReadOnlySchedule> readSchedule(Path filePath, ReadOnlyAddressBook addressBook)
            throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlySchedule} to the storage.
     *
     * @param schedule Cannot be null.
     * @throws IOException If there was any problem writing to the file.
     */
    void saveSchedule(ReadOnlySchedule schedule) throws IOException;

    /**
     * @see #saveSchedule(ReadOnlySchedule)
     */
    void saveSchedule(ReadOnlySchedule schedule, Path filePath) throws IOException;

}

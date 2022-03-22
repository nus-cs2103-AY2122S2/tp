package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyInterviewSchedule;

/**
 * Represents a storage for {@link seedu.address.model.AddressBook}.
 */
public interface InterviewScheduleStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getInterviewScheduleFilePath();

    /**
     * Returns AddressBook data as a {@link ReadOnlyAddressBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyInterviewSchedule> readInterviewSchedule() throws DataConversionException, IOException;

    /**
     * @see #getInterviewScheduleFilePath()
     */
    Optional<ReadOnlyInterviewSchedule> readInterviewSchedule(Path filePath)
            throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyInterviewSchedule} to the storage.
     * @param interviewList cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveInterviewSchedule(ReadOnlyInterviewSchedule interviewList) throws IOException;

    /**
     * @see #saveInterviewSchedule(ReadOnlyInterviewSchedule)
     */
    void saveInterviewSchedule(ReadOnlyInterviewSchedule interviewList, Path filePath) throws IOException;

}

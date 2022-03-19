package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyStudentBook;
import seedu.address.model.ReadOnlyLessonBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of StudentBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private StudentBookStorage studentBookStorage;
    private LessonBookStorage lessonBookStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code StudentBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(StudentBookStorage studentBookStorage, LessonBookStorage lessonBookStorage,
                          UserPrefsStorage userPrefsStorage) {
        this.studentBookStorage = studentBookStorage;
        this.lessonBookStorage = lessonBookStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ StudentBook methods ==============================

    @Override
    public Path getAddressBookFilePath() {
        return studentBookStorage.getAddressBookFilePath();
    }

    @Override
    public Optional<ReadOnlyStudentBook> readAddressBook() throws DataConversionException, IOException {
        return readAddressBook(studentBookStorage.getAddressBookFilePath());
    }

    @Override
    public Optional<ReadOnlyStudentBook> readAddressBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return studentBookStorage.readAddressBook(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyStudentBook addressBook) throws IOException {
        saveAddressBook(addressBook, studentBookStorage.getAddressBookFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlyStudentBook addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        studentBookStorage.saveAddressBook(addressBook, filePath);
    }

    // ================ LessonBook methods ==============================

    @Override
    public Path getLessonBookFilePath() {
        return lessonBookStorage.getLessonBookFilePath();
    }

    @Override
    public Optional<ReadOnlyLessonBook> readLessonBook() throws DataConversionException, IOException {
        return readLessonBook(lessonBookStorage.getLessonBookFilePath());
    }

    @Override
    public Optional<ReadOnlyLessonBook> readLessonBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return lessonBookStorage.readLessonBook(filePath);
    }

    @Override
    public void saveLessonBook(ReadOnlyLessonBook lessonBook) throws IOException {
        saveLessonBook(lessonBook, lessonBookStorage.getLessonBookFilePath());
    }

    @Override
    public void saveLessonBook(ReadOnlyLessonBook lessonBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        lessonBookStorage.saveLessonBook(lessonBook, filePath);
    }

}

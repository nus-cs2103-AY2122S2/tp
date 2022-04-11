package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyContactList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.UniqueModuleList;
import seedu.address.model.tasks.ReadOnlyTaskList;

/**
 * Manages storage of ContactList data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private ContactListStorage contactListStorage;
    private UserPrefsStorage userPrefsStorage;
    private TaskListStorage taskListStorage;
    private ModuleListStorage moduleListStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code ContactListStorage}, {@code UserPrefStorage} and {@code
     * taskListStorage}
     */
    public StorageManager(ContactListStorage contactListStorage, UserPrefsStorage userPrefsStorage,
                          TaskListStorage taskListStorage, ModuleListStorage moduleListStorage) {
        this.contactListStorage = contactListStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.taskListStorage = taskListStorage;
        this.moduleListStorage = moduleListStorage;
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


    // ================ ContactList methods ==============================

    @Override
    public Path getContactListFilePath() {
        return contactListStorage.getContactListFilePath();
    }

    @Override
    public Optional<ReadOnlyContactList> readContactList() throws DataConversionException, IOException {
        return readContactList(contactListStorage.getContactListFilePath());
    }

    @Override
    public Optional<ReadOnlyContactList> readContactList(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return contactListStorage.readContactList(filePath);
    }

    @Override
    public void saveContactList(ReadOnlyContactList contactList) throws IOException {
        saveContactList(contactList, contactListStorage.getContactListFilePath());
    }

    @Override
    public void saveContactList(ReadOnlyContactList contactList, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        contactListStorage.saveContactList(contactList, filePath);
    }

    // ================ TaskList methods ==============================

    @Override
    public Path getTaskListFilePath() {
        return taskListStorage.getTaskListFilePath();
    }

    @Override
    public Optional<ReadOnlyTaskList> readTaskList() throws DataConversionException, IOException {
        return readTaskList(taskListStorage.getTaskListFilePath());
    }

    @Override
    public Optional<ReadOnlyTaskList> readTaskList(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return taskListStorage.readTaskList(filePath);
    }

    @Override
    public void saveTaskList(ReadOnlyTaskList taskList) throws IOException {
        this.saveTaskList(taskList, taskListStorage.getTaskListFilePath());
    }

    @Override
    public void saveTaskList(ReadOnlyTaskList taskList, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        taskListStorage.saveTaskList(taskList, filePath);
    }

    // ================ ModuleList methods ==============================

    @Override
    public Path getModuleListFilePath() {
        return taskListStorage.getTaskListFilePath();
    }

    @Override
    public Optional<UniqueModuleList> readModuleList() throws DataConversionException, IOException {
        return readModuleList(moduleListStorage.getModuleListFilePath());
    }

    @Override
    public Optional<UniqueModuleList> readModuleList(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return moduleListStorage.readModuleList(filePath);
    }

    @Override
    public void saveModuleList(UniqueModuleList moduleList) throws IOException {
        this.saveModuleList(moduleList, moduleListStorage.getModuleListFilePath());
    }

    @Override
    public void saveModuleList(UniqueModuleList taskList, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        moduleListStorage.saveModuleList(taskList, filePath);
    }
}

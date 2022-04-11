package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyContactList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.UniqueModuleList;
import seedu.address.model.tasks.ReadOnlyTaskList;

/**
 * API of the Storage component
 */
public interface Storage extends ContactListStorage, UserPrefsStorage, TaskListStorage, ModuleListStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getContactListFilePath();

    @Override
    Optional<ReadOnlyContactList> readContactList() throws DataConversionException, IOException;

    @Override
    void saveContactList(ReadOnlyContactList contactList) throws IOException;

    @Override
    Path getTaskListFilePath();

    @Override
    Optional<ReadOnlyTaskList> readTaskList() throws DataConversionException, IOException;

    @Override
    void saveTaskList(ReadOnlyTaskList taskList) throws IOException;

    @Override
    Path getModuleListFilePath();

    @Override
    Optional<UniqueModuleList> readModuleList() throws DataConversionException, IOException;

    @Override
    void saveModuleList(UniqueModuleList moduleList) throws IOException;

}

package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.PersonUtil.ADDRESS_DESC_AMY;
import static seedu.address.testutil.PersonUtil.EMAIL_DESC_AMY;
import static seedu.address.testutil.PersonUtil.NAME_DESC_AMY;
import static seedu.address.testutil.PersonUtil.PHONE_DESC_AMY;
import static seedu.address.testutil.PersonUtil.REMARK_DESC_AMY;
import static seedu.address.testutil.PersonUtil.TAG_DESC_FRIEND;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.SerializableAddressBookStorage;
import seedu.address.storage.SerializableTempAddressBookStorage;
import seedu.address.storage.Storage;
import seedu.address.storage.StorageManager;
import seedu.address.testutil.PersonUtil;

class LogicManagerIntegrationTest {
    @TempDir
    public Path testFolder;

    private Model model;
    private Storage storage;
    private LogicManager logicManager;

    @BeforeEach
    public void setUp() {
        SerializableAddressBookStorage addressBookStorage = new SerializableAddressBookStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        SerializableTempAddressBookStorage addressBookTempStorage = new SerializableTempAddressBookStorage(
                getTempFilePath("temp ab"));

        storage = new StorageManager(addressBookStorage, userPrefsStorage, addressBookTempStorage);
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        logicManager = new LogicManager(model, storage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void undoPrevModification_success()
            throws Exception {
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        // Execute add command
        String addCommand = AddCommand.COMMAND_WORD + NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + ADDRESS_DESC_AMY + REMARK_DESC_AMY + TAG_DESC_FRIEND;

        logicManager.execute(addCommand);
        logicManager.undoPrevModification();

        assertEquals(expectedModel.getAddressBook(), model.getAddressBook());
    }

    @Test
    public void noModificationDoneDoNotCreateTemporaryFile_success()
            throws Exception {
        assertFalse(logicManager.savePrevAddressBookDataInTemp(model.getAddressBook()));
    }

    @Test
    public void modificationDoneCreateTemporaryFile_success()
            throws Exception {
        Person validPerson = PersonUtil.AMY;
        AddressBook newAddressBook = new AddressBook(model.getAddressBook());
        newAddressBook.addPerson(validPerson);

        assertTrue(logicManager.savePrevAddressBookDataInTemp(newAddressBook));
    }

    /**
     * Checks if after executing a command, logicManager is able to successfully save the updated
     * address book.
     * @throws Exception checks for execution of LogicManager is done in {@code LogicManagerTest}
     */
    @Test
    public void execute_storageSuccessfullySaveAfterCommandExecuted()
            throws Exception {
        Person validPerson = PersonUtil.AMY;
        AddressBook newAddressBook = new AddressBook(model.getAddressBook());
        newAddressBook.addPerson(validPerson);

        // Execute add command
        String addCommand = AddCommand.COMMAND_WORD + NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + ADDRESS_DESC_AMY + REMARK_DESC_AMY + TAG_DESC_FRIEND;

        logicManager.execute(addCommand);
        assertTrue(storage.readAddressBook().isPresent());
        assertEquals(newAddressBook, storage.readAddressBook().get());
    }
}

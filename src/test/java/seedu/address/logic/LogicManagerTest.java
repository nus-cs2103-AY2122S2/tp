package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_UNRECOGNIZED_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.PersonUtil.ADDRESS_DESC_AMY;
import static seedu.address.testutil.PersonUtil.EMAIL_DESC_AMY;
import static seedu.address.testutil.PersonUtil.NAME_DESC_AMY;
import static seedu.address.testutil.PersonUtil.PHONE_DESC_AMY;
import static seedu.address.testutil.PersonUtil.REMARK_DESC_AMY;
import static seedu.address.testutil.PersonUtil.TAG_DESC_FRIEND;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.SerializableAddressBookStorage;
import seedu.address.storage.SerializableTempAddressBookStorage;
import seedu.address.storage.StorageManager;
import seedu.address.testutil.PersonUtil;

class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path testFolder;

    private Model model;
    private StorageManager storageManager;
    private LogicManager logicManager;

    @BeforeEach
    public void setUp() {
        SerializableAddressBookStorage addressBookStorage = new SerializableAddressBookStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        SerializableTempAddressBookStorage addressBookTempStorage = new SerializableTempAddressBookStorage(
                getTempFilePath("temp ab"));

        storageManager = new StorageManager(addressBookStorage, userPrefsStorage, addressBookTempStorage);
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        logicManager = new LogicManager(model, storageManager);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    //@@author DaneMarc
    @Test
    public void execute_commandChainingTest_valid() {
        Person validPerson1 = PersonUtil.AMY;
        Person validPerson2 = PersonUtil.BOB;

        try {
            logicManager.execute(PersonUtil.getAddCommand(validPerson1) + " | "
                    + PersonUtil.getAddCommand(validPerson2));
        } catch (CommandException e) {
            e.getStackTrace();
        }

        assertTrue(model.hasPerson(validPerson1));
        assertTrue(model.hasPerson(validPerson2));
    }

    @Test
    public void execute_commandChainingTest_breakChain() {
        Person validPerson1 = PersonUtil.AMY;
        Person validPerson2 = PersonUtil.BOB;

        try {
            logicManager.execute(PersonUtil.getAddCommand(validPerson1) + " | invalidCommand | "
                    + PersonUtil.getAddCommand(validPerson2));
        } catch (CommandException e) {
            e.getStackTrace();
        }

        assertFalse(model.hasPerson(validPerson1));
        assertFalse(model.hasPerson(validPerson2));
    }

    @Test
    public void execute_commandChainingTest_noChainCommand() {
        Person validPerson1 = PersonUtil.AMY;

        try {
            logicManager.execute(PersonUtil.getAddCommand(validPerson1) + " | ");
        } catch (CommandException e) {
            e.getStackTrace();
        }

        assertFalse(model.hasPerson(validPerson1));
    }

    //@@author
    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertCommandException(invalidCommand, "\"" + "uicfhmowqewca" + "\" - " + MESSAGE_UNRECOGNIZED_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteCommand = "delete 20";
        assertCommandException(deleteCommand, "\"" + "delete 20" + "\" - " + MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logicManager.getFilteredPersonList().remove(0));
    }

    //@@author LapisRaider
    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonAddressBookIoExceptionThrowingStub
        SerializableAddressBookStorage addressBookStorage =
                new SerializableAddressBookIoExceptionThrowingStub(testFolder.resolve("ioExceptionAddressBook.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(testFolder.resolve("ioExceptionUserPrefs.json"));
        SerializableTempAddressBookStorage addressBookTempStorage = new SerializableTempAddressBookStorage(
                getTempFilePath("temp ab"));
        StorageManager storage = new StorageManager(addressBookStorage, userPrefsStorage, addressBookTempStorage);
        logicManager = new LogicManager(model, storage);

        // Execute add command
        String addCommand = AddCommand.COMMAND_WORD + NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + ADDRESS_DESC_AMY + REMARK_DESC_AMY + TAG_DESC_FRIEND;
        Person expectedPerson = PersonUtil.AMY;
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), model.getUserPrefs());
        expectedModel.addPerson(expectedPerson);

        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void tempStorageSaveThrowsIoException_throwsCommandException() {
        // Setup LogicManager with SerializableTempAddressBookStorage
        SerializableAddressBookStorage addressBookStorage =
                new SerializableAddressBookStorage(testFolder.resolve("ioExceptionAddressBook.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(testFolder.resolve("ioExceptionUserPrefs.json"));
        SerializableTempAddressBookStorage addressBookTempStorage =
                new SerializableTempAddressBookIoExceptionThrowingStub(getTempFilePath("temp ab"));
        StorageManager storage = new StorageManager(addressBookStorage, userPrefsStorage, addressBookTempStorage);
        logicManager = new LogicManager(new ModelManager(), storage);

        AddressBook originalAddressBook = getTypicalAddressBook();
        String expectedMessage = LogicManager.TEMP_FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertThrows(CommandException.class,
                expectedMessage, () -> logicManager.savePrevAddressBookDataInTemp(originalAddressBook));
    }

    @Test
    public void tempStorageReadThrowsIoException_throwsCommandException() {
        // Setup LogicManager with SerializableTempAddressBookStorage
        SerializableAddressBookStorage addressBookStorage =
                new SerializableAddressBookStorage(testFolder.resolve("ioExceptionAddressBook.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(testFolder.resolve("ioExceptionUserPrefs.json"));
        SerializableTempAddressBookStorage addressBookTempStorage =
                new SerializableTempAddressBookIoExceptionThrowingStub(getTempFilePath("temp ab"));
        StorageManager storage = new StorageManager(addressBookStorage, userPrefsStorage, addressBookTempStorage);
        logicManager = new LogicManager(model, storage);

        String expectedMessage = LogicManager.TEMP_FILE_READ_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertThrows(CommandException.class,
                expectedMessage, () -> logicManager.undoPrevModification());
    }

    @Test
    public void tempStorageEmpty_throwsCommandException() {
        // Setup LogicManager with SerializableTempAddressBookReturnEmptyStub
        SerializableAddressBookStorage addressBookStorage =
                new SerializableAddressBookStorage(testFolder.resolve("ioExceptionAddressBook.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(testFolder.resolve("ioExceptionUserPrefs.json"));
        SerializableTempAddressBookStorage addressBookTempStorage =
                new SerializableTempAddressBookReturnEmptyStub(getTempFilePath("temp ab"));
        StorageManager storage = new StorageManager(addressBookStorage, userPrefsStorage, addressBookTempStorage);
        logicManager = new LogicManager(model, storage);

        String expectedMessage = UndoCommand.REACHED_UNDO_LIMIT;
        assertThrows(CommandException.class, expectedMessage, () -> logicManager.undoPrevModification());
    }

    //@@author
    /**
     * Executes the command and confirms that
     * - no exceptions are thrown <br>
     * - the feedback message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandSuccess(String inputCommand, String expectedMessage,
                                      Model expectedModel) throws CommandException, ParseException {
        CommandResult result = logicManager.execute(inputCommand);
        assertEquals(expectedMessage, result.getFeedbackToUser());
        assertEquals(expectedModel, model);
    }

    /**
     * Executes the command, confirms that a ParseException is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertParseException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, ParseException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that a CommandException is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, CommandException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that the exception is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
                                      String expectedMessage) {
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        assertCommandFailure(inputCommand, expectedException, expectedMessage, expectedModel);
    }

    /**
     * Executes the command and confirms that
     * - the {@code expectedException} is thrown <br>
     * - the resulting error message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     * @see #assertCommandSuccess(String, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
                                      String expectedMessage, Model expectedModel) {
        assertThrows(expectedException, expectedMessage, () -> logicManager.execute(inputCommand));
        assertEquals(expectedModel, model);
    }

    /**
     * A stub class to throw an {@code IOException} when the save method is called.
     */
    private static class SerializableAddressBookIoExceptionThrowingStub extends SerializableAddressBookStorage {
        private SerializableAddressBookIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }

    //@@author LapisRaider
    /**
     * A stub class to throw an {@code IOException} when the save and read temporary file method is called.
     */
    private static class SerializableTempAddressBookIoExceptionThrowingStub extends SerializableTempAddressBookStorage {
        private SerializableTempAddressBookIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void addNewTempAddressBookFile(ReadOnlyAddressBook addressBook) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }

        @Override
        public Optional<ReadOnlyAddressBook> popTempAddressFileData() throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }

    /**
     * A stub class to return an Optional empty when trying to get the temporary address file data.
     */
    private static class SerializableTempAddressBookReturnEmptyStub extends SerializableTempAddressBookStorage {
        private SerializableTempAddressBookReturnEmptyStub(Path filePath) {
            super(filePath);
        }

        @Override
        public Optional<ReadOnlyAddressBook> popTempAddressFileData() {
            return Optional.empty();
        }
    }
}

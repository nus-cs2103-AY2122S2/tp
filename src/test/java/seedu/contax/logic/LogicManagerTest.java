package seedu.contax.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.contax.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static seedu.contax.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.contax.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.contax.logic.commands.CommandTestUtil.APPOINTMENT_DATE;
import static seedu.contax.logic.commands.CommandTestUtil.APPOINTMENT_DURATION;
import static seedu.contax.logic.commands.CommandTestUtil.APPOINTMENT_NAME_ALONE;
import static seedu.contax.logic.commands.CommandTestUtil.APPOINTMENT_TIME;
import static seedu.contax.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.contax.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.contax.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.contax.testutil.Assert.assertThrows;
import static seedu.contax.testutil.TypicalAppointments.APPOINTMENT_ALONE;
import static seedu.contax.testutil.TypicalPersons.AMY;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.contax.logic.commands.AddAppointmentCommand;
import seedu.contax.logic.commands.AddPersonCommand;
import seedu.contax.logic.commands.CommandResult;
import seedu.contax.logic.commands.DeletePersonCommand;
import seedu.contax.logic.commands.ListPersonCommand;
import seedu.contax.logic.commands.exceptions.CommandException;
import seedu.contax.logic.parser.exceptions.ParseException;
import seedu.contax.model.Model;
import seedu.contax.model.ModelManager;
import seedu.contax.model.ReadOnlyAddressBook;
import seedu.contax.model.ReadOnlySchedule;
import seedu.contax.model.Schedule;
import seedu.contax.model.UserPrefs;
import seedu.contax.model.person.Person;
import seedu.contax.storage.JsonAddressBookStorage;
import seedu.contax.storage.JsonScheduleStorage;
import seedu.contax.storage.JsonUserPrefsStorage;
import seedu.contax.storage.StorageManager;
import seedu.contax.testutil.PersonBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonAddressBookStorage addressBookStorage =
                new JsonAddressBookStorage(temporaryFolder.resolve("addressBook.json"));
        JsonScheduleStorage scheduleStorage =
                new JsonScheduleStorage(temporaryFolder.resolve("schedule.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(addressBookStorage, scheduleStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteCommand = DeletePersonCommand.COMMAND_WORD + " 9";
        assertCommandException(deleteCommand, MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListPersonCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListPersonCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonAddressBookIoExceptionThrowingStub
        JsonAddressBookStorage addressBookStorage =
                new JsonAddressBookIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionAddressBook.json"));
        JsonScheduleStorage scheduleStorage =
                new JsonScheduleStorage(temporaryFolder.resolve("ioExceptionSchedule.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(addressBookStorage, scheduleStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand = AddPersonCommand.COMMAND_WORD + NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + ADDRESS_DESC_AMY;
        Person expectedPerson = new PersonBuilder(AMY).withTags().build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addPerson(expectedPerson);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);

        // Setup LogicManager with JsonScheduleIoExceptionThrowingStub
        addressBookStorage = new JsonAddressBookStorage(temporaryFolder.resolve("ioExceptionAddressBook.json"));
        scheduleStorage =
                new JsonScheduleIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionSchedule.json"));
        userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        storage = new StorageManager(addressBookStorage, scheduleStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addAppointmentCommand = AddAppointmentCommand.COMMAND_WORD + APPOINTMENT_NAME_ALONE
                + APPOINTMENT_DATE + APPOINTMENT_TIME + APPOINTMENT_DURATION;
        expectedModel.addAppointment(APPOINTMENT_ALONE);
        assertCommandFailure(addAppointmentCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredPersonList().remove(0));
    }

    @Test
    public void getScheduleItemList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getScheduleItemList().remove(0));
    }

    @Test
    public void userPrefGetters_noInput_notNull() {
        assertNotNull(logic.getAddressBookFilePath());
        assertNotNull(logic.getScheduleFilePath());
        assertNotNull(logic.getGuiSettings());
    }

    /**
     * Executes the command and confirms that
     * - no exceptions are thrown <br>
     * - the feedback message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandSuccess(String inputCommand, String expectedMessage,
            Model expectedModel) throws CommandException, ParseException {
        CommandResult result = logic.execute(inputCommand);
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
        Model expectedModel = new ModelManager(model.getAddressBook(), new Schedule(), new UserPrefs());
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
        assertThrows(expectedException, expectedMessage, () -> logic.execute(inputCommand));
        assertEquals(expectedModel, model);
    }

    /**
     * A stub class to throw an {@code IOException} when the save method is called.
     */
    private static class JsonAddressBookIoExceptionThrowingStub extends JsonAddressBookStorage {
        private JsonAddressBookIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }

    /**
     * A stub class to throw an {@code IOException} when the save method is called.
     */
    private static class JsonScheduleIoExceptionThrowingStub extends JsonScheduleStorage {
        private JsonScheduleIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveSchedule(ReadOnlySchedule schedule, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}

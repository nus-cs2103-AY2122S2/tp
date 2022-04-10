package seedu.unite.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.unite.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static seedu.unite.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.unite.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.unite.logic.commands.CommandTestUtil.COURSE_DESC_AMY;
import static seedu.unite.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.unite.logic.commands.CommandTestUtil.MATRICCARD_DESC_AMY;
import static seedu.unite.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.unite.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.unite.logic.commands.CommandTestUtil.TELEGRAM_DESC_AMY;
import static seedu.unite.testutil.Assert.assertThrows;
import static seedu.unite.testutil.TypicalPersons.AMY;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.unite.logic.commands.AddCommand;
import seedu.unite.logic.commands.CommandResult;
import seedu.unite.logic.commands.ListCommand;
import seedu.unite.logic.commands.exceptions.CommandException;
import seedu.unite.logic.parser.exceptions.ParseException;
import seedu.unite.model.Model;
import seedu.unite.model.ModelManager;
import seedu.unite.model.ReadOnlyUnite;
import seedu.unite.model.UserPrefs;
import seedu.unite.model.person.Person;
import seedu.unite.storage.JsonUniteStorage;
import seedu.unite.storage.JsonUserPrefsStorage;
import seedu.unite.storage.StorageManager;
import seedu.unite.testutil.PersonBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private final Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonUniteStorage uniteStorage =
                new JsonUniteStorage(temporaryFolder.resolve("unite.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(uniteStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteCommand = "delete 9";
        assertCommandException(deleteCommand);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonUniteIoExceptionThrowingStub
        JsonUniteStorage uniteStorage =
                new JsonUniteIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionUnite.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(uniteStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand = AddCommand.COMMAND_WORD + NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + ADDRESS_DESC_AMY + COURSE_DESC_AMY + MATRICCARD_DESC_AMY + TELEGRAM_DESC_AMY;
        Person expectedPerson = new PersonBuilder(AMY).withTags().build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addPerson(expectedPerson);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;

        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredPersonList().remove(0));
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
    private void assertParseException(String inputCommand) {
        assertCommandFailure(inputCommand, ParseException.class, MESSAGE_UNKNOWN_COMMAND);
    }

    /**
     * Executes the command, confirms that a CommandException is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandException(String inputCommand) {
        assertCommandFailure(inputCommand, CommandException.class, MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Executes the command, confirms that the exception is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
            String expectedMessage) {
        Model expectedModel = new ModelManager(model.getUnite(), new UserPrefs());
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
    private static class JsonUniteIoExceptionThrowingStub extends JsonUniteStorage {
        private JsonUniteIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveUnite(ReadOnlyUnite unite, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}

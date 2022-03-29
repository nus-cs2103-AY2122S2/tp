package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.SerializableAddressBookStorage;
import seedu.address.storage.SerializableTempAddressBookStorage;
import seedu.address.storage.StorageManager;
import seedu.address.testutil.PersonUtil;

public class LogicManagerTest {
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
}

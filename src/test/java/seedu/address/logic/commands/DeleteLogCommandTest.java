package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_LOG;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.KAREN;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.FriendName;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;


/**
 * Contains integration tests (interaction with Model) and unit tests for
 * {@code DeleteLogCommand}.
 */
public class DeleteLogCommandTest {

    private static final String MESSAGE_SUCCESS = DeleteLogCommand.MESSAGE_DELETE_LOG_SUCCESS;
    private static final String MESSAGE_PERSON_NOT_FOUND = DeleteLogCommand.MESSAGE_PERSON_NOT_FOUND;
    private static final String MESSAGE_LOG_NOT_FOUND = DeleteLogCommand.MESSAGE_LOG_NOT_FOUND;
    private static final String MESSAGE_INVALID_INDEX = DeleteLogCommand.MESSAGE_INVALID_INDEX;

    // ===== UNIT TESTS =====
    @Test
    public void constructor_invalidArguments_failure() {

        Index personIndex = INDEX_FIRST_PERSON;
        Index logIndex = INDEX_FIRST_LOG;
        FriendName personName = new FriendName(VALID_NAME_AMY);

        // all flag should not have logIndex
        assertThrows(AssertionError.class, () -> new DeleteLogCommand(
                true, true, personIndex, logIndex));

        // not for one person should not have person index
        assertThrows(AssertionError.class, () -> new DeleteLogCommand(
                false, false, personIndex, logIndex));

        // not for one person should not have person name
        assertThrows(AssertionError.class, () -> new DeleteLogCommand(
                false, false, personName, logIndex));

    }

    @Test
    public void equals() {

        DeleteLogCommand command;
        DeleteLogCommand other;
        Index personIndex = INDEX_FIRST_PERSON;
        FriendName name = new FriendName(VALID_NAME_AMY);

        // all flags equal
        command = new DeleteLogCommand(true);
        other = new DeleteLogCommand(true);
        assertEquals(command, other);

        // uses index comparison
        command = new DeleteLogCommand(true, true, personIndex, null);
        other = new DeleteLogCommand(true, true, personIndex, null);
        assertEquals(command, other);

        // uses name comparison
        command = new DeleteLogCommand(true, true, name, null);
        other = new DeleteLogCommand(true, true, name, null);
        assertEquals(command, other);

    }

    // ===== INTEGRATION TESTS =====

    @Test
    public void execute_validPersonValidLog_success() {

        // ===== WITH INDEX =====
        // instantiate model
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        // expectation
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Person personWithLog = KAREN; // has one log
        Person removedLogPerson = new PersonBuilder(KAREN).withLogs().build(); // no logs
        expectedModel.setPerson(personWithLog, removedLogPerson);

        // command
        DeleteLogCommand command = new DeleteLogCommand(true,
                false,
                Index.fromOneBased(9), // ninth person in typical address book
                Index.fromOneBased(1)); // first log

        assertCommandSuccess(command, model, MESSAGE_SUCCESS, expectedModel);

        // ===== WITH NAME =====
        // instantiate model
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        // expectation
        expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        removedLogPerson = new PersonBuilder(KAREN).withLogs().build(); // no logs
        expectedModel.setPerson(personWithLog, removedLogPerson);

        // command
        command = new DeleteLogCommand(true,
                false,
                KAREN.getName(), // has one log
                Index.fromOneBased(1)); // first log

        assertCommandSuccess(command, model, MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_indexBasedValidPersonAll_success() {

        // initialize
        Model model;
        Model expectedModel;
        Command command;

        // ===== PERSON HAS SOME LOGS =====
        // instantiate model
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        // expectation
        expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Person personWithLog = KAREN; // has one log
        Person removedLogPerson = new PersonBuilder(KAREN).withLogs().build(); // no logs
        expectedModel.setPerson(personWithLog, removedLogPerson);

        // command
        command = new DeleteLogCommand(true,
                true,
                Index.fromOneBased(9), // ninth person in typical address book
                null); // no log specified

        assertCommandSuccess(command, model, MESSAGE_SUCCESS, expectedModel);

        // ===== PERSON HAS NO LOGS ======
        // instantiate model
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        // expectation, no difference expected
        expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        // command
        command = new DeleteLogCommand(true,
                true,
                INDEX_FIRST_PERSON, // first person in typical address book has no logs
                null); // no log specified

        assertCommandSuccess(command, model, MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nameBasedValidPersonAll_success() {

        // initialize
        Model model;
        Model expectedModel;
        Command command;

        // ===== PERSON HAS SOME LOGS =====
        // instantiate model
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        // expectation
        expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Person personWithLog = KAREN; // has one log
        Person removedLogPerson = new PersonBuilder(KAREN).withLogs().build(); // no logs
        expectedModel.setPerson(personWithLog, removedLogPerson);

        // command
        command = new DeleteLogCommand(true,
                true,
                KAREN.getName(), // has one log
                null); // no log specified

        assertCommandSuccess(command, model, MESSAGE_SUCCESS, expectedModel);

        // ===== PERSON HAS NO LOGS ======
        // instantiate model
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        // expectation, no difference expected
        expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        // command
        command = new DeleteLogCommand(true,
                true,
                ALICE.getName(), // has no logs
                null); // no log specified

        assertCommandSuccess(command, model, MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_validAll_success() {

        // initialize
        Model model;
        Model expectedModel;
        Command command;
        Person removedLogPerson;

        // ===== SOME PEOPLE HAVE LOGS, SOME DON'T =====
        // instantiate model
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        // expectation
        expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        for (Person p : expectedModel.getFilteredPersonList()) {
            removedLogPerson = new PersonBuilder(p).withLogs().build(); // no logs
            expectedModel.setPerson(p, removedLogPerson);
        }

        // command
        command = new DeleteLogCommand(true); // no log specified

        assertCommandSuccess(command, model, MESSAGE_SUCCESS, expectedModel);

        // ===== NO PERSONS IN ADDRESS BOOK =====
        // instantiate model
        model = new ModelManager();

        // expectation
        expectedModel = new ModelManager();

        // command
        command = new DeleteLogCommand(true); // no log specified

        assertCommandSuccess(command, model, MESSAGE_SUCCESS, expectedModel);

    }

    @Test
    public void execute_indexBasedInvalidPerson_throwsCommandException() {

        // initialize
        Model model;
        Command command;

        // ===== NON-EMPTY ADDRESS BOOK BUT PERSON NOT IN LIST =====
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        command = new DeleteLogCommand(true,
                true,
                Index.fromOneBased(2000), // not in address book
                null); // no log specified

        assertCommandFailure(command, model, MESSAGE_INVALID_INDEX);

        // ===== EMPTY ADDRESS BOOK =====
        model = new ModelManager();
        command = new DeleteLogCommand(true,
                true,
                INDEX_FIRST_PERSON, // first person, but empty address book
                null); // no log specified

        assertCommandFailure(command, model, MESSAGE_INVALID_INDEX);

    }

    @Test
    public void execute_nameBasedInvalidPerson_throwsCommandException() {

        // initialize
        Model model;
        Command command;

        // ===== NON-EMPTY ADDRESS BOOK BUT PERSON NOT IN LIST =====
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        command = new DeleteLogCommand(true,
                true,
                new FriendName("some random bloke"), // not in address book
                null); // no log specified

        assertCommandFailure(command, model, MESSAGE_PERSON_NOT_FOUND);

        // ===== EMPTY ADDRESS BOOK =====
        model = new ModelManager();
        command = new DeleteLogCommand(true,
                true,
                ALICE.getName(), // first person usually, but empty address book
                null); // no log specified

        assertCommandFailure(command, model, MESSAGE_PERSON_NOT_FOUND);

    }

    @Test
    public void execute_indexBasedInvalidLog_throwsCommandException() {

        // initialize
        Model model;
        Command command;


        // ===== PERSON HAS LOGS BUT OUT OF BOUNDS =====
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        command = new DeleteLogCommand(true,
                false,
                Index.fromOneBased(9), // has only 1 log
                Index.fromOneBased(2)); // ask to delete 2nd log

        assertCommandFailure(command, model, MESSAGE_LOG_NOT_FOUND);

        // ===== PERSON HAS NO LOGS =====
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        command = new DeleteLogCommand(true,
                false,
                INDEX_FIRST_PERSON, // first person, but person has no logs
                Index.fromOneBased(1)); // ask to delete 1st log

        assertCommandFailure(command, model, MESSAGE_LOG_NOT_FOUND);

    }

    @Test
    public void execute_nameBasedInvalidLog_throwsCommandException() {

        // initialize
        Model model;
        Command command;


        // ===== PERSON HAS LOGS BUT OUT OF BOUNDS =====
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        command = new DeleteLogCommand(true,
                false,
                KAREN.getName(), // has only 1 log
                Index.fromOneBased(2)); // ask to delete 2nd log

        assertCommandFailure(command, model, MESSAGE_LOG_NOT_FOUND);

        // ===== PERSON HAS NO LOGS =====
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        command = new DeleteLogCommand(true,
                false,
                ALICE.getName(), // first person, but person has no logs
                Index.fromOneBased(1)); // ask to delete 1st log

        assertCommandFailure(command, model, MESSAGE_LOG_NOT_FOUND);

    }

}

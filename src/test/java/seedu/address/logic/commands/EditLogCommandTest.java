package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_LOG;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_LOG;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.KAREN;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.common.Description;
import seedu.address.model.person.FriendName;
import seedu.address.model.person.Log;
import seedu.address.model.person.LogName;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class EditLogCommandTest {

    private static final String MESSAGE_INVALID_INDEX = Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
    private static final String MESSAGE_PERSON_NOT_FOUND = EditLogCommand.MESSAGE_PERSON_NOT_FOUND;
    private static final String MESSAGE_SUCCESS = EditLogCommand.MESSAGE_EDIT_LOG_SUCCESS;
    private static final String MESSAGE_DUPLICATE_LOG = EditLogCommand.MESSAGE_DUPLICATE_LOG;
    private static final String MESSAGE_LOG_NOT_FOUND = EditLogCommand.MESSAGE_LOG_NOT_FOUND;


    // ===== UNIT TESTS =====
    @Test
    public void equals() {

        // initialize
        EditLogCommand command;
        EditLogCommand other;
        EditLogCommand.EditLogDescriptor descriptor;
        EditLogCommand.EditLogDescriptor otherDescriptor;
        Index targetIndex = INDEX_FIRST_PERSON;
        Index otherIndex = INDEX_SECOND_PERSON;
        Index targetLog = INDEX_FIRST_LOG;
        Index otherLog = INDEX_SECOND_LOG;
        FriendName targetName = new FriendName(VALID_NAME_AMY);
        FriendName otherName = new FriendName(VALID_NAME_BOB);
        String title = "some title";
        String otherTitle = "some other title";

        // same values -> true
        descriptor = new EditLogCommand.EditLogDescriptor();
        descriptor.setNewTitle(title);
        command = new EditLogCommand(targetIndex, targetLog, descriptor);
        other = new EditLogCommand(targetIndex, targetLog, descriptor);
        assertEquals(command, other);

        descriptor = new EditLogCommand.EditLogDescriptor();
        descriptor.setNewTitle(title);
        command = new EditLogCommand(targetName, targetLog, descriptor);
        other = new EditLogCommand(targetName, targetLog, descriptor);
        assertEquals(command, other);

        // same object -> true
        assertEquals(command, command);

        // null -> false
        assertNotEquals(command, null);

        // different type -> false
        assertNotEquals(command, 2);

        // different index -> false
        descriptor = new EditLogCommand.EditLogDescriptor();
        descriptor.setNewTitle(title);
        command = new EditLogCommand(targetIndex, targetLog, descriptor);
        other = new EditLogCommand(otherIndex, targetLog, descriptor);
        assertNotEquals(command, other);

        // different name -> false
        descriptor = new EditLogCommand.EditLogDescriptor();
        descriptor.setNewTitle(title);
        command = new EditLogCommand(targetName, targetLog, descriptor);
        other = new EditLogCommand(otherName, targetLog, descriptor);
        assertNotEquals(command, other);

        // different log index -> false
        descriptor = new EditLogCommand.EditLogDescriptor();
        descriptor.setNewTitle(title);
        command = new EditLogCommand(targetIndex, targetLog, descriptor);
        other = new EditLogCommand(targetIndex, otherLog, descriptor);
        assertNotEquals(command, other);

        // different descriptor -> false
        otherDescriptor = new EditLogCommand.EditLogDescriptor();
        otherDescriptor.setNewTitle(otherTitle);
        descriptor = new EditLogCommand.EditLogDescriptor();
        descriptor.setNewTitle(title);
        command = new EditLogCommand(targetIndex, targetLog, descriptor);
        other = new EditLogCommand(targetIndex, targetLog, otherDescriptor);
        assertNotEquals(command, other);

        // everything same, one by name, one by index -> false
        otherDescriptor = new EditLogCommand.EditLogDescriptor();
        otherDescriptor.setNewTitle(otherTitle);
        descriptor = new EditLogCommand.EditLogDescriptor();
        descriptor.setNewTitle(title);
        command = new EditLogCommand(targetIndex, targetLog, descriptor);
        other = new EditLogCommand(targetName, targetLog, descriptor);
        assertNotEquals(command, other);
    }

    /* ===== INTEGRATION TESTS WITH MODEL =====
    Integration tests with Model component. Note that we do tests for both filtered and unfiltered lists
    as these states control what the user sees, but regardless the changes apply to the true underlying
    data state of the model.
    */
    @Test
    public void execute_withIndexValidIInputUnfilteredList_success() {

        String newTitle = "some new title";
        String newDescription = "some new description";

        // ===== ONLY TITLE =====
        // instantiate model
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        // expectation
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Person personWithLog = KAREN; // has one log
        Log originalLog = KAREN.getLogs().get(0);
        Log editedLog = new Log(new LogName(newTitle), originalLog.getDescription());
        Person editedLogPerson = new PersonBuilder(KAREN).withLogs(editedLog).build(); // one new log
        expectedModel.setPerson(personWithLog, editedLogPerson);

        // command
        EditLogCommand.EditLogDescriptor descriptor = new EditLogCommand.EditLogDescriptor();
        descriptor.setNewTitle(newTitle);
        EditLogCommand command = new EditLogCommand(
                Index.fromOneBased(9), // ninth person in typical address book
                Index.fromOneBased(1), // first log
                descriptor);

        assertCommandSuccess(command, model, MESSAGE_SUCCESS, expectedModel);

        // ===== ONLY DESCRIPTION =====
        // instantiate model
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        // expectation
        expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        personWithLog = KAREN; // has one log
        originalLog = KAREN.getLogs().get(0);
        editedLog = new Log(originalLog.getTitle(), new Description(newDescription));
        editedLogPerson = new PersonBuilder(KAREN).withLogs(editedLog).build(); // one new log
        expectedModel.setPerson(personWithLog, editedLogPerson);

        // command
        descriptor = new EditLogCommand.EditLogDescriptor();
        descriptor.setNewDescription(newDescription);
        command = new EditLogCommand(
                Index.fromOneBased(9), // ninth person in typical address book
                Index.fromOneBased(1), // first log
                descriptor);

        assertCommandSuccess(command, model, MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_withIndexValidInputFilteredList_success() {

        String newTitle = "some new title";
        String newDescription = "some new description";

        // ===== ONLY TITLE =====
        // instantiate model
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        showPersonAtIndex(model, Index.fromOneBased(9)); // apply filter

        // expectation
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        showPersonAtIndex(expectedModel, Index.fromOneBased(9)); // apply filter
        Person personWithLog = KAREN; // has one log
        Log originalLog = KAREN.getLogs().get(0);
        Log editedLog = new Log(new LogName(newTitle), originalLog.getDescription());
        Person editedLogPerson = new PersonBuilder(KAREN).withLogs(editedLog).build(); // one new log
        expectedModel.setPerson(personWithLog, editedLogPerson);

        // command
        EditLogCommand.EditLogDescriptor descriptor = new EditLogCommand.EditLogDescriptor();
        descriptor.setNewTitle(newTitle);
        EditLogCommand command = new EditLogCommand(
                Index.fromOneBased(1), // only person left in list
                Index.fromOneBased(1), // first log
                descriptor);

        assertCommandSuccess(command, model, MESSAGE_SUCCESS, expectedModel);

        // ===== ONLY DESCRIPTION =====
        // instantiate model
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        showPersonAtIndex(model, Index.fromOneBased(9)); // apply filter

        // expectation
        expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        showPersonAtIndex(expectedModel, Index.fromOneBased(9)); // apply filter
        personWithLog = KAREN; // has one log
        originalLog = KAREN.getLogs().get(0);
        editedLog = new Log(originalLog.getTitle(), new Description(newDescription));
        editedLogPerson = new PersonBuilder(KAREN).withLogs(editedLog).build(); // one new log
        expectedModel.setPerson(personWithLog, editedLogPerson);

        // command
        descriptor = new EditLogCommand.EditLogDescriptor();
        descriptor.setNewDescription(newDescription);
        command = new EditLogCommand(
                Index.fromOneBased(1), // only person left in list
                Index.fromOneBased(1), // first log
                descriptor);

        assertCommandSuccess(command, model, MESSAGE_SUCCESS, expectedModel);

    }

    @Test
    public void execute_withNameValidIInputUnfilteredList_success() {
        String newTitle = "some new title";
        String newDescription = "some new description";

        // ===== ONLY TITLE =====
        // instantiate model
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        // expectation
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Person personWithLog = KAREN; // has one log
        Log originalLog = KAREN.getLogs().get(0);
        Log editedLog = new Log(new LogName(newTitle), originalLog.getDescription());
        Person editedLogPerson = new PersonBuilder(KAREN).withLogs(editedLog).build(); // one new log
        expectedModel.setPerson(personWithLog, editedLogPerson);

        // command
        EditLogCommand.EditLogDescriptor descriptor = new EditLogCommand.EditLogDescriptor();
        descriptor.setNewTitle(newTitle);
        EditLogCommand command = new EditLogCommand(
                KAREN.getName(), // ninth person in typical address book
                Index.fromOneBased(1), // first log
                descriptor);

        assertCommandSuccess(command, model, MESSAGE_SUCCESS, expectedModel);

        // ===== ONLY DESCRIPTION =====
        // instantiate model
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        // expectation
        expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        personWithLog = KAREN; // has one log
        originalLog = KAREN.getLogs().get(0);
        editedLog = new Log(originalLog.getTitle(), new Description(newDescription));
        editedLogPerson = new PersonBuilder(KAREN).withLogs(editedLog).build(); // one new log
        expectedModel.setPerson(personWithLog, editedLogPerson);

        // command
        descriptor = new EditLogCommand.EditLogDescriptor();
        descriptor.setNewDescription(newDescription);
        command = new EditLogCommand(
                KAREN.getName(), // ninth person in typical address book
                Index.fromOneBased(1), // first log
                descriptor);

        assertCommandSuccess(command, model, MESSAGE_SUCCESS, expectedModel);

    }

    @Test
    public void execute_withNameValidIInputFilteredList_success() {
        String newTitle = "some new title";
        String newDescription = "some new description";

        // ===== ONLY TITLE =====
        // instantiate model
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        showPersonAtIndex(model, Index.fromOneBased(9)); // apply filter

        // expectation
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        showPersonAtIndex(expectedModel, Index.fromOneBased(9)); // apply filter
        Person personWithLog = KAREN; // has one log
        Log originalLog = KAREN.getLogs().get(0);
        Log editedLog = new Log(new LogName(newTitle), originalLog.getDescription());
        Person editedLogPerson = new PersonBuilder(KAREN).withLogs(editedLog).build(); // one new log
        expectedModel.setPerson(personWithLog, editedLogPerson);

        // command
        EditLogCommand.EditLogDescriptor descriptor = new EditLogCommand.EditLogDescriptor();
        descriptor.setNewTitle(newTitle);
        EditLogCommand command = new EditLogCommand(
                KAREN.getName(), // ninth person in typical address book
                Index.fromOneBased(1), // first log
                descriptor);

        assertCommandSuccess(command, model, MESSAGE_SUCCESS, expectedModel);

        // ===== ONLY DESCRIPTION =====
        // instantiate model
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        showPersonAtIndex(model, Index.fromOneBased(9)); // apply filter

        // expectation
        expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        showPersonAtIndex(expectedModel, Index.fromOneBased(9)); // apply filter
        personWithLog = KAREN; // has one log
        originalLog = KAREN.getLogs().get(0);
        editedLog = new Log(originalLog.getTitle(), new Description(newDescription));
        editedLogPerson = new PersonBuilder(KAREN).withLogs(editedLog).build(); // one new log
        expectedModel.setPerson(personWithLog, editedLogPerson);

        // command
        descriptor = new EditLogCommand.EditLogDescriptor();
        descriptor.setNewDescription(newDescription);
        command = new EditLogCommand(
                KAREN.getName(), // ninth person in typical address book
                Index.fromOneBased(1), // first log
                descriptor);

        assertCommandSuccess(command, model, MESSAGE_SUCCESS, expectedModel);

    }

    @Test
    public void execute_duplicateLog_failure() {
        // instantiate model
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        // ===== WITH NAME =====
        // command
        EditLogCommand.EditLogDescriptor descriptor = new EditLogCommand.EditLogDescriptor();
        descriptor.setNewTitle(KAREN.getLogs().get(0).getTitle().toString()); // has one log
        descriptor.setNewDescription(KAREN.getLogs().get(0).getDescription().toString());
        EditLogCommand command = new EditLogCommand(
                KAREN.getName(), // ninth person in typical address book
                Index.fromOneBased(1), // first log
                descriptor);

        assertCommandFailure(command, model, MESSAGE_DUPLICATE_LOG);

        // ===== WITH INDEX =====
        // command
        descriptor = new EditLogCommand.EditLogDescriptor();
        descriptor.setNewTitle(KAREN.getLogs().get(0).getTitle().toString()); // has one log
        descriptor.setNewDescription(KAREN.getLogs().get(0).getDescription().toString());
        command = new EditLogCommand(
                Index.fromOneBased(9), // ninth person in typical address book
                Index.fromOneBased(1), // first log
                descriptor);
        assertCommandFailure(command, model, MESSAGE_DUPLICATE_LOG);
    }

    @Test
    public void execute_invalidInputUnfilteredList_failure() {

        // instantiate model
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        // ===== INVALID INDEX =====
        // command
        EditLogCommand.EditLogDescriptor descriptor = new EditLogCommand.EditLogDescriptor();
        descriptor.setNewTitle(KAREN.getLogs().get(0).getTitle().toString()); // has one log
        descriptor.setNewDescription(KAREN.getLogs().get(0).getDescription().toString());
        EditLogCommand command = new EditLogCommand(
                Index.fromOneBased(1234), // out of bounds
                Index.fromOneBased(1), // first log
                descriptor);

        assertCommandFailure(command, model, MESSAGE_INVALID_INDEX);


        // ===== INVALID NAME =====
        // command
        descriptor = new EditLogCommand.EditLogDescriptor();
        descriptor.setNewTitle(KAREN.getLogs().get(0).getTitle().toString()); // has one log
        descriptor.setNewDescription(KAREN.getLogs().get(0).getDescription().toString());
        command = new EditLogCommand(
                new FriendName("some random name"), // not inside
                Index.fromOneBased(1), // first log
                descriptor);

        assertCommandFailure(command, model, MESSAGE_PERSON_NOT_FOUND);

        // ===== INVALID LOG =====
        // command
        descriptor = new EditLogCommand.EditLogDescriptor();
        descriptor.setNewTitle(KAREN.getLogs().get(0).getTitle().toString()); // has one log
        descriptor.setNewDescription(KAREN.getLogs().get(0).getDescription().toString());
        command = new EditLogCommand(
                Index.fromOneBased(9), // 9th person
                Index.fromOneBased(2), // only has 1 log
                descriptor);

        assertCommandFailure(command, model, MESSAGE_LOG_NOT_FOUND);
    }
}

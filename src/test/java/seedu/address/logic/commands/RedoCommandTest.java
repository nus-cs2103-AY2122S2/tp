package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.RedoCommand.COMMAND_WORD;
import static seedu.address.logic.commands.RedoCommand.MESSAGE_REDO_FAILURE;
import static seedu.address.logic.commands.RedoCommand.MESSAGE_REDO_SUCCESS;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TypicalPersons;

/**
 * Contains integration tests (interaction with the Model) for {@code RedoCommand}.
 */
public class RedoCommandTest {

    @Test
    public void execute_redoAsFirstFunction_failure() {
        Model model = new ModelManager();

        assertCommandFailure(new RedoCommand(), model, MESSAGE_REDO_FAILURE);
    }

    @Test
    public void execute_redoAfterAddFunction_failure() {
        Model model = new ModelManager();

        model.addPerson(TypicalPersons.ALICE);

        assertCommandFailure(new RedoCommand(), model, MESSAGE_REDO_FAILURE);

        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        model.addPerson(TypicalPersons.HOON);

        assertCommandFailure(new RedoCommand(), model, MESSAGE_REDO_FAILURE);
    }

    @Test
    public void execute_redoAfterDeleteFunction_failure() {
        Model model = new ModelManager();

        model.addPerson(TypicalPersons.ALICE);
        model.deletePerson(TypicalPersons.ALICE);

        assertCommandFailure(new RedoCommand(), model, MESSAGE_REDO_FAILURE);

        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        model.addPerson(TypicalPersons.HOON);
        model.deletePerson(TypicalPersons.HOON);

        assertCommandFailure(new RedoCommand(), model, MESSAGE_REDO_FAILURE);
    }

    @Test
    public void execute_redoAfterEditFunction_failure() {
        Model model = new ModelManager();

        model.addPerson(TypicalPersons.ALICE);

        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        PersonBuilder personInList = new PersonBuilder(firstPerson);
        Person editedPerson = personInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        model.setPerson(firstPerson, editedPerson);

        assertCommandFailure(new RedoCommand(), model, MESSAGE_REDO_FAILURE);

        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        model.setPerson(firstPerson, editedPerson);

        assertCommandFailure(new RedoCommand(), model, MESSAGE_REDO_FAILURE);
    }

    @Test
    public void execute_redoForUndoAfterAddFunction_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        model.addPerson(TypicalPersons.ALICE);
        model.restoreHistory();

        expectedModel.addPerson(TypicalPersons.ALICE);

        assertCommandSuccess(new RedoCommand(), model, MESSAGE_REDO_SUCCESS, expectedModel);

        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        model.addPerson(TypicalPersons.HOON);
        model.restoreHistory();

        expectedModel.addPerson(TypicalPersons.HOON);

        assertCommandSuccess(new RedoCommand(), model, MESSAGE_REDO_SUCCESS, expectedModel);
    }

    @Test
    public void execute_redoForUndoAfterDeleteFunction_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        model.addPerson(TypicalPersons.ALICE);
        model.deletePerson(TypicalPersons.ALICE);
        model.restoreHistory();

        assertCommandSuccess(new RedoCommand(), model, MESSAGE_REDO_SUCCESS, expectedModel);

        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        model.addPerson(TypicalPersons.HOON);
        model.deletePerson(TypicalPersons.HOON);
        model.restoreHistory();

        assertCommandSuccess(new RedoCommand(), model, MESSAGE_REDO_SUCCESS, expectedModel);
    }

    @Test
    public void execute_redoForUndoAfterEditFunction_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        model.addPerson(TypicalPersons.ALICE);
        expectedModel.addPerson(TypicalPersons.ALICE);

        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        PersonBuilder personInList = new PersonBuilder(firstPerson);
        Person editedPerson = personInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        model.setPerson(firstPerson, editedPerson);
        model.restoreHistory();
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(new RedoCommand(), model, MESSAGE_REDO_SUCCESS, expectedModel);

        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        personInList = new PersonBuilder(firstPerson);
        editedPerson = personInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        model.setPerson(firstPerson, editedPerson);
        model.restoreHistory();
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(new RedoCommand(), model, MESSAGE_REDO_SUCCESS, expectedModel);
    }

    @Test
    public void test_redoCommandWordIsCorrect() {
        assertTrue(COMMAND_WORD.equals("redo"));
    }

    @Test
    public void equals() {
        RedoCommand command = new RedoCommand();
        assertTrue(command.equals(new RedoCommand()));
    }
}

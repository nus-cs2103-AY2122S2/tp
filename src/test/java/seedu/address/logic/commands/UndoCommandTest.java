package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.UndoCommand.MESSAGE_UNDO_FAILURE;
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
 * Contains integration tests (interaction with the Model) for {@code UndoCommand}.
 */
public class UndoCommandTest {

    @Test
    public void execute_undoAsFirstFunction_failure() {
        Model model = new ModelManager();

        assertCommandFailure(new UndoCommand(), model, MESSAGE_UNDO_FAILURE);
    }

    @Test
    public void execute_undoForAddFunction_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        model.addPerson(TypicalPersons.ALICE);

        assertCommandSuccess(new UndoCommand(), model, UndoCommand.MESSAGE_UNDO_SUCCESS, expectedModel);

        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        model.addPerson(TypicalPersons.HOON);

        assertCommandSuccess(new UndoCommand(), model, UndoCommand.MESSAGE_UNDO_SUCCESS, expectedModel);
    }

    @Test
    public void execute_undoForDeleteFunction_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        model.addPerson(TypicalPersons.ALICE);
        expectedModel.addPerson(TypicalPersons.ALICE);

        model.deletePerson(TypicalPersons.ALICE);

        assertCommandSuccess(new UndoCommand(), model, UndoCommand.MESSAGE_UNDO_SUCCESS, expectedModel);

        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        model.addPerson(TypicalPersons.HOON);

        assertCommandSuccess(new UndoCommand(), model, UndoCommand.MESSAGE_UNDO_SUCCESS, expectedModel);
    }

    @Test
    public void execute_undoForEditFunction_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        model.addPerson(TypicalPersons.ALICE);
        expectedModel.addPerson(TypicalPersons.ALICE);

        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        PersonBuilder personInList = new PersonBuilder(firstPerson);
        Person editedPerson = personInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        model.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(new UndoCommand(), model, UndoCommand.MESSAGE_UNDO_SUCCESS, expectedModel);

        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        personInList = new PersonBuilder(firstPerson);
        editedPerson = personInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        model.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(new UndoCommand(), model, UndoCommand.MESSAGE_UNDO_SUCCESS, expectedModel);
    }

    @Test
    public void test_undoCommandWordIsCorrect() {
        assertTrue(UndoCommand.COMMAND_WORD.equals("undo"));
    }

    @Test
    public void equals() {
        UndoCommand command = new UndoCommand();
        assertTrue(command.equals(new UndoCommand()));
    }
}

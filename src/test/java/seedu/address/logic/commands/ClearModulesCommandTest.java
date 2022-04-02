package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;




class ClearModulesCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new AddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnFilteredList_success() {
        Person personToClear = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        ClearModulesCommand clearCommand = new ClearModulesCommand(INDEX_SECOND_PERSON);

        String expectedMessage = String.format(ClearModulesCommand.MESSAGE_SUCCESS, personToClear.getName());

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new AddressBook(), new UserPrefs());
        Person editedPerson = new PersonBuilder(personToClear).withModules().build();
        expectedModel.setPerson(personToClear, editedPerson);

        assertCommandSuccess(clearCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        ClearModulesCommand clearCommand = new ClearModulesCommand(outOfBoundIndex);

        assertCommandFailure(clearCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        // Filter the second person and delete first index
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        ClearModulesCommand deleteCommand = new ClearModulesCommand(INDEX_FIRST_PERSON);


        Person personToClear = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        String expectedMessage = String.format(ClearModulesCommand.MESSAGE_SUCCESS, personToClear.getName());
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new AddressBook(), new UserPrefs());
        showPersonAtIndex(expectedModel, INDEX_FIRST_PERSON);

        Person editedPerson = new PersonBuilder(personToClear).withModules().build();
        expectedModel.setPerson(personToClear, editedPerson);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        ClearModulesCommand clearFirstCommand = new ClearModulesCommand(INDEX_FIRST_PERSON);
        ClearModulesCommand clearSecondCommand = new ClearModulesCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(clearFirstCommand.equals(clearFirstCommand));

        // same values -> returns true
        ClearModulesCommand clearFirstCommandCopy = new ClearModulesCommand(INDEX_FIRST_PERSON);
        assertTrue(clearFirstCommand.equals(clearFirstCommandCopy));

        // different types -> returns false
        assertFalse(clearFirstCommand.equals(1));

        // null -> returns false
        assertFalse(clearFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(clearFirstCommand.equals(clearSecondCommand));
    }
}

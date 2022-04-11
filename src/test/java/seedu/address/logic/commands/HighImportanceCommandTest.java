package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.assertDetailedViewCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

class HighImportanceCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexListView_success() {
        Person personToEditImportanceStatus = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person highImportancePerson =
                new PersonBuilder(personToEditImportanceStatus).withHighImportance("true").build();
        HighImportanceCommand highImportanceCommand = new HighImportanceCommand(INDEX_FIRST_PERSON);

        String expectedMessage =
                String.format(HighImportanceCommand.MESSAGE_CHANGE_HIGH_IMPORTANCE_SUCCESS, highImportancePerson);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(personToEditImportanceStatus, highImportancePerson);

        assertCommandSuccess(highImportanceCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexListView_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        HighImportanceCommand highImportanceCommand = new HighImportanceCommand(outOfBoundIndex);

        assertCommandFailure(highImportanceCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personToEditImportanceStatus = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person highImportancePerson =
                new PersonBuilder(personToEditImportanceStatus).withHighImportance("true").build();
        HighImportanceCommand highImportanceCommand = new HighImportanceCommand(INDEX_FIRST_PERSON);

        String expectedMessage =
                String.format(HighImportanceCommand.MESSAGE_CHANGE_HIGH_IMPORTANCE_SUCCESS, highImportancePerson);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(personToEditImportanceStatus, highImportancePerson);
        showPersonAtIndex(expectedModel, INDEX_FIRST_PERSON);

        assertCommandSuccess(highImportanceCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        HighImportanceCommand highImportanceCommand = new HighImportanceCommand(outOfBoundIndex);

        assertCommandFailure(highImportanceCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void executeInDetailedView_success() {
        Person personToEdit = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        model.setDetailedContactView(personToEdit);

        Person editedPerson = new PersonBuilder(personToEdit).withHighImportance("true").build();
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.setPerson(personToEdit, editedPerson);
        expectedModel.setDetailedContactView(editedPerson);

        HighImportanceCommand highImportanceCommand = new HighImportanceCommand();
        CommandResult expectedResult = new CommandResult(String.format(
                HighImportanceCommand.MESSAGE_CHANGE_HIGH_IMPORTANCE_SUCCESS, editedPerson),
                CommandResult.SpecialCommandResult.DETAILED_VIEW);
        assertDetailedViewCommandSuccess(highImportanceCommand, model, expectedResult, expectedModel);
    }

    @Test
    public void equals() {
        HighImportanceCommand importantFirstContactCommand = new HighImportanceCommand(INDEX_FIRST_PERSON);
        HighImportanceCommand importantSecondContactCommand = new HighImportanceCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(importantFirstContactCommand.equals(importantFirstContactCommand));

        // different types -> returns false
        assertFalse(importantFirstContactCommand.equals(1));

        // null -> returns false
        assertFalse(importantFirstContactCommand.equals(null));

        // different person -> returns false
        assertFalse(importantFirstContactCommand.equals(importantSecondContactCommand));
    }
}

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
import seedu.address.model.person.lab.Lab;
import seedu.address.model.person.lab.LabList;
import seedu.address.model.person.lab.LabStatus;
import seedu.address.testutil.PersonBuilder;

public class EditLabStatusCommandTest {

    private static final int VALID_LABNUMBER = 1;
    private static final LabStatus VALID_LABSTATUS = LabStatus.SUBMITTED;

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validParameters_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personToEdit = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(personToEdit).build();
        LabList listToEdit = editedPerson.getLabs();
        listToEdit.setLab(listToEdit.getLabByLabNumber(VALID_LABNUMBER),
                new Lab(String.valueOf(VALID_LABNUMBER)).of(VALID_LABSTATUS.name()));

        EditLabStatusCommand command = new EditLabStatusCommand(INDEX_FIRST_PERSON, VALID_LABNUMBER, VALID_LABSTATUS);

        String expectedMessage = String.format(EditLabStatusCommand.MESSAGE_EDIT_LAB_STATUS_SUCCESS,
                VALID_LABNUMBER, personToEdit.getName(), VALID_LABSTATUS.name());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        EditLabStatusCommand command = new EditLabStatusCommand(outOfBoundIndex, VALID_LABNUMBER, VALID_LABSTATUS);

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        EditLabStatusCommand command = new EditLabStatusCommand(outOfBoundIndex, VALID_LABNUMBER, VALID_LABSTATUS);

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidLabNumber_throwsCommandException() {
        int invalidLabNumber = 0;
        EditLabStatusCommand command = new EditLabStatusCommand(INDEX_FIRST_PERSON, invalidLabNumber, VALID_LABSTATUS);

        assertCommandFailure(command, model, EditLabStatusCommand.MESSAGE_INVALID_LAB_NUMBER);
    }

    @Test
    public void execute_duplicateLab_throwsCommandException() {
        LabStatus currentStatus = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased())
                .getLabs().getLabByLabNumber(VALID_LABNUMBER).labStatus;
        EditLabStatusCommand command = new EditLabStatusCommand(INDEX_FIRST_PERSON, VALID_LABNUMBER, currentStatus);

        assertCommandFailure(command, model, EditLabStatusCommand.MESSAGE_INVALID_STATUS_CHANGE);
    }

    @Test
    public void equals() {
        EditLabStatusCommand standardCommand =
                new EditLabStatusCommand(INDEX_FIRST_PERSON, VALID_LABNUMBER, VALID_LABSTATUS);
        EditLabStatusCommand changeIndexCommand =
                new EditLabStatusCommand(INDEX_SECOND_PERSON, VALID_LABNUMBER, VALID_LABSTATUS);
        EditLabStatusCommand changeLabCommand =
                new EditLabStatusCommand(INDEX_FIRST_PERSON, 2, VALID_LABSTATUS);
        EditLabStatusCommand changeStatusCommand =
                new EditLabStatusCommand(INDEX_FIRST_PERSON, VALID_LABNUMBER, LabStatus.GRADED);

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // same values -> returns true
        EditLabStatusCommand standardCommandCopy =
                new EditLabStatusCommand(INDEX_FIRST_PERSON, 1, LabStatus.SUBMITTED);
        assertTrue(standardCommand.equals(standardCommandCopy));

        // different values -> returns false
        assertFalse(standardCommand.equals(changeIndexCommand));
        assertFalse(standardCommand.equals(changeLabCommand));
        assertFalse(standardCommand.equals(changeStatusCommand));
        assertFalse(changeIndexCommand.equals(changeLabCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));
    }
}

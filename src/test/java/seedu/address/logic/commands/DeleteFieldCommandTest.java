package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalMeetings.getTypicalMeetingBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.MeetingBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.EditPersonDescriptor;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for DeleteFieldCommand.
 */
public class DeleteFieldCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalMeetingBook(), new UserPrefs());

    @Test
    public void execute_deleteSingleFieldUnfilteredList_success() {
        Person fieldsDeletedPerson = new PersonBuilder(model.getFilteredPersonList().get(0))
                .withoutJobTitle().build();
        testDeleteFieldCommandSuccess(fieldsDeletedPerson);
    }

    @Test
    public void execute_deleteAllValuesInMapUnfilteredList_success() {
        Person fieldsDeletedPerson = new PersonBuilder(model.getFilteredPersonList().get(0))
                .withoutEmails().build();
        testDeleteFieldCommandSuccess(fieldsDeletedPerson);
    }

    @Test
    public void execute_deleteSomeValuesInMapUnfilteredList_success() {
        Person fieldsDeletedPerson = new PersonBuilder(model.getFilteredPersonList().get(0))
                .withEmails().build();
        testDeleteFieldCommandSuccess(fieldsDeletedPerson);
    }

    @Test
    public void equals() {
        final DeleteFieldCommand standardCommand = new DeleteFieldCommand(DESC_AMY, Person.getEmptyPerson());

        // same values -> returns true
        EditPersonDescriptor copyDescriptor = new EditPersonDescriptor(DESC_AMY);
        DeleteFieldCommand commandWithSameValues = new DeleteFieldCommand(copyDescriptor, Person.getEmptyPerson());
        assertEquals(standardCommand, commandWithSameValues);

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertNotEquals(null, standardCommand);

        // different types -> returns false
        assertNotEquals(standardCommand, new ClearCommand());

        // different descriptor -> returns false
        assertNotEquals(standardCommand, new DeleteFieldCommand(DESC_BOB, Person.getEmptyPerson()));
    }

    public void testDeleteFieldCommandSuccess(Person fieldsDeletedPerson) {
        EditPersonDescriptor descriptor = new EditPersonDescriptor(fieldsDeletedPerson);
        Person personToDeleteFields = model.getFilteredPersonList().get(0);
        DeleteFieldCommand deleteFieldCommand = new DeleteFieldCommand(descriptor, personToDeleteFields);

        Person updatedPerson = DeleteFieldCommand.createUpdatedPerson(descriptor);

        String expectedMessage = String.format(DeleteFieldCommand.MESSAGE_DELETE_FIELD_SUCCESS,
                updatedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new MeetingBook(model.getMeetingBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), updatedPerson);
        assertCommandSuccess(deleteFieldCommand, model, expectedMessage, expectedModel);
    }
}

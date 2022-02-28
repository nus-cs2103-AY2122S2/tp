package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

class DeleteModuleCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        List<Tag> modules = personToDelete.getTags().stream()
                .collect(Collectors.toList());
        DeleteModuleCommand deleteCommand = new DeleteModuleCommand(INDEX_FIRST_PERSON, modules);

        String expectedMessage = String.format(DeleteModuleCommand.MESSAGE_SUCCESS,
                personToDelete.getName(), personToDelete.getTags());

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        Person editedPerson = new PersonBuilder(personToDelete).withTags().build();
        expectedModel.setPerson(personToDelete, editedPerson);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        Person first = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        List<Tag> modules = first.getTags().stream()
                .collect(Collectors.toList());

        DeleteModuleCommand deleteFirstCommand = new DeleteModuleCommand(INDEX_FIRST_PERSON, modules);

        // same object -> returns true
        assertEquals(deleteFirstCommand, deleteFirstCommand);

        // same values -> returns true
        DeleteModuleCommand deleteFirstCommandCopy = new DeleteModuleCommand(INDEX_FIRST_PERSON, modules);
        assertEquals(deleteFirstCommand, deleteFirstCommandCopy);

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertNotEquals(deleteFirstCommand, null);

        // different person -> returns false
        Person second = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        List<Tag> modulesCopy = second.getTags().stream()
                .collect(Collectors.toList());
        DeleteModuleCommand deleteSecondCommand = new DeleteModuleCommand(INDEX_SECOND_PERSON, modulesCopy);
        assertNotEquals(deleteFirstCommand, deleteSecondCommand);
    }
}

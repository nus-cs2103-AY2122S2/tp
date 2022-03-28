package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

class DeleteModuleCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        List<Module> modules = new ArrayList<>(personToDelete.getModules());
        DeleteModuleCommand deleteCommand = new DeleteModuleCommand(INDEX_FIRST_PERSON, modules);

        String expectedMessage = String.format(DeleteModuleCommand.MESSAGE_SUCCESS,
                personToDelete.getName(), personToDelete.getModules());

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        Person editedPerson = new PersonBuilder(personToDelete).withModules().build();
        expectedModel.setPerson(personToDelete, editedPerson);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_failure() {
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        List<Module> modules = new ArrayList<>(personToDelete.getModules());
        DeleteModuleCommand deleteCommand = new DeleteModuleCommand(Index.fromOneBased(1000), modules);

        String expectedMessage = Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;

        assertCommandFailure(deleteCommand, model, expectedMessage);
    }

    @Test
    public void execute_modulesToDeleteDoesNotExistListUnfilteredList_failure() {
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        List<Module> modules = new ArrayList<>(personToDelete.getModules());
        List<Module> modulesPersonDoesNotHave = new ArrayList<>();

        // Change first letter of modules, e.g. "CS2106" to "DS2106"
        for (Module module : modules) {
            String moduleString = module.toString();
            String moduleName = moduleString.substring(1, moduleString.length() - 1);
            String changedFirstLetterModuleName = String.valueOf((char) (moduleName.charAt(0) + 1));
            String alteredModuleName = changedFirstLetterModuleName + moduleName.substring(1);
            modulesPersonDoesNotHave.add(new Module(alteredModuleName));
        }

        DeleteModuleCommand deleteCommand = new DeleteModuleCommand(INDEX_FIRST_PERSON, modulesPersonDoesNotHave);

        String expectedMessage = String.format(DeleteModuleCommand.MESSAGE_FAILURE, modulesPersonDoesNotHave);

        assertCommandFailure(deleteCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        Person first = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        List<Module> modules = first.getModules().stream()
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
        List<Module> modulesCopy = second.getModules().stream()
                .collect(Collectors.toList());
        DeleteModuleCommand deleteSecondCommand = new DeleteModuleCommand(INDEX_SECOND_PERSON, modulesCopy);
        assertNotEquals(deleteFirstCommand, deleteSecondCommand);
    }
}

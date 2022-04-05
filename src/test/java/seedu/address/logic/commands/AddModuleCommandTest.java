package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_PM1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_SWE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

class AddModulesCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new AddressBook(), new UserPrefs());

    @Test
    void execute_addModuleUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withModules(VALID_MODULE_SWE, VALID_MODULE_PM1).build();

        List<Module> modules = new ArrayList<>();
        modules.add(new Module(VALID_MODULE_PM1));
        AddModulesCommand addModuleCommand = new AddModulesCommand(INDEX_FIRST_PERSON, modules);

        String expectedMessage = String.format(AddModulesCommand.MESSAGE_SUCCESS, firstPerson.getName(), modules);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new AddressBook(), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(addModuleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        Person first = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        List<Module> modules = new ArrayList<>(first.getModules());

        AddModulesCommand addFirstCommand = new AddModulesCommand(INDEX_FIRST_PERSON, modules);

        // same object -> returns true
        assertEquals(addFirstCommand, addFirstCommand);

        // same values -> returns true
        AddModulesCommand deleteFirstCommandCopy = new AddModulesCommand(INDEX_FIRST_PERSON, modules);
        assertEquals(addFirstCommand, deleteFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, addFirstCommand);

        // null -> returns false
        assertNotEquals(addFirstCommand, null);

        // different person -> returns false
        Person second = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        List<Module> modulesCopy = new ArrayList<>(second.getModules());
        AddModulesCommand addSecondCommand = new AddModulesCommand(INDEX_SECOND_PERSON, modulesCopy);
        assertNotEquals(addFirstCommand, addSecondCommand);
    }
}

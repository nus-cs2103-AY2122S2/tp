package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE;
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
import seedu.address.model.person.Person;
import seedu.address.model.module.Module;
import seedu.address.testutil.PersonBuilder;

class AddModuleCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute_addModuleUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withTags(VALID_MODULE_SWE, VALID_MODULE).build();

        List<Module> modules = new ArrayList<>();
        modules.add(new Module(VALID_MODULE_SWE));
        modules.add(new Module(VALID_MODULE));
        AddModuleCommand addModuleCommand = new AddModuleCommand(INDEX_FIRST_PERSON, modules);

        String expectedMessage = String.format(AddModuleCommand.MESSAGE_SUCCESS, firstPerson.getName(), modules);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(addModuleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        Person first = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        List<Module> modules = new ArrayList<>(first.getTags());

        AddModuleCommand addFirstCommand = new AddModuleCommand(INDEX_FIRST_PERSON, modules);

        // same object -> returns true
        assertEquals(addFirstCommand, addFirstCommand);

        // same values -> returns true
        AddModuleCommand deleteFirstCommandCopy = new AddModuleCommand(INDEX_FIRST_PERSON, modules);
        assertEquals(addFirstCommand, deleteFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, addFirstCommand);

        // null -> returns false
        assertNotEquals(addFirstCommand, null);

        // different person -> returns false
        Person second = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        List<Module> modulesCopy = new ArrayList<>(second.getTags());
        AddModuleCommand addSecondCommand = new AddModuleCommand(INDEX_SECOND_PERSON, modulesCopy);
        assertNotEquals(addFirstCommand, addSecondCommand);
    }
}

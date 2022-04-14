package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.ANDY;
import static seedu.address.testutil.TypicalPersons.BENSON;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.AddressBookBuilder;
import seedu.address.testutil.PersonBuilder;


public class SortCommandTest {

    private AddressBook addressBook;
    private AddressBook expectedAddressBook;
    private Model model;
    private Model expectedModel;

    @Test
    public void execute_sortEmptyList_success() {

        addressBook = new AddressBookBuilder().build();
        expectedAddressBook = new AddressBookBuilder().build();

        model = new ModelManager(addressBook, new UserPrefs());
        expectedModel = new ModelManager(expectedAddressBook, new UserPrefs());

        assertCommandSuccess(new SortCommand(), model, SortCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_sortInitialAscendingIsNotFiltered_showsSameList() {

        Person aliceCopy = new PersonBuilder(ALICE).build();
        Person bensonCopy = new PersonBuilder(BENSON).build();

        addressBook = new AddressBookBuilder().withPerson(ALICE).withPerson(BENSON).build();
        expectedAddressBook = new AddressBookBuilder().withPerson(aliceCopy).withPerson(bensonCopy).build();

        model = new ModelManager(addressBook, new UserPrefs());
        expectedModel = new ModelManager(expectedAddressBook, new UserPrefs());

        assertCommandSuccess(new SortCommand(), model, SortCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_sortInitialAscendingIsFiltered_showsSameList() {

        Person aliceCopy = new PersonBuilder(ALICE).build();
        Person bensonCopy = new PersonBuilder(BENSON).build();

        addressBook = new AddressBookBuilder().withPerson(ALICE).withPerson(BENSON).build();
        expectedAddressBook = new AddressBookBuilder().withPerson(aliceCopy).withPerson(bensonCopy).build();

        model = new ModelManager(addressBook, new UserPrefs());
        expectedModel = new ModelManager(expectedAddressBook, new UserPrefs());

        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        assertCommandSuccess(new SortCommand(), model, SortCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_sortSameNumberOfIncompleteTasksIsNotFiltered_showsSameList() {

        Person andy = new PersonBuilder(ANDY).withTaskList("Task AA", false).build();
        Person aliceCopy = new PersonBuilder(ALICE).build();
        Person andyCopy = new PersonBuilder(andy).build();

        addressBook = new AddressBookBuilder().withPerson(ALICE).withPerson(andy).build();
        expectedAddressBook = new AddressBookBuilder().withPerson(aliceCopy).withPerson(andyCopy).build();

        model = new ModelManager(addressBook, new UserPrefs());
        expectedModel = new ModelManager(expectedAddressBook, new UserPrefs());
        expectedModel.sortFilteredPersonListByTaskLeft();

        assertCommandSuccess(new SortCommand(), model, SortCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_sortSameNumberOfIncompleteTasksIsFiltered_showsSameList() {

        Person andy = new PersonBuilder(ANDY).withTaskList("Task AA", false).build();
        Person aliceCopy = new PersonBuilder(ALICE).build();
        Person andyCopy = new PersonBuilder(andy).build();

        addressBook = new AddressBookBuilder().withPerson(ALICE).withPerson(andy).build();
        expectedAddressBook = new AddressBookBuilder().withPerson(aliceCopy).withPerson(andyCopy).build();

        model = new ModelManager(addressBook, new UserPrefs());
        expectedModel = new ModelManager(expectedAddressBook, new UserPrefs());
        expectedModel.sortFilteredPersonListByTaskLeft();

        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        assertCommandSuccess(new SortCommand(), model, SortCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_sortInitialDescendingIsNotFiltered_showsSameList() {

        Person aliceCopy = new PersonBuilder(ALICE).build();
        Person bensonCopy = new PersonBuilder(BENSON).build();

        addressBook = new AddressBookBuilder().withPerson(BENSON).withPerson(ALICE).build();
        expectedAddressBook = new AddressBookBuilder().withPerson(bensonCopy).withPerson(aliceCopy).build();

        model = new ModelManager(addressBook, new UserPrefs());
        expectedModel = new ModelManager(expectedAddressBook, new UserPrefs());
        expectedModel.sortFilteredPersonListByTaskLeft();

        assertCommandSuccess(new SortCommand(), model, SortCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_sortInitialDescendingIsFiltered_showsSameList() {


        Person aliceCopy = new PersonBuilder(ALICE).build();
        Person bensonCopy = new PersonBuilder(BENSON).build();

        addressBook = new AddressBookBuilder().withPerson(BENSON).withPerson(ALICE).build();
        expectedAddressBook = new AddressBookBuilder().withPerson(bensonCopy).withPerson(aliceCopy).build();

        model = new ModelManager(addressBook, new UserPrefs());
        expectedModel = new ModelManager(expectedAddressBook, new UserPrefs());
        expectedModel.sortFilteredPersonListByTaskLeft();

        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        assertCommandSuccess(new SortCommand(), model, SortCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_sortInitialDescendingIsNotFiltered_failure() {

        Person aliceCopy = new PersonBuilder(ALICE).build();
        Person bensonCopy = new PersonBuilder(BENSON).build();

        // Both AB are in wrong sequence in terms of sort
        addressBook = new AddressBookBuilder().withPerson(BENSON).withPerson(ALICE).build();
        expectedAddressBook = new AddressBookBuilder().withPerson(bensonCopy).withPerson(aliceCopy).build();

        model = new ModelManager(addressBook, new UserPrefs());
        model.sortFilteredPersonListByTaskLeft();
        expectedModel = new ModelManager(expectedAddressBook, new UserPrefs());

        assertNotEquals(model, expectedModel);
    }

    @Test
    public void execute_sortInitialDescendingIsFiltered_failure() {


        Person aliceCopy = new PersonBuilder(ALICE).build();
        Person bensonCopy = new PersonBuilder(BENSON).build();

        // Both AB are in wrong sequence in terms of sort
        addressBook = new AddressBookBuilder().withPerson(BENSON).withPerson(ALICE).build();
        expectedAddressBook = new AddressBookBuilder().withPerson(bensonCopy).withPerson(aliceCopy).build();

        model = new ModelManager(addressBook, new UserPrefs());
        model.sortFilteredPersonListByTaskLeft();
        expectedModel = new ModelManager(expectedAddressBook, new UserPrefs());

        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        assertNotEquals(model, expectedModel);
    }
}

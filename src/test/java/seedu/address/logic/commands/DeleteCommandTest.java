package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_NEGATIVE;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_POSITIVE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
//import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

//import java.util.List;
//import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

//import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
//import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
//import seedu.address.testutil.PersonBuilder;



/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX
                + " Only " + model.getFilteredPersonList().size() + " person(s) shown in the list.");
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);
        showNoPerson(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    //    @Test
    //    public void execute_positiveStatusPerson_success() { //TODO - FIX TEST CASE
    //        Person personToDelete = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
    //        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_PERSON);
    //
    //        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);
    //
    //        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
    //        expectedModel.deletePerson(personToDelete);
    //
    //        ObservableList<Person> studentList = expectedModel.getAddressBook().getPersonList();
    //        List<Person> filteredByClassCodeAndActivityList = studentList.stream()
    //                .filter(student -> (student.getClassCode().toString()
    //                .equals(personToDelete.getClassCode().toString())
    //                        || student.hasSameActivity(personToDelete))
    //                        && !student.isSamePerson(personToDelete))
    //                .collect(Collectors.toList());
    //
    //        for (Person classmate : filteredByClassCodeAndActivityList) {
    //
    //            List<Person> positiveRelatedToPerson = studentList.stream()
    //                    .filter(student -> (student.getClassCode().toString()
    //                            .equals(classmate.getClassCode().toString())
    //                            || student.hasSameActivity(classmate))
    //                            && !student.isSamePerson(classmate)
    //                            && student.getStatus().toString().equals(VALID_STATUS_POSITIVE))
    //                    .collect(Collectors.toList());
    //
    //            if (positiveRelatedToPerson.size() == 0) {
    //                Person editedClassmate = new PersonBuilder(classmate).withStatus(VALID_STATUS_NEGATIVE).build();
    //                expectedModel.setPerson(classmate, editedClassmate);
    //            }
    //        }
    //
    //        expectedModel.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    //
    //        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    //    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX
                + " Only " + model.getFilteredPersonList().size() + " person(s) shown in the list.");
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST_PERSON);
        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_PERSON);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPersonList(p -> false);

        assertTrue(model.getFilteredPersonList().isEmpty());
    }
}

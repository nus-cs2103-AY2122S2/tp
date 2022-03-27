package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code MakeTeamCommand}.
 */
public class MakeTeamCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_addValidIndexUnfilteredList_success() {
        Person person = model.getDisplayPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        MakeTeamCommand makeTeamCommand = new MakeTeamCommand(INDEX_FIRST_PERSON, MakeTeamCommand.TeamAction.ADD);

        String expectedMessage = String.format(MakeTeamCommand.MESSAGE_ADD_TEAMMATE_SUCCESS, person);
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        Person expectedPerson = new PersonBuilder(expectedModel.getDisplayPersonList().get(0))
                .isPotentialTeammate(true).build();
        expectedModel.setPerson(person, expectedPerson);
        expectedModel.commitAddressBook();

        assertCommandSuccess(makeTeamCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_removeValidIndexUnfilteredList_success() {
        Person person = model.getDisplayPersonList().get(INDEX_THIRD_PERSON.getZeroBased());
        MakeTeamCommand makeTeamCommand = new MakeTeamCommand(INDEX_THIRD_PERSON, MakeTeamCommand.TeamAction.REMOVE);

        String expectedMessage = String.format(MakeTeamCommand.MESSAGE_REMOVE_TEAMMATE_SUCCESS, person);
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        Person expectedPerson = new PersonBuilder(expectedModel.getDisplayPersonList().get(2))
                .isPotentialTeammate(false).build();
        expectedModel.setPerson(person, expectedPerson);
        expectedModel.commitAddressBook();

        assertCommandSuccess(makeTeamCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_addPersonThatIsAlreadyPotentialTeammate_failure() {
        // Third person in TypicalPersons.java is already a potential teammate
        MakeTeamCommand makeTeamCommand = new MakeTeamCommand(INDEX_THIRD_PERSON, MakeTeamCommand.TeamAction.ADD);

        String expectedMessage = MakeTeamCommand.MESSAGE_REDUNDANT_ADDING;
        assertCommandFailure(makeTeamCommand, model, expectedMessage);
    }

    @Test
    public void execute_removePersonThatIsNotPotentialTeammate_failure() {
        // Second person in TypicalPersons.java is not a potential teammate
        MakeTeamCommand makeTeamCommand = new MakeTeamCommand(INDEX_SECOND_PERSON, MakeTeamCommand.TeamAction.REMOVE);

        String expectedMessage = MakeTeamCommand.MESSAGE_REDUNDANT_REMOVAL;
        assertCommandFailure(makeTeamCommand, model, expectedMessage);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getDisplayPersonList().size() + 1);
        MakeTeamCommand makeTeamCommand = new MakeTeamCommand(outOfBoundIndex, MakeTeamCommand.TeamAction.ADD);

        assertCommandFailure(makeTeamCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person person = model.getDisplayPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        MakeTeamCommand makeTeamCommand = new MakeTeamCommand(INDEX_FIRST_PERSON, MakeTeamCommand.TeamAction.ADD);

        String expectedMessage = String.format(MakeTeamCommand.MESSAGE_ADD_TEAMMATE_SUCCESS, person);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        Person expectedPerson = new PersonBuilder(expectedModel.getDisplayPersonList().get(0))
                .isPotentialTeammate(true).build();
        expectedModel.setPerson(person, expectedPerson);
        expectedModel.commitAddressBook();
        showPersonAtIndex(expectedModel, INDEX_FIRST_PERSON);

        assertCommandSuccess(makeTeamCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        MakeTeamCommand makeTeamCommand = new MakeTeamCommand(outOfBoundIndex, MakeTeamCommand.TeamAction.ADD);

        assertCommandFailure(makeTeamCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        MakeTeamCommand addFirstPerson = new MakeTeamCommand(INDEX_FIRST_PERSON, MakeTeamCommand.TeamAction.ADD);
        MakeTeamCommand removeFirstPerson = new MakeTeamCommand(INDEX_FIRST_PERSON, MakeTeamCommand.TeamAction.REMOVE);
        MakeTeamCommand addSecondPerson = new MakeTeamCommand(INDEX_SECOND_PERSON, MakeTeamCommand.TeamAction.ADD);

        // same object -> returns true
        assertTrue(addFirstPerson.equals(addFirstPerson));

        // same index and action -> returns true
        MakeTeamCommand addFirstPersonCopy = new MakeTeamCommand(INDEX_FIRST_PERSON, MakeTeamCommand.TeamAction.ADD);
        assertTrue(addFirstPersonCopy.equals(addFirstPerson));
        MakeTeamCommand removePersonCopy = new MakeTeamCommand(INDEX_FIRST_PERSON, MakeTeamCommand.TeamAction.REMOVE);
        assertTrue(removeFirstPerson.equals(removePersonCopy));

        // different action same index -> returns false
        assertFalse(addFirstPerson.equals(removeFirstPerson));

        // different index same action -> returns false
        assertFalse(addFirstPerson.equals(addSecondPerson));

        // different index different action -> returns false
        assertFalse(removeFirstPerson.equals(addSecondPerson));
    }
}

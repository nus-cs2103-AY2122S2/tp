package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showTutorialAtIndex;
import static seedu.address.logic.commands.CommandTestUtil.showTutorialWithName;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalTutorials.T01;
import static seedu.address.testutil.TypicalTutorials.T02;
import static seedu.address.testutil.TypicalTutorials.TG1;
import static seedu.address.testutil.TypicalTutorials.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTutorials.getTypicalAddressBookWithPerson;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tutorial.Tutorial;
import seedu.address.testutil.TutorialBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteClassCommand}.
 */
public class DeleteClassCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model modelWithPersons = new ModelManager(getTypicalAddressBookWithPerson(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredTutorialList_success() {
        model.updateFilteredTutorialList(model.PREDICATE_SHOW_ALL_TUTORIALS);
        Tutorial tutorialToDelete = model.getFilteredTutorialList().get(INDEX_FIRST.getZeroBased());
        DeleteClassCommand deleteClassCommand = new DeleteClassCommand(INDEX_FIRST, null, false);

        String expectedMessage = String.format(DeleteClassCommand.MESSAGE_DELETE_TUTORIAL_SUCCESS, tutorialToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteTutorial(tutorialToDelete);

        assertCommandSuccess(deleteClassCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidListUnfilteredTutorialList_failure() {
        modelWithPersons.updateFilteredPersonList(modelWithPersons.PREDICATE_SHOW_ALL_PERSONS);
        DeleteClassCommand deleteClassCommand = new DeleteClassCommand(INDEX_FIRST, null, false);
        String expectedMessage = Messages.MESSAGE_INDEX_LIST_MISMATCH + DeleteClassCommand.MESSAGE_INDEX_USAGE;
        assertCommandFailure(deleteClassCommand, modelWithPersons, expectedMessage);
    }

    @Test
    public void execute_validTutorialNameUnfilteredTutorialList_success() {
        Tutorial tutorialToDelete = T01;
        DeleteClassCommand deleteClassCommand = new DeleteClassCommand(null, T01.getTutorialName(), true);

        String expectedMessage = String.format(DeleteClassCommand.MESSAGE_DELETE_TUTORIAL_SUCCESS, tutorialToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteTutorial(tutorialToDelete);

        assertCommandSuccess(deleteClassCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredTutorialList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTutorialList().size() + 1);
        DeleteClassCommand deleteClassCommand = new DeleteClassCommand(outOfBoundIndex, null, false);

        assertCommandFailure(deleteClassCommand, model, Messages.MESSAGE_INVALID_TUTORIAL_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidTutorialUnfilteredTutorialList_throwsCommandException() {
        Tutorial invalidTutorial = new TutorialBuilder().withTutorialName("T05")
                .withDay("Wed").withTime("10:00").withVenue("LT15").build();
        DeleteClassCommand deleteClassCommand = new DeleteClassCommand(null, invalidTutorial.getTutorialName(), true);

        assertCommandFailure(deleteClassCommand, model, Messages.MESSAGE_TUTORIAL_NOT_FOUND);
    }

    @Test
    public void execute_validIndexFilteredTutorialList_success() {
        showTutorialAtIndex(model, INDEX_FIRST);

        Tutorial tutorialToDelete = model.getFilteredTutorialList().get(INDEX_FIRST.getZeroBased());
        DeleteClassCommand deleteClassCommand = new DeleteClassCommand(INDEX_FIRST, null, false);

        String expectedMessage = String.format(DeleteClassCommand.MESSAGE_DELETE_TUTORIAL_SUCCESS, tutorialToDelete);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteTutorial(tutorialToDelete);
        showNoTutorial(expectedModel);

        assertCommandSuccess(deleteClassCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validTutorialFilteredTutorialList_success() {
        showTutorialWithName(model, T01.getTutorialName());

        Tutorial tutorialToDelete = model.getFilteredTutorialList().get(0);
        DeleteClassCommand deleteClassCommand = new DeleteClassCommand(null, T01.getTutorialName(), true);

        String expectedMessage = String.format(DeleteClassCommand.MESSAGE_DELETE_TUTORIAL_SUCCESS, tutorialToDelete);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteTutorial(tutorialToDelete);
        showNoTutorial(expectedModel);

        assertCommandSuccess(deleteClassCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredTutorialList_throwsCommandException() {
        showTutorialAtIndex(model, INDEX_FIRST);

        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book tutorial list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getTutorialList().size());

        DeleteClassCommand deleteClassCommand = new DeleteClassCommand(outOfBoundIndex, null, false);

        assertCommandFailure(deleteClassCommand, model, Messages.MESSAGE_INVALID_TUTORIAL_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidTutorialNameFilteredTutorialList_throwsCommandException() {
        showTutorialWithName(model, T01.getTutorialName());

        DeleteClassCommand deleteClassCommand = new DeleteClassCommand(null, TG1.getTutorialName(), true);

        assertCommandFailure(deleteClassCommand, model, Messages.MESSAGE_TUTORIAL_NOT_FOUND);
    }

    @Test
    public void equals() {
        DeleteClassCommand deleteFirstClassCommand = new DeleteClassCommand(INDEX_FIRST, null, false);
        DeleteClassCommand deleteSecondClassCommand = new DeleteClassCommand(INDEX_SECOND, null, false);

        // same object -> returns true
        assertTrue(deleteFirstClassCommand.equals(deleteFirstClassCommand));

        // same values -> return true
        DeleteClassCommand deleteFirstClassCommandCopy = new DeleteClassCommand(INDEX_FIRST, null, false);
        assertTrue(deleteFirstClassCommand.equals(deleteFirstClassCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstClassCommand.equals(1));

        // null -> return false
        assertFalse(deleteFirstClassCommand.equals(null));

        // different class -> return false
        assertFalse(deleteFirstClassCommand.equals(deleteSecondClassCommand));

        DeleteClassCommand deleteT01ClassCommand = new DeleteClassCommand(null, T01.getTutorialName(), true);
        DeleteClassCommand deleteT02ClassCommand = new DeleteClassCommand(null, T02.getTutorialName(), true);

        // same object -> returns true
        assertTrue(deleteT01ClassCommand.equals(deleteT01ClassCommand));

        // same values -> return true
        DeleteClassCommand deleteT01ClassCommandCopy = new DeleteClassCommand(null, T01.getTutorialName(), true);
        assertTrue(deleteT01ClassCommand.equals(deleteT01ClassCommandCopy));

        // different types -> returns false
        assertFalse(deleteT01ClassCommand.equals(1));

        // null -> return false
        assertFalse(deleteT01ClassCommand.equals(null));

        // different class -> return false
        assertFalse(deleteT01ClassCommand.equals(deleteT02ClassCommand));
    }

    /**
     * Updates {@code model}'s filtered tutorial list to show no one.
     */
    private void showNoTutorial(Model model) {
        model.updateFilteredTutorialList(t -> false);

        assertTrue(model.getFilteredTutorialList().isEmpty());
    }


}

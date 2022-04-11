package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTutorials.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tutorial.Day;
import seedu.address.model.tutorial.TutorialHasSameDay;

public class ListClassCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    private Day monday = new Day("Monday");
    private Day tuesday = new Day("Tuesday");
    private Day friday = new Day("Friday");

    private TutorialHasSameDay mondayPredicate = new TutorialHasSameDay(monday);
    private TutorialHasSameDay tuesdayPredicate = new TutorialHasSameDay(tuesday);
    private TutorialHasSameDay fridayPredicate = new TutorialHasSameDay(friday);

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ListClassCommand(mondayPredicate).execute(null));
    }

    @Test
    public void equals() {
        ListClassCommand firstCommand = new ListClassCommand(mondayPredicate);
        ListClassCommand secondCommand = new ListClassCommand(tuesdayPredicate);
        ListClassCommand allClassCommand = new ListClassCommand(null);

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        // same values -> returns true
        ListClassCommand firstCommandCopy = new ListClassCommand(mondayPredicate);
        assertTrue(firstCommand.equals(firstCommandCopy));
        ListClassCommand allClassCommandCopy = new ListClassCommand(null);
        assertTrue(allClassCommand.equals(allClassCommandCopy));

        // different types -> returns false
        assertFalse(firstCommand.equals(1));

        // null -> returns false
        assertFalse(firstCommand.equals(null));

        // different predicate -> returns false
        assertFalse(firstCommand.equals(secondCommand));
    }

    @Test
    public void execute_noDayInput_listAllClasses() {
        String expectedMessage = ListClassCommand.MESSAGE_SUCCESS;
        ListClassCommand command = new ListClassCommand(null);
        expectedModel.updateFilteredTutorialList(expectedModel.PREDICATE_SHOW_ALL_TUTORIALS);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertTrue(model.getFilteredTutorialList().size() == 4);
    }

    @Test
    public void execute_fridayInput_listClassesOnFriday() {
        String expectedMessage = String.format(ListClassCommand.MESSAGE_SUCCESS_DAY, fridayPredicate.getDay());
        ListClassCommand command = new ListClassCommand(fridayPredicate);
        expectedModel.updateFilteredTutorialList(fridayPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertTrue(model.getFilteredTutorialList().size() == 2);
    }
}

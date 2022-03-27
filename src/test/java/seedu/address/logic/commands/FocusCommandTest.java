package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCandidates.getTypicalAddressBook;
import static seedu.address.testutil.TypicalInterviews.getTypicalInterviewSchedule;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.InterviewSchedule;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;


public class FocusCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalInterviewSchedule(), new UserPrefs());

    @Test
    public void constructor_nullComparator_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new FocusCommand(null));
    }

    @Test
    public void constructor_wrongIndex_throwsIndexOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> new FocusCommand(Index.fromZeroBased(-1)));
    }

    @Test
    public void equals() {
        FocusCommand fc1 = new FocusCommand(Index.fromZeroBased(1));
        FocusCommand fc2 = new FocusCommand(Index.fromZeroBased(1));
        FocusCommand fc3 = new FocusCommand(Index.fromZeroBased(2));
        HelpCommand hc = new HelpCommand();

        // same object -> returns true
        assertTrue(fc1.equals(fc2));

        // null -> returns false
        assertFalse(fc1.equals(null));

        // null -> returns false
        assertFalse(fc2.equals(null));

        //same instance -> returns true
        assertTrue(fc1.equals(fc1));
        assertTrue(fc2.equals(fc2));

        //different index -> returns false
        assertFalse(fc1.equals(fc3));
        assertFalse(fc2.equals(fc3));

        //different command
        assertFalse(fc1.equals(hc));
    }

    @Test
    public void execute_focusCommand_success() {
        FocusCommand focusCommand = new FocusCommand(Index.fromZeroBased(1));
        String expectedMessage = String.format(FocusCommand.MESSAGE_FOCUS_CANDIDATE);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new InterviewSchedule(model.getInterviewSchedule()), new UserPrefs());
        assertCommandSuccess(focusCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void getTargetIndex_success() {
        FocusCommand focusCommand = new FocusCommand(Index.fromZeroBased(1));
        assertEquals(focusCommand.getTargetIndex(), Index.fromZeroBased(1));
    }

    @Test
    public void emptyList_throwsCommandException() throws CommandException {
        model = new ModelManager(new AddressBook(), new InterviewSchedule(), new UserPrefs());
        FocusCommand focusCommand = new FocusCommand(Index.fromZeroBased(1));
        assertThrows(CommandException.class, () -> focusCommand.execute(model));
    }

    @Test
    public void indexOutOfBound_throwsCommandException() throws CommandException {
        model = new ModelManager(getTypicalAddressBook(), getTypicalInterviewSchedule(), new UserPrefs());
        FocusCommand focusCommand = new FocusCommand(Index.fromZeroBased(100));
        assertThrows(CommandException.class, () -> focusCommand.execute(model));
    }
}

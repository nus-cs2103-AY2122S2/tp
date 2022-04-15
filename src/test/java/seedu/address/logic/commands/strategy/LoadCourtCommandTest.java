package seedu.address.logic.commands.strategy;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.person.ClearPersonCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.StrategyBoard;
import seedu.address.model.UserPrefs;
import seedu.address.model.image.Image;

public class LoadCourtCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalTaskBook(),
            new StrategyBoard(), new UserPrefs());

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new LoadCourtCommand(null));
    }

    @Test
    public void equals() {
        Image imageStub1 = new Image("test");
        Image imageStub2 = new Image("test2");
        LoadCourtCommand standardCommand = new LoadCourtCommand(imageStub1);
        LoadCourtCommand standardCommand2 = new LoadCourtCommand(imageStub2);

        // same values -> returns true
        LoadCourtCommand commandWithSameValues = new LoadCourtCommand(imageStub1);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearPersonCommand()));

        // different image -> returns false
        assertFalse(standardCommand.equals(standardCommand2));
    }
}

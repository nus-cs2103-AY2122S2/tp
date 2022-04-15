package seedu.address.logic.commands.strategy;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.person.ClearPersonCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.StrategyBoard;
import seedu.address.model.UserPrefs;
import seedu.address.model.strategy.Player;

class DeletePlayerCommandTest {
    private static final String PLAYER_NAME_STUB_1 = "player1";
    private static final String PLAYER_NAME_STUB_2 = "player2";

    private final Model model = new ModelManager(getTypicalAddressBook(), getTypicalTaskBook(),
            new StrategyBoard(), new UserPrefs());

    @Test
    public void constructor_nullPlayer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeletePlayerCommand(null));
    }

    @Test
    public void execute_validPlayerName_success() {
        final Player player1 = new Player(PLAYER_NAME_STUB_1);
        final String expectedMessage = String.format(DeletePlayerCommand.MESSAGE_SUCCESS, PLAYER_NAME_STUB_1);

        Model model = new ModelManager(getTypicalAddressBook(), getTypicalTaskBook(),
                new StrategyBoard(), new UserPrefs());
        model.addPlayer(player1);

        DeletePlayerCommand deletePlayerCommand = new DeletePlayerCommand(PLAYER_NAME_STUB_1);

        Model expectedModel = new ModelManager(getTypicalAddressBook(), getTypicalTaskBook(),
                new StrategyBoard(), new UserPrefs());

        assertCommandSuccess(deletePlayerCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPlayerName_failure() {
        final String expectedMessage = String.format(DeletePlayerCommand.MESSAGE_PLAYER_NOT_FOUND, PLAYER_NAME_STUB_2);

        Model model = new ModelManager(getTypicalAddressBook(), getTypicalTaskBook(),
                new StrategyBoard(), new UserPrefs());
        model.addPlayer(new Player(PLAYER_NAME_STUB_1));

        DeletePlayerCommand deletePlayerCommand = new DeletePlayerCommand(PLAYER_NAME_STUB_2);

        assertCommandFailure(deletePlayerCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        final DeletePlayerCommand standardCommand = new DeletePlayerCommand(PLAYER_NAME_STUB_1);

        // same values -> returns true
        DeletePlayerCommand commandWithSameValues = new DeletePlayerCommand(PLAYER_NAME_STUB_1);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearPersonCommand()));

        // different player -> returns false
        assertFalse(standardCommand.equals(new DeletePlayerCommand(PLAYER_NAME_STUB_2)));
    }
}

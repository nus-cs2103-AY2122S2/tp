package seedu.address.logic.commands.strategy;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.person.ClearPersonCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.StrategyBoard;
import seedu.address.model.UserPrefs;
import seedu.address.model.strategy.Player;

class MovePlayerCommandTest {

    private static final String PLAYER_NAME_STUB_1 = "player1";
    private static final String PLAYER_NAME_STUB_2 = "player2";
    private static final int POS_X_STUB_1 = 123;
    private static final int POS_Y_STUB_1 = 456;
    private static final int POS_X_STUB_2 = 321;
    private static final int POS_Y_STUB_2 = 654;

    private final Model model = new ModelManager(getTypicalAddressBook(), getTypicalTaskBook(),
            new StrategyBoard(), new UserPrefs());

    @Test
    public void constructor_nullPlayerName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new MovePlayerCommand(null, POS_X_STUB_1, POS_Y_STUB_1));
    }

    @Test
    public void execute_validPlayerNamePosition_success() {
        final Player player1 = new Player(PLAYER_NAME_STUB_1, POS_X_STUB_1, POS_Y_STUB_1);
        final Player player1WithoutPos = new Player(PLAYER_NAME_STUB_1);
        final String expectedMessage = String.format(MovePlayerCommand.MESSAGE_SUCCESS,
                PLAYER_NAME_STUB_1, POS_X_STUB_1, POS_Y_STUB_1);

        Model model = new ModelManager(getTypicalAddressBook(), getTypicalTaskBook(),
                new StrategyBoard(), new UserPrefs());
        model.addPlayer(player1WithoutPos);

        MovePlayerCommand movePlayerCommand = new MovePlayerCommand(PLAYER_NAME_STUB_1, POS_X_STUB_1, POS_Y_STUB_1);

        Model expectedModel = new ModelManager(getTypicalAddressBook(), getTypicalTaskBook(),
                new StrategyBoard(), new UserPrefs());
        expectedModel.addPlayer(player1);

        assertCommandSuccess(movePlayerCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPlayerNamePosition_failure() {
        final String expectedMessage = String.format(Messages.MESSAGE_INVALID_PLAYER, PLAYER_NAME_STUB_2);

        Model model = new ModelManager(getTypicalAddressBook(), getTypicalTaskBook(),
                new StrategyBoard(), new UserPrefs());
        model.addPlayer(new Player(PLAYER_NAME_STUB_1));
        MovePlayerCommand movePlayerCommand = new MovePlayerCommand(PLAYER_NAME_STUB_2, POS_X_STUB_1, POS_Y_STUB_1);

        assertCommandFailure(movePlayerCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        final MovePlayerCommand standardCommand = new MovePlayerCommand(PLAYER_NAME_STUB_1, POS_X_STUB_1, POS_Y_STUB_1);

        // same values -> returns true
        MovePlayerCommand commandWithSameValues = new MovePlayerCommand(PLAYER_NAME_STUB_1, POS_X_STUB_1, POS_Y_STUB_1);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearPersonCommand()));

        // different player -> returns false
        assertFalse(standardCommand.equals(new MovePlayerCommand(PLAYER_NAME_STUB_2, POS_X_STUB_1, POS_Y_STUB_1)));

        // different Position -> returns false
        assertFalse(standardCommand.equals(new MovePlayerCommand(PLAYER_NAME_STUB_2, POS_X_STUB_2, POS_Y_STUB_1)));

        // different Position -> returns false
        assertFalse(standardCommand.equals(new MovePlayerCommand(PLAYER_NAME_STUB_2, POS_X_STUB_1, POS_Y_STUB_2)));
    }
}

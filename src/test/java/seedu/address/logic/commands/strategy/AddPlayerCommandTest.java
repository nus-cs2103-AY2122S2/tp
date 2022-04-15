package seedu.address.logic.commands.strategy;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.person.ClearPersonCommand;
import seedu.address.logic.commands.task.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.StrategyBoard;
import seedu.address.model.UserPrefs;
import seedu.address.model.strategy.Player;

class AddPlayerCommandTest {
    private static final String PLAYER_NAME_STUB_1 = "player1";
    private static final String PLAYER_NAME_STUB_2 = "player2";

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalTaskBook(),
            new StrategyBoard(), new UserPrefs());

    @Test
    public void constructor_nullPlayer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddPlayerCommand(null));
    }

    @Test
    public void execute_validPlayer_notNull() throws CommandException {
        AddPlayerCommand addPlayerCommand = new AddPlayerCommand(new Player(PLAYER_NAME_STUB_1));
        assertNotNull(addPlayerCommand.execute(model));
    }

    @Test
    public void execute_duplicatedPlayer_failure() {
        Model model = new ModelManager(getTypicalAddressBook(), getTypicalTaskBook(),
                new StrategyBoard(), new UserPrefs());
        model.addPlayer(new Player(PLAYER_NAME_STUB_1));
        AddPlayerCommand addPlayerCommand = new AddPlayerCommand(new Player(PLAYER_NAME_STUB_1));

        assertCommandFailure(addPlayerCommand, model, AddPlayerCommand.MESSAGE_DUPLICATE_PLAYER);
    }

    @Test
    public void equals() {
        final Player player1 = new Player(PLAYER_NAME_STUB_1);
        final Player player2 = new Player(PLAYER_NAME_STUB_2);
        final AddPlayerCommand standardCommand = new AddPlayerCommand(player1);

        // same values -> returns true
        AddPlayerCommand commandWithSameValues = new AddPlayerCommand(player1);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearPersonCommand()));

        // different player -> returns false
        assertFalse(standardCommand.equals(new AddPlayerCommand(player2)));
    }
}

package seedu.address.logic.commands.strategy;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PLAYERS;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.task.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.strategy.Player;

/**
 * Moves the player to the specified location in the strategy board.
 */
public class MovePlayerCommand extends Command {
    public static final String COMMAND_WORD = "move";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Moves the player to the specified location.\n"
            + "Parameters: "
            + "PLAYER_NAME "
            + "x/X_COORDINATE (must be non-negative integers no greater than 1000) "
            + "y/Y_COORDINATE (must be non-negative integers no greater than 600)\n"
            + "Example: " + COMMAND_WORD + " " + "John" + " " + "x/100" + " " + "y/200";

    public static final String MESSAGE_SUCCESS = "%1$s has been moved to the specified location: (%2$d, %3$d).";

    private final String playerName;
    private final int xCoordinate;
    private final int yCoordinate;

    /**
     * @param playerName the name of the player to move
     * @param xCoordinate the x coordinate of the location to move to
     * @param yCoordinate the y coordinate of the location to move to
     */
    public MovePlayerCommand(String playerName, int xCoordinate, int yCoordinate) {
        requireAllNonNull(playerName, xCoordinate, yCoordinate);

        this.playerName = playerName;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireAllNonNull(model);

        Player player = new Player(playerName);

        if (!model.hasPlayer(player)) {
            throw new CommandException(String.format(Messages.MESSAGE_INVALID_PLAYER, playerName));
        }

        Player editedPlayer = player.toCopy();

        editedPlayer.setXCoord(xCoordinate);
        editedPlayer.setYCoord(yCoordinate);

        //model.setPlayer(player, editedPlayer);
        model.deletePlayer(player);
        model.addPlayer(editedPlayer);
        model.updateFilteredPlayerList(PREDICATE_SHOW_ALL_PLAYERS);

        return new CommandResult(String.format(MESSAGE_SUCCESS, playerName, xCoordinate, yCoordinate));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MovePlayerCommand // instanceof handles nulls
                && (playerName.equals(((MovePlayerCommand) other).playerName))
                && (xCoordinate == ((MovePlayerCommand) other).xCoordinate)
                && (yCoordinate == ((MovePlayerCommand) other).yCoordinate));
    }
}


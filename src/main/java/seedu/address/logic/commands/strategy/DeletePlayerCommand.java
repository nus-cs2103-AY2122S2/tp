package seedu.address.logic.commands.strategy;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.task.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.strategy.Player;

/**
 * Deletes a player in the strategy panel
 */
public class DeletePlayerCommand extends Command {
    public static final String COMMAND_WORD = "del-player";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Delete a player in the strategy panel. "
            + "Parameters: "
            + "PLAYER_NAME (must be non-empty and not more than 50 characters)\n"
            + "Example: " + COMMAND_WORD + " "
            + "Lionel Messi";

    public static final String MESSAGE_NOT_IMPLEMENTED_YET =
            "DeletePlayer command not implemented yet";

    public static final String MESSAGE_SUCCESS = "Player deleted: %1$s";
    public static final String MESSAGE_PLAYER_NOT_FOUND = "Player not found in the list: %1$s";

    private final String playerName;

    /**
     * @param playerName the name of the player to be added
     */
    public DeletePlayerCommand(String playerName) {
        requireAllNonNull(playerName);
        this.playerName = playerName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (playerName.replace("/s", "").isEmpty()) {
            throw new CommandException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPlayerCommand.MESSAGE_USAGE));
        }

        Player player = new Player(playerName);
        if (!model.hasPlayer(player)) {
            throw new CommandException(
                    String.format(MESSAGE_PLAYER_NOT_FOUND, playerName));
        }
        requireNonNull(model);
        model.deletePlayer(player);
        return new CommandResult(String.format(MESSAGE_SUCCESS, playerName));
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeletePlayerCommand // instanceof handles nulls
                && (playerName.equals(((DeletePlayerCommand) other).playerName)));
    }
}

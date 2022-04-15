package seedu.address.logic.commands.strategy;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.task.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.strategy.Player;

/**
 * Adds a player in the strategy panel
 */
public class AddPlayerCommand extends Command {
    public static final String COMMAND_WORD = "add-player";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Add a player in the strategy panel. "
            + "Parameters: "
            + "PLAYER_NAME"
            + "(must be non-empty, not more than 50 characters and does not contain \"/\")\n"
            + "Example: " + COMMAND_WORD + " "
            + "Lionel Messi";

    public static final String MESSAGE_NOT_IMPLEMENTED_YET =
            "AddPlayer command not implemented yet";

    public static final String MESSAGE_SUCCESS = "New player added: %1$s";
    public static final String MESSAGE_DUPLICATE_PLAYER = "This player already exists in the PlayerList";


    private final Player toAdd;

    /**
     * Creates an AddPlayerCommand to add the specified {@code Player}.
     */
    public AddPlayerCommand(Player player) {
        requireAllNonNull(player);
        this.toAdd = player;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPlayer(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PLAYER);
        }

        model.addPlayer(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd.getName()));
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddPlayerCommand // instanceof handles nulls
                && (toAdd.equals(((AddPlayerCommand) other).toAdd)));
    }
}

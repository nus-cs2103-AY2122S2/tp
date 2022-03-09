package seedu.contax.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.contax.logic.commands.exceptions.CommandException;
import seedu.contax.model.Model;

/**
 * Adds chain command.
 */
public class ChainCommand extends Command {
    public static final String COMMAND_WORD = "chain";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": [Command] && [Command]";

    private final List<Command> commands;
    /**
     * Creates Commands list
     */
    public ChainCommand(List<Command> userCommands) {
        requireNonNull(userCommands);
        commands = userCommands;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        for (Command command:commands) {
            command.execute(model);
        }
        return commands.get(commands.size() - 1).execute(model);
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof ChainCommand) {
            for (int i = 0; i < commands.size(); i++) {
                if (!commands.get(i).equals(((ChainCommand) other).commands.get(i))) {
                    return false;
                }
            }
        }
        return true;
    }
}

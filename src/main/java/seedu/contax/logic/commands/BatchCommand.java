package seedu.contax.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.contax.logic.commands.exceptions.CommandException;
import seedu.contax.model.Model;

/**
 * Adds batch command.
 */
public class BatchCommand extends Command {
    public static final String COMMAND_WORD = "batch";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": [Command] && [Command]";

    private final List<Command> commands;
    /**
     * Creates Commands list
     */
    public BatchCommand(List<Command> userCommands) {
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
}

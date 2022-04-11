package seedu.contax.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.contax.logic.commands.exceptions.CommandException;
import seedu.contax.model.Model;

/**
 * Adds chain command.
 */
public class ChainCommand extends Command {
    public static final String COMMAND_WORD = "chain";
    public static final String MESSAGE_USAGE = "`" + COMMAND_WORD + "`: **Chain two commands together for execution**"
            + "\nParameters: *[Command] && [Command]*"
            + "\nExample: `" + COMMAND_WORD + "deleteperson 1 "
            + "&& addperson n/Alice p/91231231 e/alice@example.com a/Address`";

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
        List<CommandResult> commandResultList = new ArrayList<>();
        for (Command command:commands) {
            try {
                commandResultList.add(command.execute(model));
            } catch (CommandException ce) {
                commandResultList.clear();
                commandResultList.add(new CommandResult(ce.getMessage()));
                break;
            }
        }
        StringBuilder resultOutput = new StringBuilder();
        for (CommandResult result: commandResultList) {
            resultOutput.append(result.getFeedbackToUser()).append("\n");
        }
        return new CommandResult(resultOutput.toString());
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof ChainCommand) {
            return ((ChainCommand) other).commands.equals(commands);
        } else {
            return false;
        }
    }
}

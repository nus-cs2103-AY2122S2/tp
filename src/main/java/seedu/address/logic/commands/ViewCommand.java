package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_PLAYER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEAM;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": View the different entity identified "
            + "by the prefix(es) used in the command. "
            + "\n"
            + "Parameters:  (must be a positive integer) "
            + PREFIX_PLAYER + "[REMARK]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TEAM + "Likes to swim.";

    public static final String COMMAND_SUCCESS = "viewed";

    public String input;

    public ViewCommand(String input) {
        this.input = input;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {

        return new CommandResult(input);
    }
}

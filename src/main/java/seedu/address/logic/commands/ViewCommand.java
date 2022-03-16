package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_LINEUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLAYER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEAM;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE_TEAM = "Parameters: " + PREFIX_TEAM + "[TEAM_NAME]\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_TEAM + " Lakers.\n"
            + "Or\n"
            + COMMAND_WORD + " " + PREFIX_TEAM;

    public static final String MESSAGE_USAGE_PLAYER = "Parameters: " + PREFIX_PLAYER + "[PLAYER_NAME]\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_PLAYER + " LBJ.\n"
            + "Or\n"
            + COMMAND_WORD + " " + PREFIX_PLAYER;

    public static final String MESSAGE_USAGE_LINEUP = "Parameters: " + PREFIX_LINEUP + "[LINEUP_NAME]\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_TEAM + " Lakers " + PREFIX_LINEUP + " starting5.\n"
            + "Or\n"
            + COMMAND_WORD + " " + PREFIX_TEAM + " Lakers " + PREFIX_LINEUP;

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Views the summarised information of "
            + "team, lineup and player.\n"
            + MESSAGE_USAGE_TEAM + "\n"
            + MESSAGE_USAGE_PLAYER + "\n"
            + MESSAGE_USAGE_LINEUP;

    public static final String COMMAND_SUCCESS = "";

    private String input;

    public ViewCommand(String input) {
        this.input = input;
    }

    public String getInput() {
        return input;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {

        return new CommandResult(input);
    }
}

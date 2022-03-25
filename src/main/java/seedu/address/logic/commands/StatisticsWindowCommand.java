package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class StatisticsWindowCommand extends Command {
    public static final String COMMAND_WORD = "stats";
    public static final String MESSAGE_STATISTICS_WINDOW_SUCCESS =
            "Successfully opened Statistics window!";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Opens up the Statistics window "
            + "that displays a pie chart with data of buyers and sellers in an area.\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult(MESSAGE_STATISTICS_WINDOW_SUCCESS, false, false, false, true, false);
    }
}

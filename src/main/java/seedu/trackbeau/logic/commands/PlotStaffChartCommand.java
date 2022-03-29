package seedu.trackbeau.logic.commands;

import seedu.trackbeau.model.Model;

public class PlotStaffChartCommand extends Command {
    public static final String COMMAND_WORD = "plotStaff";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Plots staff preference chart of customers.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_CHART_MESSAGE = "Charted staff chart.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_CHART_MESSAGE, false,
                false, true, false);
    }
}

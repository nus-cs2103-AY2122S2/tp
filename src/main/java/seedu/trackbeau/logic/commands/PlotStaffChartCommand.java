package seedu.trackbeau.logic.commands;

import seedu.trackbeau.model.Model;
import seedu.trackbeau.ui.Panel;

/**
 * Plots a Staff Chart that shows the favourite staffs amongst customers.
 */
public class PlotStaffChartCommand extends Command {
    public static final String COMMAND_WORD = "plotStaff";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Plots staff preference chart of customers.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_CHART_MESSAGE = "Charted staff chart.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_CHART_MESSAGE, false,
                false, true, false,
                false, false, false, false, Panel.NO_CHANGE);
    }
}

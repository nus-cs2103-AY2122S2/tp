package seedu.trackbeau.logic.commands;

import seedu.trackbeau.model.Model;
import seedu.trackbeau.ui.Panel;

/**
 * Plots a chart that shows the number of customers gained per month.
 */
public class PlotMonthlyCustomerChartCommand extends Command {
    public static final String COMMAND_WORD = "plotMonthlyCustomers";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Plots monthly customer chart.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_CHART_MESSAGE = "Charted monthly customer chart.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_CHART_MESSAGE, false,
                false, false, false,
                false, false, false, true, Panel.NO_CHANGE);
    }
}

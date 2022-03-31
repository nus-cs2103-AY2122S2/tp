package seedu.trackbeau.logic.commands;

import seedu.trackbeau.model.Model;
import seedu.trackbeau.ui.Panel;

/**
 * Plots a Service Chart that shows the favourite services amongst customers.
 */
public class PlotServiceChartCommand extends Command {
    public static final String COMMAND_WORD = "plotService";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Plots service preference chart of customers.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_CHART_MESSAGE = "Charted service chart.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_CHART_MESSAGE, false,
                false, false, true,
                false, false, false, false, Panel.NO_CHANGE);
    }
}

package seedu.trackbeau.logic.commands;

import seedu.trackbeau.model.Model;
import seedu.trackbeau.ui.Panel;

/**
 * Plots a Hair Type Chart that shows the most common hair type amongst customers.
 */
public class PlotHairChartCommand extends Command {
    public static final String COMMAND_WORD = "plotHair";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Plots hair type chart of customers.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_CHART_MESSAGE = "Charted hair type chart.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_CHART_MESSAGE, false,
                false, false, false,
                false, false, true, false, Panel.NO_CHANGE);
    }
}

package seedu.trackbeau.logic.commands;

import seedu.trackbeau.model.Model;
import seedu.trackbeau.ui.Panel;

/**
 * Plots a Skin Type Chart that shows the common skin type amongst customers.
 */
public class PlotSkinChartCommand extends Command {
    public static final String COMMAND_WORD = "plotSkin";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Plots skin type chart of customers.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_CHART_MESSAGE = "Charted skin chart.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_CHART_MESSAGE, false,
                false, false, false,
                false, true, false, false, Panel.NO_CHANGE);
    }
}

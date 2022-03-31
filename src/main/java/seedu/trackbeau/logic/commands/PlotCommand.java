package seedu.trackbeau.logic.commands;

import seedu.trackbeau.model.Model;
import seedu.trackbeau.ui.Panel;

/**
 * Plots all charts.
 */
public class PlotCommand extends Command {
    public static final String COMMAND_WORD = "plotAll";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Plots all charts.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_CHART_MESSAGE = "Charted all charts.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_CHART_MESSAGE, false,
                false, true,
                true, true, true, true,
            true, Panel.NO_CHANGE);
    }
}

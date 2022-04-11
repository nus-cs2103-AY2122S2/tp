package seedu.trackbeau.logic.commands;

import seedu.trackbeau.model.Model;
import seedu.trackbeau.ui.Panel;

/**
 * Plots a Allergy Chart that shows the most common allergies amongst customers.
 */
public class PlotAllergyChartCommand extends Command {
    public static final String COMMAND_WORD = "plotAllergy";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Plots allergy chart of customers.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_CHART_MESSAGE = "Charted allergy chart.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_CHART_MESSAGE, false,
                false, false, false, true, false,
            false, false, Panel.NO_CHANGE);
    }
}

package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

public class ShowInsightsCommand extends Command {

    public static final String COMMAND_WORD = "showinsights";
    public static final String COMMAND_ALIAS = "si";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows insights about friends in Amigos.\n";

    public static final String MESSAGE_SUCCESS = "Insights of friends shown below!";

    /**
     * Creates a ShowInsightsCommand.
     */
    public ShowInsightsCommand() {}

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model); // sanity check

        // simply directs UI to show, since UI handles updating
        return new CommandResult(MESSAGE_SUCCESS, false, false, false, false, true);
    }

    @Override
    public String toString() {
        return COMMAND_WORD;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof ShowInsightsCommand;
    }

}

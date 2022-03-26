package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Searches for all matching properties and preferences
 * and opens a new window to display respective sellers and buyers.
 */
public class MatchCommand extends Command {

    public static final String COMMAND_WORD = "match";

    public static final String SHOWING_MATCH_MESSAGE = "Matched all preferences with properties. Opened Match Window.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateMatchList();
        return new CommandResult(SHOWING_MATCH_MESSAGE, false, true, false, false, false);
    }
}

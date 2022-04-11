package seedu.trackbeau.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.trackbeau.model.Model;
import seedu.trackbeau.model.TrackBeau;
import seedu.trackbeau.ui.Panel;

/**
 * Clears trackBeau.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "TrackBeau has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setTrackBeau(new TrackBeau());
        return new CommandResult(MESSAGE_SUCCESS, Panel.NO_CHANGE);
    }
}

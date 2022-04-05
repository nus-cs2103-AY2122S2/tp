package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_POTENTIAL_TEAMMATES;

import seedu.address.model.Model;

/**
 * Shows the list of all potential teammates in the HackNet.
 */
public class ShowCommand extends Command {

    public static final String COMMAND_WORD = "show";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": List all potential teammates.";

    public static final String MESSAGE_SUCCESS = "Listed your potential teammate(s)!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateDisplayPersonList(PREDICATE_SHOW_POTENTIAL_TEAMMATES);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

package seedu.trackermon.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.trackermon.model.Model.PREDICATE_SHOW_ALL_SHOWS;

import seedu.trackermon.model.Model;

/**
 * Lists all shows in Trackermon to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all shows";

    /**
     * Executes a {@code Model} object.
     * @param model {@code Model} which the command should operate on.
     * @return a {@code CommandResult} object.
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredShowList(PREDICATE_SHOW_ALL_SHOWS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

package seedu.trackermon.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.trackermon.model.Model;
import seedu.trackermon.model.ShowList;

/**
 * Clears the show list.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Show list has been cleared!";
    /**
     * Executes a {@code Model} object.
     * @param model {@code Model} which the command should operate on.
     * @return a {@code CommandResult} object.
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setShowList(new ShowList());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

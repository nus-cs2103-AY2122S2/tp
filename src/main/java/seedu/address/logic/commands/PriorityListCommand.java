package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.model.Model;

/**
 * Sorts and displays the list of persons in the address book by the priority level of their tags.
 */
public class PriorityListCommand extends Command {
    public static final String COMMAND_WORD = "prioList";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts and lists contacts by priority "
            + "level of their tags.";

    public static final String MESSAGE_SUCCESS = "Listed by priority!";

    private static Logger logger = Logger.getLogger("PriorityListCommandLogger");

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortByPriority();
        logger.log(Level.INFO, "Priority list command executed successfully!");
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

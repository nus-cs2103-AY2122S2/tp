package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Sorts and displays the list of persons in the address book by the priority level of their tags.
 */
public class PriorityListCommand extends Command {
    public static final String COMMAND_WORD = "prioList";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts and lists contacts by priority "
            + "level of their tags.";

    public static final String MESSAGE_SUCCESS = "Listed by priority!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortByPriority();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

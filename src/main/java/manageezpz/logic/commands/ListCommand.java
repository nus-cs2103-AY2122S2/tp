package manageezpz.logic.commands;

import static java.util.Objects.requireNonNull;

import manageezpz.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_ALL_SUCCESS = "Listed all Tasks";

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTaskList(model.PREDICATE_SHOW_ALL_TASKS);

        return new CommandResult(MESSAGE_ALL_SUCCESS);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ListCommand) {
            return true;
        } else {
            return false;
        }
    }
}

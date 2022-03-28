package manageezpz.logic.commands;

import static java.util.Objects.requireNonNull;

import manageezpz.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListEmployeesCommand extends Command {
    public static final String COMMAND_WORD = "listEmployees";
    public static final String MESSAGE_ALL_SUCCESS = "Listed all Employees";

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(model.PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(MESSAGE_ALL_SUCCESS);
    }
}

package seedu.trackbeau.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.trackbeau.model.Model.PREDICATE_SHOW_ALL_CUSTOMERS;

import seedu.trackbeau.model.Model;

/**
 * Lists all customers in trackBeau to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all customers";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredCustomerList(PREDICATE_SHOW_ALL_CUSTOMERS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

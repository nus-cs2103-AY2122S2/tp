package seedu.trackbeau.logic.commands.customer;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.trackbeau.commons.core.Messages;
import seedu.trackbeau.commons.core.index.Index;
import seedu.trackbeau.logic.commands.Command;
import seedu.trackbeau.logic.commands.CommandResult;
import seedu.trackbeau.logic.commands.exceptions.CommandException;
import seedu.trackbeau.model.Model;
import seedu.trackbeau.model.customer.Customer;

/**
 * Deletes a customer identified using it's displayed index from trackBeau.
 */
public class DeleteCustomerCommand extends Command {

    public static final String COMMAND_WORD = "deletec";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the customer identified by the index number used in the displayed customer list.\n"
            + "Parameters: INDEXES (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1,2";

    public static final String MESSAGE_DELETE_CUSTOMER_SUCCESS = "Deleted Customer(s):\n%1$s";

    private final ArrayList<Index> targetIndexes;

    public DeleteCustomerCommand(ArrayList<Index> targetIndexes) {
        this.targetIndexes = targetIndexes;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Customer> lastShownList = model.getFilteredCustomerList();

        ArrayList<Customer> customersToDelete = new ArrayList<>();
        for (Index targetIndex : targetIndexes) {
            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_CUSTOMER_DISPLAYED_INDEX);
            }
            customersToDelete.add(lastShownList.get(targetIndex.getZeroBased()));
        }

        StringBuilder sb = new StringBuilder();
        for (Customer customerToDelete : customersToDelete) {
            model.deleteCustomer(customerToDelete);
            sb.append(customerToDelete).append("\n");
        }

        return new CommandResult(String.format(MESSAGE_DELETE_CUSTOMER_SUCCESS, sb));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCustomerCommand // instanceof handles nulls
                && targetIndexes.containsAll(((DeleteCustomerCommand) other).targetIndexes)); // state check
    }
}

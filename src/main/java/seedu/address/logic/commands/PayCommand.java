package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.transaction.Transaction;


public class PayCommand extends Command {

    public static final String COMMAND_WORD = "pay";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Set the status of the transaction at specified index to 'Paid'.\n"
            + "Parameters: INDEX (between 1 and 2147483647 inclusive) \n"
            + "Format: " + COMMAND_WORD + " INDEX\n"
            + "Example: " + COMMAND_WORD + " 1";
    public static final String MESSAGE_SUCCESS = "Transaction set to paid.";

    private final Index index;
    public PayCommand(Index index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Transaction> transactionList = model.getFilteredTransactionList();

        if (index.getZeroBased() >= transactionList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TRANSACTION_DISPLAYED_INDEX);
        }

        Transaction transactionToEdit = transactionList.get(index.getZeroBased());
        Transaction editedTransaction = transactionToEdit.setStatusTo(PayCommand.class);

        model.setTransaction(transactionToEdit, editedTransaction);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PayCommand // instanceof handles nulls
                && index.equals(((PayCommand) other).index)); // state check
    }
}


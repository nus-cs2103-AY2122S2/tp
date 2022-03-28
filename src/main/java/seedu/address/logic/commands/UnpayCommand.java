package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.transaction.Transaction;


public class UnpayCommand extends Command {

    public static final String COMMAND_WORD = "unpay";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": set the status of "
            + "the transaction at specified index to unpaid (false). "
            + "Index must be greater than 0.\n"
            + "Format: " + COMMAND_WORD + " INDEX\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Transaction status has "
            + "been changed!";

    private final Index index;

    public UnpayCommand(Index index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        List<Transaction> transactionList = model.getFilteredTransactionList();

        Transaction transactionToEdit = transactionList.get(index.getZeroBased());
        Transaction editedTransaction = transactionToEdit.setStatusTo(UnpayCommand.class);

        model.setTransaction(transactionToEdit, editedTransaction);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UnpayCommand // instanceof handles nulls
                && index.equals(((UnpayCommand) other).index)); // state check
    }
}


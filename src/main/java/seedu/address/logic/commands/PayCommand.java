package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.transaction.Transaction;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class PayCommand extends Command {

    public static final String COMMAND_WORD = "pay";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": set the status of "
            + "the transaction at specified index to paid (true). "
            + "Index must be greater than 0.\n"
            + "Format: " + COMMAND_WORD + " INDEX\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Transaction status has "
            + "been changed!";

    private final Index index;
    public PayCommand(Index index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        List<Transaction> transactionList = model.getFilteredTransactionList();

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


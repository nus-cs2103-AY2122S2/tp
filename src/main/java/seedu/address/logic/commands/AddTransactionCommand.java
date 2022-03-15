package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.transaction.Transaction;

public class AddTransactionCommand extends Command {
    public static final String COMMAND_WORD = "addTransaction";
//    public static final String MESSAGE_USAGE = COMMAND_WORD
//            + ": Add a transaction to the transaction list of the "
//            + "identified person by the index number used in the last person "
//            + "listing. "
//            + "Existing remark will be overwritten by the input.\n"
//            + "Parameters: INDEX (must be a positive integer) "
//            + "r/ [REMARK]\n"
//            + "Example: " + COMMAND_WORD + " 1 "
//            + "t/ Likes to swim.";
//    public static final String MESSAGE_ARGUMENTS = "Index: %1$d, Remark: %2$s";
//    public static final String MESSAGE_ADD_REMARK_SUCCESS = "Added remark to Person: %1$s";
//    public static final String MESSAGE_DELETE_REMARK_SUCCESS = "Removed remark from Person: %1$s";

    private final Index index;
    private final Transaction transaction;

    public AddTransactionCommand(Index index, Transaction transaction) {
        this.index = index;
        this.transaction = transaction;
    }

    public CommandResult execute(Model model) {
        return new CommandResult();
    }
}

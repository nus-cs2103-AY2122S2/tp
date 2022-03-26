package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TRANSACTIONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.transaction.Amount;
import seedu.address.model.transaction.DueDate;
import seedu.address.model.transaction.Note;
import seedu.address.model.transaction.Transaction;
import seedu.address.model.transaction.TransactionDate;
import seedu.address.model.transaction.util.TransactionProducer;


public class AddTransactionCommand extends Command {
    public static final String COMMAND_WORD = "addTransaction";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Add a transaction to the transaction list of the "
            + "identified person by the index number used in the last person "
            + "listing.\n"
            + "Parameters: "
            + Amount.PREFIX + "AMOUNT "
            + TransactionDate.PREFIX + "TRANSACTION DATE "
            + DueDate.PREFIX + "DUE DATE <OPTIONAL> "
            + Note.PREFIX + "NOTE <OPTIONAL>\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "a/123.456 "
            + "td/2022-11-11 "
            + "dd/2022-11-11 "
            + "n/paid SGD 123.456 for haircut";

    public static final String MESSAGE_SUCCESS = "Added Transaction to Person: %1$s";

    private final Index index;
    private final TransactionProducer transactionProducer;

    /**
     * Constructs AddTransaction command with the specified index
     * of the person and the transaction.
     *
     * @param index
     * @param transactionProducer
     */
    public AddTransactionCommand(Index index, TransactionProducer transactionProducer) {
        requireAllNonNull(index, transactionProducer);
        this.index = index;
        this.transactionProducer = transactionProducer;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person person = lastShownList.get(index.getZeroBased());
        long personIdentifier = person.getUniqueId();

        Transaction transaction = transactionProducer.createTransaction(personIdentifier);

        model.addTransaction(transaction);
        model.updateFilteredTransactionList(PREDICATE_SHOW_ALL_TRANSACTIONS);

        return new CommandResult(String.format(MESSAGE_SUCCESS, transaction.toString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddTransactionCommand // instanceof handles nulls
                && index.equals(((AddTransactionCommand) other).index))
                && transactionProducer.equals(((AddTransactionCommand) other).transactionProducer);
    }
}

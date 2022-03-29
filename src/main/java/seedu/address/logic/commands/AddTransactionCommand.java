package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.transaction.Amount;
import seedu.address.model.transaction.DueDate;
import seedu.address.model.transaction.Note;
import seedu.address.model.transaction.Status;
import seedu.address.model.transaction.Transaction;
import seedu.address.model.transaction.TransactionDate;
import seedu.address.model.transaction.util.TransactionBuilder;


public class AddTransactionCommand extends Command {
    public static final String COMMAND_WORD = "addTransaction";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Add a transaction to the transaction list of the "
            + "identified person by the index number used in the last person "
            + "listing. If specified, the transaction date can't be after the due date.\n"
            + "Parameters: "
            + Amount.PREFIX + "AMOUNT "
            + TransactionDate.PREFIX + "TRANSACTION DATE "
            + DueDate.PREFIX + "DUE DATE <OPTIONAL> "
            + Note.PREFIX + "NOTE <OPTIONAL> "
            + Status.PREFIX + "<OPTIONAL>\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "a/123.456 "
            + "td/2022-11-11 "
            + "dd/2022-11-11 "
            + "n/paid SGD 123.456 for haircut --paid";

    public static final String MESSAGE_SUCCESS = "Added Transaction to Person: %1$s";

    private final Index index;
    private final TransactionBuilder transactionBuilder;

    /**
     * Constructs AddTransaction command with the specified index
     * of the person and the transaction.
     *
     * @param index
     * @param transactionBuilder
     */
    public AddTransactionCommand(Index index, TransactionBuilder transactionBuilder) {
        requireAllNonNull(index, transactionBuilder);
        this.index = index;
        this.transactionBuilder = transactionBuilder;
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

        Transaction transaction = transactionBuilder.createTransaction(personIdentifier);

        if (!transaction.isValid()) {
            throw new CommandException(MESSAGE_USAGE);
        }

        model.addTransaction(transaction);

        return new CommandResult(String.format(MESSAGE_SUCCESS, transaction.toString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddTransactionCommand // instanceof handles nulls
                && index.equals(((AddTransactionCommand) other).index))
                && transactionBuilder.equals(((AddTransactionCommand) other).transactionBuilder);
    }
}

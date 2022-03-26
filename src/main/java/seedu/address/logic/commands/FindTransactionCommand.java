package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.transaction.util.TransactionPredicateProducer;
import seedu.address.model.transaction.util.TransactionWithIdentifierPredicate;


public class FindTransactionCommand extends Command {

    public static final String COMMAND_WORD = "findTransaction";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all Transactions of person "
            + "by using the index of the displayed list of persons. "
            + "Index must be greater than 0.\n"
            + "Example: " + COMMAND_WORD + " 1";

    private final Index index;
    private final TransactionPredicateProducer predicateProducer;

    /**
     * Creates a FindTransactionCommand with the specified index
     * and predicateProducer that returns a TransactionPredicate.
     */
    public FindTransactionCommand(Index index, TransactionPredicateProducer predicateProducer) {
        this.index = index;
        this.predicateProducer = predicateProducer;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(MESSAGE_USAGE);
        }

        Person person = lastShownList.get(index.getZeroBased());
        long personIdentifier = person.getUniqueId();

        TransactionWithIdentifierPredicate predicate = predicateProducer.createTransactionPredicate(personIdentifier);
        model.updateFilteredTransactionList(predicate);

        return new CommandResult(
                String.format(Messages.MESSAGE_TRANSACTIONS_LISTED_OVERVIEW,
                        model.getFilteredTransactionList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindTransactionCommand // instanceof handles nulls
                && index.equals(((FindTransactionCommand) other).index))
                && predicateProducer.equals(((FindTransactionCommand) other).predicateProducer);
    }
}

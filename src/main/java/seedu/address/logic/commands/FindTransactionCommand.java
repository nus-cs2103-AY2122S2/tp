package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.transaction.TransactionWithIdentifierPredicate;

import java.util.List;
import java.util.function.Function;

import static java.util.Objects.requireNonNull;

public class FindTransactionCommand extends Command {

    public static final String COMMAND_WORD = "findTransaction";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all Transactions of person "
            + "with the specified index"
            + "Example: " + COMMAND_WORD + " 1";

    private final Index index;
    private final Function<String, TransactionWithIdentifierPredicate> predicateProducer;

    public FindTransactionCommand(Index index, Function<String, TransactionWithIdentifierPredicate> predicateProducer) {
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
        String personIdentifier = person.getEmail().getValue();

        TransactionWithIdentifierPredicate predicate = predicateProducer.apply(personIdentifier);
        model.updateFilteredTransactionList((t) -> false);

        return new CommandResult(
                String.format(Messages.MESSAGE_TRANSACTIONS_LISTED_OVERVIEW, model.getFilteredTransactionList().size()));
    }
}

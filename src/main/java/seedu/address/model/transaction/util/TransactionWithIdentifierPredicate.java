package seedu.address.model.transaction.util;

import java.util.function.Predicate;

import seedu.address.model.transaction.Transaction;


public class TransactionWithIdentifierPredicate implements Predicate<Transaction> {
    private final long identifier;

    public TransactionWithIdentifierPredicate(long identifier) {
        this.identifier = identifier;
    }

    @Override
    public boolean test(Transaction transaction) {
        return transaction.hasIdentifier(identifier);
    }
}

package seedu.address.model.transaction.util;

import java.util.function.Predicate;

import seedu.address.model.transaction.Transaction;


public class TransactionWithIdentifierPredicate implements Predicate<Transaction> {
    private final String identifier;

    public TransactionWithIdentifierPredicate(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public boolean test(Transaction transaction) {
        return transaction.hasIdentifier(identifier);
    }
}

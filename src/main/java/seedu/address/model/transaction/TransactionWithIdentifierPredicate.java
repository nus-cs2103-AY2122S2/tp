package seedu.address.model.transaction;

import java.util.function.Predicate;

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

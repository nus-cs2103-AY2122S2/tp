package seedu.address.model.transaction.util;

/**
 * Functional interface to produce a Transaction
 * with the specified person identifier (email)
 */
public interface TransactionPredicateProducer {
    TransactionWithIdentifierPredicate createTransactionPredicate(long personId);
}

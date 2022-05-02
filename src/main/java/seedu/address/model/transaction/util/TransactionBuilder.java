package seedu.address.model.transaction.util;

import seedu.address.model.transaction.Transaction;

/**
 * Functional interface to produce a Transaction
 * with the specified person identifier (email)
 */
public interface TransactionBuilder {
    Transaction createTransaction(long personId);
}

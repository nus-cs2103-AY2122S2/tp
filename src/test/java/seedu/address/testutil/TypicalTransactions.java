package seedu.address.testutil;

import static seedu.address.testutil.TransactionUtil.TRANSACTION_ONE;
//import static seedu.address.testutil.TransactionUtil.TRANSACTION_THREE;
import static seedu.address.testutil.TransactionUtil.TRANSACTION_TWO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;
import seedu.address.model.transaction.Transaction;


/**
 * A utility class containing a list of {@code Transaction} objects to be used in tests.
 */
public class TypicalTransactions {

    private TypicalTransactions() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        return new AddressBook(getEmptyPerson(), getTypicalTransactions());
    }

    public static List<Person> getEmptyPerson() {
        return new ArrayList<>();
    }

    public static List<Transaction> getTypicalTransactions() {
        return new ArrayList<>(Arrays.asList(TRANSACTION_ONE, TRANSACTION_TWO));
    }
}

package seedu.address.testutil;

import java.util.List;

import seedu.address.model.transaction.Amount;
import seedu.address.model.transaction.DueDate;
import seedu.address.model.transaction.Note;
import seedu.address.model.transaction.Transaction;
import seedu.address.model.transaction.TransactionDate;



public class TransactionUtil {
    public static final String VALID_AMOUNT_ONE = "123.45";
    public static final String VALID_TRANSACTION_DATE_ONE = "2020-11-11";
    public static final String VALID_DUE_DATE_ONE = "2021-11-11";
    public static final String VALID_NOTE_ONE = "This is a valid note";

    public static final String VALID_AMOUNT_TWO = "11.99";
    public static final String VALID_TRANSACTION_DATE_TWO = "2020-11-12";
    public static final String VALID_DUE_DATE_TWO = "2021-11-12";
    public static final String VALID_NOTE_TWO = "This is a valid note as well";

    public static final Transaction TRANSACTION_ONE_COMPLETE = new Transaction(
            List.of(
                    new Amount(VALID_AMOUNT_ONE),
                    new TransactionDate(VALID_TRANSACTION_DATE_ONE),
                    new DueDate(VALID_DUE_DATE_ONE),
                    new Note(VALID_NOTE_ONE)
            )
    );

    public static final Transaction TRANSACTION_ONE_INCOMPLETE = new Transaction(
            List.of(
                    new Amount(VALID_AMOUNT_ONE),
                    new TransactionDate(VALID_TRANSACTION_DATE_ONE)
            )
    );

    public static final Transaction TRANSACTION_TWO_COMPLETE = new Transaction(
            List.of(
                    new Amount(VALID_AMOUNT_TWO),
                    new TransactionDate(VALID_TRANSACTION_DATE_TWO),
                    new DueDate(VALID_DUE_DATE_TWO),
                    new Note(VALID_NOTE_TWO)
            )
    );
}

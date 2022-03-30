package seedu.address.testutil;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.model.transaction.Amount;
import seedu.address.model.transaction.DueDate;
import seedu.address.model.transaction.Note;
import seedu.address.model.transaction.Status;
import seedu.address.model.transaction.Transaction;
import seedu.address.model.transaction.TransactionDate;


public class TransactionUtil {
    public static final long VALID_ID = PersonUtil.AMY.getUniqueId(); // set all transactions to be amy's

    public static final String VALID_AMOUNT_ONE = "123.45";
    public static final String VALID_TRANSACTION_DATE_ONE = "2020-11-11";
    public static final String VALID_DUE_DATE_ONE = "2021-11-11";
    public static final String VALID_NOTE_ONE = "This is a valid note";
    public static final String VALID_STATUS_ONE = Status.STATUS_PAID;
    public static final String AMOUNT_ONE = " " + Amount.PREFIX + VALID_AMOUNT_ONE;
    public static final String TRANSACTION_DATE_ONE = " " + TransactionDate.PREFIX + VALID_TRANSACTION_DATE_ONE;
    public static final String DUE_DATE_ONE = " " + DueDate.PREFIX + VALID_DUE_DATE_ONE;
    public static final String NOTE_ONE = " " + Note.PREFIX + VALID_NOTE_ONE;
    public static final String FLAG_PAID = " " + Status.PREFIX;

    public static final String VALID_AMOUNT_TWO = "11.99";
    public static final String VALID_TRANSACTION_DATE_TWO = "2020-11-12";
    public static final String VALID_DUE_DATE_TWO = "2021-11-12";
    public static final String VALID_NOTE_TWO = "This is a valid note as well";
    public static final String AMOUNT_TWO = " " + Amount.PREFIX + VALID_AMOUNT_TWO;
    public static final String TRANSACTION_DATE_TWO = " " + TransactionDate.PREFIX + VALID_TRANSACTION_DATE_TWO;
    public static final String DUE_DATE_TWO = " " + DueDate.PREFIX + VALID_DUE_DATE_TWO;
    public static final String NOTE_TWO = " " + Note.PREFIX + VALID_NOTE_TWO;

    public static final String VALID_AMOUNT_THREE = "100";
    public static final String VALID_TRANSACTION_DATE_THREE = "2020-12-11";
    public static final String VALID_DUE_DATE_THREE = "2022-11-11";
    public static final String VALID_NOTE_THREE = "This is a note";

    public static final String INVALID_TRANSACTION_DATE = " " + TransactionDate.PREFIX + "2020-20-20";
    public static final String INVALID_DUE_DATE = " " + DueDate.PREFIX + "2002-20-20";
    public static final String INVALID_AMOUNT = " " + Amount.PREFIX + "-1";
    public static final String INVALID_INDEX_STRING = "Not an index";

    public static final String DUE_DATE_EARLY = "2001-11-11";
    public static final String VALID_INDEX_STRING = " 1";

    public static final Index VALID_INDEX = Index.fromZeroBased(0);
    public static final Index INDEX_FIRST_PERSON = Index.fromOneBased(1);
    public static final Index INDEX_FIRST_TRANSACTION = Index.fromOneBased(1);
    public static final Index INDEX_SECOND_TRANSACTION = Index.fromOneBased(2);
    public static final Index INDEX_THRID_TRANSACTION = Index.fromOneBased(3);

    public static final Transaction TRANSACTION_ONE = new Transaction(
            List.of(
                    new Amount(VALID_AMOUNT_ONE),
                    new TransactionDate(VALID_TRANSACTION_DATE_ONE),
                    new DueDate(VALID_DUE_DATE_ONE),
                    new Note(VALID_NOTE_ONE),
                    new Status(VALID_STATUS_ONE)
            ),
            VALID_ID
    );

    public static final Transaction TRANSACTION_ONE_INCOMPLETE = new Transaction(
            List.of(
                    new Amount(VALID_AMOUNT_ONE),
                    new TransactionDate(VALID_TRANSACTION_DATE_ONE)
            ),
            VALID_ID
    );

    public static final Transaction TRANSACTION_TWO = new Transaction(
            List.of(
                    new Amount(VALID_AMOUNT_TWO),
                    new TransactionDate(VALID_TRANSACTION_DATE_TWO),
                    new DueDate(VALID_DUE_DATE_TWO),
                    new Note(VALID_NOTE_TWO)
            ),
            VALID_ID
    );

    public static final Transaction TRANSACTION_THREE = new Transaction(
            List.of(
                    new Amount(VALID_AMOUNT_THREE),
                    new TransactionDate(VALID_TRANSACTION_DATE_THREE),
                    new DueDate(VALID_DUE_DATE_THREE),
                    new Note(VALID_NOTE_THREE)
            ),
            VALID_ID
    );

    public static final Transaction INVALID_TRANSACTION = new Transaction(
            List.of(
                    new Amount(VALID_AMOUNT_TWO),
                    new TransactionDate(VALID_TRANSACTION_DATE_TWO),
                    new DueDate(DUE_DATE_EARLY),
                    new Note(VALID_NOTE_TWO)
            ),
            VALID_ID
    );
}

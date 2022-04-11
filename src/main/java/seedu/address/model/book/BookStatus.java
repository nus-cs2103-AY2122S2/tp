package seedu.address.model.book;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.book.BookStatusType.AVAILABLE;
import static seedu.address.model.book.BookStatusType.BORROWED;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import seedu.address.model.patron.Patron;

/**
 * Represents a BookStatus in LibTask.
 * Guarantees: immutable; is valid as declared in {@link #isValidStatus(BookStatusType, Optional, Optional, Optional)}
 */
public class BookStatus {

    public static final String MESSAGE_CONSTRAINTS =
            "An available book status must not contain any borrower, borrowDate and return date "
                    + "and a borrowed status must contain borrower, borrowDate and return date,"
                    + "and date must be in dd-MMM-yyyy format.";
    public static final SimpleDateFormat STATUS_DATE_FORMAT = new SimpleDateFormat("dd-MMM-yyyy");
    public static final String VALIDATION_REGEX = "^\\d{2}-[a-zA-Z]{3}-\\d{4}$";

    private final BookStatusType bookStatusType;
    private final Optional<Patron> borrower;
    private final Optional<Date> borrowDate;
    private final Optional<Date> returnDate;

    /**
     * Constructs a {@code BookStatus}.
     * @param bookStatusType The status of this book.
     * @param borrower The patron who borrows this book, if any.
     * @param borrowDate The borrowDate of this book, if any.
     * @param returnDate The returnDate of this book, if any.
     */
    public BookStatus(BookStatusType bookStatusType, Optional<Patron> borrower, Optional<String> borrowDate,
                      Optional<String> returnDate) {
        requireAllNonNull(bookStatusType, borrower, borrowDate, returnDate);
        checkArgument(isValidStatus(bookStatusType, borrower, borrowDate, returnDate), MESSAGE_CONSTRAINTS);
        this.borrowDate = borrowDate.map(BookStatus::parseToDate);
        this.returnDate = returnDate.map(BookStatus::parseToDate);
        this.bookStatusType = bookStatusType;
        this.borrower = borrower;
    }

    /**
     * A static function for creating an AVAILABLE book status.
     *
     * @return An AVAILABLE BookStatus object.
     */
    public static BookStatus createAvailableBookStatus() {
        return new BookStatus(AVAILABLE, Optional.empty(), Optional.empty(),
                Optional.empty());
    }

    /**
     * Returns a BookStatus with the exact same fields as this book status, but with its borrower replaced with the
     * edited borrower. This book status must be of borrowed type before {@code editBorrower} is called.
     *
     * @param editedBorrower The updated version of borrower. It must not be null
     */
    public BookStatus editBorrower(Patron editedBorrower) {
        requireNonNull(editedBorrower);
        assert isBorrowed();
        Optional<String> borrowDateString = borrowDate.map(STATUS_DATE_FORMAT::format);
        Optional<String> returnDateString = returnDate.map(STATUS_DATE_FORMAT::format);
        return new BookStatus(bookStatusType, Optional.of(editedBorrower), borrowDateString, returnDateString);
    }

    public static String getCurrentDateString() {
        return STATUS_DATE_FORMAT.format(new Date());
    }

    /**
     * Returns true if a given parameters can be converted to a valid book status.
     */
    public static boolean isValidStatus(BookStatusType bookStatusType, Optional<Patron> borrower,
                                        Optional<String> borrowDate, Optional<String> returnDate) {
        requireAllNonNull(bookStatusType, borrower, borrowDate, returnDate);

        // A book with available status should not have any borrower, borrowing or returning dates
        boolean isAvailableConstraintsSatisfied = bookStatusType == AVAILABLE
                && borrower.isEmpty() && borrowDate.isEmpty() && returnDate.isEmpty();

        // A book with borrowed status must have corresponding borrower, borrow and return dates.
        String borrowDateString = borrowDate.orElse("");
        String returnDateString = returnDate.orElse("");
        boolean isValidBorrowRegex = borrowDateString.matches(VALIDATION_REGEX);
        boolean isValidReturnRegex = returnDateString.matches(VALIDATION_REGEX);
        boolean isBorrowedConstraintsSatisfied = bookStatusType == BORROWED
                && borrower.isPresent() && borrowDate.isPresent()
                && returnDate.isPresent() && isValidBorrowRegex && isValidReturnRegex
                && isValidDateString(borrowDateString) && isValidDateString(returnDateString);

        return isAvailableConstraintsSatisfied || isBorrowedConstraintsSatisfied;
    }

    /**
     * Returns true if the status is AVAILABLE status.
     */
    public boolean isAvailable() {
        return bookStatusType == AVAILABLE;
    }

    /**
     * Returns true if the status is BORROWED status.
     */
    public boolean isBorrowed() {
        return bookStatusType == BORROWED;
    }

    /**
     * Returns a String representing the borrow date of this book, or an empty string if this book is not borrowed.
     */
    public String getBorrowDateString() {
        if (borrowDate.isEmpty()) {
            return "";
        }
        return STATUS_DATE_FORMAT.format(borrowDate.get());
    }

    /**
     * Returns a String representing the return date of this book, or an empty string if this book is not borrowed.
     */
    public String getReturnDateString() {
        if (returnDate.isEmpty()) {
            return "";
        }
        return STATUS_DATE_FORMAT.format(returnDate.get());
    }

    /**
     * Returns true if this book is overdue.
     */
    public boolean isOverdue() {
        if (!isBorrowed()) {
            return false;
        }
        String returnDateString = getReturnDateString();
        String currentDateString = STATUS_DATE_FORMAT.format(new Date());
        Date currentDate = parseToDate(currentDateString);
        Date returnDate = parseToDate(returnDateString);
        return currentDate.after(returnDate);
    }

    /**
     * Returns a String representing the borrower's name of this book, or an empty string if this book is not borrowed.
     */
    public String getBorrowerName() {
        return borrower.map(patron -> patron.getName().toString()).orElse("");
    }

    public Optional<Patron> getBorrower() {
        return borrower;
    }

    public String getBookStatusTypeString() {
        return bookStatusType.toString();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(bookStatusType.toString());
        if (isBorrowed()) {
            builder.append("; Borrowed by: ").append(borrower.map(b -> b.getName()).get());
            builder.append("; Borrow Date: ").append(borrowDate.map(date -> STATUS_DATE_FORMAT.format(date)).get());
            builder.append("; Return Date: ").append(returnDate.map(date -> STATUS_DATE_FORMAT.format(date)).get());
        }
        return builder.toString();
    }

    @Override
    public boolean equals(Object other) {
        BookStatus otherBookStatus = (BookStatus) other;
        return other == this // short circuit if same object
                || (other instanceof BookStatus // instanceof handles nulls
                && bookStatusType == ((BookStatus) other).bookStatusType
                && borrower.equals(otherBookStatus.borrower)
                && borrowDate.equals(otherBookStatus.borrowDate)
                && returnDate.equals(otherBookStatus.returnDate)); // state check
    }

    /**
     * Returns true if the string to be tested is a valid date in dd-MMM-yyyy format, false otherwise.
     *
     * @param dateString The string to be tested.
     * @return True if the string to be tested is a valid date in dd-MMM-yyyy format.
     */
    public static boolean isValidDateString(String dateString) {
        try {
            Date parsedDate = STATUS_DATE_FORMAT.parse(dateString);
            String parsedDateString = STATUS_DATE_FORMAT.format(parsedDate);
            return parsedDateString.toLowerCase().equals(dateString.toLowerCase());
        } catch (ParseException e) {
            return false;
        }
    }

    private static Date parseToDate(String dateString) throws IllegalArgumentException {
        try {
            return STATUS_DATE_FORMAT.parse(dateString);
        } catch (ParseException e) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }
    }

}

package seedu.address.model.expense;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.function.Predicate;

/**
 * Tests that a {@code Expense}'s {@code ExpenseDate} matches the parsed date filter.
 */
public class ExpenseDateIsParsedDatePredicate implements Predicate<Expense> {

    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String MONTH_FORMAT = "yyyy-MM";

    private final LocalDate date;
    private final YearMonth ym;

    /**
     * Creates a Predicate Object for Filter Command. String parsed will either be of length 7 for filtering
     * by month and year or length 10 for filtering by exact date.
     *
     * @param predicate the filtering date/month.
     */
    public ExpenseDateIsParsedDatePredicate(String predicate) {
        if (predicate.length() == 7) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern(MONTH_FORMAT);
            this.ym = YearMonth.parse(predicate, dtf);
            this.date = null;
            return;
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATE_FORMAT);
        this.date = LocalDate.parse(predicate, dtf);
        this.ym = null;

    }

    @Override
    public boolean test(Expense expense) {
        //Compare month and year only
        if (ym != null) {
            return expense.getExpenseDate().date.getMonthValue() == ym.getMonthValue()
                    && expense.getExpenseDate().date.getYear() == ym.getYear();
        }

        return expense.getExpenseDate().date.equals(date);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof ExpenseDateIsParsedDatePredicate) {
            ExpenseDateIsParsedDatePredicate temp = (ExpenseDateIsParsedDatePredicate) other;
            if (date == null && temp.date == null) {
                return ym.equals(temp.ym);
            } else if (ym == null && temp.ym == null) {
                return date.equals(temp.date);
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}

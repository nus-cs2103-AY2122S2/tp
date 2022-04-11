package seedu.address.model.book;

import java.util.List;
import java.util.function.Predicate;


/**
 * Tests that a {@code Book}'s {@code BookName} matches any of the keywords given.
 */
public class BookNameContainsKeywordsPredicate implements Predicate<Book> {
    private final List<String> keywords;

    public BookNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Book book) {
        return keywords.stream()
                .anyMatch(keyword -> book.getBookName().toString().toUpperCase().contains(keyword.toUpperCase()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BookNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((BookNameContainsKeywordsPredicate) other).keywords)); // state check
    }
}

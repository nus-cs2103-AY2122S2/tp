package seedu.address.model.entry.predicate;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.entry.Company;
import seedu.address.model.entry.Entry;

/**
 * This class is an abstraction of combining {@code NameContainsKeywords}, {@code TagContainsKeywords}.
 * In particular, it tests that a {@code Company}'s  attributes matches any of the keywords given by user.
 * Acts the main logic part for checking whether a Company should be displayed for findc command
 */
public class CompanyContainsKeywordsPredicate implements Predicate<Company> {

    private final List<String> nameKeywords;
    private final List<String> tagKeywords;
    private final Predicate<Entry> searchTypePredicate;

    /**
     * Main Constructor for CompanyContainsKeywordsPredicate
     * @param nameKeywords user input to search company name
     * @param tagKeywords user input to search company tag
     */
    public CompanyContainsKeywordsPredicate(List<String> nameKeywords,
                                          List<String> tagKeywords, Predicate<Entry> searchTypePredicate) {
        this.nameKeywords = nameKeywords;
        this.tagKeywords = tagKeywords;
        this.searchTypePredicate = searchTypePredicate;
    }

    /**
     * It must be noted that the test function is implemented differently than the usual (ANY)ContainsKeywordsPredicate.
     * In particular, it checks name and tag keywords. If the keyword is not available (not given by user), then
     * it will automatically be removed from consideration when testing an company.
     */
    @Override
    public boolean test(Company company) {
        boolean correctName;
        boolean correctTag;

        correctName = invalidKeywords(nameKeywords)
                || new NameContainsKeywordsPredicate(nameKeywords).test(company);
        correctTag = invalidKeywords(tagKeywords) || new TagContainsKeywordsPredicate(tagKeywords).test(company);

        return correctName && correctTag && searchTypePredicate.test(company);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CompanyContainsKeywordsPredicate // instanceof handles nulls
                && nameKeywords.equals(((CompanyContainsKeywordsPredicate) other).nameKeywords)
                && tagKeywords.equals(((CompanyContainsKeywordsPredicate) other).tagKeywords)
                && searchTypePredicate.equals(((CompanyContainsKeywordsPredicate) other).searchTypePredicate));
    }

    /**
     * Returns whether the user input inside the keywords is empty. In particular, if it consists of 1 element of
     * empty string.
     */
    private boolean invalidKeywords(List<String> keywords) {
        return keywords.size() == 1 && keywords.get(0) == "";
    }

}

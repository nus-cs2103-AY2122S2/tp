package seedu.address.model.entry.predicate;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.entry.Entry;
import seedu.address.model.entry.Person;

/**
 * This class is an abstraction of combining {@code NameContainsKeywords}, {@code CompanyContainsKeywords},
 * {@code TagContainsKeywords}. In particular, it tests that a {@code Person}'s  attributes matches any of the
 * keywords given by user. Acts the main logic part for checking whether a Company should be displayed
 * for findp command
 */
public class PersonContainsKeywordsPredicate implements Predicate<Person> {

    private final List<String> nameKeywords;
    private final List<String> companyNameKeywords;
    private final List<String> tagKeywords;
    private final Predicate<Entry> searchTypePredicate;

    /**
     * Main Constructor for CompanyContainsKeywordsPredicate
     * @param nameKeywords user input to search person's name
     * @param companyNameKeywords user input to search person's companyName
     * @param tagKeywords user input to search person's tag
     */
    public PersonContainsKeywordsPredicate(List<String> nameKeywords, List<String> companyNameKeywords,
                                            List<String> tagKeywords, Predicate<Entry> searchTypePredicate) {
        this.nameKeywords = nameKeywords;
        this.companyNameKeywords = companyNameKeywords;
        this.tagKeywords = tagKeywords;
        this.searchTypePredicate = searchTypePredicate;
    }

    /**
     * It must be noted that the test function is implemented differently than the usual (ANY)ContainsKeywordsPredicate.
     * In particular, it checks name, companyName, and tag keywords.
     * If the keyword is not available (not given by user), then
     * it will automatically be removed from consideration when testing an company.
     */
    @Override
    public boolean test(Person person) {
        boolean correctName;
        boolean correctCompanyName;
        boolean correctTag;

        correctName = invalidKeywords(nameKeywords)
                || new NameContainsKeywordsPredicate(nameKeywords).test(person);
        correctCompanyName = invalidKeywords(companyNameKeywords)
                || new CompanyNameContainsKeywordsPredicate(companyNameKeywords).test(person);
        correctTag = invalidKeywords(tagKeywords) || new TagContainsKeywordsPredicate(tagKeywords).test(person);

        return correctName && correctCompanyName && correctTag && searchTypePredicate.test(person);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonContainsKeywordsPredicate // instanceof handles nulls
                && nameKeywords.equals(((PersonContainsKeywordsPredicate) other).nameKeywords)
                && companyNameKeywords.equals(((PersonContainsKeywordsPredicate) other).companyNameKeywords)
                && tagKeywords.equals(((PersonContainsKeywordsPredicate) other).tagKeywords)
                && searchTypePredicate.equals(((PersonContainsKeywordsPredicate) other).searchTypePredicate));
    }

    /**
     * Returns whether the user input inside the keywords is empty. In particular, if it consists of 1 element of
     * empty string.
     */
    private boolean invalidKeywords(List<String> keywords) {
        return keywords.size() == 1 && keywords.get(0) == "";
    }

}

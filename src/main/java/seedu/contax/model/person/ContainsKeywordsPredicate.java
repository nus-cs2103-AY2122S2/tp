package seedu.contax.model.person;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that an attribute of a {@code Person} matches any of the keywords given.
 */
public abstract class ContainsKeywordsPredicate implements Predicate<Person> {

    private final List<String> keywords;

    public ContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    public List<String> getKeywords() {
        return keywords;
    }

}

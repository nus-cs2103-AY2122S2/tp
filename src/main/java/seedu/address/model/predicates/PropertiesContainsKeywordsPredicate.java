package seedu.address.model.predicates;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;

/**
 * Tests that any of the {@code Properties} of a {@code Person} matches any of the keywords given.
 */
public class PropertiesContainsKeywordsPredicate implements Predicate<Person> {

    public static final String COMMAND_WORD = "properties";

    private final List<String> keywords;

    public PropertiesContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream().anyMatch(keyword ->
                person.getProperties().stream().anyMatch(property ->
                        StringUtil.containsWordIgnoreCase(property.toParseValue(), keyword)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PropertiesContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PropertiesContainsKeywordsPredicate) other).keywords)); // state check
    }

}

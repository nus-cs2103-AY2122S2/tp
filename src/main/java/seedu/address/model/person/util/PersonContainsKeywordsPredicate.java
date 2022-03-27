package seedu.address.model.person.util;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Name}, {@code Phone}, {@code Email}, {@code Address} and
 * {@code Tag} matches any of the keywords given.
 */
public class PersonContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public PersonContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream().anyMatch(keyword ->
                    person.getFields().parallelStream().anyMatch(field ->
                                    StringUtil.containsWordIgnoreCase(field.getValue(), keyword)
                                    || person.getTags().stream().map(tag -> tag.value)
                                            .anyMatch(keyword::equalsIgnoreCase)
                    ));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PersonContainsKeywordsPredicate) other).keywords)); // state check
    }

}

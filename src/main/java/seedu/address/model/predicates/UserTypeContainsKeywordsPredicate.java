package seedu.address.model.predicates;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code UserType} matches any of the keywords given.
 */
public class UserTypeContainsKeywordsPredicate implements Predicate<Person> {

    public static final String COMMAND_WORD = "usertype";

    private final List<String> keywords;

    public UserTypeContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getUserType().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UserTypeContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((UserTypeContainsKeywordsPredicate) other).keywords)); // state check
    }

}

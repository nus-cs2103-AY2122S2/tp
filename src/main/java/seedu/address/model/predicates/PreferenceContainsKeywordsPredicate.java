package seedu.address.model.predicates;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Preference} matches any of the keywords given.
 */
public class PreferenceContainsKeywordsPredicate implements Predicate<Person> {

    public static final String COMMAND_WORD = "preference";

    private final List<String> keywords;

    public PreferenceContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        String preference = person.preferenceToPlainString();
        if (preference.isEmpty()) {
            return false;
        } else {
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(preference, keyword));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PreferenceContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PreferenceContainsKeywordsPredicate) other).keywords)); // state check
    }

}

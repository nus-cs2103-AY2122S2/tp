package seedu.address.model.activity;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Activity} matches any of the keywords given.
 */
public class ActivityContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public ActivityContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        String activitiesString = person.getActivities().toString().replace("[", "")
                .replace("]", "").replace(", ", " ");
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(activitiesString, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ActivityContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ActivityContainsKeywordsPredicate) other).keywords)); // state check
    }
}

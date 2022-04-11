package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;
    private final boolean isPartial;

    /**
     * Constructor
     * @param keywords
     * @param isPartial
     */
    public NameContainsKeywordsPredicate(List<String> keywords, boolean isPartial) {
        this.isPartial = isPartial;
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        // check for NULL_INPUT
        if (keywords.size() == 1 && keywords.get(0).equals("NULL_INPUT")) {
            return false;
        }

        if (isPartial) {
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsPartialWordIgnoreCase(person.getName().fullName, keyword));
        } else {
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getName().fullName, keyword));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsKeywordsPredicate) other).keywords)); // state check
    }

}

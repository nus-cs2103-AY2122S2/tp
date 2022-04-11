package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class NameFacultyRoleContainsAnyKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public NameFacultyRoleContainsAnyKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getName().fullName, keyword)
                        || StringUtil.containsWordIgnoreCase(person.getFaculty().value, keyword)
                        || StringUtil.containsWordIgnoreCase(person.getRole().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameFacultyRoleContainsAnyKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameFacultyRoleContainsAnyKeywordsPredicate) other).keywords)); // state check
    }

}

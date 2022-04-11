package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s details matches any of the keywords given for a specified field in {@code Name,
 * Company, Job Title, Address, Email, Phone, Tags}.
 */
public class FieldContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;
    private final String field;

    /**
     * Constructor for FieldContainsKeywordsPredicate
     * @param keywords The list of keywords to match
     * @param field The field to search in
     */
    public FieldContainsKeywordsPredicate(List<String> keywords, String field) {
        this.keywords = keywords;
        this.field = field;
    }

    @Override
    public boolean test(Person person) {
        if ("c".equals(field)) {
            return testCompany(person);
        } else if ("j".equals(field)) {
            return testJob(person);
        } else if ("t".equals(field)) {
            return testTag(person);
        } else if ("p".equals(field)) {
            return testPhone(person);
        } else if ("e".equals(field)) {
            return testEmail(person);
        } else if ("a".equals(field)) {
            return testAddress(person);
        } else if ("n".equals(field)) {
            return testName(person);
        }
        return testAll(person);
    }

    private boolean testCompany(Person person) {
        if (person.getCompany().isPresent()) {
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getCompany().get().company, keyword));
        }
        return false;
    }

    private boolean testName(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getName().fullName, keyword));
    }

    private boolean testJob(Person person) {
        if (person.getJobTitle().isPresent()) {
            return keywords.stream().anyMatch(keyword -> StringUtil.containsWordIgnoreCase(
                    person.getJobTitle().get().jobTitle, keyword));
        }
        return false;
    }

    private boolean testTag(Person person) {
        if (person.getTagSet().isEmpty()) {
            return false;
        }
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCaseInSet(person.getTagSet(), keyword));
    }

    private boolean testPhone(Person person) {
        if (person.getNumbers().isEmpty()) {
            return false;
        }
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCaseInMap(person.getNumbers(), keyword));
    }

    private boolean testEmail(Person person) {
        if (person.getEmails().isEmpty()) {
            return false;
        }
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCaseInMap(person.getEmails(), keyword));
    }

    private boolean testAddress(Person person) {
        if (person.getAddresses().isEmpty()) {
            return false;
        }
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCaseInMap(person.getAddresses(), keyword));
    }

    private boolean testAll(Person person) {
        return testAddress(person)
                || testName(person)
                || testTag(person)
                || testJob(person)
                || testCompany(person)
                || testEmail(person)
                || testPhone(person);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FieldContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((FieldContainsKeywordsPredicate) other).keywords))
                && field.equals(((FieldContainsKeywordsPredicate) other).field); // state check
    }
}

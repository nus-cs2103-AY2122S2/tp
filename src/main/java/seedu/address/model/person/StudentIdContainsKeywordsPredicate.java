package seedu.address.model.person;

import static seedu.address.model.person.NusNetId.NULL_INPUT;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Student}'s {@code NusNetId} matches any of the keywords given.
 */
public class StudentIdContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public StudentIdContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        // check for NULL_INPUT
        if (keywords.size() == 1 && keywords.get(0).equals(NULL_INPUT)) {
            return false;
        }
        if (person instanceof Student) {
            Student student = (Student) person;
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsPartialWordIgnoreCase(student.getStudentId().id, keyword));
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentIdContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((StudentIdContainsKeywordsPredicate) other).keywords)); // state check
    }

}

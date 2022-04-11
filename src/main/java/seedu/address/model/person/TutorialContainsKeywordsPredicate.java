package seedu.address.model.person;

import static seedu.address.model.tutorial.TutorialName.NULL_INPUT;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@Code Tutorial}'s {@Code TutorialName} matches any of the keywords given.
 */
public class TutorialContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public TutorialContainsKeywordsPredicate(List<String> keywords) {
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
                    .anyMatch(keyword -> StringUtil
                            .containsPartialWordIgnoreCase(student.getTutorialName().name, keyword));
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TutorialContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TutorialContainsKeywordsPredicate) other).keywords)); // state check
    }

}

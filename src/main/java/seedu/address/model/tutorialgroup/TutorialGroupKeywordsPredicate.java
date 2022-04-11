package seedu.address.model.tutorialgroup;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.student.Student;

/**
 * Tests that a {@code Student}'s {@code Tutorial Group} matches any of the keywords given.
 */
public class TutorialGroupKeywordsPredicate implements Predicate<Student> {
    private final String keywords;

    /**
     * Constructor of the tutorial group keywords predicate class
     * @param keywords need to match exactly to an existing tutorial group(case-insensitive)
     */
    public TutorialGroupKeywordsPredicate(String keywords) {
        assert(keywords != null) : "Invalid keywords";
        this.keywords = keywords;
    }

    @Override
    public boolean test(Student student) {
        return student.getTutorialGroups().stream()
                .anyMatch(tg -> StringUtil.containsFullSentenceIgnoreCase(tg.tutorialGroupName, keywords));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TutorialGroupKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TutorialGroupKeywordsPredicate) other).keywords)); // state check
    }
}

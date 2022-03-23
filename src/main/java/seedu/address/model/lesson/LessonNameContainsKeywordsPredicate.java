package seedu.address.model.lesson;

import seedu.address.commons.util.StringUtil;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Lesson}'s {@code LessonName} matches any of the keywords given.
 */
public class LessonNameContainsKeywordsPredicate implements Predicate<Lesson> {
    private final List<String> keywords;

    public LessonNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Lesson lesson) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(lesson.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LessonNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((LessonNameContainsKeywordsPredicate) other).keywords)); // state check
    }

}

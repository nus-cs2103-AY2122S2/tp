package seedu.address.model.lesson;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Lesson}'s {@code LessonName} or {@code Subject} matches any of the keywords given.
 */
public class LessonNameOrSubjectContainsKeywordsPredicate implements Predicate<Lesson> {
    private final List<String> keywords;

    public LessonNameOrSubjectContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Lesson lesson) {
        boolean nameMatch = keywords.stream()
                .anyMatch(keyword -> lesson.getName().fullName.toLowerCase().contains(keyword.toLowerCase()));

        boolean subjectMatch = keywords.stream()
                .anyMatch(keyword -> lesson.getSubject().subjectName.toLowerCase().contains(keyword.toLowerCase()));

        return nameMatch || subjectMatch;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LessonNameOrSubjectContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((LessonNameOrSubjectContainsKeywordsPredicate) other).keywords)); // state check
    }

}

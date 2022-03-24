package seedu.address.model.student;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Student}'s {@code Name} or {@code Tags} matches any of the keywords given.
 */
public class NameOrTagsContainsKeywordsPredicate implements Predicate<Student> {
    private final List<String> keywords;

    public NameOrTagsContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Student student) {
        boolean nameMatch = nameContainsKeyword(student);
        boolean tagMatch = tagsContainKeyword(student);

        return nameMatch || tagMatch;
    }

    private boolean nameContainsKeyword(Student student) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(student.getName().fullName, keyword));
    }

    private boolean tagsContainKeyword(Student student) {
        List<Tag> tagList = new ArrayList<>(student.getTags());

        boolean tagMatch = false;
        for (Tag t : tagList) {
            tagMatch = keywords.stream().anyMatch(keyword ->
                    StringUtil.containsWordIgnoreCase(t.tagName, keyword));

            if (tagMatch == true) {
                break;
            }
        }

        return tagMatch;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameOrTagsContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameOrTagsContainsKeywordsPredicate) other).keywords)); // state check
    }

}

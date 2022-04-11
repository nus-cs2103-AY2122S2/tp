package seedu.address.model.application;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.tag.ApplicationStatusTagType;
import seedu.address.model.tag.PriorityTagType;
import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Application}'s {@code Tag} matches any of the keywords given.
 */
public class TagContainsKeywordsPredicate implements Predicate<Application> {
    private final List<String> keywords;

    public TagContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Application application) {
        StringBuilder builder = new StringBuilder();
        for (Tag tag : application.getTags()) {
            if (PriorityTagType.contains(tag.toString()) || ApplicationStatusTagType.contains(tag.toString())) {
                continue;
            }
            builder.append(tag.toString());
            builder.append(" ");
        }
        String allTags = builder.toString().trim();

        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(allTags, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TagContainsKeywordsPredicate) other).keywords)); // state check
    }

}

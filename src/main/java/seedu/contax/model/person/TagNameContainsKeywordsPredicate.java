package seedu.contax.model.person;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.contax.model.tag.Tag;

/**
 * Tests that an {@code Person}'s Tags matches any of the keywords given.
 */
public class TagNameContainsKeywordsPredicate implements Predicate<Person> {

    private String keyword;

    public TagNameContainsKeywordsPredicate(String keyword) {
        this.keyword = keyword.toLowerCase();
    }

    public String getKeyword() {
        return this.keyword;
    }

    /**
     * Returns true if there exists a {@code Tag} in {@code Person} which matches the specified keyword.
     */
    public boolean test(Person person) {
        Set<Tag> tags = person.getTags();

        for (Tag tag : tags) {
            if (tag.getTagNameString().equals(keyword)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Returns true if the keyword exists in the tag list.
     */
    public boolean existsInTagList(List<Tag> tagList) {
        return tagList.stream().anyMatch(tag -> tag.getTagNameString().equals(keyword));
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof TagNameContainsKeywordsPredicate)) {
            return false;
        }

        return ((TagNameContainsKeywordsPredicate) o).getKeyword().equals(keyword);
    }
}

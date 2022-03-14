package seedu.contax.model.person;

import java.util.Set;
import java.util.function.Predicate;

import seedu.contax.model.tag.Tag;

public class TagNameContainsKeywordsPredicate implements Predicate<Person> {

    private String keyword;

    public TagNameContainsKeywordsPredicate(String keyword) {
        this.keyword = keyword;
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
            if (tag.getTagNameString().contains(keyword)) {
                return true;
            }
        }

        return false;
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

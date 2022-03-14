package seedu.contax.model.tag;

import java.util.function.Predicate;

/**
 * Tests that a {@code Tag}'s {@code Name} matches the keyword given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Tag> {

    private String keyword;

    public NameContainsKeywordsPredicate(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return this.keyword;
    }

    public boolean test(Tag tag) {
        return tag.getTagNameString().contains(keyword);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof NameContainsKeywordsPredicate)) {
            return false;
        }

        return ((NameContainsKeywordsPredicate) o).getKeyword().equals(keyword);
    }
}

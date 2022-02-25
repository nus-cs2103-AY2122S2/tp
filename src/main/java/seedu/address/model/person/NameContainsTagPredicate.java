package seedu.address.model.person;

import java.util.Set;
import java.util.function.Predicate;

import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsTagPredicate implements Predicate<Person> {
    // May possibly change to a list of tags
    private final Tag tag;

    public NameContainsTagPredicate(String tag) {
        this.tag = new Tag(tag);
    }

    @Override
    public boolean test(Person person) {
        // instead of converting to stream, it is simply a tag now
        // so, to do the "test", we only need to check if tag is within the set of tags
        Set<Tag> tags = person.getTags();
        return tags.contains(tag);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsTagPredicate // instanceof handles nulls
                && tag.equals(((NameContainsTagPredicate) other).tag)); // state check
    }

}

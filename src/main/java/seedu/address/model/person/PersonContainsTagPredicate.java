package seedu.address.model.person;

import java.util.Set;
import java.util.function.Predicate;

import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Person}'s profile is attached to a given {@code Tag}.
 */
public class PersonContainsTagPredicate implements Predicate<Person> {
    private final Tag tag;

    public PersonContainsTagPredicate(Tag tag) {
        this.tag = tag;
    }

    @Override
    public boolean test(Person person) {
        Set<Tag> attachedTags = person.getTags();
        return attachedTags.contains(tag);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                 || (other instanceof PersonContainsTagPredicate // instanceof handles nulls
                 && tag.isSameTag(((PersonContainsTagPredicate) other).tag)); // state check
    }
}

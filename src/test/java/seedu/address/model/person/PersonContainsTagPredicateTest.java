package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class PersonContainsTagPredicateTest {

    @Test
    public void equals() {
        Tag firstTag = new Tag("first");
        Tag secondTag = new Tag("second");

        PersonContainsTagPredicate firstPredicate = new PersonContainsTagPredicate(firstTag);
        PersonContainsTagPredicate secondPredicate = new PersonContainsTagPredicate(secondTag);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PersonContainsTagPredicate firstPredicateCopy = new PersonContainsTagPredicate(firstTag);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_personContainsTag_returnsTrue() {
        Tag tag = new Tag("friends");
        PersonContainsTagPredicate predicate = new PersonContainsTagPredicate(tag);
        assertTrue(predicate.test(new PersonBuilder().withTags("friends").build()));
    }

    @Test
    public void test_personDoesNotContainsTag_returnsFalse() {
        Tag tag = new Tag("nonExistingTag");
        PersonContainsTagPredicate predicate = new PersonContainsTagPredicate(tag);
        assertFalse(predicate.test(new PersonBuilder().withTags("friends", "family").build()));
    }


}

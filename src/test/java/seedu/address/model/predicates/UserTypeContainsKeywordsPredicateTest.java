package seedu.address.model.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class UserTypeContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        UserTypeContainsKeywordsPredicate firstPredicate =
                new UserTypeContainsKeywordsPredicate(firstPredicateKeywordList);
        UserTypeContainsKeywordsPredicate secondPredicate =
                new UserTypeContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        UserTypeContainsKeywordsPredicate firstPredicateCopy =
                new UserTypeContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_userTypeContainsKeywords_returnsTrue() {
        // One keyword
        UserTypeContainsKeywordsPredicate predicate =
                new UserTypeContainsKeywordsPredicate(Collections.singletonList("buyer"));
        assertTrue(predicate.test(new PersonBuilder().build()));

        // Multiple keywords
        predicate = new UserTypeContainsKeywordsPredicate(Arrays.asList("buyer", "seller"));
        assertTrue(predicate.test(new PersonBuilder().build()));

        // Mixed-case keywords
        predicate = new UserTypeContainsKeywordsPredicate(Collections.singletonList("bUYer"));
        assertTrue(predicate.test(new PersonBuilder().build()));
    }

    @Test
    public void test_userTypeDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        UserTypeContainsKeywordsPredicate predicate =
                new UserTypeContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().build()));

        // Non-matching keyword
        predicate = new UserTypeContainsKeywordsPredicate(Collections.singletonList("seller"));
        assertFalse(predicate.test(new PersonBuilder().build()));

        // Keywords match name, phone, email and address, but does not match userType
        predicate = new UserTypeContainsKeywordsPredicate(
                Arrays.asList("Alice", "12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build()));
    }
}

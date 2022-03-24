package seedu.address.model.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class PropertiesContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        PropertiesContainsKeywordsPredicate firstPredicate =
                new PropertiesContainsKeywordsPredicate(firstPredicateKeywordList);
        PropertiesContainsKeywordsPredicate secondPredicate =
                new PropertiesContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PropertiesContainsKeywordsPredicate firstPredicateCopy =
                new PropertiesContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_propertiesContainsKeywords_returnsTrue() {
        Person testPerson = new PersonBuilder().withProperties("north,some place,2-room,$300000").build();

        // One keyword
        PropertiesContainsKeywordsPredicate predicate =
                new PropertiesContainsKeywordsPredicate(Collections.singletonList("north"));
        assertTrue(predicate.test(testPerson));

        predicate = new PropertiesContainsKeywordsPredicate(Collections.singletonList("place"));
        assertTrue(predicate.test(testPerson));

        predicate = new PropertiesContainsKeywordsPredicate(Collections.singletonList("2-room"));
        assertTrue(predicate.test(testPerson));

        predicate = new PropertiesContainsKeywordsPredicate(Collections.singletonList("$300000"));
        assertTrue(predicate.test(testPerson));

        // Multiple keywords
        predicate = new PropertiesContainsKeywordsPredicate(Arrays.asList("north", "2-room"));
        assertTrue(predicate.test(testPerson));

        // Only one matching keyword
        predicate = new PropertiesContainsKeywordsPredicate(Arrays.asList("north", "3-room"));
        assertTrue(predicate.test(testPerson));

        // Mixed-case keywords
        predicate = new PropertiesContainsKeywordsPredicate(Arrays.asList("noRTh", "pLaCE"));
        assertTrue(predicate.test(testPerson));
    }

    @Test
    public void test_propertiesDoesNotContainKeywords_returnsFalse() {
        Person testPerson = new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street")
                .withProperties("north,some place,2-room,$300000").build();

        // Zero keywords
        PropertiesContainsKeywordsPredicate predicate =
                new PropertiesContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(testPerson));

        // Non-matching keyword
        predicate = new PropertiesContainsKeywordsPredicate(Arrays.asList("west", "other", "3-room", "$400000"));
        assertFalse(predicate.test(testPerson));

        // Keywords match name, phone, email and address, but does not match properties
        predicate = new PropertiesContainsKeywordsPredicate(
                Arrays.asList("Alice", "12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(testPerson));
    }
}

package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class EmailContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        EmailContainsKeywordsPredicate firstPredicate = new EmailContainsKeywordsPredicate(firstPredicateKeywordList);
        EmailContainsKeywordsPredicate secondPredicate = new EmailContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        EmailContainsKeywordsPredicate firstPredicateCopy =
                new EmailContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_emailContainsKeywords_returnsTrue() {
        // One keyword
        EmailContainsKeywordsPredicate predicate =
                new EmailContainsKeywordsPredicate(Collections.singletonList("amy@example.com"));
        assertTrue(predicate.test(new PersonBuilder().withEmail(VALID_EMAIL_AMY).build()));

        // Multiple keywords
        predicate = new EmailContainsKeywordsPredicate(Arrays.asList("amy@example.com", "bob@example.com"));
        assertTrue(predicate.test(new PersonBuilder().withEmail(VALID_EMAIL_AMY).build())
                && predicate.test(new PersonBuilder().withEmail(VALID_EMAIL_BOB).build()));

        // Mixed-case keywords
        predicate = new EmailContainsKeywordsPredicate(Arrays.asList("aMy@ExAmPlE.cOm"));
        assertTrue(predicate.test(new PersonBuilder().withEmail(VALID_EMAIL_AMY).build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        EmailContainsKeywordsPredicate predicate = new EmailContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withEmail(VALID_EMAIL_AMY).build()));

        // Non-matching keyword
        predicate = new EmailContainsKeywordsPredicate(Arrays.asList("heinz@example.com"));
        assertFalse(predicate.test(new PersonBuilder().withEmail(VALID_EMAIL_AMY).build()));

        // Multiple Non-matching keyword
        predicate = new EmailContainsKeywordsPredicate(Arrays.asList("heinz@example.com", "wheeze@example.com"));
        assertFalse(predicate.test(new PersonBuilder().withEmail(VALID_EMAIL_AMY).build())
                && predicate.test(new PersonBuilder().withEmail(VALID_EMAIL_BOB).build()));

        // Keywords match phone, name and address, but does not match email
        predicate = new EmailContainsKeywordsPredicate(Arrays.asList("12345", "Alice", "heinz@example.com", "Main",
                "Street"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail(VALID_EMAIL_AMY).withAddress("Main Street").build()));
    }
}

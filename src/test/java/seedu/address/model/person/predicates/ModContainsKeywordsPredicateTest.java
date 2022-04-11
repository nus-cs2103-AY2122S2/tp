package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class ModContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        ModContainsKeywordsPredicate firstPredicate = new ModContainsKeywordsPredicate(firstPredicateKeywordList);
        ModContainsKeywordsPredicate secondPredicate = new ModContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ModContainsKeywordsPredicate firstPredicateCopy = new ModContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different mod -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_modContainsKeywords_returnsTrue() {
        // One keyword
        ModContainsKeywordsPredicate predicate = new ModContainsKeywordsPredicate(Collections.singletonList("CS2100"));
        assertTrue(predicate.test(new PersonBuilder().withMod("CS2100").build()));

        // Multiple keywords
        predicate = new ModContainsKeywordsPredicate(Arrays.asList("CS2100", "CS"));
        assertTrue(predicate.test(new PersonBuilder().withMod("CS2100").build()));

        // Only one matching keyword
        predicate = new ModContainsKeywordsPredicate(Arrays.asList("CS2100", "CS2030S"));
        assertTrue(predicate.test(new PersonBuilder().withMod("CS2100").build()));

        // Mixed-case keywords
        predicate = new ModContainsKeywordsPredicate(Arrays.asList("cS2100", "Cs2030s"));
        assertTrue(predicate.test(new PersonBuilder().withMod("CS2100").build()));
    }

    @Test
    public void test_modDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        ModContainsKeywordsPredicate predicate = new ModContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withMod("CS2100").build()));

        // Non-matching keyword
        predicate = new ModContainsKeywordsPredicate(Arrays.asList("MA1101R"));
        assertFalse(predicate.test(new PersonBuilder().withMod("CS2100").build()));

        // Keywords match student number, email and name, but does not match mod
        predicate = new ModContainsKeywordsPredicate(Arrays.asList("12345", "alice@email.com", "Alice"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withStudentNumber("A0246813G")
                .withEmail("alice@email.com").withMod("CS2105").build()));
    }
}

package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.keyword.Keyword;
import seedu.address.testutil.TaskBuilder;

public class NameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        Set<Keyword> firstPredicateKeywordSet = Collections.singleton(new Keyword("first"));
        Set<Keyword> secondPredicateKeywordSet = Set.of(new Keyword("first"), new Keyword("second"));

        NameContainsKeywordsPredicate firstPredicate = new NameContainsKeywordsPredicate(firstPredicateKeywordSet);
        NameContainsKeywordsPredicate secondPredicate = new NameContainsKeywordsPredicate(secondPredicateKeywordSet);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NameContainsKeywordsPredicate firstPredicateCopy =
                new NameContainsKeywordsPredicate(firstPredicateKeywordSet);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different task -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {

        // Zero keywords
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Collections.emptySet());
        assertTrue(predicate.test(new TaskBuilder().withName("CS3240").build()));

        // One keyword
        predicate = new NameContainsKeywordsPredicate(
                Collections.singleton(new Keyword("CS2103T")));
        assertTrue(predicate.test(new TaskBuilder().withName("CS2103T tutorial").build()));

        // Multiple keywords
        predicate = new NameContainsKeywordsPredicate(Set.of(new Keyword("CS2103T"), new Keyword("Tutorial")));
        assertTrue(predicate.test(new TaskBuilder().withName("CS2103T Tutorial").build()));

        // Only one matching keyword
        predicate = new NameContainsKeywordsPredicate(Set.of(new Keyword("CS2103T"), new Keyword("CS3240")));
        assertTrue(predicate.test(new TaskBuilder().withName("CS3240 Finals").build()));

        // Mixed-case keywords
        predicate = new NameContainsKeywordsPredicate(Set.of(new Keyword("TutoriaL"), new Keyword("cs3240")));
        assertTrue(predicate.test(new TaskBuilder().withName("CS3240 tutorial").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {

        // Non-matching keyword
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Set.of(new Keyword("CS3240")));
        assertFalse(predicate.test(new TaskBuilder().withName("CS2103T Tutorial").build()));

        // Keywords match description, completion status, deadline and tags, but does not match name
        predicate = new NameContainsKeywordsPredicate(Set.of(new Keyword("G1"), new Keyword("true"),
                new Keyword("2022"), new Keyword("CS2103T")));
        assertFalse(predicate.test(new TaskBuilder()
                .withName("Complete Tutorial")
                .withDescription("Finish 3 parts of G1")
                .withCompletionStatus("true")
                .withDeadline("2022-10-22")
                .withTags("CS2103T").build()));
    }
}

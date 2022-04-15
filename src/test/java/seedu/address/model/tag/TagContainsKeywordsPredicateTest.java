package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.keyword.Keyword;
import seedu.address.testutil.TaskBuilder;

public class TagContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        Set<Keyword> firstPredicateKeywordList = new HashSet<>(Arrays.asList(new Keyword("first")));
        Set<Keyword> secondPredicateKeywordList = new HashSet<>(Arrays.asList(new Keyword("first"),
                new Keyword("second")));

        TagContainsKeywordsPredicate firstPredicate = new TagContainsKeywordsPredicate(firstPredicateKeywordList);
        TagContainsKeywordsPredicate secondPredicate = new TagContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TagContainsKeywordsPredicate firstPredicateCopy =
                new TagContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different task -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_tagContainsKeywords_returnsTrue() {

        // Zero keywords
        TagContainsKeywordsPredicate predicate = new TagContainsKeywordsPredicate(new HashSet<>());

        assertTrue(predicate.test(new TaskBuilder().withTags("CS3240").build()));
        // One keyword
        predicate = new TagContainsKeywordsPredicate(
                Collections.singleton(new Keyword("CS2103T")));
        assertTrue(predicate.test(new TaskBuilder().withTags("CS2103T").build()));

        // Multiple keywords
        predicate = new TagContainsKeywordsPredicate(new HashSet<>(Arrays.asList(new Keyword("CS2103T"),
                new Keyword("Tutorial"))));
        assertTrue(predicate.test(new TaskBuilder().withTags("CS2103T", "Tutorial").build()));

        // Only one matching keyword
        predicate = new TagContainsKeywordsPredicate(new HashSet<>(Arrays.asList(new Keyword("CS2103T"),
                new Keyword("CS3240"))));
        assertTrue(predicate.test(new TaskBuilder().withTags("CS3240").build()));

        // Mixed-case keywords
        predicate = new TagContainsKeywordsPredicate(new HashSet<>(Arrays.asList(new Keyword("TutoriaL"),
                new Keyword("cs3240"))));
        assertTrue(predicate.test(new TaskBuilder().withTags("CS3240", "tutorial").build()));
    }

    @Test
    public void test_tagDoesNotContainKeywords_returnsFalse() {

        // Non-matching keyword
        TagContainsKeywordsPredicate predicate = new TagContainsKeywordsPredicate(Collections.singleton(
                new Keyword("CS3240")));
        assertFalse(predicate.test(new TaskBuilder().withTags("CS2103T", "Tutorial").build()));

        // Keywords match name, description, completion status, but does not match tag
        predicate = new TagContainsKeywordsPredicate(
                new HashSet<>(Arrays.asList(new Keyword("Tutorial"), new Keyword("G1"),
                        new Keyword("true"))));
        assertFalse(predicate.test(new TaskBuilder()
                .withName("Complete Tutorial")
                .withDescription("Finish 3 parts of G1")
                .withCompletionStatus("true")
                .withDeadline("2022-10-22")
                .withTags("CS2103T").build()));
    }
}


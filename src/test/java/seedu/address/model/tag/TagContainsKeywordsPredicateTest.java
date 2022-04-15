package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TaskBuilder;

/**
 * Test class for {@code TaskTagContainsKeywordsPredicate} and {@code PersonTagContainsKeywordPredicate}
 */
public class TagContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        // Persons Test
        PersonTagContainsKeywordsPredicate firstPersonPredicate =
                new PersonTagContainsKeywordsPredicate(firstPredicateKeywordList);
        PersonTagContainsKeywordsPredicate secondPersonPredicate =
                new PersonTagContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPersonPredicate.equals(firstPersonPredicate));

        // same values -> returns true
        PersonTagContainsKeywordsPredicate firstPersonPredicateCopy =
                new PersonTagContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPersonPredicate.equals(firstPersonPredicateCopy));

        // different types -> returns false
        assertFalse(firstPersonPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPersonPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPersonPredicate.equals(secondPersonPredicate));

        // Tasks Test
        TaskTagContainsKeywordsPredicate firstTaskPredicate =
                new TaskTagContainsKeywordsPredicate(firstPredicateKeywordList);
        TaskTagContainsKeywordsPredicate secondTaskPredicate =
                new TaskTagContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstTaskPredicate.equals(firstTaskPredicate));

        // same values -> returns true
        TaskTagContainsKeywordsPredicate firstTaskPredicateCopy =
                new TaskTagContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstTaskPredicate.equals(firstTaskPredicateCopy));

        // different types -> returns false
        assertFalse(firstTaskPredicate.equals(1));

        // null -> returns false
        assertFalse(firstTaskPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstTaskPredicate.equals(secondTaskPredicate));
    }

    @Test
    public void test_tagContainsKeywords_returnsTrue() {
        // Persons Test
        // One keyword
        PersonTagContainsKeywordsPredicate personPredicate =
                new PersonTagContainsKeywordsPredicate(Collections.singletonList("Family"));
        assertTrue(personPredicate.test(new PersonBuilder().withTags("Family", "Colleague").build()));

        // Multiple keywords
        personPredicate = new PersonTagContainsKeywordsPredicate(Arrays.asList("Family", "Colleague"));
        assertTrue(personPredicate.test(new PersonBuilder().withTags("Family", "Colleague").build()));

        // Only one matching keyword
        personPredicate = new PersonTagContainsKeywordsPredicate(Arrays.asList("Colleague", "Team"));
        assertTrue(personPredicate.test(new PersonBuilder().withTags("Family", "Team").build()));

        // Mixed-case keywords
        personPredicate = new PersonTagContainsKeywordsPredicate(Arrays.asList("fAmiLy", "coLLEague"));
        assertTrue(personPredicate.test(new PersonBuilder().withTags("Family", "Colleague").build()));

        // Tasks Test
        TaskTagContainsKeywordsPredicate taskPredicate =
                new TaskTagContainsKeywordsPredicate(Collections.singletonList("Family"));
        assertTrue(taskPredicate.test(new TaskBuilder().withTags("Family", "Colleague").build()));

        // Multiple keywords
        taskPredicate = new TaskTagContainsKeywordsPredicate(Arrays.asList("Family", "Colleague"));
        assertTrue(taskPredicate.test(new TaskBuilder().withTags("Family", "Colleague").build()));

        // Only one matching keyword
        taskPredicate = new TaskTagContainsKeywordsPredicate(Arrays.asList("Colleague", "Team"));
        assertTrue(taskPredicate.test(new TaskBuilder().withTags("Family", "Team").build()));

        // Mixed-case keywords
        taskPredicate = new TaskTagContainsKeywordsPredicate(Arrays.asList("fAmiLy", "coLLEague"));
        assertTrue(taskPredicate.test(new TaskBuilder().withTags("Family", "Colleague").build()));

    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        //Persons Test
        // Zero keywords
        PersonTagContainsKeywordsPredicate personPredicate =
                new PersonTagContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(personPredicate.test(new PersonBuilder().withTags("Family").build()));

        // Non-matching keyword
        personPredicate = new PersonTagContainsKeywordsPredicate(Arrays.asList("Team"));
        assertFalse(personPredicate.test(new PersonBuilder().withTags("Family", "Colleague").build()));

        // Keywords match name, phone, and address, but does not match tag
        personPredicate = new PersonTagContainsKeywordsPredicate(
                Arrays.asList("Alice" , "12345", "Main", "Street"));
        assertFalse(personPredicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").withTags("Family", "Colleague").build()));

        //Tasks Test
        // Zero keywords
        TaskTagContainsKeywordsPredicate taskPredicate =
                new TaskTagContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(taskPredicate.test(new TaskBuilder().withTags("Family").build()));

        // Non-matching keyword
        taskPredicate = new TaskTagContainsKeywordsPredicate(Arrays.asList("Team"));
        assertFalse(taskPredicate.test(new TaskBuilder().withTags("Family", "Colleague").build()));

        // Keywords match name, but does not match tag
        taskPredicate = new TaskTagContainsKeywordsPredicate(
                Arrays.asList("Meeting" , "Dinner", "Lunch", "Medical"));
        assertFalse(taskPredicate.test(new TaskBuilder().withName("Meeting").withDate("10-03-2020")
                .withStartTime("08:00").withEndTime("09:00").withTags("Family", "Colleague").build()));
    }
}

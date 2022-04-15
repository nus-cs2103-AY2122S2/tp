package seedu.address.model.name;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TaskBuilder;

/**
 * Test class for {@code PersonNameContainsKeywordsPredicate} and {@code TaskNameContainsKeywordPredicate}
 */
public class NameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        // Persons Test
        PersonNameContainsKeywordsPredicate firstPersonPredicate =
                new PersonNameContainsKeywordsPredicate(firstPredicateKeywordList);
        PersonNameContainsKeywordsPredicate secondPersonPredicate =
                new PersonNameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPersonPredicate.equals(firstPersonPredicate));

        // same values -> returns true
        PersonNameContainsKeywordsPredicate firstPersonPredicateCopy =
                new PersonNameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPersonPredicate.equals(firstPersonPredicateCopy));

        // different types -> returns false
        assertFalse(firstPersonPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPersonPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPersonPredicate.equals(secondPersonPredicate));

        // Tasks Test
        TaskNameContainsKeywordsPredicate firstTaskPredicate =
                new TaskNameContainsKeywordsPredicate(firstPredicateKeywordList);
        TaskNameContainsKeywordsPredicate secondTaskPredicate =
                new TaskNameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstTaskPredicate.equals(firstTaskPredicate));

        // same values -> returns true
        TaskNameContainsKeywordsPredicate firstTaskPredicateCopy =
                new TaskNameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstTaskPredicate.equals(firstTaskPredicateCopy));

        // different types -> returns false
        assertFalse(firstTaskPredicate.equals(1));

        // null -> returns false
        assertFalse(firstTaskPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstTaskPredicate.equals(secondTaskPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // Persons Test
        // One keyword
        PersonNameContainsKeywordsPredicate personPredicate =
                new PersonNameContainsKeywordsPredicate(Collections.singletonList("Alice"));
        assertTrue(personPredicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        personPredicate = new PersonNameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(personPredicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        personPredicate = new PersonNameContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(personPredicate.test(new PersonBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        personPredicate = new PersonNameContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(personPredicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Tasks Test
        TaskNameContainsKeywordsPredicate taskPredicate =
                new TaskNameContainsKeywordsPredicate(Collections.singletonList("Meeting"));
        assertTrue(taskPredicate.test(new TaskBuilder().withName("Meeting Dinner").build()));

        // Multiple keywords
        taskPredicate = new TaskNameContainsKeywordsPredicate(Arrays.asList("Meeting", "Dinner"));
        assertTrue(taskPredicate.test(new TaskBuilder().withName("Meeting Dinner").build()));

        // Only one matching keyword
        taskPredicate = new TaskNameContainsKeywordsPredicate(Arrays.asList("Dinner", "Lunch"));
        assertTrue(taskPredicate.test(new TaskBuilder().withName("Meeting Lunch").build()));

        // Mixed-case keywords
        taskPredicate = new TaskNameContainsKeywordsPredicate(Arrays.asList("MeEtINg", "diNner"));
        assertTrue(taskPredicate.test(new TaskBuilder().withName("Meeting Dinner").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        //Persons Test
        // Zero keywords
        PersonNameContainsKeywordsPredicate personPredicate =
                new PersonNameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(personPredicate.test(new PersonBuilder().withName("Alice").build()));

        // Non-matching keyword
        personPredicate = new PersonNameContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(personPredicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Keywords match phone, email and address, but does not match name
        personPredicate =
                new PersonNameContainsKeywordsPredicate(Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(personPredicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build()));

        //Tasks Test
        // Zero keywords
        TaskNameContainsKeywordsPredicate taskPredicate =
                new TaskNameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(taskPredicate.test(new TaskBuilder().withName("Meeting").build()));

        // Non-matching keyword
        taskPredicate = new TaskNameContainsKeywordsPredicate(Arrays.asList("Lunch"));
        assertFalse(taskPredicate.test(new TaskBuilder().withName("Meeting Dinner").build()));

        // Keywords match date, start time and end time, but does not match name
        taskPredicate =
                new TaskNameContainsKeywordsPredicate(Arrays.asList("10-03-2020", "08:00", "09:00"));
        assertFalse(taskPredicate.test(new TaskBuilder().withName("Meeting").withDate("10-03-2020")
                .withStartTime("08:00").withEndTime("09:00").build()));
    }
}

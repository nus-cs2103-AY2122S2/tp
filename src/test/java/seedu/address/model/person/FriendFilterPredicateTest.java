package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.FriendFilterPredicateBuilder;
import seedu.address.testutil.PersonBuilder;

public class FriendFilterPredicateTest {

    @Test
    public void equals() {
        FriendFilterPredicate firstPredicate = new FriendFilterPredicateBuilder().withNameSubstring("first").build();
        FriendFilterPredicate secondPredicate = new FriendFilterPredicateBuilder().withNameSubstring("second").build();

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        FriendFilterPredicate firstPredicateCopy = new FriendFilterPredicateBuilder().withNameSubstring("first").build();
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_friendNameContainsKeywords_returnsTrue() {
        // One keyword
        FriendFilterPredicate predicate = new FriendFilterPredicateBuilder().withNameSubstring("Alice").build();
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new FriendFilterPredicateBuilder().withNameSubstring("Alice").withNameSubstring("Bob").build();
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Substring match
        predicate = new FriendFilterPredicateBuilder().withNameSubstring("Bob").withNameSubstring("Carol").build();
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Caroline").build()));

        // Mixed-case keywords
        predicate = new FriendFilterPredicateBuilder().withNameSubstring("aLice").withNameSubstring("bOB").build();
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        FriendFilterPredicate predicate = new FriendFilterPredicateBuilder().build();
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new FriendFilterPredicateBuilder().withNameSubstring("Alex").build();
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

    }

    @Test
    public void test_tagContainsKeywords_returnsTrue() {
        // single keyword match
        FriendFilterPredicate predicate = new FriendFilterPredicateBuilder().withTagSubstring("Neighbour").build();
        assertTrue(predicate.test(new PersonBuilder().withTags("Neighbour", "Friend").build()));

        // Multiple keywords given - one keyword match
        predicate = new FriendFilterPredicateBuilder().withTagSubstring("Neighbour").withTagSubstring("Friend").build();
        assertTrue(predicate.test(new PersonBuilder().withTags("Neighbour").build()));

        // Substring match
        predicate = new FriendFilterPredicateBuilder().withTagSubstring("School").build();
        assertTrue(predicate.test(new PersonBuilder().withTags("Schoolmates").build()));

        // Mixed-case keywords
        predicate = new FriendFilterPredicateBuilder().withTagSubstring("sChOol").build();
        assertTrue(predicate.test(new PersonBuilder().withTags("School").build()));
    }

    @Test
    public void test_tagDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        FriendFilterPredicate predicate = new FriendFilterPredicateBuilder().build();
        assertFalse(predicate.test(new PersonBuilder().withTags("owemoney").build()));

        // Non-matching keyword
        predicate = new FriendFilterPredicateBuilder().withTagSubstring("Friend").build();
        assertFalse(predicate.test(new PersonBuilder().withTags("Buddy").build()));
    }

    @Test
    public void test_logTitleContainsKeywords_returnsTrue() {
        // log title match to person with one log
        FriendFilterPredicate predicate = new FriendFilterPredicateBuilder().withLogTitleSubstring("My first log!").build();
        assertTrue(predicate.test(new PersonBuilder().withLogs(new Log("My First Log!", "desc")).build()));

        // log title match to person with multiple logs
        predicate = new FriendFilterPredicateBuilder().withLogTitleSubstring("My second log!").build();
        assertTrue(predicate.test(new PersonBuilder()
                .withLogs(new Log("My First Log!", "desc"),
                          new Log("My Second Log!", "desc")).build()));

        // Substring match
        predicate = new FriendFilterPredicateBuilder().withLogTitleSubstring("Lunch").build();
        assertTrue(predicate.test(new PersonBuilder().withLogs(new Log("Late lunch with Tom", "desc")).build()));

        // Mixed-case keywords
        predicate = new FriendFilterPredicateBuilder().withLogTitleSubstring("log tITLE").build();
        assertTrue(predicate.test(new PersonBuilder().withLogs(new Log("log title", "desc")).build()));
    }

    @Test
    public void test_logTitleDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        FriendFilterPredicate predicate = new FriendFilterPredicateBuilder().build();
        assertFalse(predicate.test(new PersonBuilder().withLogs(new Log("log title", "desc")).build()));

        // Non-matching keyword
        predicate = new FriendFilterPredicateBuilder().withLogTitleSubstring("My third log!").build();
        assertFalse(predicate.test(new PersonBuilder()
                .withLogs(new Log("My First Log!", "desc"),
                        new Log("My Second Log!", "desc")).build()));

    }
}

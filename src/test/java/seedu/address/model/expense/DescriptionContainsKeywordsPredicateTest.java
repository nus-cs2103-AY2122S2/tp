package seedu.address.model.expense;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ExpenseBuilder;

public class DescriptionContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        DescriptionContainsKeywordsPredicate firstPredicate =
                new DescriptionContainsKeywordsPredicate(firstPredicateKeywordList);
        DescriptionContainsKeywordsPredicate secondPredicate =
                new DescriptionContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        DescriptionContainsKeywordsPredicate firstPredicateCopy =
                new DescriptionContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different predicate -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_descriptionContainsKeywords_returnsTrue() {
        // One keyword
        DescriptionContainsKeywordsPredicate predicate =
                new DescriptionContainsKeywordsPredicate(Collections.singletonList("Providore"));
        assertTrue(predicate.test(new ExpenseBuilder().withDescription("Lunch at Providore Cafe").build()));

        // Multiple keywords
        predicate = new DescriptionContainsKeywordsPredicate(Arrays.asList("Providore", "Cafe"));
        assertTrue(predicate.test(new ExpenseBuilder().withDescription("Lunch at Providore Cafe").build()));

        // Only one matching keyword
        predicate = new DescriptionContainsKeywordsPredicate(Arrays.asList("Netflix", "Providore"));
        assertTrue(predicate.test(new ExpenseBuilder().withDescription("Monthly Netflix Subscription").build()));

        // Mixed-case keywords
        predicate = new DescriptionContainsKeywordsPredicate(Arrays.asList("nEtFlix", "Providore"));
        assertTrue(predicate.test(new ExpenseBuilder().withDescription("Monthly Netflix Subscription").build()));
    }

    @Test
    public void test_descriptionDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        DescriptionContainsKeywordsPredicate predicate =
                new DescriptionContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new ExpenseBuilder().withDescription("Monthly Netflix Subscription").build()));

        // Non-matching keyword
        predicate = new DescriptionContainsKeywordsPredicate(Arrays.asList("Netflix"));
        assertFalse(predicate.test(new ExpenseBuilder().withDescription("Lunch at Providore Cafe").build()));

        // Keywords match amount, expenseCategory and expenseDate, but does not match description
        predicate = new DescriptionContainsKeywordsPredicate(Arrays.asList("Food", "50", "2022-02-13"));
        assertFalse(predicate.test(new ExpenseBuilder().withDescription("Lunch at Providore Cafe").withAmount("50")
                .withExpenseCategory("Food").withExpenseDate("2022-02-13").build()));
    }
}

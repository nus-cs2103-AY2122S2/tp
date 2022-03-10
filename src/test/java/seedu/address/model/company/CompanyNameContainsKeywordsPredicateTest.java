package seedu.address.model.company;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.CompanyBuilder;
import seedu.address.testutil.RoleBuilder;

public class CompanyNameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        CompanyNameContainsKeywordsPredicate firstPredicate =
                new CompanyNameContainsKeywordsPredicate(firstPredicateKeywordList,
                        firstPredicateKeywordList);
        CompanyNameContainsKeywordsPredicate secondPredicate =
                new CompanyNameContainsKeywordsPredicate(secondPredicateKeywordList,
                        secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        CompanyNameContainsKeywordsPredicate firstPredicateCopy =
                new CompanyNameContainsKeywordsPredicate(firstPredicateKeywordList,
                        firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different company -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One company keyword
        CompanyNameContainsKeywordsPredicate predicate =
                new CompanyNameContainsKeywordsPredicate(new ArrayList<>(),
                        Collections.singletonList("Square"));
        assertTrue(predicate.test(new CompanyBuilder().withName("Square Enix").build()));

        // Both company and role keywords
        predicate = new CompanyNameContainsKeywordsPredicate(Arrays.asList("Software", "developer"),
                Arrays.asList("Square", "enix"));
        assertTrue(predicate.test(new CompanyBuilder().withName("Square enix")
                .withRoles(new RoleBuilder().withName("Software developer").build()).build()));

        // Only one matching keyword
        predicate = new CompanyNameContainsKeywordsPredicate(new ArrayList<>(),
                Arrays.asList("Meta", "Square"));
        assertTrue(predicate.test(new CompanyBuilder().withName("Square enix").build()));

        // Mixed-case keywords
        predicate = new CompanyNameContainsKeywordsPredicate(new ArrayList<>(),
                Arrays.asList("sQuARE", "eNiX"));
        assertTrue(predicate.test(new CompanyBuilder().withName("Square enix").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        CompanyNameContainsKeywordsPredicate predicate =
                new CompanyNameContainsKeywordsPredicate(Collections.emptyList(),
                        Collections.emptyList());
        assertFalse(predicate.test(new CompanyBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new CompanyNameContainsKeywordsPredicate(Arrays.asList("Carol"),
                Arrays.asList("Carol"));
        assertFalse(predicate.test(new CompanyBuilder().withName("Alice Bob").build()));

        // Keywords match phone, email and address, but does not match name
        predicate = new CompanyNameContainsKeywordsPredicate(Arrays.asList("12345", "alice@email"
                + ".com", "Main", "Street"), Arrays.asList("12345", "alice@email" + ".com", "Main", "Street"));
        assertFalse(predicate.test(new CompanyBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build()));
    }
}

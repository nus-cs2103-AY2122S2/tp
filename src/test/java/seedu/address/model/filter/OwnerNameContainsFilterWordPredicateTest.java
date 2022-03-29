package seedu.address.model.filter;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PetBuilder;


public class OwnerNameContainsFilterWordPredicateTest {
    private static final String firstNameKeyword = "Alice";
    private static final String secondNameKeyword = "Pe Ter";


    @Test
    public void test_equals() {
        OwnerNameContainsFilterWordPredicate firstPredicate =
                new OwnerNameContainsFilterWordPredicate(firstNameKeyword);
        OwnerNameContainsFilterWordPredicate secondPredicate =
                new OwnerNameContainsFilterWordPredicate(secondNameKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        OwnerNameContainsFilterWordPredicate firstPredicateCopy =
                new OwnerNameContainsFilterWordPredicate(firstNameKeyword);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different pet -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_ownerNameContainsKeyword_returnsTrue() {
        // Partial match: Alice Tan == Alice -> true
        OwnerNameContainsFilterWordPredicate predicate =
                new OwnerNameContainsFilterWordPredicate(firstNameKeyword);
        assertTrue(predicate.test(new PetBuilder().withOwnerName("Alice Tan").build()));

        // Multiple words full match per word i.e. Pe Ter Tan == Pe Ter -> true
        OwnerNameContainsFilterWordPredicate secondPredicate =
                new OwnerNameContainsFilterWordPredicate(secondNameKeyword);
        assertTrue(secondPredicate.test(new PetBuilder().withOwnerName("Pe Ter Tan").build()));

        // Multiple words partial match per word: Peter Tera Tan == Pe Ter -> true
        OwnerNameContainsFilterWordPredicate thirdPredicate =
                new OwnerNameContainsFilterWordPredicate(secondNameKeyword);
        assertTrue(thirdPredicate.test(new PetBuilder().withOwnerName("Peter Tera Tan").build()));

        // Full match with excess filter word: Alice Tan == Alice Tan Bee Bee -> true
        OwnerNameContainsFilterWordPredicate fourthPredicate =
                new OwnerNameContainsFilterWordPredicate(firstNameKeyword + " Tan Bee Bee");
        assertTrue(fourthPredicate.test(new PetBuilder().withOwnerName("Alice Tan").build()));

        // Mixed case: Alice Tan == ALiCe -> true
        OwnerNameContainsFilterWordPredicate fifthPredicate =
                new OwnerNameContainsFilterWordPredicate("ALiCe");
        assertTrue(fifthPredicate.test(new PetBuilder().withOwnerName("Alice Tan").build()));
    }

    @Test
    public void test_ownerNameDoesNotContainKeyword_returnsFalse() {
        // No match: Peter Tan == Alice -> false
        OwnerNameContainsFilterWordPredicate predicate =
                new OwnerNameContainsFilterWordPredicate(firstNameKeyword);
        assertFalse(predicate.test(new PetBuilder().withOwnerName("Peter Tan").build()));

        // Multiple words no match: Peter Tan == Alice Ong -> false
        OwnerNameContainsFilterWordPredicate secondPredicate =
                new OwnerNameContainsFilterWordPredicate(firstNameKeyword + "Ong");
        assertFalse(secondPredicate.test(new PetBuilder().withOwnerName("Peter Tan").build()));

        // Multiple words partial match: Alice Tan == Alice Ong -> false
        OwnerNameContainsFilterWordPredicate thirdPredicate =
                new OwnerNameContainsFilterWordPredicate(firstNameKeyword + "Ong");
        assertFalse(thirdPredicate.test(new PetBuilder().withOwnerName("Alice Tan").build()));
    }
}

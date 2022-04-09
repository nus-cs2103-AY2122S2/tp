package seedu.address.model.buyer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.property.House;
import seedu.address.model.property.HouseType;
import seedu.address.model.property.Location;
import seedu.address.model.property.PriceRange;
import seedu.address.model.property.PropertyToBuy;
import seedu.address.testutil.BuyerBuilder;

public class BuyerLocationContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        BuyerLocationContainsKeywordsPredicate firstPredicate = new BuyerLocationContainsKeywordsPredicate(
                firstPredicateKeywordList);
        BuyerLocationContainsKeywordsPredicate secondPredicate = new BuyerLocationContainsKeywordsPredicate(
                secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        BuyerLocationContainsKeywordsPredicate firstPredicateCopy = new BuyerLocationContainsKeywordsPredicate(
                firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different client -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_locationContainsKeywords_returnsTrue() {
        // One keyword
        BuyerLocationContainsKeywordsPredicate predicate = new BuyerLocationContainsKeywordsPredicate(
                Collections.singletonList("Kranji"));
        assertTrue(predicate.test(new BuyerBuilder().withProperty(new PropertyToBuy(
                new House(HouseType.BUNGALOW, new Location("Kranji")),
                new PriceRange(100, 500))).build()));

        // Multiple keywords
        predicate = new BuyerLocationContainsKeywordsPredicate(Arrays.asList("Kran", "Ji"));
        assertTrue(predicate.test(new BuyerBuilder().withProperty(new PropertyToBuy(
                new House(HouseType.BUNGALOW, new Location("Kranji")),
                new PriceRange(100, 500))).build()));

        // Only one matching keyword
        predicate = new BuyerLocationContainsKeywordsPredicate(Arrays.asList("Kran", "Pi"));
        assertTrue(predicate.test(new BuyerBuilder().withProperty(new PropertyToBuy(
                new House(HouseType.BUNGALOW, new Location("Kranji")),
                new PriceRange(100, 500))).build()));

        // Mixed-case keywords
        predicate = new BuyerLocationContainsKeywordsPredicate(Arrays.asList("kRaN", "jI"));
        assertTrue(predicate.test(new BuyerBuilder().withProperty(new PropertyToBuy(
                new House(HouseType.BUNGALOW, new Location("Kranji")),
                new PriceRange(100, 500))).build()));
    }

    @Test
    public void test_locationDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        BuyerLocationContainsKeywordsPredicate predicate = new BuyerLocationContainsKeywordsPredicate(
                Collections.emptyList());
        assertFalse(predicate.test(new BuyerBuilder().withProperty(new PropertyToBuy(
                new House(HouseType.BUNGALOW, new Location("Kranji")),
                new PriceRange(100, 500))).build()));

        // Non-matching keyword
        predicate = new BuyerLocationContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new BuyerBuilder().withProperty(new PropertyToBuy(
                new House(HouseType.BUNGALOW, new Location("Kranji")),
                new PriceRange(100, 500))).build()));

        // Keywords match phone, email and address, but does not match House Type
        predicate = new BuyerLocationContainsKeywordsPredicate(
                Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new BuyerBuilder().withName("Alice").withPhone("12345")
                .withProperty(new PropertyToBuy(new House(HouseType.BUNGALOW, new Location("Kranji")),
                        new PriceRange(100, 500))).build()));
    }
}

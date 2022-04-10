package seedu.address.model.seller;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.property.Address;
import seedu.address.model.property.House;
import seedu.address.model.property.HouseType;
import seedu.address.model.property.Location;
import seedu.address.model.property.PriceRange;
import seedu.address.model.property.PropertyToSell;
import seedu.address.testutil.SellerBuilder;

public class SellerLocationContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        SellerLocationContainsKeywordsPredicate firstPredicate = new SellerLocationContainsKeywordsPredicate(
                firstPredicateKeywordList);
        SellerLocationContainsKeywordsPredicate secondPredicate = new SellerLocationContainsKeywordsPredicate(
                secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        SellerLocationContainsKeywordsPredicate firstPredicateCopy = new SellerLocationContainsKeywordsPredicate(
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
        SellerLocationContainsKeywordsPredicate predicate = new SellerLocationContainsKeywordsPredicate(
                Collections.singletonList("Kranji"));
        assertTrue(predicate.test(new SellerBuilder().withProperty(new PropertyToSell(
                new House(HouseType.BUNGALOW, new Location("Kranji")),
                new PriceRange(100, 500),
                new Address("Avenue 10"))).build()));

        // Multiple keywords
        predicate = new SellerLocationContainsKeywordsPredicate(Arrays.asList("Kran", "Ji"));
        assertTrue(predicate.test(new SellerBuilder().withProperty(new PropertyToSell(
                new House(HouseType.BUNGALOW, new Location("Kranji")),
                new PriceRange(100, 500),
                new Address("Avenue 10"))).build()));

        // Only one matching keyword
        predicate = new SellerLocationContainsKeywordsPredicate(Arrays.asList("Kran", "Pi"));
        assertTrue(predicate.test(new SellerBuilder().withProperty(new PropertyToSell(
                new House(HouseType.BUNGALOW, new Location("Kranji")),
                new PriceRange(100, 500),
                new Address("Avenue 10"))).build()));

        // Mixed-case keywords
        predicate = new SellerLocationContainsKeywordsPredicate(Arrays.asList("kRaN", "jI"));
        assertTrue(predicate.test(new SellerBuilder().withProperty(new PropertyToSell(
                new House(HouseType.BUNGALOW, new Location("Kranji")),
                new PriceRange(100, 500),
                new Address("Avenue 10"))).build()));
    }

    @Test
    public void test_locationDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        SellerLocationContainsKeywordsPredicate predicate = new SellerLocationContainsKeywordsPredicate(
                Collections.emptyList());
        assertFalse(predicate.test(new SellerBuilder().withProperty(new PropertyToSell(
                new House(HouseType.BUNGALOW, new Location("Kranji")),
                new PriceRange(100, 500),
                new Address("Avenue 10"))).build()));

        // Non-matching keyword
        predicate = new SellerLocationContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new SellerBuilder().withProperty(new PropertyToSell(
                new House(HouseType.BUNGALOW, new Location("Kranji")),
                new PriceRange(100, 500),
                new Address("Avenue 10"))).build()));

        // Keywords match phone, email and address, but does not match House Type
        predicate = new SellerLocationContainsKeywordsPredicate(
                Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new SellerBuilder().withName("Alice").withPhone("12345")
                .withProperty(new PropertyToSell(new House(HouseType.BUNGALOW, new Location("Kranji")),
                        new PriceRange(100, 500),
                        new Address("Avenue 10"))).build()));
    }
}

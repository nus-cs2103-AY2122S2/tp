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

public class SellerHouseTypeContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        SellerHouseTypeContainsKeywordsPredicate firstPredicate = new SellerHouseTypeContainsKeywordsPredicate(
                firstPredicateKeywordList);
        SellerHouseTypeContainsKeywordsPredicate secondPredicate = new SellerHouseTypeContainsKeywordsPredicate(
                secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        SellerHouseTypeContainsKeywordsPredicate firstPredicateCopy = new SellerHouseTypeContainsKeywordsPredicate(
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
    public void test_houseTypeContainsKeywords_returnsTrue() {
        // One keyword
        SellerHouseTypeContainsKeywordsPredicate predicate = new SellerHouseTypeContainsKeywordsPredicate(
                Collections.singletonList("Bungalow"));
        assertTrue(predicate.test(new SellerBuilder().withProperty(new PropertyToSell(
                new House(HouseType.BUNGALOW, new Location("Kranji")),
                new PriceRange(100, 500),
                new Address("Avenue 20"))).build()));

        // Multiple keywords
        predicate = new SellerHouseTypeContainsKeywordsPredicate(Arrays.asList("Bunga", "Low"));
        assertTrue(predicate.test(new SellerBuilder().withProperty(new PropertyToSell(
                new House(HouseType.BUNGALOW, new Location("Kranji")),
                new PriceRange(100, 500),
                new Address("Avenue 20"))).build()));

        // Only one matching keyword
        predicate = new SellerHouseTypeContainsKeywordsPredicate(Arrays.asList("Bunga", "High"));
        assertTrue(predicate.test(new SellerBuilder().withProperty(new PropertyToSell(
                new House(HouseType.BUNGALOW, new Location("Kranji")),
                new PriceRange(100, 500),
                new Address("Avenue 20"))).build()));

        // Mixed-case keywords
        predicate = new SellerHouseTypeContainsKeywordsPredicate(Arrays.asList("BuNg", "aLoW"));
        assertTrue(predicate.test(new SellerBuilder().withProperty(new PropertyToSell(
                new House(HouseType.BUNGALOW, new Location("Kranji")),
                new PriceRange(100, 500),
                new Address("Avenue 20"))).build()));
    }

    @Test
    public void test_houseTypeDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        SellerHouseTypeContainsKeywordsPredicate predicate = new SellerHouseTypeContainsKeywordsPredicate(
                Collections.emptyList());
        assertFalse(predicate.test(new SellerBuilder().withProperty(new PropertyToSell(
                new House(HouseType.BUNGALOW, new Location("Kranji")),
                new PriceRange(100, 500),
                new Address("Avenue 20"))).build()));

        // Non-matching keyword
        predicate = new SellerHouseTypeContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new SellerBuilder().withProperty(new PropertyToSell(
                new House(HouseType.BUNGALOW, new Location("Kranji")),
                new PriceRange(100, 500),
                new Address("Avenue 20"))).build()));

        // Keywords match phone, email and address, but does not match House Type
        predicate = new SellerHouseTypeContainsKeywordsPredicate(
                Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new SellerBuilder().withName("Alice").withPhone("12345")
                .withProperty(new PropertyToSell(new House(HouseType.BUNGALOW, new Location("Kranji")),
                        new PriceRange(100, 500),
                        new Address("Avenue 20"))).build()));
    }
}

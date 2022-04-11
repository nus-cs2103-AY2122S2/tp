package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class FieldContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        FieldContainsKeywordsPredicate firstPredicate = new FieldContainsKeywordsPredicate(firstPredicateKeywordList,
                "all");
        FieldContainsKeywordsPredicate secondPredicate = new FieldContainsKeywordsPredicate(secondPredicateKeywordList,
                "all");

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        FieldContainsKeywordsPredicate firstPredicateCopy = new FieldContainsKeywordsPredicate(
                firstPredicateKeywordList, "all");
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        FieldContainsKeywordsPredicate predicate = new FieldContainsKeywordsPredicate(Collections.singletonList("Alex"),
                "n");
        assertTrue(predicate.test(new PersonBuilder().withName("Alex Bob").build()));

        // Multiple keywords
        predicate = new FieldContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"), "n");
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new FieldContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"), "n");
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new FieldContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"), "n");
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        FieldContainsKeywordsPredicate predicate = new FieldContainsKeywordsPredicate(Collections.emptyList(), "n");
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new FieldContainsKeywordsPredicate(Arrays.asList("Carol"), "n");
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_companyContainsKeywords_returnsTrue() {
        // One keyword
        FieldContainsKeywordsPredicate predicate = new FieldContainsKeywordsPredicate(Collections
                .singletonList("Tesla"), "c");
        assertTrue(predicate.test(new PersonBuilder().withCompany("Tesla").build()));

        // Multiple keywords
        predicate = new FieldContainsKeywordsPredicate(Arrays.asList("Tesla", "Dell", "Alpha"), "c");
        assertTrue(predicate.test(new PersonBuilder().withCompany("Dell").build()));

        // Only one matching keyword
        predicate = new FieldContainsKeywordsPredicate(Arrays.asList("Tesla", "Alpha"), "c");
        assertTrue(predicate.test(new PersonBuilder().withCompany("Alpha Tech").build()));

        // Mixed-case keywords
        predicate = new FieldContainsKeywordsPredicate(Arrays.asList("dElL", "AlPhA"), "c");
        assertTrue(predicate.test(new PersonBuilder().withCompany("Alpha").build()));
    }

    @Test
    public void test_jobContainsKeywords_returnsTrue() {
        // One keyword
        FieldContainsKeywordsPredicate predicate = new FieldContainsKeywordsPredicate(Collections
                .singletonList("Engineer"), "j");
        assertTrue(predicate.test(new PersonBuilder().withJobTitle("Engineer").build()));

        // Multiple keywords
        predicate = new FieldContainsKeywordsPredicate(Arrays.asList("Engineer", "Doctor", "Manager"), "j");
        assertTrue(predicate.test(new PersonBuilder().withJobTitle("Manager").build()));

        // Only one matching keyword
        predicate = new FieldContainsKeywordsPredicate(Arrays.asList("Manager", "CEO"), "j");
        assertTrue(predicate.test(new PersonBuilder().withJobTitle("Branch Manager").build()));

        // Mixed-case keywords
        predicate = new FieldContainsKeywordsPredicate(Arrays.asList("mAnAgeR", "cEo"), "j");
        assertTrue(predicate.test(new PersonBuilder().withJobTitle("ceo").build()));
    }


    @Test
    public void test_tagContainsKeywords_returnsTrue() {
        // One keyword
        FieldContainsKeywordsPredicate predicate = new FieldContainsKeywordsPredicate(Collections.singletonList("peer"),
                "t");
        assertTrue(predicate.test(new PersonBuilder().withTags("peer").build()));

        // Multiple keywords
        predicate = new FieldContainsKeywordsPredicate(Arrays.asList("peer", "friend", "supplier"), "t");
        assertTrue(predicate.test(new PersonBuilder().withTags("friend").build()));

        // Only one matching keyword
        predicate = new FieldContainsKeywordsPredicate(Arrays.asList("peer", "friend", "supplier"), "t");
        assertTrue(predicate.test(new PersonBuilder().withTags("best friend").build()));

        // Mixed-case keywords
        predicate = new FieldContainsKeywordsPredicate(Arrays.asList("pEeR", "FrIenD", "suPPliEr"), "t");
        assertTrue(predicate.test(new PersonBuilder().withTags("friend").build()));
    }

    @Test
    public void test_phoneContainsKeywords_returnsTrue() {
        // One keyword
        FieldContainsKeywordsPredicate predicate = new FieldContainsKeywordsPredicate(Collections.singletonList("1234"),
                "p");
        assertTrue(predicate.test(new PersonBuilder().withNumbers("1234").build()));

        // Multiple keywords
        predicate = new FieldContainsKeywordsPredicate(Arrays.asList("1234", "5678", "9012"), "p");
        assertTrue(predicate.test(new PersonBuilder().withNumbers("9012").build()));
    }

    @Test
    public void test_emailContainsKeywords_returnsTrue() {
        // One keyword
        FieldContainsKeywordsPredicate predicate = new FieldContainsKeywordsPredicate(Collections
                .singletonList("test@gmail.com"), "e");
        assertTrue(predicate.test(new PersonBuilder().withEmails("test@gmail.com").build()));

        // Multiple keywords
        predicate = new FieldContainsKeywordsPredicate(Arrays.asList("test1@gmail.com", "test2@gmail.com",
                "test3@aol.com"), "e");
        assertTrue(predicate.test(new PersonBuilder().withEmails("test3@aol.com").build()));

        // Mixed-case keywords
        predicate = new FieldContainsKeywordsPredicate(Arrays.asList("TeSt1@gmaIl.com", "test2@Gmail.COM",
                "TESt3@AOl.com"), "e");
        assertTrue(predicate.test(new PersonBuilder().withEmails("test2@gmail.com").build()));
    }

    @Test
    public void test_addressContainsKeywords_returnsTrue() {
        // One keyword
        FieldContainsKeywordsPredicate predicate = new FieldContainsKeywordsPredicate(Collections
                .singletonList("Alexandra"), "a");
        assertTrue(predicate.test(new PersonBuilder().withAddresses("#123 Alexandra Rd").build()));

        // Multiple keywords
        predicate = new FieldContainsKeywordsPredicate(Arrays.asList("Alexandra", "Clementi", "Dover"), "a");
        assertTrue(predicate.test(new PersonBuilder().withAddresses("#1278 Dover Crescent").build()));

        // Mixed-case keywords
        predicate = new FieldContainsKeywordsPredicate(Arrays.asList("ALEXANDRA", "cleMenTi", "dOvEr"), "a");
        assertTrue(predicate.test(new PersonBuilder().withAddresses("#1278 Dover Crescent").build()));
    }
}

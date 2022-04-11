package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INSURANCE_PACKAGE_AMY;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class InsurancePackageContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        InsurancePackageContainsKeywordsPredicate firstPredicate =
                new InsurancePackageContainsKeywordsPredicate(firstPredicateKeywordList);
        InsurancePackageContainsKeywordsPredicate secondPredicate =
                new InsurancePackageContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        InsurancePackageContainsKeywordsPredicate firstPredicateCopy =
                new InsurancePackageContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_insurancePackageContainsKeywords_returnsTrue() {
        // One keyword
        InsurancePackageContainsKeywordsPredicate predicate =
                new InsurancePackageContainsKeywordsPredicate(Collections.singletonList("Golden"));
        assertTrue(predicate.test(new PersonBuilder().withInsurancePackage(VALID_INSURANCE_PACKAGE_AMY).build()));

        // Multiple keywords
        predicate = new InsurancePackageContainsKeywordsPredicate(Arrays.asList("Golden", "Plus"));
        assertTrue(predicate.test(new PersonBuilder().withInsurancePackage(VALID_INSURANCE_PACKAGE_AMY).build()));

        // Only one matching keyword
        predicate = new InsurancePackageContainsKeywordsPredicate(Arrays.asList("Golden", "Premium"));
        assertTrue(predicate.test(new PersonBuilder().withInsurancePackage(VALID_INSURANCE_PACKAGE_AMY).build()));

        // Mixed-case keywords
        predicate = new InsurancePackageContainsKeywordsPredicate(Arrays.asList("gOlDeN", "pLuS"));
        assertTrue(predicate.test(new PersonBuilder().withName(VALID_INSURANCE_PACKAGE_AMY).build()));
    }

    @Test
    public void test_insurancePackageDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        InsurancePackageContainsKeywordsPredicate predicate =
                new InsurancePackageContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withInsurancePackage(VALID_INSURANCE_PACKAGE_AMY).build()));

        // Non-matching keyword
        predicate = new InsurancePackageContainsKeywordsPredicate(Arrays.asList("Undecided"));
        assertFalse(predicate.test(new PersonBuilder().withInsurancePackage(VALID_INSURANCE_PACKAGE_AMY).build()));

        // Multiple Non-matching keyword
        predicate = new InsurancePackageContainsKeywordsPredicate(Arrays.asList("Undecided", "Dragon"));
        assertFalse(predicate.test(new PersonBuilder().withInsurancePackage(VALID_INSURANCE_PACKAGE_AMY).build()));

        // Keywords match phone, email and address, but does not match insurance package
        predicate = new InsurancePackageContainsKeywordsPredicate(Arrays.asList("Alice", "12345", "alice@email.com",
                "Main", "Street", "Undecided"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street")
                .withInsurancePackage(VALID_INSURANCE_PACKAGE_AMY).build()));
    }
}

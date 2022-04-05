package seedu.address.model.prescription;

import org.junit.jupiter.api.Test;
import seedu.address.testutil.PrescriptionBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PrescriptionConatainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        PrescriptionContainsKeywordsPredicate firstPredicate =
                new PrescriptionContainsKeywordsPredicate(firstPredicateKeywordList);
        PrescriptionContainsKeywordsPredicate secondPredicate =
                new PrescriptionContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PrescriptionContainsKeywordsPredicate firstPredicateCopy =
                new PrescriptionContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        assertFalse(firstPredicate.equals(secondPredicate));
    }

//    @Test
//    public void test_prescriptionContainsKeywords_returnsTrue() {
//        // One keyword
//        PrescriptionContainsKeywordsPredicate predicate =
//                new PrescriptionContainsKeywordsPredicate(Collections.singletonList("Azithromycin"));
//        assertTrue(predicate.test(new PrescriptionBuilder().withDrugName("Azithromycin xyz").build()));
//
//        // Multiple keywords
//        predicate = new PrescriptionContainsKeywordsPredicate(Arrays.asList("23 Feb 2019", "Bob"));
//        assertTrue(predicate.test(new PrescriptionBuilder().withDate("23 Feb 2019").build()));
//
//        // Only one matching keyword
//        predicate = new PrescriptionContainsKeywordsPredicate(Arrays.asList("Azithromycin", "Ativan"));
//        assertTrue(predicate.test(new PrescriptionBuilder().withDrugName("Amlodipine Ativan").build()));
//
//        // Mixed-case keywords
//        predicate = new PrescriptionContainsKeywordsPredicate(Arrays.asList("amlodipine", "ativan"));
//        assertTrue(predicate.test(new PrescriptionBuilder().withDrugName("Amlodipine Ativan").build()));
//    }
}

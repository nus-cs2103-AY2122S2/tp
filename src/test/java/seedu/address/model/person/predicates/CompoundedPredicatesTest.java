package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class CompoundedPredicatesTest {
    @Test
    public void equals() {
        CompoundedPredicates preds1 = new CompoundedPredicates();
        CompoundedPredicates preds2 = new CompoundedPredicates();
        CompoundedPredicates preds3 = new CompoundedPredicates();

        EmailContainsKeywordsPredicate emailPred = new EmailContainsKeywordsPredicate(
                Arrays.asList("example.com", "google.com"));
        preds1.addPredicate(emailPred);
        preds2.addPredicate(emailPred);
        preds3.addPredicate(emailPred);

        ModContainsKeywordsPredicate modPred = new ModContainsKeywordsPredicate(
                Arrays.asList("CS2100", "MA1101R"));
        preds1.addPredicate(modPred);
        preds2.addPredicate(modPred);

        // same object -> returns true
        assertTrue(preds1.equals(preds1));

        // same contents -> returns true
        assertTrue(preds1.equals(preds2));

        // different types -> returns false
        assertFalse(preds1.equals(1));

        // different contents -> returns false
        assertFalse(preds1.equals(preds3));
    }

    @Test
    public void isEmpty_somethingAdded_returnsFalse() {
        CompoundedPredicates preds = new CompoundedPredicates();
        preds.addPredicate(new NameContainsKeywordsPredicate(Arrays.asList("Alice")));
        assertFalse(preds.isEmpty());
    }

    @Test
    public void isEmpty_nothingAdded_returnsTrue() {
        CompoundedPredicates preds = new CompoundedPredicates();
        assertTrue(preds.isEmpty());
    }

    @Test
    public void test_matchingKeywords_returnsTrue() {
        CompoundedPredicates preds = new CompoundedPredicates();
        EmailContainsKeywordsPredicate emailPreds = new EmailContainsKeywordsPredicate(Arrays.asList("google.com"));
        ModContainsKeywordsPredicate modPreds = new ModContainsKeywordsPredicate(Arrays.asList("CS2100"));

        Person testPerson = new PersonBuilder().withEmail("Alice@google.com.sg").withMod("CS2100").withName("Alice "
                + "Henderson").withStudentNumber("A0123456L").withTags("friend").build();

        // no predicates
        assertTrue(preds.test(testPerson));

        // one predicate
        preds.addPredicate(emailPreds);
        assertTrue(preds.test(testPerson));

        // multiple predicates
        preds.addPredicate(modPreds);
        assertTrue(preds.test(testPerson));
    }

    @Test
    public void test_nonMatchingKeywords_returnsFalse() {
        CompoundedPredicates preds = new CompoundedPredicates();
        EmailContainsKeywordsPredicate emailPreds = new EmailContainsKeywordsPredicate(Arrays.asList("google.com"));
        ModContainsKeywordsPredicate modPreds = new ModContainsKeywordsPredicate(Arrays.asList("CS2100"));

        Person testPerson = new PersonBuilder().withEmail("Alice@example.com").withMod("CS2100").withName("Alice "
                + "Henderson").withStudentNumber("A0246813G").withTags("friend").build();

        // 1 predicate, non-matching predicate
        preds.addPredicate(emailPreds);
        assertFalse(preds.test(testPerson));

        // multiple predicates, 1 non-matching
        preds.addPredicate(modPreds);
        assertFalse(preds.test(testPerson));

    }
}

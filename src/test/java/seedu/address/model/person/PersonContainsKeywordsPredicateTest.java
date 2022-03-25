package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.util.PersonContainsKeywordsPredicate;
import seedu.address.testutil.PersonUtil;

public class PersonContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        PersonContainsKeywordsPredicate firstPredicate =
                new PersonContainsKeywordsPredicate(firstPredicateKeywordList);
        PersonContainsKeywordsPredicate secondPredicate =
                new PersonContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PersonContainsKeywordsPredicate firstPredicateCopy =
                new PersonContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_personContainsKeywords_returnsTrue() {
        // One keyword
        PersonContainsKeywordsPredicate predicate =
                new PersonContainsKeywordsPredicate(Collections.singletonList("Amy"));
        assertTrue(predicate.test(PersonUtil.AMY));

        // Multiple keywords
        predicate = new PersonContainsKeywordsPredicate(Arrays.asList("Amy", "Bee"));
        assertTrue(predicate.test(PersonUtil.AMY));

        // Only one matching keyword
        predicate = new PersonContainsKeywordsPredicate(Arrays.asList("Amy", "Meier"));
        assertTrue(predicate.test(PersonUtil.AMY));

        // Mixed-case keywords
        predicate = new PersonContainsKeywordsPredicate(Arrays.asList("AMy", "bEe"));
        assertTrue(predicate.test(PersonUtil.AMY));

        // Keywords match phone, email and address, but does not match name.
        predicate = new PersonContainsKeywordsPredicate(
            Arrays.asList(PersonUtil.VALID_PHONE_AMY, PersonUtil.VALID_EMAIL_AMY, PersonUtil.VALID_ADDRESS_AMY));
        assertTrue(predicate.test(PersonUtil.AMY));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        PersonContainsKeywordsPredicate predicate = new PersonContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(PersonUtil.AMY));

        // Non-matching keyword
        predicate = new PersonContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(PersonUtil.AMY));
    }
}

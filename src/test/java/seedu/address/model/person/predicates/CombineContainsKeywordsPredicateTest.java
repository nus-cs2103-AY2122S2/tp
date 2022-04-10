package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MULTIPLE_NAME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PredicatesListBuilder;

public class CombineContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstNamePredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        PhoneContainsKeywordsPredicate firstPhonePredicate =
                new PhoneContainsKeywordsPredicate(Collections.singletonList("88888888"));
        List<FieldContainsKeywordsPredicate> firstPredicatesList = new PredicatesListBuilder()
                .addNamePredicate(firstNamePredicate)
                .addPhonePredicate(firstPhonePredicate)
                .build();
        CombineContainsKeywordsPredicate firstCombinePredicate = new CombineContainsKeywordsPredicate(
                firstPredicatesList);

        NameContainsKeywordsPredicate secondNamePredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));
        PhoneContainsKeywordsPredicate secondPhonePredicate =
                new PhoneContainsKeywordsPredicate(Collections.singletonList("77777777"));
        List<FieldContainsKeywordsPredicate> secondPredicatesList = new PredicatesListBuilder()
                .addNamePredicate(secondNamePredicate)
                .addPhonePredicate(secondPhonePredicate)
                .build();
        CombineContainsKeywordsPredicate secondCombinePredicate = new CombineContainsKeywordsPredicate(
                secondPredicatesList);

        // same object -> returns true
        assertTrue(firstCombinePredicate.equals(firstCombinePredicate));

        // same values -> returns true
        CombineContainsKeywordsPredicate firstCombinePredicateCopy =
                new CombineContainsKeywordsPredicate(firstPredicatesList);
        assertTrue(firstCombinePredicate.equals(firstCombinePredicateCopy));

        // different types -> returns false
        assertFalse(firstCombinePredicate.equals(1));

        // null -> returns false
        assertFalse(firstCombinePredicate.equals(null));

        // different person -> returns false
        assertFalse(firstCombinePredicate.equals(secondCombinePredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword, One Field
        CombineContainsKeywordsPredicate predicate = new CombineContainsKeywordsPredicate(
                new PredicatesListBuilder()
                    .addNamePredicate(new NameContainsKeywordsPredicate(Collections.singletonList("Alice")))
                    .build());
        assertTrue(predicate.test(new PersonBuilder().withName(VALID_MULTIPLE_NAME).build()));

        // Multiple keywords, One Field
        predicate = new CombineContainsKeywordsPredicate(
                        new PredicatesListBuilder()
                            .addNamePredicate(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")))
                            .build());
        assertTrue(predicate.test(new PersonBuilder().withName(VALID_MULTIPLE_NAME).build()));

        // Multiple keywords, Multiple Fields have all match
        predicate = new CombineContainsKeywordsPredicate(
                new PredicatesListBuilder()
                        .addNamePredicate(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")))
                        .addAddressPredicate(new AddressContainsKeywordsPredicate(Arrays.asList("Amy", "Street")))
                        .build());
        assertTrue(predicate.test(new PersonBuilder().withName(VALID_MULTIPLE_NAME)
                                                    .withAddress(VALID_ADDRESS_AMY)
                                                    .build()));

        // Only one matching keyword
        predicate = new CombineContainsKeywordsPredicate(
                new PredicatesListBuilder()
                        .addNamePredicate(new NameContainsKeywordsPredicate(Arrays.asList("Bob", "Carol")))
                        .build());
        assertTrue(predicate.test(new PersonBuilder().withName(VALID_MULTIPLE_NAME).build()));

        // Only one matching keyword, Multiple Fields each have at least one match
        predicate = new CombineContainsKeywordsPredicate(
                new PredicatesListBuilder()
                        .addNamePredicate(new NameContainsKeywordsPredicate(Arrays.asList("Bob", "Carol")))
                        .addAddressPredicate(new AddressContainsKeywordsPredicate(Arrays.asList("Amy", "East")))
                        .build());
        assertTrue(predicate.test(new PersonBuilder().withName(VALID_MULTIPLE_NAME)
                                                        .withAddress(VALID_ADDRESS_AMY)
                                                        .build()));

        // Mixed-case keywords
        predicate = new CombineContainsKeywordsPredicate(
                new PredicatesListBuilder()
                        .addNamePredicate(new NameContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB")))
                        .build());
        assertTrue(predicate.test(new PersonBuilder().withName(VALID_MULTIPLE_NAME).build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        CombineContainsKeywordsPredicate predicate = new CombineContainsKeywordsPredicate(
                new PredicatesListBuilder()
                        .addNamePredicate(new NameContainsKeywordsPredicate(Collections.emptyList()))
                        .build());
        assertFalse(predicate.test(new PersonBuilder().withName(VALID_NAME_AMY).build()));

        // Only one matching keyword, Multiple Fields, some have no match
        predicate = new CombineContainsKeywordsPredicate(
                new PredicatesListBuilder()
                        .addNamePredicate(new NameContainsKeywordsPredicate(Arrays.asList("Bob", "Carol")))
                        .addAddressPredicate(new AddressContainsKeywordsPredicate(Arrays.asList("Bedok", "East")))
                        .build());
        assertFalse(predicate.test(new PersonBuilder().withName(VALID_MULTIPLE_NAME)
                .withAddress(VALID_ADDRESS_AMY)
                .build()));

        // Non-matching single keyword
        predicate = new CombineContainsKeywordsPredicate(
                new PredicatesListBuilder()
                        .addNamePredicate(new NameContainsKeywordsPredicate(Arrays.asList("Carol")))
                        .build());
        assertFalse(predicate.test(new PersonBuilder().withName(VALID_MULTIPLE_NAME).build()));

        // Non-matching single keyword for multiple fields
        predicate = new CombineContainsKeywordsPredicate(
                new PredicatesListBuilder()
                        .addNamePredicate(new NameContainsKeywordsPredicate(Arrays.asList("Carol")))
                        .addAddressPredicate(new AddressContainsKeywordsPredicate(Arrays.asList("Bedok")))
                        .build());
        assertFalse(predicate.test(new PersonBuilder().withName(VALID_MULTIPLE_NAME)
                                                        .withAddress(VALID_ADDRESS_AMY)
                                                        .build()));

        // Matching single keyword for one field, non-matching for other fields
        predicate = new CombineContainsKeywordsPredicate(
                new PredicatesListBuilder()
                        .addNamePredicate(new NameContainsKeywordsPredicate(Arrays.asList("Alice")))
                        .addAddressPredicate(new AddressContainsKeywordsPredicate(Arrays.asList("Bedok")))
                        .build());
        assertFalse(predicate.test(new PersonBuilder().withName(VALID_MULTIPLE_NAME)
                .withAddress(VALID_ADDRESS_AMY)
                .build()));

        // Multiple keywords match for one field, Multiple Fields, some fields have no match
        predicate = new CombineContainsKeywordsPredicate(
                new PredicatesListBuilder()
                        .addNamePredicate(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")))
                        .addAddressPredicate(new AddressContainsKeywordsPredicate(Arrays.asList("Bedok", "East")))
                        .build());
        assertFalse(predicate.test(new PersonBuilder().withName(VALID_MULTIPLE_NAME)
                .withAddress(VALID_ADDRESS_AMY)
                .build()));

        // Multiple Non-matching keywords
        predicate = new CombineContainsKeywordsPredicate(
                new PredicatesListBuilder()
                        .addNamePredicate(new NameContainsKeywordsPredicate(Arrays.asList("Jane", "Carol")))
                        .build());
        assertFalse(predicate.test(new PersonBuilder().withName(VALID_MULTIPLE_NAME).build()));

        // Multiple Non-matching keywords for multiple fields
        predicate = new CombineContainsKeywordsPredicate(
                new PredicatesListBuilder()
                        .addNamePredicate(new NameContainsKeywordsPredicate(Arrays.asList("Jane", "Carol")))
                        .addAddressPredicate(new AddressContainsKeywordsPredicate(Arrays.asList("Bedok", "East")))
                        .build());
        assertFalse(predicate.test(new PersonBuilder().withName(VALID_MULTIPLE_NAME)
                                                        .withAddress(VALID_ADDRESS_AMY)
                                                        .build()));

        // Keywords match phone, email and address, but does not match name
        predicate = new CombineContainsKeywordsPredicate(
                new PredicatesListBuilder()
                        .addNamePredicate(new NameContainsKeywordsPredicate(Arrays.asList("John")))
                        .addEmailPredicate(new EmailContainsKeywordsPredicate(Arrays.asList("alice@email.com")))
                        .addAddressPredicate(new AddressContainsKeywordsPredicate(Arrays.asList("Main", "Street")))
                        .addPhonePredicate(new PhoneContainsKeywordsPredicate(Arrays.asList("12345")))
                        .build());
        assertFalse(predicate.test(new PersonBuilder().withName(VALID_NAME_AMY).withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build()));
    }
}

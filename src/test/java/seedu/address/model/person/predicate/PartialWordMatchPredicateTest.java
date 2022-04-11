package seedu.address.model.person.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

class PartialWordMatchPredicateTest {
    @Test
    void test_partialWordMatch_returnsTrue() {
        PartialWordMatchPredicate namePredicate = new PartialWordMatchPredicate(PREFIX_NAME, List.of("ai"));
        PartialWordMatchPredicate phonePredicate = new PartialWordMatchPredicate(PREFIX_PHONE, List.of("9543"));
        PartialWordMatchPredicate emailPredicate = new PartialWordMatchPredicate(PREFIX_EMAIL, List.of("gmail"));
        PartialWordMatchPredicate phonePredicateWithSpace = new PartialWordMatchPredicate(PREFIX_PHONE, List.of("+65 "
                + "9040"));

        // Partial word match -> returns true
        assertTrue(namePredicate.test(new PersonBuilder().withName("Aidan").build()));
        assertTrue(namePredicate.test(new PersonBuilder().withName("Abigail").build()));
        assertTrue(namePredicate.test(new PersonBuilder().withName("ai").build()));
        assertTrue(phonePredicate.test(new PersonBuilder().withPhone("12349543").build()));
        assertTrue(phonePredicate.test(new PersonBuilder().withPhone("9543211").build()));
        assertTrue(phonePredicate.test(new PersonBuilder().withPhone("11954311").build()));
        assertTrue(phonePredicateWithSpace.test(new PersonBuilder().withPhone("+6590400204").build()));
        assertTrue(emailPredicate.test(new PersonBuilder().withEmail("redherringmail@yahoo.com").build()));
    }

    @Test
    void test_partialWordMatchBetweenSentences_returnsTrue() {
        PartialWordMatchPredicate namePredicate = new PartialWordMatchPredicate(PREFIX_NAME, List.of("Alex", "Yeoh"));
        assertTrue(namePredicate.test(new PersonBuilder().withName("alex bin tan").build()));
        assertTrue(namePredicate.test(new PersonBuilder().withName("bin tan Yeoh").build()));
    }

    @Test
    void test_invalidCases_returnsFalse() {
        PartialWordMatchPredicate namePredicate = new PartialWordMatchPredicate(PREFIX_NAME, List.of("Alex", "Yeoh"));
        PartialWordMatchPredicate phonePredicateWithSpace = new PartialWordMatchPredicate(PREFIX_PHONE, List.of("+65 "
                + "9999"));

        assertFalse(namePredicate.test(new PersonBuilder().withName("Aleen Tan").build()));
        assertFalse(namePredicate.test(new PersonBuilder().withName("Leo").build()));
        // Test input without space.
        assertFalse(phonePredicateWithSpace.test(new PersonBuilder().withPhone("+65 91111234").build()));
        // Test input with space.
        assertFalse(phonePredicateWithSpace.test(new PersonBuilder().withPhone("+6591111234").build()));
    }
}

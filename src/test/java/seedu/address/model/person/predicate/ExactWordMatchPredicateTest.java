package seedu.address.model.person.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

class ExactWordMatchPredicateTest {

    @Test
    void test_exactMatchBetweenWords_returnsTrue() {
        ExactWordMatchPredicate tagPredicate = new ExactWordMatchPredicate(PREFIX_TAG, List.of("colleagues"));
        ExactWordMatchPredicate phonePredicateWithSpace = new ExactWordMatchPredicate(PREFIX_PHONE, List.of("+65 "
                + "90400204"));

        // Space is ignored during the check.
        assertTrue(phonePredicateWithSpace.test(new PersonBuilder().withPhone("+6590400204").build()));

        // case-insensitive search -> returns true
        assertTrue(tagPredicate.test(new PersonBuilder().withTags("Colleagues").build()));

        // same word -> returns true
        assertTrue(tagPredicate.test(new PersonBuilder().withTags("colleagues").build()));
    }

    @Test
    void test_exactWordMatchBetweenTwoSentences_returnsTrue() {
        ExactWordMatchPredicate addressPredicate = new ExactWordMatchPredicate(PREFIX_ADDRESS, List.of("QueensTown",
                "street", "10"));
        // case-insensitive search -> returns true
        assertTrue(addressPredicate.test(new PersonBuilder().withAddress("Brisbane Street").build()));

        // same word -> returns true
        assertTrue(addressPredicate.test(new PersonBuilder().withAddress("QueensTown street 10").build()));
    }

    @Test
    void test_partialWordMatch_returnsFalse() {
        ExactWordMatchPredicate tagPredicate = new ExactWordMatchPredicate(PREFIX_TAG, List.of("fam"));
        ExactWordMatchPredicate addressPredicate = new ExactWordMatchPredicate(PREFIX_ADDRESS, List.of("Covington",
                "LA"));

        // Partial word match -> returns false
        assertFalse(tagPredicate.test(new PersonBuilder().withTags("family").build()));
        assertFalse(tagPredicate.test(new PersonBuilder().withTags("famous").build()));
        assertFalse(addressPredicate.test(new PersonBuilder().withAddress("CovidTon Los Angelos").build()));
        assertFalse(addressPredicate.test(new PersonBuilder().withAddress("Laos").build()));
    }

    @Test
    void test_emptyWhiteSpaceAttribute_returnsFalse() {
        ExactWordMatchPredicate addressPredicate = new ExactWordMatchPredicate(PREFIX_ADDRESS, List.of("Some", "test",
                "value"));
        assertFalse(addressPredicate.test(new PersonBuilder().withAddress("address").build()));
    }
}

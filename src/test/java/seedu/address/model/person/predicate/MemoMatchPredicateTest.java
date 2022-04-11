package seedu.address.model.person.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

class MemoMatchPredicateTest {

    @Test
    void test_validMemoArg_returnsTrue() {
        MemoMatchPredicate word = new MemoMatchPredicate(List.of("am"));
        MemoMatchPredicate wordWithSpace = new MemoMatchPredicate(List.of("am "));
        MemoMatchPredicate partialWord = new MemoMatchPredicate(List.of("town"));
        MemoMatchPredicate partialWordWithSpace = new MemoMatchPredicate(List.of("town "));
        MemoMatchPredicate caseInsensitive = new MemoMatchPredicate(List.of("Am"));
        MemoMatchPredicate sentence = new MemoMatchPredicate(List.of("I am at queenstown"));
        MemoMatchPredicate emptyArg = new MemoMatchPredicate(List.of(""));

        // Word match -> returns true
        String testString = "I am at queenstown road.";
        assertTrue(word.test(new PersonBuilder().withMemo(testString).build()));
        assertTrue(wordWithSpace.test(new PersonBuilder().withMemo(testString).build()));
        assertTrue(partialWord.test(new PersonBuilder().withMemo(testString).build()));
        assertTrue(partialWordWithSpace.test(new PersonBuilder().withMemo(testString).build()));
        assertTrue(caseInsensitive.test(new PersonBuilder().withMemo(testString).build()));
        assertTrue(sentence.test(new PersonBuilder().withMemo(testString).build()));

        // When user key in find m/ person that has an empty memo is searched.
        assertTrue(emptyArg.test(new PersonBuilder().withMemo("").build()));
    }

    @Test
    void test_validMemoArg_returnsFalse() {
        MemoMatchPredicate word = new MemoMatchPredicate(List.of("sleepy"));
        MemoMatchPredicate wordWithSpace = new MemoMatchPredicate(List.of("streets "));
        MemoMatchPredicate sentence = new MemoMatchPredicate(List.of("I am sleeping on the streets."));
        MemoMatchPredicate emptyArg = new MemoMatchPredicate(List.of(""));

        // No partial word match -> returns false.
        String testString = "I'm sleeping on the streets.";
        assertFalse(word.test(new PersonBuilder().withMemo(testString).build()));
        assertFalse(wordWithSpace.test(new PersonBuilder().withMemo(testString).build()));
        assertFalse(sentence.test(new PersonBuilder().withMemo(testString).build()));

        // Person have a memo does not match with find m/ -> returns false.
        assertFalse(emptyArg.test(new PersonBuilder().withMemo("test memo").build()));
    }
}

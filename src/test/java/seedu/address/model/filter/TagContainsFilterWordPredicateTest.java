package seedu.address.model.filter;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PetBuilder;

public class TagContainsFilterWordPredicateTest {
    private static final String firstTagKeyword = "Golden Retriever";
    private static final String secondTagKeyword = "Beagle";

    @Test
    public void test_equals() {
        TagContainsFilterWordPredicate firstPredicate =
                new TagContainsFilterWordPredicate(firstTagKeyword);
        TagContainsFilterWordPredicate secondPredicate =
                new TagContainsFilterWordPredicate(secondTagKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TagContainsFilterWordPredicate firstPredicateCopy =
                new TagContainsFilterWordPredicate(firstTagKeyword);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different pet -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_tagContainsKeyword_returnsTrue() {
        // Full match: Golden Retriever == Golden Retriever -> true
        TagContainsFilterWordPredicate firstPredicate =
                new TagContainsFilterWordPredicate(firstTagKeyword);
        assertTrue(firstPredicate.test(new PetBuilder().withTags(firstTagKeyword).build()));

        // Partial match: Golden Retriever == Golden -> true
        TagContainsFilterWordPredicate secondPredicate =
                new TagContainsFilterWordPredicate("Golden");
        assertTrue(secondPredicate.test(new PetBuilder().withTags(firstTagKeyword).build()));

        // Partial match: Golden Retriever == Retriever -> true
        TagContainsFilterWordPredicate thirdPredicate =
                new TagContainsFilterWordPredicate("Retriever");
        assertTrue(thirdPredicate.test(new PetBuilder().withTags(firstTagKeyword).build()));

        // Partial match: Golden Retriever == Gold Retri -> true
        TagContainsFilterWordPredicate fourthPredicate =
                new TagContainsFilterWordPredicate("Gold Retri");
        assertTrue(fourthPredicate.test(new PetBuilder().withTags(firstTagKeyword).build()));

        // Partial match: Golden Retriever == Gold -> true
        TagContainsFilterWordPredicate fifthPredicate =
                new TagContainsFilterWordPredicate("Gold");
        assertTrue(fifthPredicate.test(new PetBuilder().withTags(firstTagKeyword).build()));

        // Mixed case: Golden Retriever == goLdEn ReTrieVer -> true
        TagContainsFilterWordPredicate sixthPredicate =
                new TagContainsFilterWordPredicate("goLdEn ReTrieVer");
        assertTrue(sixthPredicate.test(new PetBuilder().withTags(firstTagKeyword).build()));

    }

    @Test
    public void test_tagDoesNotContainKeyword_returnsFalse() {
        // No match: Golden Retriever == Beagle -> false
        TagContainsFilterWordPredicate firstPredicate =
                new TagContainsFilterWordPredicate(secondTagKeyword);
        assertFalse(firstPredicate.test(new PetBuilder().withTags(firstTagKeyword).build()));

        // Partial match: Golden Retriever == Gold Dachshund -> false
        TagContainsFilterWordPredicate secondPredicate =
                new TagContainsFilterWordPredicate("Gold Dachshund");
        assertFalse(secondPredicate.test(new PetBuilder().withTags(firstTagKeyword).build()));

        // No match: Golden Retriever == Border Collie -> false
        TagContainsFilterWordPredicate thirdPredicate =
                new TagContainsFilterWordPredicate("Border Collie");
        assertFalse(thirdPredicate.test(new PetBuilder().withTags(firstTagKeyword).build()));
    }
}

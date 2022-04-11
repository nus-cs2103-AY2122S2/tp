package seedu.address.model.person.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.ARRAY_OF_PREFIX;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.testutil.PersonBuilder;

class ScrubPersonPredicateTest {

    @Test
    void test() {
        ArgumentMultimap singleArgDescriptor = ArgumentTokenizer.tokenize(" e/@mail p/90400204 t/tag1",
                ARRAY_OF_PREFIX);
        ArgumentMultimap multipleArgDescriptor = ArgumentTokenizer.tokenize(" e/@mail e/@gmail p/90400204 p/90400203 "
                + "t/tag1 t/tag2", ARRAY_OF_PREFIX);
        ScrubPersonPredicate singleArgPredicate = new ScrubPersonPredicate(singleArgDescriptor);
        assertTrue(singleArgPredicate.test(new PersonBuilder().withEmail("tester@mail.com").withTags("tag1")
                .withPhone("90400204").build()));
        assertFalse(singleArgPredicate.test(new PersonBuilder().withEmail("tester@mail.com").build()));
        assertFalse(singleArgPredicate.test(new PersonBuilder().withPhone("90400204").build()));
        assertFalse(singleArgPredicate.test(new PersonBuilder().withTags("tag1").build()));
        assertFalse(singleArgPredicate.test(new PersonBuilder().withTags("tag22").build()));

        ScrubPersonPredicate multipleArgPredicate = new ScrubPersonPredicate(multipleArgDescriptor);
        assertTrue(multipleArgPredicate.test(new PersonBuilder().withEmail("tester@gmail.com")
                .withPhone("90400204").withTags("tag2").build()));
        assertFalse(multipleArgPredicate.test(new PersonBuilder().withPhone("90400203").build()));
        assertFalse(multipleArgPredicate.test(new PersonBuilder().withTags("tag2").build()));

        ArgumentMultimap multipleTagDescriptor = ArgumentTokenizer.tokenize("t/tag1 t/tag2", ARRAY_OF_PREFIX);
        ScrubPersonPredicate multipleTagPredicate = new ScrubPersonPredicate(multipleTagDescriptor);
        assertFalse(multipleTagPredicate.test(new PersonBuilder().withTags("tag22").build()));
    }
}

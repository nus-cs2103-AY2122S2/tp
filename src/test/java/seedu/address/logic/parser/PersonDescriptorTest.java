package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.ARRAY_OF_PREFIX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class PersonDescriptorTest {

    @Test
    void getAllAvailablePrefix() {
        List<Prefix> expectedPrefixList = Arrays.asList(PREFIX_NAME, PREFIX_EMAIL, PREFIX_PHONE);
        ArgumentMultimap descriptor = ArgumentTokenizer.tokenize(" e/test1 n/test2 p/test3", ARRAY_OF_PREFIX);
        assertEquals(expectedPrefixList, descriptor.getAllAvailablePrefix());
    }

    @Test
    void isEmpty() {
        ArgumentMultimap emptyDescriptor = ArgumentTokenizer.tokenize(" ", ARRAY_OF_PREFIX);
        ArgumentMultimap populatedDescriptor = ArgumentTokenizer.tokenize(" n/ test1 ", ARRAY_OF_PREFIX);
        assertTrue(emptyDescriptor.hasNoValidPrefix());
        assertFalse(populatedDescriptor.hasNoValidPrefix());
    }

    @Test
    void getDescription() {
        ArgumentMultimap dummyDescriptor = ArgumentTokenizer.tokenize(" n/ test statement ", ARRAY_OF_PREFIX);
        assertEquals("test statement", dummyDescriptor.getValue(PREFIX_NAME).orElse(""));
    }

    @Test
    void testEquals() {
        ArgumentMultimap descriptor = ArgumentTokenizer.tokenize(" n/ test statement ", ARRAY_OF_PREFIX);
        ArgumentMultimap duplicatedDescriptor = ArgumentTokenizer.tokenize(" n/ test statement ", ARRAY_OF_PREFIX);
        ArgumentMultimap differentDescriptor = ArgumentTokenizer.tokenize(" n/ test statement 1", ARRAY_OF_PREFIX);
        assertEquals(descriptor, duplicatedDescriptor);
        assertNotEquals(descriptor, differentDescriptor);
    }
}

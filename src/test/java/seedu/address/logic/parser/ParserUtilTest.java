package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.typical.TypicalIndexes.INDEX_FIRST_MEETING;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;


/**
 * Contains util methods for parser
 * This file is an adapted version of AddressBook's ParserUtil and is currently missing certain functions
 * due to missing implementation. Please refer to the obsolete package for reference.
 */
public class ParserUtilTest {

    private static final String VALID_INDEX_TYPE_1 = "Module";
    // As the first character of the index type is extracted for capitalization, we need to test this one character
    // edge case to ensure that no unexpected error occurs (e.g. out of bounds access on the index type string).
    private static final String VALID_INDEX_TYPE_2 = "M";
    private static final String EMPTY_INDEX_TYPE = "";
    private static final String INVALID_TAG = "#friend";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";
    private static final String WHITESPACE = " \t\r\n";

    private String createParseIndexErrMsg(String indexType) {
        return String.format("%s %s", indexType.trim(), MESSAGE_INVALID_INDEX).trim();
    }

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        final String indexInput = "10 a";

        // Test variant of `parseIndex` that does not accept an `indexType`.
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, () -> ParserUtil.parseIndex(indexInput));

        // Test variant of `parseIndex` that accepts an `indexType`.
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, () ->
                ParserUtil.parseIndex(indexInput, EMPTY_INDEX_TYPE));
        assertThrows(ParseException.class, createParseIndexErrMsg(VALID_INDEX_TYPE_1), () ->
                ParserUtil.parseIndex(indexInput, VALID_INDEX_TYPE_1));
        assertThrows(ParseException.class, createParseIndexErrMsg(VALID_INDEX_TYPE_2), () ->
                ParserUtil.parseIndex(indexInput, VALID_INDEX_TYPE_2));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        final String indexInput = Long.toString(Integer.MAX_VALUE + 1);

        // Test variant of `parseIndex` that does not accept an `indexType`.
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, () -> ParserUtil.parseIndex(indexInput));

        // Test variant of `parseIndex` that accepts an `indexType`.
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, () ->
                ParserUtil.parseIndex(indexInput, EMPTY_INDEX_TYPE));
        assertThrows(ParseException.class, createParseIndexErrMsg(VALID_INDEX_TYPE_1), () ->
                ParserUtil.parseIndex(indexInput, VALID_INDEX_TYPE_1));
        assertThrows(ParseException.class, createParseIndexErrMsg(VALID_INDEX_TYPE_2), () ->
                ParserUtil.parseIndex(indexInput, VALID_INDEX_TYPE_2));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_MEETING, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_MEETING, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        final Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        final String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        final Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        final Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        final Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }
}

package seedu.trackermon.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.trackermon.commons.core.Messages.MESSAGE_INVALID_INDEX;
import static seedu.trackermon.testutil.Assert.assertThrows;
import static seedu.trackermon.testutil.TypicalIndexes.INDEX_FIRST_SHOW;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.trackermon.logic.parser.exceptions.ParseException;
import seedu.trackermon.model.show.Name;
import seedu.trackermon.model.show.Status;
import seedu.trackermon.model.tag.Tag;

/**
 * Contains unit tests for {@code ParserUtil}.
 */
public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_STATUS = " ";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = "JoJo";
    private static final String VALID_STATUS = "completed";
    private static final String VALID_TAG_1 = "action";
    private static final String VALID_TAG_2 = "horror";

    private static final String WHITESPACE = " \t\r\n";

    /**
     * Tests the parsing of invalid index input from the execution of {@code ParserUtil}.
     */
    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    /**
     * Tests the parsing of out of range index input from the execution of {@code ParserUtil}.
     */
    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    /**
     * Tests the parsing of valid name input from the execution of {@code ParserUtil}.
     */
    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_SHOW, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_SHOW, ParserUtil.parseIndex("  1  "));
    }

    /**
     * Tests the parsing of null name input from the execution of {@code ParserUtil}.
     */
    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    /**
     * Tests the parsing of invalid name value from the execution of {@code ParserUtil}.
     */
    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    /**
     * Tests the parsing of valid name value with no whitespace from the execution of {@code ParserUtil}.
     */
    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    /**
     * Tests the parsing of valid name value with whitespace from the execution of {@code ParserUtil}.
     */
    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    /**
     * Tests the parsing of null status input from the execution of {@code ParserUtil}.
     */
    @Test
    public void parseStatus_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseStatus((String) null));
    }

    /**
     * Tests the parsing of invalid status value from the execution of {@code ParserUtil}.
     */
    @Test
    public void parseStatus_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseStatus(INVALID_STATUS));
    }

    /**
     * Tests the parsing of valid status value with no whitespace from the execution of {@code ParserUtil}.
     */
    @Test
    public void parseStatus_validValueWithoutWhitespace_returnsStatus() throws Exception {
        Status expectedStatus = Status.getStatus(VALID_STATUS);
        assertEquals(expectedStatus, ParserUtil.parseStatus(VALID_STATUS));
    }

    /**
     * Tests the parsing of valid status value with whitespace from the execution of {@code ParserUtil}.
     */
    @Test
    public void parseStatus_validValueWithWhitespace_returnsTrimmedStatus() throws Exception {
        String statusWithWhitespace = WHITESPACE + VALID_STATUS + WHITESPACE;
        Status expectedStatus = Status.getStatus(VALID_STATUS);
        assertEquals(expectedStatus, ParserUtil.parseStatus(statusWithWhitespace));
    }

    /**
     * Tests the parsing of null tag input from the execution of {@code ParserUtil}.
     */
    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    /**
     * Tests the parsing of invalid tag value from the execution of {@code ParserUtil}.
     */
    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
    }

    /**
     * Tests the parsing of valid tag value with no whitespace from the execution of {@code ParserUtil}.
     */
    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_1));
    }

    /**
     * Tests the parsing of valid tag value with whitespace from the execution of {@code ParserUtil}.
     */
    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
    }

    /**
     * Tests the parsing of null tag input from the execution of {@code ParserUtil}.
     */
    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
    }

    /**
     * Tests the parsing of invalid tag value from the execution of {@code ParserUtil}.
     */
    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    }

    /**
     * Tests the parsing of empty tag collection from the execution of {@code ParserUtil}.
     */
    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    }

    /**
     * Tests the parsing of collection of tags from the execution of {@code ParserUtil}.
     */
    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }
}

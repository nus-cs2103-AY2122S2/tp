package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.ContactedDate;
import seedu.address.model.person.Email;
import seedu.address.model.person.Memo;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.MemoUtil;

public class ParserUtilTest {
    private static final String INVALID_NAME = "  ";
    private static final String INVALID_PHONE = "@651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_CONTACTED_DATE = "01/01/2022";
    private static final String INVALID_MEMO = MemoUtil.ONE_MORE_THAN_MAXIMUM_MEMO_STRING;
    private static final String INVALID_TAG = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
            + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_CONTACTED_DATE = "01-01-2022";
    private static final String VALID_EMPTY_CONTACTED_DATE = "";
    private static final String VALID_MEMO = "Avid hiker";
    private static final String VALID_EMPTY_MEMO = "";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";

    private static final String WHITESPACE = " \t\r\n";

    // EP: invalid string with trailing alphabet
    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    // EP: out of range
    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    // EP: valid input
    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("  1  "));
    }

    // EP: null
    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    // EP: invalid white space string
    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    // EP: valid string
    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    // EP: valid string with whitespace
    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    // EP: null
    @Test
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone((String) null));
    }

    // EP: invalid string
    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE));
    }

    // EP: valid string
    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE));
    }

    // EP: valid string with whitespace
    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(phoneWithWhitespace));
    }

    // EP: null
    @Test
    public void parseAddress_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAddress((String) null));
    }

    // EP: invalid whitespace string
    @Test
    public void parseAddress_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAddress(INVALID_ADDRESS));
    }

    // EP: valid string
    @Test
    public void parseAddress_validValueWithoutWhitespace_returnsAddress() throws Exception {
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(VALID_ADDRESS));
    }

    // EP: valid string with whitespace
    @Test
    public void parseAddress_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_ADDRESS + WHITESPACE;
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(addressWithWhitespace));
    }

    // EP: null
    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail((String) null));
    }

    // EP: invalid string without @
    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
    }

    // EP: valid string
    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
    }

    // EP: valid string with whitespace
    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
    }

    // EP: null
    @Test
    public void parseContactedDate_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseContactedDate((String) null));
    }

    // EP: invalid format
    @Test
    public void parseContactedDate_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseContactedDate(INVALID_CONTACTED_DATE));
    }

    // EP: valid dd-mm-yyyy format
    @Test
    public void parseContactedDate_validValueWithoutWhitespace_returnsContactedDate() throws Exception {
        ContactedDate expectedContactedDate = new ContactedDate(VALID_CONTACTED_DATE);
        assertEquals(expectedContactedDate, ParserUtil.parseContactedDate(VALID_CONTACTED_DATE));
    }

    // EP: valid dd-mm-yyyy format with whitespace
    @Test
    public void parseContactedDate_validValueWithWhitespace_returnsTrimmedContactedDate() throws Exception {
        String contactedDateWithWhitespace = WHITESPACE + VALID_CONTACTED_DATE + WHITESPACE;
        ContactedDate expectedContactedDate = new ContactedDate(VALID_CONTACTED_DATE);
        assertEquals(expectedContactedDate, ParserUtil.parseContactedDate(contactedDateWithWhitespace));
    }

    // EP: empty string
    @Test
    public void parseContactedDate_validEmptyString_returnsEmptyContactedDate() throws Exception {
        ContactedDate expectedEmptyContactedDate = new ContactedDate(VALID_EMPTY_CONTACTED_DATE);
        ContactedDate parsedContactedDate = ParserUtil.parseContactedDate(VALID_EMPTY_CONTACTED_DATE);
        assertEquals(parsedContactedDate, expectedEmptyContactedDate);
        assertEquals(parsedContactedDate, ContactedDate.EMPTY_CONTACTED_DATE);
    }

    // EP: null
    @Test
    public void parseMemo_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseMemo((String) null));
    }

    // EP: invalid string, longer than maximum length allowed
    @Test
    public void parseMemo_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseMemo(INVALID_MEMO));
    }

    // EP: valid string
    @Test
    public void parseMemo_validValueWithoutWhitespace_returnsMemo() throws Exception {
        Memo expectedMemo = new Memo(VALID_MEMO);
        assertEquals(expectedMemo, ParserUtil.parseMemo(VALID_MEMO));
    }

    // EP: valid string with whitespace
    @Test
    public void parseMemo_validValueWithWhitespace_returnsTrimmedMemo() throws Exception {
        String memoWithWhitespace = WHITESPACE + VALID_MEMO + WHITESPACE;
        Memo expectedMemo = new Memo(VALID_MEMO);
        assertEquals(expectedMemo, ParserUtil.parseMemo(memoWithWhitespace));
    }

    // EP: empty string
    @Test
    public void parseMemo_validEmptyString_returnsEmptyMemo() throws Exception {
        Memo expectedEmptyMemo = new Memo(VALID_EMPTY_MEMO);
        Memo parsedMemo = ParserUtil.parseMemo(VALID_EMPTY_MEMO);
        assertEquals(parsedMemo, expectedEmptyMemo);
        assertEquals(parsedMemo, Memo.EMPTY_MEMO);
    }

    // EP: null
    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    // EP: invalid string more than maximum length allowed
    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
    }

    // EP: valid string
    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_1));
    }

    // EP: valid string with whitespace
    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
    }

    // EP: null
    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
    }

    // EP: valid string and invalid string
    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    }

    // EP: empty string
    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    }

    // EP: valid strings
    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }
}

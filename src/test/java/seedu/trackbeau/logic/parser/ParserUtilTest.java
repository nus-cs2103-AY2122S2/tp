package seedu.trackbeau.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.trackbeau.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.trackbeau.testutil.Assert.assertThrows;
import static seedu.trackbeau.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.trackbeau.logic.parser.exceptions.ParseException;
import seedu.trackbeau.model.customer.Address;
import seedu.trackbeau.model.customer.Email;
import seedu.trackbeau.model.customer.Hair;
import seedu.trackbeau.model.customer.Name;
import seedu.trackbeau.model.customer.Phone;
import seedu.trackbeau.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_SKIN_TYPE = " ";
    private static final String INVALID_HAIR_TYPE = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_STAFF = "#Jane";
    private static final String INVALID_SERVICE = "#Facial";
    private static final String INVALID_ALLERGY = "#Nickel";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_SKIN_TYPE = "Oily";
    private static final String VALID_HAIR_TYPE = "Dry";
    private static final String VALID_STAFF_1 = "Jane";
    private static final String VALID_STAFF_2 = "John";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone((String) null));
    }

    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(phoneWithWhitespace));
    }

    @Test
    public void parseAddress_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAddress((String) null));
    }

    @Test
    public void parseAddress_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAddress(INVALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithoutWhitespace_returnsAddress() throws Exception {
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(VALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_ADDRESS + WHITESPACE;
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(addressWithWhitespace));
    }

    @Test
    public void parseSkinType_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseSkinType((String) null));
    }

    @Test
    public void parseSkinType_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseSkinType(INVALID_SKIN_TYPE));
    }

    /*
    @Test
    public void parseSkinType_validValueWithoutWhitespace_returnsSkinType() throws Exception {
        Skin expectedSkin = new Skin(INVALID_SKIN_TYPE);
        assertEquals(expectedSkin, ParserUtil.parseAddress(INVALID_SKIN_TYPE));
    }
    */

    /*
    @Test
    public void parseSkinType_validValueWithWhitespace_returnsTrimmedSkinType() throws Exception {
        String skinTypeWithWhitespace = WHITESPACE + VALID_SKIN_TYPE + WHITESPACE;
        Skin expectedSkin = new Skin(VALID_SKIN_TYPE);
        assertEquals(expectedSkin, ParserUtil.parseAddress(skinTypeWithWhitespace));
    }
    */

    @Test
    public void parseHairType_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseHairType((String) null));
    }

    @Test
    public void parseHairType_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseHairType(INVALID_HAIR_TYPE));
    }

    /*
    @Test
    public void parseHairType_validValueWithoutWhitespace_returnsHairType() throws Exception {
        Hair expectedHair = new Hair(INVALID_HAIR_TYPE);
        assertEquals(expectedHair, ParserUtil.parseAddress(INVALID_HAIR_TYPE));
    }
    */

    public void parseHairType_validValueWithWhitespace_returnsTrimmedHairType() throws Exception {
        String hairTypeWithWhitespace = WHITESPACE + VALID_HAIR_TYPE + WHITESPACE;
        Hair expectedHair = new Hair(VALID_HAIR_TYPE);
        assertEquals(expectedHair, ParserUtil.parseAddress(hairTypeWithWhitespace));
    }

    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail((String) null));
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
    }

    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    /*
    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_STAFF));
    }
    */

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsStaff() throws Exception {
        Tag expectedTag = new Tag(VALID_STAFF_1);
        assertEquals(expectedTag, ParserUtil.parseTag(VALID_STAFF_1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String staffWithWhitespace = WHITESPACE + VALID_STAFF_1 + WHITESPACE;
        Tag expectedStaff = new Tag(VALID_STAFF_1);
        assertEquals(expectedStaff, ParserUtil.parseTag(staffWithWhitespace));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
    }

    /*
    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList(VALID_STAFF_1, VALID_STAFF_2)));
    }
    */

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_STAFF_1, VALID_STAFF_2));
        Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_STAFF_1), new Tag(VALID_STAFF_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }
}

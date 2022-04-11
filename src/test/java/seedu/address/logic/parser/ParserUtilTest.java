package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

import org.junit.jupiter.api.Test;

import javafx.util.Pair;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.InsurancePackage;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Priority;
import seedu.address.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_INSURANCE_PACKAGE = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "friend :q3";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_INSURANCE_PACKAGE = "Golden Premium Plus";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TAG_NAME_1 = "friend";
    private static final String VALID_TAG_NAME_2 = "neighbour";

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
    public void parseOutIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseOutIndex("a b"));
    }

    @Test
    public void parseOutIndex_validInput_success() throws Exception {
        // No whitespaces
        Pair<Index, String> expectedPair = new Pair<>(INDEX_FIRST_PERSON, VALID_TAG_NAME_1);
        assertEquals(expectedPair, ParserUtil.parseOutIndex("1 " + VALID_TAG_NAME_1));

        // Leading and trailing whitespaces
        assertEquals(expectedPair, ParserUtil.parseOutIndex("  1     " + VALID_TAG_NAME_1));
        assertEquals(new Pair<>(INDEX_FIRST_PERSON, ""), ParserUtil.parseOutIndex("1     "));
    }

    @Test
    public void parseOutNumber_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseOutNumber("a b"));
    }

    @Test
    public void parseOutNumber_validInput_success() throws Exception {
        // No whitespaces
        Pair<Integer, String> expectedPair = new Pair<>(1, VALID_TAG_NAME_1);
        assertEquals(expectedPair, ParserUtil.parseOutNumber("1 " + VALID_TAG_NAME_1));

        // Leading and trailing whitespaces
        assertEquals(expectedPair, ParserUtil.parseOutNumber("  1     " + VALID_TAG_NAME_1));
        assertEquals(new Pair<>(1, ""), ParserUtil.parseOutNumber("1     "));
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
    public void parseInsurancePackage_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseInsurancePackage((String) null));
    }

    @Test
    public void parseInsurancePackage_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseInsurancePackage(INVALID_INSURANCE_PACKAGE));
    }

    @Test
    public void parseInsurancePackage_validValueWithoutWhitespace_returnsInsurancePackage() throws Exception {
        InsurancePackage expectedInsurancePackage = new InsurancePackage(VALID_INSURANCE_PACKAGE);
        assertEquals(expectedInsurancePackage, ParserUtil.parseInsurancePackage(VALID_INSURANCE_PACKAGE));
    }

    @Test
    public void parseInsurancePackage_validValueWithWhitespace_returnsTrimmedInsurancePackage() throws Exception {
        String insurancePackageWithWhitespace = WHITESPACE + VALID_INSURANCE_PACKAGE + WHITESPACE;
        InsurancePackage expectedInsurancePackage = new InsurancePackage(VALID_INSURANCE_PACKAGE);
        assertEquals(expectedInsurancePackage, ParserUtil.parseInsurancePackage(insurancePackageWithWhitespace));
    }

    @Test
    public void parseInsurancePackageName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseInsurancePackageName((String) null));
    }

    @Test
    public void parseInsurancePackageName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseInsurancePackageName(INVALID_INSURANCE_PACKAGE));
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

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Tag expectedTag = new Tag(VALID_TAG_NAME_1, null);
        assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_NAME_1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_NAME_1 + WHITESPACE;
        Tag expectedTag = new Tag(VALID_TAG_NAME_1, null);
        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
    }

    @Test
    public void parseTag_priorityNeededForEquality_returnTag() throws Exception {
        String tag = VALID_TAG_NAME_2;
        Tag notEqualTag = new Tag(VALID_TAG_NAME_2, Priority.PRIORITY_2);
        assertNotEquals(notEqualTag, ParserUtil.parseTag(tag));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList(VALID_TAG_NAME_1, INVALID_TAG)));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        ArrayList<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_NAME_1, VALID_TAG_NAME_2));
        ArrayList<Tag> expectedTagSet = new ArrayList<>(Arrays.asList(new Tag(VALID_TAG_NAME_1, null),
                new Tag(VALID_TAG_NAME_2, null)));
        for (Iterator<Tag> it = actualTagSet.iterator(); it.hasNext(); ) {
            Tag t = it.next();
        }
        for (Iterator<Tag> it = expectedTagSet.iterator(); it.hasNext(); ) {
            Tag t = it.next();
        }

        assertEquals(expectedTagSet, actualTagSet);
    }
}

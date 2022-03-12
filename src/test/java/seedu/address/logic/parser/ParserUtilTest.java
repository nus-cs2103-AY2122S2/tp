package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Cca;
import seedu.address.model.person.Education;
import seedu.address.model.person.Email;
import seedu.address.model.person.Internship;
import seedu.address.model.person.Module;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG_CCA = "Tr@ck and F!eld";
    private static final String INVALID_TAG_MODULE = "CS****S";
    private static final String INVALID_TAG_INTERNSHIP = "#OCBC";
    private static final String INVALID_TAG_EDUCATION = "#NUS";
    private static final String EMPTY_SPACE = " ";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TAG_CCA_1 = "Track and Field";
    private static final String VALID_TAG_CCA_2 = "Table Tennis";
    private static final String VALID_TAG_MODULE_1 = "CS1231S";
    private static final String VALID_TAG_MODULE_2 = "CS2100";
    private static final String VALID_TAG_INTERNSHIP_1 = "Google";
    private static final String VALID_TAG_INTERNSHIP_2 = "Facebook";
    private static final String VALID_TAG_EDUCATION_1 = "NUS";
    private static final String VALID_TAG_EDUCATION_2 = "NTU";

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
    // broken from below
    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null, Tag.CCA));
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null, Tag.EDUCATION));
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null, Tag.INTERNSHIP));
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null, Tag.MODULE));
    }

    @Test
    public void parseTag_emptyString_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(EMPTY_SPACE, Tag.CCA));
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(EMPTY_SPACE, Tag.EDUCATION));
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(EMPTY_SPACE, Tag.INTERNSHIP));
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(EMPTY_SPACE, Tag.MODULE));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG_CCA, Tag.CCA));
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG_EDUCATION, Tag.EDUCATION));
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG_INTERNSHIP, Tag.INTERNSHIP));
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG_MODULE, Tag.MODULE));

    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Tag expectedTagCca = new Cca(VALID_TAG_CCA_1);
        Tag expectedTagEducation = new Education(VALID_TAG_EDUCATION_1);
        Tag expectedTagInternship = new Internship(VALID_TAG_INTERNSHIP_1);
        Tag expectedTagModule = new Module(VALID_TAG_MODULE_1);

        assertEquals(expectedTagCca, ParserUtil.parseTag(VALID_TAG_CCA_1, Tag.CCA));
        assertEquals(expectedTagEducation, ParserUtil.parseTag(VALID_TAG_EDUCATION_1, Tag.EDUCATION));
        assertEquals(expectedTagInternship, ParserUtil.parseTag(VALID_TAG_INTERNSHIP_1, Tag.INTERNSHIP));
        assertEquals(expectedTagModule, ParserUtil.parseTag(VALID_TAG_MODULE_1, Tag.MODULE));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagCcaWithWhitespace = WHITESPACE + VALID_TAG_CCA_1 + WHITESPACE;
        String tagEducationWithWhitespace = WHITESPACE + VALID_TAG_EDUCATION_1 + WHITESPACE;
        String tagInternshipWithWhitespace = WHITESPACE + VALID_TAG_INTERNSHIP_1 + WHITESPACE;
        String tagModuleWithWhitespace = WHITESPACE + VALID_TAG_MODULE_1 + WHITESPACE;

        Tag expectedTagCca = new Cca(VALID_TAG_CCA_1);
        Tag expectedTagEducation = new Education(VALID_TAG_EDUCATION_1);
        Tag expectedTagInternship = new Internship(VALID_TAG_INTERNSHIP_1);
        Tag expectedTagModule = new Module(VALID_TAG_MODULE_1);

        assertEquals(expectedTagCca, ParserUtil.parseTag(tagCcaWithWhitespace, Tag.CCA));
        assertEquals(expectedTagEducation, ParserUtil.parseTag(tagEducationWithWhitespace, Tag.EDUCATION));
        assertEquals(expectedTagInternship, ParserUtil.parseTag(tagInternshipWithWhitespace, Tag.INTERNSHIP));
        assertEquals(expectedTagModule, ParserUtil.parseTag(tagModuleWithWhitespace, Tag.MODULE));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null, Tag.CCA));
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null, Tag.EDUCATION));
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null, Tag.INTERNSHIP));
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null, Tag.MODULE));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(
                    Arrays.asList(VALID_TAG_CCA_1, INVALID_TAG_CCA), Tag.CCA));
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(
                    Arrays.asList(VALID_TAG_INTERNSHIP_1, INVALID_TAG_INTERNSHIP), Tag.INTERNSHIP));
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(
                    Arrays.asList(VALID_TAG_EDUCATION_1, INVALID_TAG_EDUCATION), Tag.EDUCATION));
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(
                    Arrays.asList(VALID_TAG_MODULE_1, INVALID_TAG_MODULE), Tag.MODULE));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags(Collections.emptyList(), Tag.CCA).isEmpty());
        assertTrue(ParserUtil.parseTags(Collections.emptyList(), Tag.EDUCATION).isEmpty());
        assertTrue(ParserUtil.parseTags(Collections.emptyList(), Tag.INTERNSHIP).isEmpty());
        assertTrue(ParserUtil.parseTags(Collections.emptyList(), Tag.MODULE).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        HashSet<Tag> actualTagsCca = new HashSet<>(
                    ParserUtil.parseTags(Arrays.asList(VALID_TAG_CCA_1, VALID_TAG_CCA_2), Tag.CCA));
        HashSet<Tag> actualTagsEducation = new HashSet<>(
                    ParserUtil.parseTags(Arrays.asList(VALID_TAG_EDUCATION_1, VALID_TAG_EDUCATION_2), Tag.EDUCATION));
        HashSet<Tag> actualTagsInternship = new HashSet<>(
                ParserUtil.parseTags(Arrays.asList(VALID_TAG_INTERNSHIP_1, VALID_TAG_INTERNSHIP_2), Tag.INTERNSHIP));
        HashSet<Tag> actualTagsModule = new HashSet<>(
                    ParserUtil.parseTags(Arrays.asList(VALID_TAG_MODULE_1, VALID_TAG_MODULE_2), Tag.MODULE));

        HashSet<Tag> expectedTagsCca = new HashSet<>(Arrays.asList(
                    new Cca(VALID_TAG_CCA_1.toLowerCase()),
                    new Cca(VALID_TAG_CCA_2.toLowerCase())));
        HashSet<Tag> expectedTagsEducation = new HashSet<>(Arrays.asList(
                    new Education(VALID_TAG_EDUCATION_1.toLowerCase()),
                    new Education(VALID_TAG_EDUCATION_2.toLowerCase())));
        HashSet<Tag> expectedTagsInternship = new HashSet<>(Arrays.asList(
                    new Internship(VALID_TAG_INTERNSHIP_1.toLowerCase()),
                    new Internship(VALID_TAG_INTERNSHIP_2.toLowerCase())));
        HashSet<Tag> expectedTagsModule = new HashSet<>(Arrays.asList(
                    new Module(VALID_TAG_MODULE_1.toLowerCase()),
                    new Module(VALID_TAG_MODULE_2.toLowerCase())));

        assertEquals(expectedTagsCca, actualTagsCca);
        assertEquals(expectedTagsEducation, actualTagsEducation);
        assertEquals(expectedTagsInternship, actualTagsInternship);
        assertEquals(expectedTagsModule, actualTagsModule);
    }

    @Test
    public void parseTags_collectionWithDuplicateTags_returnsTagSet() throws Exception {
        List<Tag> actualTagsCca = ParserUtil.parseTags(
                Arrays.asList(VALID_TAG_CCA_1, VALID_TAG_CCA_1), Tag.CCA);
        List<Tag> actualTagsEducation = ParserUtil.parseTags(
                Arrays.asList(VALID_TAG_EDUCATION_1, VALID_TAG_EDUCATION_1), Tag.EDUCATION);
        List<Tag> actualTagsInternship = ParserUtil.parseTags(
                Arrays.asList(VALID_TAG_INTERNSHIP_1, VALID_TAG_INTERNSHIP_1), Tag.INTERNSHIP);
        List<Tag> actualTagsModule = ParserUtil.parseTags(
                Arrays.asList(VALID_TAG_MODULE_1, VALID_TAG_MODULE_1), Tag.MODULE);

        List<Tag> expectedTagsCca = Arrays.asList(new Cca(VALID_TAG_CCA_1));
        List<Tag> expectedTagsEducation = Arrays.asList(new Education(VALID_TAG_EDUCATION_1));
        List<Tag> expectedTagsInternship = Arrays.asList(new Internship(VALID_TAG_INTERNSHIP_1));
        List<Tag> expectedTagsModule = Arrays.asList(new Module(VALID_TAG_MODULE_1));

        assertEquals(expectedTagsCca, actualTagsCca);
        assertEquals(expectedTagsEducation, actualTagsEducation);
        assertEquals(expectedTagsInternship, actualTagsInternship);
        assertEquals(expectedTagsModule, actualTagsModule);
    }
}

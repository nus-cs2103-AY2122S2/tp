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
import seedu.address.model.activity.Activity;
import seedu.address.model.person.Address;
import seedu.address.model.person.ClassCode;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Status;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_PHONE_TOO_LONG = "6512347890123456";
    private static final String INVALID_PHONE_TOO_SHORT = "65";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_ACTIVITY = "#friend";
    private static final String INVALID_STATUS = "almost covid positive";
    private static final String INVALID_ADDRESS_TOO_LONG = "fakjsdhfklasdjhflkadsjhflkasdjhfkalj \n"
            + " sdhfklajsdhflkajsdhflkasdfas asdf";
    private static final String INVALID_EMAIL_TOO_LONG = "fakjsdhfklasdjhflkadsjhflkasdjhfkalj@\n"
            + "sdhfklajsdhflkajsdhflkasdfasaasadfadfasdfdf";
    private static final String INVALID_ACTIVITY_TOO_LONG = "fakjsdhfklasdjhflkadsjhflkasdjhfkalj@\n"
            + "sdhfklajsdhflkajsdhflkasdfasaasadfadfasdfdf";
    private static final String INVALID_NAME_TOO_LONG = "Hubert Blaine Wolfeschlegelsteinhausenbergerdorff Sr.";
    private static final String INVALID_CLASSCODE = "55G";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_STATUS_POS = "Positive";
    private static final String VALID_STATUS_NEG = "Negative";
    private static final String VALID_STATUS_CC = "Close-Contact";
    private static final String VALID_ACTIVITY_1 = "badminton";
    private static final String VALID_ACTIVITY_2 = "choir";
    private static final String VALID_CLASSCODE = "3A";

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
    public void parsePhone_invalidValueLongShort_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE_TOO_LONG));
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE_TOO_SHORT));
    }

    @Test
    public void parseStatus_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseStatus(INVALID_STATUS));
    }

    @Test
    public void parseStatus_validValues() throws Exception {
        Status expectedStatus = new Status(VALID_STATUS_POS);
        assertEquals(expectedStatus, ParserUtil.parseStatus(VALID_STATUS_POS));

        expectedStatus = new Status(VALID_STATUS_NEG);
        assertEquals(expectedStatus, ParserUtil.parseStatus(VALID_STATUS_NEG));

        expectedStatus = new Status(VALID_STATUS_CC);
        assertEquals(expectedStatus, ParserUtil.parseStatus(VALID_STATUS_CC));
    }

    @Test
    public void parseClassCode_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseClassCode(INVALID_CLASSCODE));
    }

    @Test
    public void parseClassCode_validValues() throws Exception {
        ClassCode expectedCc = new ClassCode(VALID_CLASSCODE);
        assertEquals(expectedCc, ParserUtil.parseClassCode(VALID_CLASSCODE));
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

    @Test
    public void parseActivity_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseActivity(null));
    }

    @Test
    public void parseActivity_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseActivity(INVALID_ACTIVITY));
    }

    @Test
    public void parseActivity_validValueWithoutWhitespace_returnsActivity() throws Exception {
        Activity expectedActivity = new Activity(VALID_ACTIVITY_1);
        assertEquals(expectedActivity, ParserUtil.parseActivity(VALID_ACTIVITY_1));
    }

    @Test
    public void parseActivity_validValueWithWhitespace_returnsTrimmedActivity() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_ACTIVITY_1 + WHITESPACE;
        Activity expectedActivity = new Activity(VALID_ACTIVITY_1);
        assertEquals(expectedActivity, ParserUtil.parseActivity(tagWithWhitespace));
    }

    @Test
    public void parseActivities_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseActivities(null));
    }

    @Test
    public void parseActivities_collectionWithInvalidActivities_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseActivities(Arrays.asList(VALID_ACTIVITY_1,
                INVALID_ACTIVITY)));
    }

    @Test
    public void parseActivities_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseActivities(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseActivities_collectionWithValidActivities_returnsActivitySet() throws Exception {
        Set<Activity> actualActivitySet = ParserUtil.parseActivities(Arrays.asList(VALID_ACTIVITY_1, VALID_ACTIVITY_2));
        Set<Activity> expectedActivitySet = new HashSet<Activity>(Arrays
                .asList(new Activity(VALID_ACTIVITY_1),
                        new Activity(VALID_ACTIVITY_2)));

        assertEquals(expectedActivitySet, actualActivitySet);
    }

    @Test
    public void parseActivity_invalidValueTooLong_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseActivity(INVALID_ACTIVITY_TOO_LONG));
    }

    @Test
    public void parseEmail_invalidValueTooLong_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL_TOO_LONG));
    }

    @Test
    public void parseAddress_invalidValueTooLong_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAddress(INVALID_ADDRESS_TOO_LONG));
    }

    @Test
    public void parseName_invalidValueTooLong_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME_TOO_LONG));
    }
}

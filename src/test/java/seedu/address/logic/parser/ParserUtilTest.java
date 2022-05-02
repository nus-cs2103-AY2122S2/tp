package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CANDIDATE;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.candidate.Email;
import seedu.address.model.candidate.Name;
import seedu.address.model.candidate.Phone;
import seedu.address.model.candidate.StudentId;

public class ParserUtilTest {
    private static final String INVALID_STUDENT_ID = "A0123456";
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_SENIORITY = "one";
    private static final String INVALID_DATE_TIME = "01/01/2000 12:00";
    private static final String INVALID_FORMAT_DATE_TIME = "2025/03/01 10PM";
    private static final String INVALID_TIME_PERIOD = "A0123456";

    private static final String VALID_STUDENT_ID = "A0123456B";
    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "98765432";
    private static final String VALID_EMAIL = "E0123456@u.nus.edu";
    private static final String VALID_DATE_TIME = "20-03-2025 10:00";
    private static final String VALID_TIME_PERIOD = "TODAY";

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
        assertEquals(INDEX_FIRST_CANDIDATE, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_CANDIDATE, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseTimePeriod_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTimePeriod((String) null));
    }

    @Test
    public void parseTimePeriod_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTimePeriod(INVALID_TIME_PERIOD));
    }

    @Test
    public void parseTimePeriod_validValueWithoutWhitespace_returnsString() throws Exception {
        String expectedTimePeriod = "today";
        assertEquals(expectedTimePeriod, ParserUtil.parseTimePeriod(VALID_TIME_PERIOD));
    }

    @Test
    public void parseTimePeriod_validValueWithWhitespace_returnsTrimmedString() throws Exception {
        String expectedTimePeriod = "today";
        assertEquals(expectedTimePeriod, ParserUtil.parseTimePeriod(VALID_TIME_PERIOD + WHITESPACE));
    }

    @Test
    public void parseId_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseStudentId((String) null));
    }

    @Test
    public void parseId_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseStudentId(INVALID_STUDENT_ID));
    }

    @Test
    public void parseId_validValueWithoutWhitespace_returnsName() throws Exception {
        StudentId expectedId = new StudentId(VALID_STUDENT_ID);
        assertEquals(expectedId, ParserUtil.parseStudentId(VALID_STUDENT_ID));
    }

    @Test
    public void parseId_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String idWithWhitespace = WHITESPACE + VALID_STUDENT_ID + WHITESPACE;
        StudentId expectedId = new StudentId(VALID_STUDENT_ID);
        assertEquals(expectedId, ParserUtil.parseStudentId(idWithWhitespace));
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
    public void parseSeniority_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseSeniority(INVALID_SENIORITY));
    }


    @Test
    public void parseDateTime_validFormat_returnsLocalDateTime() throws ParseException {
        LocalDateTime actualInterviewDateTime = ParserUtil.parseDateTime(VALID_DATE_TIME);
        LocalDateTime expectedInterviewDateTime = LocalDateTime.of(2025, 03, 20, 10, 00);

        assertEquals(actualInterviewDateTime, expectedInterviewDateTime);
    }

    @Test
    public void parseDateTime_invalidFormat_throwsException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDateTime(INVALID_FORMAT_DATE_TIME));
    }

    @Test
    public void parseDateTime_invalidDateTime_throwsException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDateTime(INVALID_DATE_TIME));
    }
}

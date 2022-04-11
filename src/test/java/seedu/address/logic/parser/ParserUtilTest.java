package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.order.DeliveryDateTime;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_TAG_TOO_LONG =
            "12345678901234567890123456789012345678901234567890123456789012345678901";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";

    private static final String WHITESPACE = " \t\r\n";

    private static final LocalDate nextMon = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY));
    private static final LocalDate nextTues = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.TUESDAY));
    private static final LocalDate nextWeds = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.WEDNESDAY));
    private static final LocalDate nextThurs = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.THURSDAY));
    private static final LocalDate nextFri = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
    private static final LocalDate nextSat = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
    private static final LocalDate nextSun = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.SUNDAY));

    private static String nextMonStr = dateFormat("Monday");
    private static String nextTuesStr = dateFormat("Tuesday");
    private static String nextWedsStr = dateFormat("Wednesday");
    private static String nextThursStr = dateFormat("Thursday");
    private static String nextFriStr = dateFormat("Friday");
    private static String nextSatStr = dateFormat("Saturday");
    private static String nextSunStr = dateFormat("Sunday");

    private static final String VALID_DATETIME = "20-10-2022 23:59";
    private static final String VALID_NATURAL_MON_DATETIME = "Monday 23:59";

    private static final String VALID_MON_DATETIME = nextMonStr + " 23:59";
    private static final String VALID_NATURAL_TUES_DATETIME = "Tuesday 23:59";
    private static final String VALID_TUES_DATETIME = nextTuesStr + " 23:59";
    private static final String VALID_NATURAL_WEDS_DATETIME = "Wednesday 23:59";
    private static final String VALID_WEDS_DATETIME = nextWedsStr + " 23:59";
    private static final String VALID_NATURAL_THURS_DATETIME = "Thursday 23:59";
    private static final String VALID_THURS_DATETIME = nextThursStr + " 23:59";
    private static final String VALID_NATURAL_FRI_DATETIME = "Friday 23:59";
    private static final String VALID_FRI_DATETIME = nextFriStr + " 23:59";
    private static final String VALID_NATURAL_SAT_DATETIME = "Saturday 23:59";
    private static final String VALID_SAT_DATETIME = nextSatStr + " 23:59";
    private static final String VALID_NATURAL_SUN_DATETIME = "Sunday 23:59";
    private static final String VALID_SUN_DATETIME = nextSunStr + " 23:59";

    private static final String INVALID_TIME_NATURAL_DATE = "Thursday 8:30";
    private static final String INVALID_DATE_VALID_TIME = "20/10/2022 08:30";
    private static final String INVALID_TIME_VALID_DATE = "20-10-2022 8:30";

    private static String dateFormat(String dayToGet) {
        LocalDate date = LocalDate.now();
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        if (dayToGet.equals("Monday") && (day != Calendar.MONDAY)) {
            date = nextMon;
        } else if (dayToGet.equals("Tuesday") && day != Calendar.TUESDAY) {
            date = nextTues;
        } else if (dayToGet.equals("Wednesday") && day != Calendar.WEDNESDAY) {
            date = nextWeds;
        } else if (dayToGet.equals("Thursday") && day != Calendar.THURSDAY) {
            date = nextThurs;
        } else if (dayToGet.equals("Friday") && day != Calendar.FRIDAY) {
            date = nextFri;
        } else if (dayToGet.equals("Saturday") && day != Calendar.SATURDAY) {
            date = nextSat;
        } else if (dayToGet.equals("Sunday") && day != Calendar.SUNDAY) {
            date = nextSun;
        }
        return date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }



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

    @Test
    public void parseCollectionDateTime_validValueWithWhitespace_returnsDate() throws Exception {
        String datetimeWithWhitespace = WHITESPACE + VALID_DATETIME + WHITESPACE;
        DeliveryDateTime expectedDatetime = new DeliveryDateTime(VALID_DATETIME);
        assertEquals(expectedDatetime, ParserUtil.parseDeliveryDateTime(datetimeWithWhitespace));
    }

    @Test
    public void parseCollectionDateTime_validValueWithoutWhitespace_returnsDate() throws Exception {
        DeliveryDateTime expectedDatetime = new DeliveryDateTime(VALID_DATETIME);
        assertEquals(expectedDatetime, ParserUtil.parseDeliveryDateTime(VALID_DATETIME));
    }

    @Test
    public void parseCollectionDateTime_invalidDateWithoutWhitespace_returnsDate() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDeliveryDateTime(INVALID_DATE_VALID_TIME));
    }

    @Test
    public void parseCollectionDateTime_invalidTimeWithoutWhitespace_returnsDate() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDeliveryDateTime(INVALID_TIME_VALID_DATE));
    }

    @Test
    public void parseCollectionDateTime_validNaturalDateMonday_returnsDate() throws Exception {
        DeliveryDateTime expectedDatetime = ParserUtil.parseDeliveryDateTime(VALID_NATURAL_MON_DATETIME);
        assertEquals(expectedDatetime, ParserUtil.parseDeliveryDateTime(VALID_MON_DATETIME));
    }

    @Test
    public void parseCollectionDateTime_validNaturalDateTuesday_returnsDate() throws Exception {
        DeliveryDateTime expectedDatetime = ParserUtil.parseDeliveryDateTime(VALID_NATURAL_TUES_DATETIME);
        assertEquals(expectedDatetime, ParserUtil.parseDeliveryDateTime(VALID_TUES_DATETIME));
    }

    @Test
    public void parseCollectionDateTime_validNaturalDateWednesday_returnsDate() throws Exception {
        DeliveryDateTime expectedDatetime = new DeliveryDateTime(VALID_WEDS_DATETIME);
        assertEquals(expectedDatetime, ParserUtil.parseDeliveryDateTime(VALID_NATURAL_WEDS_DATETIME));
    }

    @Test
    public void parseCollectionDateTime_validNaturalDateThursday_returnsDate() throws Exception {
        DeliveryDateTime expectedDatetime = ParserUtil.parseDeliveryDateTime(VALID_NATURAL_THURS_DATETIME);
        assertEquals(expectedDatetime, ParserUtil.parseDeliveryDateTime(VALID_THURS_DATETIME));
    }


    @Test
    public void parseCollectionDateTime_validNaturalDateFriday_returnsDate() throws Exception {
        DeliveryDateTime expectedDatetime = ParserUtil.parseDeliveryDateTime(VALID_NATURAL_FRI_DATETIME);
        assertEquals(expectedDatetime, ParserUtil.parseDeliveryDateTime(VALID_FRI_DATETIME));
    }


    @Test
    public void parseCollectionDateTime_validNaturalDateSaturday_returnsDate() throws Exception {
        DeliveryDateTime expectedDatetime = ParserUtil.parseDeliveryDateTime(VALID_NATURAL_SAT_DATETIME);
        assertEquals(expectedDatetime, ParserUtil.parseDeliveryDateTime(VALID_SAT_DATETIME));
    }


    @Test
    public void parseCollectionDateTime_validNaturalDateSunday_returnsDate() throws Exception {
        DeliveryDateTime expectedDatetime = ParserUtil.parseDeliveryDateTime(VALID_NATURAL_SUN_DATETIME);
        assertEquals(expectedDatetime, ParserUtil.parseDeliveryDateTime(VALID_SUN_DATETIME));
    }

    @Test
    public void parseCollectionDateTime_invalidTimeValidNaturalDateWithoutWhitespace_returnsDate() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDeliveryDateTime(INVALID_TIME_NATURAL_DATE));
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
    public void parseTag_invalidValueTooLong_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG_TOO_LONG));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        Tag expectedTag = new Tag(VALID_TAG_1);
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
        Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }
}

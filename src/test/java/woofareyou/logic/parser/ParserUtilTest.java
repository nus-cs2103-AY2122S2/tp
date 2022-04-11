package woofareyou.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static woofareyou.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static woofareyou.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static woofareyou.testutil.Assert.assertThrows;
import static woofareyou.testutil.TypicalIndexes.INDEX_FIRST_PET;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import woofareyou.commons.core.Messages;
import woofareyou.logic.parser.exceptions.ParseException;
import woofareyou.model.pet.Address;
import woofareyou.model.pet.Diet;
import woofareyou.model.pet.Name;
import woofareyou.model.pet.OwnerName;
import woofareyou.model.pet.Phone;
import woofareyou.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_DIET = "%%%%%";
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_OWNERNAME = "S@rah";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_DIET = "Test abc";
    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "82345678";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_OWNERNAME = "Sarah";
    private static final String VALID_TAG_1 = "friend";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, Messages.MESSAGE_INVALID_PET_DISPLAYED_INDEX, ()
            -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, Messages.MESSAGE_INVALID_PET_DISPLAYED_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_PET, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PET, ParserUtil.parseIndex("  1  "));
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
    public void parseDiet_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDiet(INVALID_DIET));
    }

    @Test
    public void parseDiet_validValueWithoutWhitespace_returnsDiet() throws Exception {
        Diet expectedDiet = new Diet(VALID_DIET);
        assertEquals(expectedDiet, ParserUtil.parseDiet(VALID_DIET));
    }

    @Test
    public void parseDiet_validValueWithWhitespace_returnsTrimmedDiet() throws Exception {
        String dietWithWhitespace = WHITESPACE + VALID_DIET + WHITESPACE;
        Diet expectedDiet = new Diet(VALID_DIET);
        assertEquals(expectedDiet, ParserUtil.parseDiet(dietWithWhitespace));
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
    public void parseOwnerName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseOwnerName((String) null));
    }

    @Test
    public void parseOwnerName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseOwnerName(INVALID_OWNERNAME));
    }

    @Test
    public void parseOwnerName_validValueWithoutWhitespace_returnsOwnerName() throws Exception {
        OwnerName expectedOwnerName = new OwnerName(VALID_OWNERNAME);
        assertEquals(expectedOwnerName, ParserUtil.parseOwnerName(VALID_OWNERNAME));
    }

    @Test
    public void parseOwnerName_validValueWithWhitespace_returnsTrimmedOwnerName() throws Exception {
        String ownerNameWithWhitespace = WHITESPACE + VALID_OWNERNAME + WHITESPACE;
        OwnerName expectedOwnerName = new OwnerName(VALID_OWNERNAME);
        assertEquals(expectedOwnerName, ParserUtil.parseOwnerName(ownerNameWithWhitespace));
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
    public void parseTag_multipleTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(TAG_DESC_FRIEND + TAG_DESC_HUSBAND));
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
    public void parseTags_collectionWithInvalidTag_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(Collections.singletonList(INVALID_TAG)));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseAttendanceDate_returnsLocalDate() throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate expectedLocalDate = LocalDate.parse("22-03-2022", formatter);
        assertEquals(ParserUtil.parseAttendanceDate("22-03-2022"), expectedLocalDate);
        assertThrows(ParseException.class, () -> ParserUtil.parseAttendanceDate("2022-03-22"));
    }

    @Test
    public void parseAppointmentDateTime_returnsLocalDateTime() throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime expectedLocalDateTime = LocalDateTime.parse("22-08-2024 09:00", formatter);
        assertEquals(ParserUtil.parseAppointmentDateTime("22-08-2024 09:00"), expectedLocalDateTime);
        // invalid date
        assertThrows(ParseException.class, () -> ParserUtil.parseAppointmentDateTime("2022-03-22 08:30"));
        // invalid time
        assertThrows(ParseException.class, () -> ParserUtil.parseAppointmentDateTime("22-03-2023 0830"));
        // missing date or time
        assertThrows(ParseException.class, () -> ParserUtil.parseAppointmentDateTime("22-08-2024"));
        assertThrows(ParseException.class, () -> ParserUtil.parseAppointmentDateTime("09:00"));
        // missing date and time
        assertThrows(ParseException.class, () -> ParserUtil.parseAppointmentDateTime(" "));
        // leap year
        assertThrows(ParseException.class, () -> ParserUtil.parseAppointmentDateTime("30-02-2024 09:00"));
        // previous dates
        assertThrows(ParseException.class, () -> ParserUtil.parseAppointmentDateTime("04-04-2022 09:00"));

    }

    @Test
    public void parseFilterDate_returnsLocalDate() throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate expectedLocalDate = LocalDate.parse("22-03-2022", formatter);
        assertEquals(ParserUtil.parseFilterDate("22-03-2022"), expectedLocalDate);
        assertThrows(ParseException.class, () -> ParserUtil.parseFilterDate("2022-03-22"));
    }

    @Test
    public void parseFilterAppDate_returnsLocalDate() throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate expectedLocalDate = LocalDate.parse("22-03-2022", formatter);
        assertEquals(ParserUtil.parseFilterAppointmentDate("22-03-2022"), expectedLocalDate);
        assertThrows(ParseException.class, () -> ParserUtil.parseFilterAppointmentDate("2022-03-22"));
    }

    @Test
    public void parsePickUpTime_returnsLocalTime() throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime expectedLocalTime = LocalTime.parse("09:00", formatter);
        assertEquals(ParserUtil.parsePickUpTime("09:00"), expectedLocalTime);
        assertThrows(ParseException.class, () -> ParserUtil.parsePickUpTime("0800"));
    }

    @Test
    public void parseDropOffTime_returnsLocalTime() throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime expectedLocalTime = LocalTime.parse("18:00", formatter);
        assertEquals(ParserUtil.parseDropOffTime("18:00"), expectedLocalTime);
        assertThrows(ParseException.class, () -> ParserUtil.parseDropOffTime("0800"));
    }
}

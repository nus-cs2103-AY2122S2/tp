package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_INDEX;
import static seedu.address.logic.parser.ParserUtil.parseLabel;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.label.Label;
import seedu.address.model.meeting.Agenda;
import seedu.address.model.meeting.MeetingPlace;
import seedu.address.model.meeting.MeetingTime;
import seedu.address.model.person.Address;
import seedu.address.model.person.Company;
import seedu.address.model.person.Email;
import seedu.address.model.person.JobTitle;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Pronoun;
import seedu.address.model.tag.Tag;

public class ParserUtilTest {
    //-------------------------Person--------------------------------------------------------
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_PRONOUN = " ";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_COMPANY = " ";

    private static final String INVALID_JOBTITLE = "123Manager";
    private static final String INVALID_JOBTITLE_2 = "_PizzaHutWorker";
    private static final String INVALID_JOBTITLE_3 = "        ";

    private static final String INVALID_PHONE_LABEL_1 = "123456l/ home";
    private static final String INVALID_PHONE_LABEL_2 = "123456l/home";

    private static final String INVALID_ADDRESS_WITH_LABEL_1 = "l/home";
    private static final String INVALID_ADDRESS_WITH_LABEL_2 = "123 Main Street #0505l/home";
    private static final String INVALID_ADDRESS_WITH_LABEL_3 = " ";

    private static final String INVALID_EMAIL_WITH_LABEL_2 = "rachel@example.coml/home";
    private static final String INVALID_EMAIL_WITH_LABEL_3 = "rachel@example.coml/ home";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_COMPANY = "Microhard";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_PRONOUN = "they";
    private static final String VALID_PRONOUN_2 = "she";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_JOBTITLE = "Head Chef";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";

    private static final String VALID_ADDRESS_WITH_LABEL = "123 Main Street #0505 l/home";
    private static final String VALID_EMAIL_WITH_LABEL = "rachel@example.com l/friend";
    private static final String VALID_PHONE_WITH_LABEL = "123456 l/home";
    //--------------------------------------Meeting---------------------------------------------
    private static final String VALID_AGENDA = "Important meeting with client.";
    private static final String VALID_MEETING_PLACE = "Inside the conference room.";
    private static final String VALID_MEETING_TIME_1 = "22-08-2022 10:00";
    private static final String VALID_MEETING_TIME_2 = "29-08-2024 23:59";
    private static final String VALID_MEETING_ATTENDEE_1 = "1";
    private static final String VALID_MEETING_ATTENDEE_2 = "999";

    private static final String INVALID_AGENDA_1 = "";
    private static final String INVALID_AGENDA_2 = "    ";
    private static final String INVALID_MEETING_PLACE_1 = "";
    private static final String INVALID_MEETING_PLACE_2 = "   ";
    private static final String INVALID_MEETING_TIME_1 = "2024-08-29 23:59";
    private static final String INVALID_MEETING_TIME_2 = "32-08-2025 23:59";
    private static final String INVALID_MEETING_ATTENDEE_1 = "0";
    private static final String INVALID_MEETING_ATTENDEE_2 = "-1";
    private static final String INVALID_MEETING_ATTENDEE_3 = "+1";
    private static final String INVALID_MEETING_ATTENDEE_4 = "one";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, () -> ParserUtil
                .parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("  1  "));
    }

    //------------------------------------------------------------------------------------------------------------------

    @Test
    public void parseAgenda_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAgenda(null));
    }

    @Test
    public void parseAgenda_invalidValue_throwsParseException() {
        //emptyString
        assertThrows(ParseException.class, () -> ParserUtil.parseAgenda(INVALID_AGENDA_1));
    }

    @Test
    public void parseAgenda_invalidValue2_throwsParseException() {
        //space
        assertThrows(ParseException.class, () -> ParserUtil.parseAgenda(INVALID_AGENDA_2));
    }

    @Test
    public void parseAgenda_validValueWithoutWhitespace_returnsAgenda() throws Exception {
        Agenda expectedAgenda = new Agenda(VALID_AGENDA);
        assertEquals(expectedAgenda, ParserUtil.parseAgenda(VALID_AGENDA));
    }

    @Test
    public void parseAgenda_validValueWithWhitespace_returnsTrimmedAgenda() throws Exception {
        String agendaWithWhitespace = WHITESPACE + VALID_AGENDA + WHITESPACE;
        Agenda expectedAgenda = new Agenda(VALID_AGENDA);
        assertEquals(expectedAgenda, ParserUtil.parseAgenda(agendaWithWhitespace));
    }
    //------------------------------------------------------------------------------------------------------------------

    @Test
    public void parseMeetingPlace_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseMeetingPlace(null));
    }

    @Test
    public void parseMeetingPlace_invalidValue_throwsParseException() {
        //emptyString
        assertThrows(ParseException.class, () -> ParserUtil.parseMeetingPlace(INVALID_MEETING_PLACE_1));
    }

    @Test
    public void parseMeetingPlace_invalidValue2_throwsParseException() {
        //space
        assertThrows(ParseException.class, () -> ParserUtil.parseMeetingPlace(INVALID_MEETING_PLACE_2));
    }

    @Test
    public void parseMeetingPlace_validValueWithoutWhitespace_returnsMeetingPlace() throws Exception {
        MeetingPlace expectedPlace = new MeetingPlace(VALID_MEETING_PLACE);
        assertEquals(expectedPlace, ParserUtil.parseMeetingPlace(VALID_MEETING_PLACE));
    }

    @Test
    public void parseMeetingPlace_validValueWithWhitespace_returnsTrimmedMeetingPlace() throws Exception {
        String meetingPlaceWithWhitespace = WHITESPACE + VALID_MEETING_PLACE + WHITESPACE;
        MeetingPlace expectedPlace = new MeetingPlace(VALID_MEETING_PLACE);
        assertEquals(expectedPlace, ParserUtil.parseMeetingPlace(meetingPlaceWithWhitespace));
    }

    //------------------------------------------------------------------------------------------------------------------

    @Test
    public void parseMeetingTime_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseMeetingTime(null));
    }

    @Test
    public void parseMeetingTime_invalidValue_throwsParseException() {
        //wrong date format
        assertThrows(ParseException.class, () -> ParserUtil.parseMeetingTime(INVALID_MEETING_TIME_1));
    }

    @Test
    public void parseMeetingTime_invalidValue2_throwsParseException() {
        //invalid date
        assertThrows(ParseException.class, () -> ParserUtil.parseMeetingTime(INVALID_MEETING_TIME_2));
    }

    @Test
    public void parseMeetingTime_validValueWithWhitespace_returnsTrimmedMeetingTime() throws Exception {
        String meetingTimeWithWhitespace = WHITESPACE + VALID_MEETING_TIME_2 + WHITESPACE;
        MeetingTime expectedTime = new MeetingTime(VALID_MEETING_TIME_2);
        assertEquals(expectedTime, ParserUtil.parseMeetingTime(meetingTimeWithWhitespace));
    }

    //------------------------------------------------------------------------------------------------------------------

    @Test
    public void parseMeetingAttendees_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAttendees(null));
    }

    @Test
    public void parseMeetingAttendees_invalidValues_throwsParseException() {
        //negative index
        assertThrows(ParseException.class, () -> ParserUtil.parseAttendees(INVALID_MEETING_ATTENDEE_2));
        //zero index
        assertThrows(ParseException.class, () -> ParserUtil.parseAttendees(INVALID_MEETING_ATTENDEE_1));
        //non-number index
        assertThrows(ParseException.class, () -> ParserUtil.parseAttendees(INVALID_MEETING_ATTENDEE_4));
    }

    //------------------------------------------------------------------------------------------------------------------

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName(null));
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

    //------------------------------------------------------------------------------------------------------------------
    @Test
    public void parseLabel_validValueWithoutLabel_returnsPlaceholderLabel() throws Exception {
        Label expectedLabel = new Label(VALID_ADDRESS, true);
        Label actualContent = parseLabel(VALID_ADDRESS);
        assertEquals(expectedLabel, actualContent);
    }

    @Test
    public void parseLabel_validValueWithLabel_returnsLabel() throws Exception {
        Label expectedLabel = new Label("home", false);
        Label actualContent = parseLabel(VALID_ADDRESS_WITH_LABEL);
        assertEquals(expectedLabel, actualContent);
    }

    //------------------------------------------------------------------------------------------------------------------

    @Test
    public void parseJobTitle_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseJobTitle(null));
    }

    @Test
    public void parseJobTitle_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseJobTitle(INVALID_JOBTITLE));
        assertThrows(ParseException.class, () -> ParserUtil.parseJobTitle(INVALID_JOBTITLE_2));
        assertThrows(ParseException.class, () -> ParserUtil.parseJobTitle(INVALID_JOBTITLE_3));
    }

    @Test
    public void parseJobTitle_validValueWithoutWhitespace_returnsName() throws Exception {
        JobTitle expectedJobTitle = new JobTitle(VALID_JOBTITLE);
        assertEquals(expectedJobTitle, ParserUtil.parseJobTitle(VALID_JOBTITLE));
    }

    @Test
    public void parseJobTitle_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String jobtitleWithWhitespace = WHITESPACE + VALID_JOBTITLE + WHITESPACE;
        JobTitle expectedJobTitle = new JobTitle(VALID_JOBTITLE);
        assertEquals(expectedJobTitle, ParserUtil.parseJobTitle(jobtitleWithWhitespace));
    }

    //------------------------------------------------------------------------------------------------------------------

    @Test
    public void parseCompany_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseCompany(null));
    }

    @Test
    public void parseCompany_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseCompany(INVALID_COMPANY));
    }

    @Test
    public void parseCompany_validValueWithoutWhitespace_returnsName() throws Exception {
        Company expectedCompany = new Company(VALID_COMPANY);
        assertEquals(expectedCompany, ParserUtil.parseCompany(VALID_COMPANY));
    }

    @Test
    public void parseCompany_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String companyWithWhitespace = WHITESPACE + VALID_COMPANY + WHITESPACE;
        Company expectedCompany = new Company(VALID_COMPANY);
        assertEquals(expectedCompany, ParserUtil.parseCompany(companyWithWhitespace));
    }

    //------------------------------------------------------------------------------------------------------------------

    @Test
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone(null));
    }

    @Test
    public void parsePhone_invalidValueWithoutLabel_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE));
    }

    @Test
    public void parsePhone_invalidValueWithLabel_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE_LABEL_1));
    }

    @Test
    public void parsePhone_validValueWithoutLabelWithWhiteSpace_returnsPhone() throws Exception {
        String phoneWithWhiteSpace = WHITESPACE + VALID_PHONE + WHITESPACE;
        Phone parsedPhone = ParserUtil.parsePhone(phoneWithWhiteSpace);
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, parsedPhone);
    }

    @Test
    public void parsePhone_validValueWithWhitespaceWithLabel_returnsPhone() throws Exception {
        String phoneWithWhiteSpace = WHITESPACE + VALID_PHONE_WITH_LABEL + WHITESPACE;
        Phone parsedPhone = ParserUtil.parsePhone(phoneWithWhiteSpace);
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, parsedPhone);
    }

    @Test
    public void parseNumbers_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseNumbers(null));
    }

    @Test
    public void parseNumbers_collectionWithInvalidNumbers_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil
                .parseNumbers(Arrays.asList(VALID_PHONE_WITH_LABEL, INVALID_PHONE)));
        assertThrows(ParseException.class, () -> ParserUtil.parseNumbers(Arrays.asList(VALID_PHONE, INVALID_PHONE)));
    }

    @Test
    public void parseNumbers_collectionWithInvalidNumbersPlusLabel_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseNumbers(Arrays.asList(VALID_PHONE, INVALID_PHONE)));
        assertThrows(ParseException.class, () -> ParserUtil
                .parseNumbers(Arrays.asList(VALID_PHONE, INVALID_PHONE_LABEL_1)));
        assertThrows(ParseException.class, () -> ParserUtil
                .parseNumbers(Arrays.asList(VALID_PHONE, INVALID_PHONE_LABEL_2)));
    }

    @Test
    public void parseNumbers_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseNumbers(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseNumbers_collectionWithValidNumbers_returnsNumberMap() throws Exception {
        HashMap<Label, Phone> actualNumbersMap = ParserUtil
                .parseNumbers(Arrays.asList(VALID_PHONE, VALID_PHONE_WITH_LABEL));
        HashMap<Label, Phone> expectedNumbersMap = new HashMap<>();

        expectedNumbersMap
                .put(new Label(VALID_PHONE, true), new Phone(VALID_PHONE));
        expectedNumbersMap.put(new Label("home", false), new Phone(VALID_PHONE));
        assertEquals(expectedNumbersMap, actualNumbersMap);
    }

    //------------------------------------------------------------------------------------------------------------------

    @Test
    public void parseAddress_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAddress(null));
    }

    @Test
    public void parseAddress_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAddress(INVALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithoutLabelWithWhiteSpace_returnsAddress() throws Exception {
        String addressWithWhiteSpace = WHITESPACE + VALID_ADDRESS + WHITESPACE;
        Address parsedAddress = ParserUtil.parseAddress(addressWithWhiteSpace);
        Address expectedAddressContent = new Address(VALID_ADDRESS);
        assertEquals(expectedAddressContent, parsedAddress);
    }

    @Test
    public void parseAddress_validValueWithWhitespaceWithLabel_returnaAddress() throws Exception {
        String addressWithWhiteSpace = WHITESPACE + VALID_ADDRESS_WITH_LABEL + WHITESPACE;
        Address parsedAddress = ParserUtil.parseAddress(addressWithWhiteSpace);
        Address expectedAddressContent = new Address(VALID_ADDRESS);
        assertEquals(expectedAddressContent, parsedAddress);
    }

    @Test
    public void parseAddresses_collectionWithValidAddresses_returnsAddressesMap() throws Exception {
        HashMap<Label, Address> actualAddressesMap = ParserUtil
                .parseAddresses(Arrays.asList(VALID_ADDRESS, VALID_ADDRESS_WITH_LABEL));
        HashMap<Label, Address> expectedAddressesMap = new HashMap<>();

        expectedAddressesMap
                .put(new Label(VALID_ADDRESS, true), new Address(VALID_ADDRESS));
        expectedAddressesMap.put(new Label("home", false), new Address(VALID_ADDRESS));
        assertEquals(expectedAddressesMap, actualAddressesMap);
    }

    @Test
    public void parseAddresses_collectionWithInvalidAddressesPlusLabel_throwsParseException() throws ParseException {
        assertThrows(ParseException.class, () -> ParserUtil
                .parseAddresses(Arrays.asList(VALID_ADDRESS, INVALID_ADDRESS)));
        assertThrows(ParseException.class, () -> ParserUtil
                .parseAddresses(Arrays.asList(VALID_ADDRESS, INVALID_ADDRESS_WITH_LABEL_1)));
        assertThrows(ParseException.class, () -> ParserUtil
                .parseAddresses(Arrays.asList(VALID_ADDRESS, INVALID_ADDRESS_WITH_LABEL_2)));
        assertThrows(ParseException.class, () -> ParserUtil
                .parseAddresses(Arrays.asList(VALID_ADDRESS, INVALID_ADDRESS_WITH_LABEL_3)));
    }

    //------------------------------------------------------------------------------------------------------------------

    @Test
    public void parseEmail_null_throwsNullPointerException() {
        HashMap<String, Email> testEmailMap = new HashMap<>();
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail(null));
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithoutLabel_returnsEmail() throws Exception {
        Email parsedEmail = ParserUtil.parseEmail(VALID_EMAIL);
        Email expectedEmailContent = new Email(VALID_EMAIL);
        assertEquals(expectedEmailContent, parsedEmail);
    }

    @Test
    public void parseEmail_validValueWithLabel_returnsEmail() throws Exception {
        Email parsedEmail = ParserUtil.parseEmail(VALID_EMAIL_WITH_LABEL);
        Email expectedEmailContent = new Email(VALID_EMAIL);
        assertEquals(expectedEmailContent, parsedEmail);
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsEmail() throws Exception {
        String emailWithWhiteSpace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email parsedEmail = ParserUtil.parseEmail(emailWithWhiteSpace);
        Email expectedEmailContent = new Email(VALID_EMAIL);
        assertEquals(expectedEmailContent, parsedEmail);
    }

    @Test
    public void parseEmail_validValueWithWhitespaceWithLabel_returnsEmail() throws Exception {
        String emailWithWhiteSpace = WHITESPACE + VALID_EMAIL_WITH_LABEL + WHITESPACE;
        Email parsedEmail = ParserUtil.parseEmail(emailWithWhiteSpace);
        Email expectedEmailContent = new Email(VALID_EMAIL);
        assertEquals(expectedEmailContent, parsedEmail);
    }

    @Test
    public void parseEmails_collectionWithValidEmails_returnsEmailsMap() throws Exception {
        HashMap<Label, Email> actualEmailsMap = ParserUtil
                .parseEmails(Arrays.asList(VALID_EMAIL, VALID_EMAIL_WITH_LABEL));
        HashMap<Label, Email> expectedEmailsMap = new HashMap<>();

        expectedEmailsMap
                .put(new Label(VALID_EMAIL, true), new Email(VALID_EMAIL));
        expectedEmailsMap.put(new Label("friend", false), new Email(VALID_EMAIL));
        assertEquals(expectedEmailsMap, actualEmailsMap);
    }

    @Test
    public void parseEmails_collectionWithInvalidEmailsPlusLabel_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmails(Arrays.asList(VALID_EMAIL, INVALID_EMAIL)));
        assertThrows(ParseException.class, () -> ParserUtil
                .parseEmails(Arrays.asList(VALID_EMAIL, INVALID_EMAIL_WITH_LABEL_2)));
        assertThrows(ParseException.class, () -> ParserUtil
                .parseEmails(Arrays.asList(VALID_EMAIL, INVALID_EMAIL_WITH_LABEL_3)));
    }

    //------------------------------------------------------------------------------------------------------------------

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

    //-------------------------------------------------------------------------------------------------------
    @Test
    public void parsePronoun_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePronoun(null));
    }

    @Test
    public void parsePronoun_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePronoun(INVALID_PRONOUN));
    }

    @Test
    public void parsePronoun_validValueWithoutWhitespace_returnsPronoun() throws Exception {
        Pronoun expectedPronoun = new Pronoun(VALID_PRONOUN);
        assertEquals(expectedPronoun, ParserUtil.parsePronoun(VALID_PRONOUN));
    }

    @Test
    public void parsePronoun_validValueWithWhitespace_returnsTrimmedPronoun() throws Exception {
        String pronounWithWhitespace = WHITESPACE + VALID_PRONOUN + WHITESPACE;
        Pronoun expectedPronoun = new Pronoun(VALID_PRONOUN);
        assertEquals(expectedPronoun, ParserUtil.parsePronoun(pronounWithWhitespace));
    }

    @Test
    public void parsePronouns_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePronouns(null));
    }

    @Test
    public void parsePronouns_collectionWithInvalidPronouns_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil
                .parsePronouns(Arrays.asList(VALID_PRONOUN, INVALID_PRONOUN)));
    }

    @Test
    public void parsePronouns_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parsePronouns(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parsePronouns_collectionWithValidPronouns_returnsPronounSet() throws Exception {
        Set<Pronoun> actualPronounSet = ParserUtil.parsePronouns(Arrays.asList(VALID_PRONOUN, VALID_PRONOUN_2));
        Set<Pronoun> expectedPronounSet = new HashSet<>(Arrays
                .asList(new Pronoun(VALID_PRONOUN), new Pronoun(VALID_PRONOUN_2)));

        assertEquals(expectedPronounSet, actualPronounSet);
    }
}

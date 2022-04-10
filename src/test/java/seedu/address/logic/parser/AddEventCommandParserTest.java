package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.COMPANY_DESC_BIG_BANK;
import static seedu.address.logic.commands.CommandTestUtil.COMPANY_DESC_JANICE_STREET;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_A;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_B;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_COMPANY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.LOCATION_DESC_A;
import static seedu.address.logic.commands.CommandTestUtil.LOCATION_DESC_B;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_EVENT_INTERVIEW_BIG_BANK;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_EVENT_INTERVIEW_JANICE_STREET;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_BEHAVIOURAL;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_TECHNICAL;
import static seedu.address.logic.commands.CommandTestUtil.TIME_DESC_A;
import static seedu.address.logic.commands.CommandTestUtil.TIME_DESC_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_JANICE_STREET;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_A;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_INTERVIEW_JANICE_STREET;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_BEHAVIOURAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_TECHNICAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_B;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalEntries.INTERVIEW_BIG_BANK;
import static seedu.address.testutil.TypicalEntries.INTERVIEW_JANICE_STREET;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddEventCommand;
import seedu.address.model.entry.Date;
import seedu.address.model.entry.Event;
import seedu.address.model.entry.Name;
import seedu.address.model.entry.Time;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EventBuilder;

public class AddEventCommandParserTest {
    private AddEventCommandParser parser = new AddEventCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Event expectedEvent = new EventBuilder(INTERVIEW_JANICE_STREET).withTags(VALID_TAG_BEHAVIOURAL).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_EVENT_INTERVIEW_JANICE_STREET
                + COMPANY_DESC_JANICE_STREET + DATE_DESC_B + TIME_DESC_B + LOCATION_DESC_B
                + TAG_DESC_BEHAVIOURAL, new AddEventCommand(expectedEvent));

        // multiple names - last name accepted
        assertParseSuccess(parser, PREAMBLE_WHITESPACE
                + NAME_DESC_EVENT_INTERVIEW_BIG_BANK
                + NAME_DESC_EVENT_INTERVIEW_JANICE_STREET
                + COMPANY_DESC_JANICE_STREET + DATE_DESC_B + TIME_DESC_B + LOCATION_DESC_B
                + TAG_DESC_BEHAVIOURAL, new AddEventCommand(expectedEvent));

        // multiple company names - last company name accepted
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_EVENT_INTERVIEW_JANICE_STREET
                + COMPANY_DESC_BIG_BANK + COMPANY_DESC_JANICE_STREET
                + DATE_DESC_B + TIME_DESC_B + LOCATION_DESC_B
                + TAG_DESC_BEHAVIOURAL, new AddEventCommand(expectedEvent));

        // multiple dates - last date accepted
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_EVENT_INTERVIEW_JANICE_STREET
                + COMPANY_DESC_JANICE_STREET
                + DATE_DESC_A + DATE_DESC_B
                + TIME_DESC_B + LOCATION_DESC_B
                + TAG_DESC_BEHAVIOURAL, new AddEventCommand(expectedEvent));

        // multiple times - last time accepted
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_EVENT_INTERVIEW_JANICE_STREET
                + COMPANY_DESC_JANICE_STREET + DATE_DESC_B
                + TIME_DESC_A + TIME_DESC_B + LOCATION_DESC_B
                + TAG_DESC_BEHAVIOURAL, new AddEventCommand(expectedEvent));

        // multiple locations - last location accepted
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_EVENT_INTERVIEW_JANICE_STREET
                + COMPANY_DESC_JANICE_STREET + DATE_DESC_B + TIME_DESC_B
                + LOCATION_DESC_A + LOCATION_DESC_B
                + TAG_DESC_BEHAVIOURAL, new AddEventCommand(expectedEvent));


        // multiple tags - all accepted
        Event expectedEventMultipleTags = new EventBuilder(INTERVIEW_JANICE_STREET)
                .withTags(VALID_TAG_BEHAVIOURAL, VALID_TAG_TECHNICAL)
                .build();
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_EVENT_INTERVIEW_JANICE_STREET
                + COMPANY_DESC_JANICE_STREET + DATE_DESC_B + TIME_DESC_B + LOCATION_DESC_B
                + TAG_DESC_BEHAVIOURAL + TAG_DESC_TECHNICAL, new AddEventCommand(expectedEventMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Event expectedEvent = new EventBuilder(INTERVIEW_BIG_BANK).withTags().build();
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_EVENT_INTERVIEW_BIG_BANK
                + COMPANY_DESC_BIG_BANK + DATE_DESC_A + TIME_DESC_A
                + LOCATION_DESC_A, new AddEventCommand(expectedEvent));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEventCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_EVENT_INTERVIEW_JANICE_STREET
                        + COMPANY_DESC_JANICE_STREET + DATE_DESC_B + TIME_DESC_B
                        + LOCATION_DESC_B + TAG_DESC_BEHAVIOURAL, expectedMessage);

        // missing company name prefix
        assertParseFailure(parser, NAME_DESC_EVENT_INTERVIEW_JANICE_STREET
                + VALID_COMPANY_JANICE_STREET + DATE_DESC_B + TIME_DESC_B
                + LOCATION_DESC_B + TAG_DESC_BEHAVIOURAL, expectedMessage);

        // missing date prefix
        assertParseFailure(parser, NAME_DESC_EVENT_INTERVIEW_JANICE_STREET
                + COMPANY_DESC_JANICE_STREET + VALID_DATE_B + TIME_DESC_B
                + LOCATION_DESC_B + TAG_DESC_BEHAVIOURAL, expectedMessage);

        // missing time prefix
        assertParseFailure(parser, NAME_DESC_EVENT_INTERVIEW_JANICE_STREET
                + COMPANY_DESC_JANICE_STREET + DATE_DESC_B + VALID_TIME_B
                + LOCATION_DESC_B + TAG_DESC_BEHAVIOURAL, expectedMessage);

        // missing location prefix
        assertParseFailure(parser, NAME_DESC_EVENT_INTERVIEW_JANICE_STREET
                + COMPANY_DESC_JANICE_STREET + DATE_DESC_B + TIME_DESC_B
                + VALID_LOCATION_B + TAG_DESC_BEHAVIOURAL, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_EVENT_INTERVIEW_JANICE_STREET
                + VALID_COMPANY_JANICE_STREET + VALID_DATE_B + VALID_DATE_A
                + VALID_LOCATION_B + TAG_DESC_BEHAVIOURAL, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC
                + COMPANY_DESC_JANICE_STREET + DATE_DESC_B + TIME_DESC_B
                + LOCATION_DESC_B + TAG_DESC_BEHAVIOURAL, Name.MESSAGE_CONSTRAINTS);

        // invalid company name
        assertParseFailure(parser, NAME_DESC_EVENT_INTERVIEW_JANICE_STREET
                + INVALID_COMPANY_DESC + DATE_DESC_B + TIME_DESC_B
                + LOCATION_DESC_B + TAG_DESC_BEHAVIOURAL, Name.MESSAGE_CONSTRAINTS);

        // invalid date
        assertParseFailure(parser, NAME_DESC_EVENT_INTERVIEW_JANICE_STREET
                + COMPANY_DESC_JANICE_STREET + INVALID_DATE_DESC + TIME_DESC_B
                + LOCATION_DESC_B + TAG_DESC_BEHAVIOURAL, Date.MESSAGE_CONSTRAINTS);

        // invalid time
        assertParseFailure(parser, NAME_DESC_EVENT_INTERVIEW_JANICE_STREET
                + COMPANY_DESC_JANICE_STREET + DATE_DESC_B + INVALID_TIME_DESC
                + LOCATION_DESC_B + TAG_DESC_BEHAVIOURAL, Time.MESSAGE_CONSTRAINTS);

        // invalid location is not possible

        // invalid tag
        assertParseFailure(parser, NAME_DESC_EVENT_INTERVIEW_JANICE_STREET
                + COMPANY_DESC_JANICE_STREET + DATE_DESC_B + TIME_DESC_B
                + LOCATION_DESC_B + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC
                + COMPANY_DESC_JANICE_STREET + INVALID_DATE_DESC + TIME_DESC_B
                + LOCATION_DESC_B + TAG_DESC_BEHAVIOURAL, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_EVENT_INTERVIEW_JANICE_STREET
                + COMPANY_DESC_JANICE_STREET + DATE_DESC_B + TIME_DESC_B
                + LOCATION_DESC_B + TAG_DESC_BEHAVIOURAL,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEventCommand.MESSAGE_USAGE));
    }
}

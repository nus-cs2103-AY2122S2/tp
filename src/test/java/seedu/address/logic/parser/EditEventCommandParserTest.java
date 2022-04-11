package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_NOT_EDITED;
import static seedu.address.logic.commands.CommandTestUtil.COMPANY_DESC_BIG_BANK;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_A;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_B;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_A;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.LOCATION_DESC_A;
import static seedu.address.logic.commands.CommandTestUtil.LOCATION_DESC_B;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_EVENT_INTERVIEW_BIG_BANK;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_EVENT_INTERVIEW_JANICE_STREET;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_APPLIED;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_BEHAVIOURAL;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_TECHNICAL;
import static seedu.address.logic.commands.CommandTestUtil.TIME_DESC_A;
import static seedu.address.logic.commands.CommandTestUtil.TIME_DESC_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_BIG_BANK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_A;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_INTERVIEW_BIG_BANK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_INTERVIEW_JANICE_STREET;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_A;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_APPLIED;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_TECHNICAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_A;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_B;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ENTRY;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ENTRY;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_ENTRY;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditEventCommand;
import seedu.address.logic.commands.EditEventCommand.EditEventDescriptor;
import seedu.address.model.entry.Date;
import seedu.address.model.entry.Name;
import seedu.address.model.entry.Time;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditEventDescriptorBuilder;

public class EditEventCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditEventCommand.MESSAGE_USAGE);

    private EditEventCommandParser parser = new EditEventCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_COMPANY_BIG_BANK, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + COMPANY_DESC_BIG_BANK, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + COMPANY_DESC_BIG_BANK, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_DATE_DESC, Date.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, "1" + INVALID_TIME_DESC, Time.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid date followed by valid time
        assertParseFailure(parser, "1" + INVALID_DATE_DESC + TIME_DESC_A, Date.MESSAGE_CONSTRAINTS);

        // valid date followed by invalid date. The test case for invalid date followed by valid date
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + DATE_DESC_B + INVALID_DATE_DESC, Date.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Event} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_TECHNICAL + TAG_DESC_BEHAVIOURAL + TAG_EMPTY,
                Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_TECHNICAL + TAG_EMPTY + TAG_DESC_BEHAVIOURAL,
                Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_TECHNICAL + TAG_DESC_BEHAVIOURAL,
                Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_DATE_DESC
                        + VALID_TIME_A + VALID_LOCATION_A,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_ENTRY;
        String userInput = targetIndex.getOneBased() + NAME_DESC_EVENT_INTERVIEW_BIG_BANK
                + DATE_DESC_A + TIME_DESC_A + TAG_DESC_TECHNICAL
                + LOCATION_DESC_A;

        EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withName(VALID_EVENT_INTERVIEW_BIG_BANK)
                .withDate(VALID_DATE_A).withTime(VALID_TIME_A).withLocation(VALID_LOCATION_A)
                .withTags(VALID_TAG_TECHNICAL).build();
        EditEventCommand expectedCommand = new EditEventCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_ENTRY;
        String userInput = targetIndex.getOneBased() + DATE_DESC_A + LOCATION_DESC_A;

        EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withDate(VALID_DATE_A)
                .withLocation(VALID_LOCATION_A).build();
        EditEventCommand expectedCommand = new EditEventCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_ENTRY;
        String userInput = targetIndex.getOneBased() + NAME_DESC_EVENT_INTERVIEW_BIG_BANK;
        EditEventDescriptor descriptor = new EditEventDescriptorBuilder()
                .withName(VALID_EVENT_INTERVIEW_BIG_BANK).build();
        EditEventCommand expectedCommand = new EditEventCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // date
        userInput = targetIndex.getOneBased() + DATE_DESC_A;
        descriptor = new EditEventDescriptorBuilder().withDate(VALID_DATE_A).build();
        expectedCommand = new EditEventCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // time
        userInput = targetIndex.getOneBased() + TIME_DESC_A;
        descriptor = new EditEventDescriptorBuilder().withTime(VALID_TIME_A).build();
        expectedCommand = new EditEventCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // location
        userInput = targetIndex.getOneBased() + LOCATION_DESC_A;
        descriptor = new EditEventDescriptorBuilder().withLocation(VALID_LOCATION_A).build();
        expectedCommand = new EditEventCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);


        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_TECHNICAL;
        descriptor = new EditEventDescriptorBuilder().withTags(VALID_TAG_TECHNICAL).build();
        expectedCommand = new EditEventCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_ENTRY;
        String userInput = targetIndex.getOneBased() + DATE_DESC_A + NAME_DESC_EVENT_INTERVIEW_BIG_BANK
                + EMAIL_DESC_A + DATE_DESC_A + TIME_DESC_A
                + NAME_DESC_EVENT_INTERVIEW_BIG_BANK + LOCATION_DESC_A + TIME_DESC_B
                + DATE_DESC_B + NAME_DESC_EVENT_INTERVIEW_JANICE_STREET + LOCATION_DESC_B + TAG_DESC_APPLIED;

        EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withName(VALID_EVENT_INTERVIEW_JANICE_STREET)
                .withDate(VALID_DATE_B).withTime(VALID_TIME_B).withLocation(VALID_LOCATION_B)
                .withTags(VALID_TAG_APPLIED)
                .build();
        EditEventCommand expectedCommand = new EditEventCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_ENTRY;
        String userInput = targetIndex.getOneBased() + INVALID_DATE_DESC + DATE_DESC_B;
        EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withDate(VALID_DATE_B).build();
        EditEventCommand expectedCommand = new EditEventCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + DATE_DESC_B + INVALID_TIME_DESC
                + NAME_DESC_EVENT_INTERVIEW_JANICE_STREET
                + TIME_DESC_B;
        descriptor = new EditEventDescriptorBuilder().withDate(VALID_DATE_B).withTime(VALID_TIME_B)
                .withName(VALID_EVENT_INTERVIEW_JANICE_STREET).build();
        expectedCommand = new EditEventCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_ENTRY;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withTags().build();
        EditEventCommand expectedCommand = new EditEventCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}

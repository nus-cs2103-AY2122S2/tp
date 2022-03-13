package seedu.contax.logic.parser;

import static seedu.contax.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.contax.logic.commands.CommandTestUtil.APPOINTMENT_DATE;
import static seedu.contax.logic.commands.CommandTestUtil.APPOINTMENT_DATE2;
import static seedu.contax.logic.commands.CommandTestUtil.APPOINTMENT_DURATION;
import static seedu.contax.logic.commands.CommandTestUtil.APPOINTMENT_DURATION2;
import static seedu.contax.logic.commands.CommandTestUtil.APPOINTMENT_FIRST_PERSON;
import static seedu.contax.logic.commands.CommandTestUtil.APPOINTMENT_NAME_ALONE;
import static seedu.contax.logic.commands.CommandTestUtil.APPOINTMENT_NAME_AMELIA;
import static seedu.contax.logic.commands.CommandTestUtil.APPOINTMENT_REMOVE_PERSON;
import static seedu.contax.logic.commands.CommandTestUtil.APPOINTMENT_SECOND_PERSON;
import static seedu.contax.logic.commands.CommandTestUtil.APPOINTMENT_TIME;
import static seedu.contax.logic.commands.CommandTestUtil.APPOINTMENT_TIME2;
import static seedu.contax.logic.commands.CommandTestUtil.INVALID_APPOINTMENT_DURATION_DESC;
import static seedu.contax.logic.commands.CommandTestUtil.INVALID_APPOINTMENT_NAME_DESC;
import static seedu.contax.logic.commands.CommandTestUtil.INVALID_APPOINTMENT_PERSON;
import static seedu.contax.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.contax.logic.commands.CommandTestUtil.INVALID_TIME_DESC;
import static seedu.contax.logic.commands.CommandTestUtil.VALID_APPOINTMENT_DURATION_HOUR;
import static seedu.contax.logic.commands.CommandTestUtil.VALID_APPOINTMENT_DURATION_MINUTE;
import static seedu.contax.logic.commands.CommandTestUtil.VALID_APPOINTMENT_NAME_ALONE;
import static seedu.contax.logic.commands.CommandTestUtil.VALID_APPOINTMENT_NAME_AMELIA;
import static seedu.contax.logic.commands.CommandTestUtil.VALID_DATE;
import static seedu.contax.logic.commands.CommandTestUtil.VALID_DATE2;
import static seedu.contax.logic.commands.CommandTestUtil.VALID_TIME;
import static seedu.contax.logic.commands.CommandTestUtil.VALID_TIME2;
import static seedu.contax.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.contax.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.contax.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.contax.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.contax.commons.core.Messages;
import seedu.contax.commons.core.index.Index;
import seedu.contax.logic.commands.EditAppointmentCommand;
import seedu.contax.logic.commands.EditAppointmentCommand.EditAppointmentDescriptor;
import seedu.contax.model.appointment.Duration;
import seedu.contax.model.appointment.Name;
import seedu.contax.testutil.EditAppointmentDescriptorBuilder;

public class EditAppointmentCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditAppointmentCommand.MESSAGE_USAGE);

    private EditAppointmentCommandParser parser = new EditAppointmentCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_APPOINTMENT_NAME_ALONE, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditAppointmentCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + APPOINTMENT_NAME_ALONE, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + APPOINTMENT_NAME_ALONE, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // Various invalid fields
        assertParseFailure(parser, "1" + INVALID_APPOINTMENT_NAME_DESC, Name.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_DATE_DESC, Messages.MESSAGE_INVALID_DATE);
        assertParseFailure(parser, "1" + INVALID_TIME_DESC, Messages.MESSAGE_INVALID_TIME);
        assertParseFailure(parser, "1" + INVALID_APPOINTMENT_DURATION_DESC, Duration.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_APPOINTMENT_PERSON, ParserUtil.MESSAGE_INVALID_INDEX);

        // Parsing order left to right test, stop upon first failure
        assertParseFailure(parser,
                "1" + INVALID_APPOINTMENT_NAME_DESC + APPOINTMENT_TIME, Name.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser,
                "1" + APPOINTMENT_NAME_ALONE + INVALID_DATE_DESC, Messages.MESSAGE_INVALID_DATE);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_APPOINTMENT_NAME_DESC + INVALID_DATE_DESC,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = Index.fromOneBased(2);
        String userInput = targetIndex.getOneBased() + APPOINTMENT_NAME_AMELIA + APPOINTMENT_DATE
                + APPOINTMENT_TIME + APPOINTMENT_DURATION + APPOINTMENT_FIRST_PERSON;

        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder()
                .withName(VALID_APPOINTMENT_NAME_AMELIA)
                .withStartDate(VALID_DATE)
                .withStartTime(VALID_TIME)
                .withDuration(VALID_APPOINTMENT_DURATION_HOUR)
                .withPerson(INDEX_FIRST_PERSON).build();
        EditAppointmentCommand expectedCommand = new EditAppointmentCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = Index.fromOneBased(1);
        String userInput = targetIndex.getOneBased() + APPOINTMENT_DATE + APPOINTMENT_DURATION;

        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder()
                .withStartDate(VALID_DATE)
                .withDuration(VALID_APPOINTMENT_DURATION_HOUR).build();
        EditAppointmentCommand expectedCommand = new EditAppointmentCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = Index.fromOneBased(1);
        String userInput = targetIndex.getOneBased() + APPOINTMENT_NAME_ALONE;
        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder()
                .withName(VALID_APPOINTMENT_NAME_ALONE).build();
        EditAppointmentCommand expectedCommand = new EditAppointmentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // start date
        userInput = targetIndex.getOneBased() + APPOINTMENT_DATE;
        descriptor = new EditAppointmentDescriptorBuilder().withStartDate(VALID_DATE).build();
        expectedCommand = new EditAppointmentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // start time
        userInput = targetIndex.getOneBased() + APPOINTMENT_TIME;
        descriptor = new EditAppointmentDescriptorBuilder().withStartTime(VALID_TIME).build();
        expectedCommand = new EditAppointmentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // duration
        userInput = targetIndex.getOneBased() + APPOINTMENT_DURATION;
        descriptor = new EditAppointmentDescriptorBuilder()
                .withDuration(VALID_APPOINTMENT_DURATION_HOUR).build();
        expectedCommand = new EditAppointmentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // person
        userInput = targetIndex.getOneBased() + APPOINTMENT_FIRST_PERSON;
        descriptor = new EditAppointmentDescriptorBuilder()
                .withPerson(INDEX_FIRST_PERSON).build();
        expectedCommand = new EditAppointmentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // remove person
        userInput = targetIndex.getOneBased() + APPOINTMENT_REMOVE_PERSON;
        descriptor = new EditAppointmentDescriptorBuilder()
                .withPerson(null).build();
        expectedCommand = new EditAppointmentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = Index.fromOneBased(2);
        String userInput = targetIndex.getOneBased() + APPOINTMENT_NAME_ALONE + APPOINTMENT_DATE
                + APPOINTMENT_TIME + APPOINTMENT_DURATION + APPOINTMENT_FIRST_PERSON + APPOINTMENT_NAME_AMELIA
                + APPOINTMENT_DATE2 + APPOINTMENT_TIME2 + APPOINTMENT_DURATION2 + APPOINTMENT_SECOND_PERSON;

        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder()
                .withName(VALID_APPOINTMENT_NAME_AMELIA)
                .withStartDate(VALID_DATE2)
                .withStartTime(VALID_TIME2)
                .withDuration(VALID_APPOINTMENT_DURATION_MINUTE)
                .withPerson(INDEX_SECOND_PERSON).build();

        EditAppointmentCommand expectedCommand = new EditAppointmentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_duplicateFieldsWithInvalidInputFirst_success() {
        // only the duplicated field present
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + INVALID_APPOINTMENT_NAME_DESC + APPOINTMENT_NAME_ALONE;
        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder()
                .withName(VALID_APPOINTMENT_NAME_ALONE).build();
        EditAppointmentCommand expectedCommand = new EditAppointmentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // multiple duplicated and invalid fields
        userInput = targetIndex.getOneBased() + INVALID_APPOINTMENT_NAME_DESC + INVALID_DATE_DESC
                + APPOINTMENT_NAME_ALONE + APPOINTMENT_DATE + APPOINTMENT_TIME;
        descriptor = new EditAppointmentDescriptorBuilder()
                .withName(VALID_APPOINTMENT_NAME_ALONE)
                .withStartDate(VALID_DATE)
                .withStartTime(VALID_TIME).build();
        expectedCommand = new EditAppointmentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}

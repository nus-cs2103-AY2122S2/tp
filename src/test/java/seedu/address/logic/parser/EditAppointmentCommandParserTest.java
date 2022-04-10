package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DATETIME_APPOINTMENT1;
import static seedu.address.logic.commands.CommandTestUtil.DESC_APPOINTMENT1;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATETIME_APPOINTMENT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DESC_APPOINTMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_APPOINTMENT1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESC_APPOINTMENT1;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_OBJECT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_OBJECT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditAppointmentCommand;
import seedu.address.logic.commands.EditAppointmentCommand.EditAppointmentDescriptor;
import seedu.address.model.appointment.DateTime;
import seedu.address.model.appointment.Description;
import seedu.address.testutil.EditAppointmentDescriptorBuilder;

public class EditAppointmentCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditAppointmentCommand.MESSAGE_USAGE);

    private EditAppointmentCommandParser parser = new EditAppointmentCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_DATETIME_APPOINTMENT1, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditAppointmentCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + VALID_DATETIME_APPOINTMENT1, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + VALID_DATETIME_APPOINTMENT1, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_DATETIME_APPOINTMENT,
                DateTime.MESSAGE_CONSTRAINTS); // invalid datetime
        assertParseFailure(parser, "1" + INVALID_DESC_APPOINTMENT,
                Description.MESSAGE_CONSTRAINTS); // invalid description
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_OBJECT;
        String userInput = targetIndex.getOneBased() + DESC_APPOINTMENT1 + DATETIME_APPOINTMENT1;

        EditAppointmentDescriptor descriptor =
                new EditAppointmentDescriptorBuilder().withDescription(VALID_DESC_APPOINTMENT1)
                .withDateTime(VALID_DATETIME_APPOINTMENT1).build();
        EditAppointmentCommand expectedCommand = new EditAppointmentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldsSpecified_success() {
        // Description
        Index targetIndex = INDEX_FIRST_OBJECT;
        String userInput = targetIndex.getOneBased() + DESC_APPOINTMENT1;

        EditAppointmentDescriptor descriptor =
                new EditAppointmentDescriptorBuilder().withDescription(VALID_DESC_APPOINTMENT1).build();
        EditAppointmentCommand expectedCommand = new EditAppointmentCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);

        // Datetime
        userInput = targetIndex.getOneBased() + DATETIME_APPOINTMENT1;

        descriptor = new EditAppointmentDescriptorBuilder().withDateTime(VALID_DATETIME_APPOINTMENT1).build();
        expectedCommand = new EditAppointmentCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_OBJECT;
        String userInput = targetIndex.getOneBased() + DESC_APPOINTMENT1 + DATETIME_APPOINTMENT1 + DESC_APPOINTMENT1
                + DATETIME_APPOINTMENT1;

        EditAppointmentDescriptor descriptor =
                new EditAppointmentDescriptorBuilder().withDescription(VALID_DESC_APPOINTMENT1)
                .withDateTime(VALID_DATETIME_APPOINTMENT1).withDescription(VALID_DESC_APPOINTMENT1)
                        .withDateTime(VALID_DATETIME_APPOINTMENT1).build();
        EditAppointmentCommand expectedCommand = new EditAppointmentCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_OBJECT;
        String userInput = targetIndex.getOneBased() + INVALID_DESC_APPOINTMENT + DESC_APPOINTMENT1;
        EditAppointmentDescriptor descriptor =
                new EditAppointmentDescriptorBuilder().withDescription(VALID_DESC_APPOINTMENT1).build();
        EditAppointmentCommand expectedCommand = new EditAppointmentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + INVALID_DESC_APPOINTMENT + DATETIME_APPOINTMENT1 + DESC_APPOINTMENT1;
        descriptor = new EditAppointmentDescriptorBuilder().withDescription(VALID_DESC_APPOINTMENT1)
                .withDateTime(VALID_DATETIME_APPOINTMENT1).build();
        expectedCommand = new EditAppointmentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}

package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_SWIMMING;
import static seedu.address.logic.commands.CommandTestUtil.DESC_SWIMMING;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DEADLINE_MONTH;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DEADLINE_YEAR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_MARCH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_SWIMMING;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.UpdateTaskCommand;

public class UpdateTaskCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
        String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateTaskCommand.MESSAGE_USAGE);

    private UpdateTaskCommandParser parser = new UpdateTaskCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no taskId specified
        assertParseFailure(parser, VALID_DESCRIPTION_SWIMMING, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            UpdateTaskCommand.MESSAGE_USAGE));

        // no field specified
        assertParseFailure(parser, "1", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            UpdateTaskCommand.MESSAGE_NOT_EDITED));

        // no taskId and no field specified
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            UpdateTaskCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + DESC_SWIMMING, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + DESC_SWIMMING, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_DEADLINE_YEAR, MESSAGE_INVALID_FORMAT); // invalid deadline
        assertParseFailure(parser, "1" + INVALID_DEADLINE_MONTH, MESSAGE_INVALID_FORMAT); // invalid deadline
        assertParseFailure(parser, "1" + " d/", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            UpdateTaskCommand.MESSAGE_EMPTY_PARAMETERS)); // empty description
        assertParseFailure(parser, "1" + " t/", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            UpdateTaskCommand.MESSAGE_EMPTY_PARAMETERS)); // empty deadline

        // valid description followed by invalid deadline
        assertParseFailure(parser, "1" + DESC_SWIMMING + INVALID_DEADLINE_YEAR,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateTaskCommand.MESSAGE_USAGE));

        // invalid description followed by valid deadline.
        assertParseFailure(parser, "1" + " d/" + DEADLINE_SWIMMING,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateTaskCommand.MESSAGE_EMPTY_PARAMETERS));

        // multiple invalid values
        assertParseFailure(parser, "1" + " d/" + INVALID_DEADLINE_YEAR,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateTaskCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Integer targetTaskId = 1;
        String userInput = targetTaskId + DESC_SWIMMING + DEADLINE_SWIMMING;

        UpdateTaskCommand.UpdateTaskDescriptor descriptor = new UpdateTaskCommand.UpdateTaskDescriptor();
        descriptor.setDescription(VALID_DESCRIPTION_SWIMMING);
        descriptor.setDeadline(VALID_DEADLINE_MARCH);

        UpdateTaskCommand expectedCommand = new UpdateTaskCommand(targetTaskId, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_failure() {
        Integer targetTaskId = 1;
        String userInput = targetTaskId + DESC_SWIMMING + DESC_SWIMMING;

        assertParseFailure(parser, userInput, MESSAGE_INVALID_FORMAT);
    }

}

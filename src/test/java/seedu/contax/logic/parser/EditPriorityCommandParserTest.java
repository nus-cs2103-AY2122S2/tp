package seedu.contax.logic.parser;

import static seedu.contax.commons.core.Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX;
import static seedu.contax.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.contax.commons.core.Messages.MESSAGE_INVALID_PRIORITY_LEVEL;
import static seedu.contax.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.contax.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.contax.commons.core.index.Index;
import seedu.contax.logic.commands.EditPriorityCommand;
import seedu.contax.logic.commands.EditPriorityCommand.EditPriorityDescriptor;
import seedu.contax.model.appointment.Priority;


/**
 * Parses input arguments and creates a new EditTagCommand object.
 */
public class EditPriorityCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            EditPriorityCommand.MESSAGE_USAGE);

    private EditPriorityCommandParser parser = new EditPriorityCommandParser();

    @Test
    public void parse_invalidFields_failure() {
        // no index specified
        assertParseFailure(parser, "pri/high", MESSAGE_INVALID_FORMAT);

        // no tag name specified
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);

        // not tag name and no index specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5 pri/high", MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);

        // zero index
        assertParseFailure(parser, "0 pri/high", MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);

        // invalid arguments as preamble
        assertParseFailure(parser, "0 some random text", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 pri/123",
                String.format(MESSAGE_INVALID_PRIORITY_LEVEL, EditPriorityCommand.MESSAGE_USAGE));
    }

    @Test
    public void parser_validInput_success() {
        EditPriorityDescriptor editPriorityDescriptor = new EditPriorityDescriptor();
        editPriorityDescriptor.setPriority(Priority.HIGH);
        assertParseSuccess(parser, "1 pri/high",
                new EditPriorityCommand(Index.fromOneBased(1), editPriorityDescriptor));
        editPriorityDescriptor.setPriority(Priority.MEDIUM);
        assertParseSuccess(parser, "1 pri/medium",
                new EditPriorityCommand(Index.fromOneBased(1), editPriorityDescriptor));
        editPriorityDescriptor.setPriority(Priority.LOW);
        assertParseSuccess(parser, "1 pri/low",
                new EditPriorityCommand(Index.fromOneBased(1), editPriorityDescriptor));
        editPriorityDescriptor.setPriority(null);
        assertParseSuccess(parser, "1 pri/none",
                new EditPriorityCommand(Index.fromOneBased(1), editPriorityDescriptor));
    }
}

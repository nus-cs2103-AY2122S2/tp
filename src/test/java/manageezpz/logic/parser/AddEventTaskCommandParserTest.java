package manageezpz.logic.parser;

import static manageezpz.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT_BIND;
import static manageezpz.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static manageezpz.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static manageezpz.logic.commands.CommandTestUtil.VALID_TASK_DESCRIPTION;
import static manageezpz.logic.parser.CommandParserTestUtil.assertParseFailure;
import static manageezpz.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static manageezpz.testutil.TypicalTasks.HOUSE_VISTING;

import org.junit.jupiter.api.Test;

import manageezpz.logic.commands.AddEventTaskCommand;
import manageezpz.model.task.Description;

public class AddEventTaskCommandParserTest {

    private static final String HOUSE_VISTING_INPUT = " desc/House Visiting at/2022-09-15 1800 2000";
    private static final String INVALID_HOUSE_VISTING_INPUT = " desc/ at/2022-09-15 1800 2000";

    private final AddEventTaskCommandParser parser = new AddEventTaskCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + HOUSE_VISTING_INPUT,
                new AddEventTaskCommand(HOUSE_VISTING));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT_BIND, AddEventTaskCommand.MESSAGE_USAGE);

        // missing description prefix
        assertParseFailure(parser, VALID_TASK_DESCRIPTION, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {

        // invalid description
        assertParseFailure(parser, INVALID_HOUSE_VISTING_INPUT,
                Description.MESSAGE_CONSTRAINTS + "\n\n" + AddEventTaskCommand.MESSAGE_USAGE);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + HOUSE_VISTING_INPUT,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT_BIND, AddEventTaskCommand.MESSAGE_USAGE));
    }
}

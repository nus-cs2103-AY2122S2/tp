package manageezpz.logic.parser;

import static manageezpz.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT_BIND;
import static manageezpz.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static manageezpz.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static manageezpz.logic.commands.CommandTestUtil.VALID_TASK_DESCRIPTION;
import static manageezpz.logic.parser.CommandParserTestUtil.assertParseFailure;
import static manageezpz.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static manageezpz.testutil.TypicalTasks.GET_DRINK;

import org.junit.jupiter.api.Test;

import manageezpz.logic.commands.AddDeadlineTaskCommand;
import manageezpz.model.task.Description;

public class AddDeadlineTaskCommandParserTest {
    private static final String GET_DRINK_INPUT = " desc/Get Drink by/2022-05-13 1800";
    private static final String INVALID_GET_DRINK_INPUT = " desc/ by/2022-05-13 1800";
    private final AddDeadlineTaskCommandParser parser = new AddDeadlineTaskCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + GET_DRINK_INPUT,
                new AddDeadlineTaskCommand(GET_DRINK));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT_BIND,
                AddDeadlineTaskCommand.MESSAGE_USAGE);

        // missing description prefix
        assertParseFailure(parser, VALID_TASK_DESCRIPTION, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {

        // invalid description
        assertParseFailure(parser, INVALID_GET_DRINK_INPUT,
                Description.MESSAGE_CONSTRAINTS + "\n\n" + AddDeadlineTaskCommand.MESSAGE_USAGE);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + GET_DRINK_INPUT,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT_BIND, AddDeadlineTaskCommand.MESSAGE_USAGE));
    }
}

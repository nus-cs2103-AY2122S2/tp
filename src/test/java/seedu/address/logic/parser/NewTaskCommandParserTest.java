package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_ASSIGNMENT;
import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_HOMEWORK;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DEADLINE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_ASSIGNMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_HOMEWORK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_ASSIGNMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_HOMEWORK;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.NewTaskCommand;
import seedu.address.model.tasks.Task;
import seedu.address.testutil.TaskBuilder;

public class NewTaskCommandParserTest {
    private NewTaskCommandParser parser = new NewTaskCommandParser();

    @Test
    public void parse_allArgsPresent_success() {
        Task expectedTask =
                new TaskBuilder()
                        .withDeadline(VALID_DEADLINE_ASSIGNMENT)
                        .withDesc(VALID_DESCRIPTION_ASSIGNMENT).build();

        // whitespace included preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + VALID_DESCRIPTION_ASSIGNMENT + DEADLINE_ASSIGNMENT,
                new NewTaskCommand(expectedTask));

        // multiple deadlines - last deadline accepted
        assertParseSuccess(parser,
                PREAMBLE_WHITESPACE + VALID_DESCRIPTION_ASSIGNMENT + DEADLINE_HOMEWORK + DEADLINE_ASSIGNMENT,
                new NewTaskCommand(expectedTask));

    }

    @Test
    public void parse_someArgsMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, NewTaskCommand.MESSAGE_USAGE);

        // missing description
        assertParseFailure(parser, DEADLINE_ASSIGNMENT, expectedMessage);

        // missing deadline
        assertParseFailure(parser, VALID_DESCRIPTION_HOMEWORK, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, NewTaskCommand.MESSAGE_USAGE);
        // wrong description
        assertParseFailure(parser, INVALID_DESCRIPTION + VALID_DEADLINE_HOMEWORK, expectedMessage);

        // wrong deadline
        assertParseFailure(parser, VALID_DESCRIPTION_ASSIGNMENT + INVALID_DEADLINE, expectedMessage);
    }
}

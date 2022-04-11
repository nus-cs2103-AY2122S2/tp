package manageezpz.logic.parser;

import static manageezpz.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT_BIND;
import static manageezpz.logic.commands.CommandTestUtil.INVALID_TODO_DESC_DRINK;
import static manageezpz.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static manageezpz.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static manageezpz.logic.commands.CommandTestUtil.VALID_TASK_DESCRIPTION;
import static manageezpz.logic.commands.CommandTestUtil.VALID_TODO_DESC_READ_BOOK;
import static manageezpz.logic.parser.CommandParserTestUtil.assertParseFailure;
import static manageezpz.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static manageezpz.testutil.TypicalTasks.READ_BOOK;

import org.junit.jupiter.api.Test;

import manageezpz.logic.commands.AddTodoTaskCommand;
import manageezpz.model.task.Description;
import manageezpz.model.task.Todo;
import manageezpz.testutil.TodoBuilder;

public class AddTodoTaskCommandParserTest {
    private final AddTodoTaskCommandParser parser = new AddTodoTaskCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Todo expectedTodo = new TodoBuilder(READ_BOOK).build();

        // Whitespace only preamble && Description Valid
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + VALID_TODO_DESC_READ_BOOK,
                new AddTodoTaskCommand(expectedTodo));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT_BIND, AddTodoTaskCommand.MESSAGE_USAGE);

        // missing description prefix
        assertParseFailure(parser, VALID_TASK_DESCRIPTION, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {

        // invalid description
        assertParseFailure(parser, INVALID_TODO_DESC_DRINK,
                Description.MESSAGE_CONSTRAINTS + "\n\n" + AddTodoTaskCommand.MESSAGE_USAGE);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + VALID_TODO_DESC_READ_BOOK,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT_BIND, AddTodoTaskCommand.MESSAGE_USAGE));
    }
}

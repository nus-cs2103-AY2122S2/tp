package manageezpz.logic.parser;

import static manageezpz.logic.commands.CommandTestUtil.INVALID_BOOLEAN;
import static manageezpz.logic.commands.CommandTestUtil.INVALID_DATE;
import static manageezpz.logic.commands.CommandTestUtil.INVALID_PRIORITY;
import static manageezpz.logic.commands.CommandTestUtil.LIST_DESCRIPTIONS;
import static manageezpz.logic.commands.CommandTestUtil.VALID_BOOLEAN;
import static manageezpz.logic.commands.CommandTestUtil.VALID_DATE;
import static manageezpz.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static manageezpz.logic.commands.CommandTestUtil.VALID_PRIORITY;
import static manageezpz.logic.commands.CommandTestUtil.VALID_TASK_DESCRIPTION;
import static manageezpz.logic.parser.CliSyntax.PREFIX_ASSIGNEES;
import static manageezpz.logic.parser.CliSyntax.PREFIX_DATE;
import static manageezpz.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static manageezpz.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static manageezpz.logic.parser.CliSyntax.PREFIX_IS_MARKED;
import static manageezpz.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static manageezpz.logic.parser.CliSyntax.PREFIX_TASK;
import static manageezpz.logic.parser.CliSyntax.PREFIX_TODO;
import static manageezpz.logic.parser.CommandParserTestUtil.assertParseFailure;
import static manageezpz.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import manageezpz.commons.core.Messages;
import manageezpz.logic.commands.FindCommand;
import manageezpz.logic.commands.FindTaskCommand;
import manageezpz.model.task.Date;
import manageezpz.model.task.Priority;
import manageezpz.model.task.TaskMultiplePredicate;

class FindTaskCommandParserTest {
    static final String EMPTY_DESCRIPTION_ERROR_MESSAGE = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
            FindTaskCommand.EMPTY_KEYWORD + FindCommand.MESSAGE_USAGE);
    static final String INVALID_DATE_ERROR_MESSAGE = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
            FindTaskCommand.INVALID_DATE + FindCommand.MESSAGE_USAGE);
    static final String INVALID_PRIORITY_ERROR_MESSAGE = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
            FindTaskCommand.INVALID_PRIORITY + FindCommand.MESSAGE_USAGE);
    static final String EMPTY_ASSIGNEE_COMMAND_MESSAGE = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
            FindTaskCommand.EMPTY_ASSIGNEE + FindCommand.MESSAGE_USAGE);
    static final String INVALID_BOOLEAN_ERROR_MESSAGE = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
            FindTaskCommand.INVALID_BOOLEAN + FindCommand.MESSAGE_USAGE);
    static final String TODO_DATE_ERROR_MESSAGE = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
            FindTaskCommand.TODO_AND_DATE_OPTION_TOGETHER + FindCommand.MESSAGE_USAGE);

    private FindTaskCommandParser parser = new FindTaskCommandParser();

    @Test
    void findTaskCommandParserTest_noArgumentsAfterTaskType_findTaskCommand() {
        String userInput = String.join(" ", FindCommand.COMMAND_WORD, PREFIX_TASK.toString());
        TaskMultiplePredicate expectedPredicate = new TaskMultiplePredicate(PREFIX_TASK, null, null,
                null, null, null);
        FindTaskCommand expectedCommand = new FindTaskCommand(expectedPredicate);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    void findTaskCommandParserTest_emptyDescription_throwsParseError() {
        String userInput = String.join(" ", FindCommand.COMMAND_WORD, PREFIX_TASK.toString(),
                PREFIX_DESCRIPTION.toString());
        assertParseFailure(parser, userInput, EMPTY_DESCRIPTION_ERROR_MESSAGE);
    }

    @Test
    void findTaskCommandParserTest_withDescription_findTaskCommand() {
        String userInput = String.join(" ", FindCommand.COMMAND_WORD, PREFIX_TASK.toString(),
                PREFIX_DESCRIPTION.toString(), VALID_TASK_DESCRIPTION);
        TaskMultiplePredicate expectedPredicate = new TaskMultiplePredicate(PREFIX_TASK, LIST_DESCRIPTIONS, null,
                null, null, null);
        FindCommand expectedCommand = new FindTaskCommand(expectedPredicate);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    void findTaskCommandParserTest_noDate_throwsParseException() {
        String userInput = String.join(" ", FindCommand.COMMAND_WORD, PREFIX_TASK.toString(),
                PREFIX_DATE.toString());

        assertParseFailure(parser, userInput, INVALID_DATE_ERROR_MESSAGE);
    }

    @Test
    void findTaskCommandParserTest_invalidDate_throwsParseException() {
        String userInput = String.join(" ", FindCommand.COMMAND_WORD, PREFIX_TASK.toString(),
                PREFIX_DATE.toString(), INVALID_DATE);

        assertParseFailure(parser, userInput, INVALID_DATE_ERROR_MESSAGE);
    }

    @Test
    void findTaskCommandParserTest_todoWithDateOption_throwParseError() {
        String userInput = String.join(" ", FindCommand.COMMAND_WORD, PREFIX_TODO.toString(),
                PREFIX_DATE.toString(), VALID_DATE);

        assertParseFailure(parser, userInput, TODO_DATE_ERROR_MESSAGE);
    }

    @Test
    void findTaskCommandParserTest_validDate_findTaskCommand() {
        String userInput = String.join(" ", FindCommand.COMMAND_WORD, PREFIX_TASK.toString(),
                PREFIX_DATE.toString(), VALID_DATE);
        TaskMultiplePredicate expectedPredicate = new TaskMultiplePredicate(PREFIX_TASK, null,
                new Date(VALID_DATE), null, null, null);
        FindTaskCommand expectedCommand = new FindTaskCommand(expectedPredicate);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    void findTaskCommandPaeser_emptyPriority_throwParseException() {
        String userInput = String.join(" ", FindCommand.COMMAND_WORD, PREFIX_TASK.toString(),
                PREFIX_PRIORITY.toString());

        assertParseFailure(parser, userInput, INVALID_PRIORITY_ERROR_MESSAGE);
    }

    @Test
    void findTaskCommandParser_invalidPriority_throwParseException() {
        String userInput = String.join(" ", FindCommand.COMMAND_WORD, PREFIX_TASK.toString(),
                PREFIX_PRIORITY.toString(), INVALID_PRIORITY);

        assertParseFailure(parser, userInput, INVALID_PRIORITY_ERROR_MESSAGE);
    }

    @Test
    void findTaskCommandParser_validPriority_findTaskCommand() {
        String userInput = String.join(" ", FindCommand.COMMAND_WORD, PREFIX_TASK.toString(),
                PREFIX_PRIORITY.toString(), VALID_PRIORITY);
        TaskMultiplePredicate expectedPredicate = new TaskMultiplePredicate(PREFIX_TASK, null,
                null, Priority.valueOf(VALID_PRIORITY), null, null);
        FindTaskCommand expectedCommand = new FindTaskCommand(expectedPredicate);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    void findTaskCommandParser_emptyAssignee_throwParseException() {
        String userInput = String.join(" ", FindCommand.COMMAND_WORD, PREFIX_TASK.toString(),
                PREFIX_ASSIGNEES.toString());

        assertParseFailure(parser, userInput, EMPTY_ASSIGNEE_COMMAND_MESSAGE);
    }

    @Test
    void findTaskCommandParser_haveAssignee_findCommand() {
        String userInput = String.join(" ", FindCommand.COMMAND_WORD, PREFIX_TASK.toString(),
                PREFIX_ASSIGNEES.toString(), VALID_NAME_AMY);
        TaskMultiplePredicate expectedPredicate = new TaskMultiplePredicate(PREFIX_TASK, null, null,
                null, VALID_NAME_AMY, null);
        FindTaskCommand expectedCommand = new FindTaskCommand(expectedPredicate);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    void findTaskCommandParser_emptyBoolean_throwParseError() {
        String userInput = String.join(" ", FindCommand.COMMAND_WORD, PREFIX_TASK.toString(),
                PREFIX_IS_MARKED.toString());

        assertParseFailure(parser, userInput, INVALID_BOOLEAN_ERROR_MESSAGE);
    }

    @Test
    void findTaskCommandParser_invalidBoolean_throwParseException() {
        String userInput = String.join(" ", FindCommand.COMMAND_WORD, PREFIX_TASK.toString(),
                PREFIX_IS_MARKED.toString(), INVALID_BOOLEAN);

        assertParseFailure(parser, userInput, INVALID_BOOLEAN_ERROR_MESSAGE);
    }

    @Test
    void findTaskCommandParser_validBoolean_findCommand() {
        String userInput = String.join(" ", FindCommand.COMMAND_WORD, PREFIX_TASK.toString(),
                PREFIX_IS_MARKED.toString(), VALID_BOOLEAN);
        TaskMultiplePredicate expectedPredicate = new TaskMultiplePredicate(PREFIX_TASK, null,
                null, null, null, Boolean.valueOf(VALID_BOOLEAN));
        FindTaskCommand expectedCommand = new FindTaskCommand(expectedPredicate);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    void findTaskCommandParser_multipleArguments_findCommand() {
        String userInput = String.join(" ", FindCommand.COMMAND_WORD, PREFIX_DEADLINE.toString(),
                PREFIX_DESCRIPTION.toString(), VALID_TASK_DESCRIPTION, PREFIX_DATE.toString(), VALID_DATE,
                PREFIX_PRIORITY.toString(), VALID_PRIORITY, PREFIX_ASSIGNEES.toString(), VALID_NAME_AMY,
                PREFIX_IS_MARKED.toString(), VALID_BOOLEAN);
        TaskMultiplePredicate expectedPredicate = new TaskMultiplePredicate(PREFIX_DEADLINE, LIST_DESCRIPTIONS,
                new Date(VALID_DATE), Priority.valueOf(VALID_PRIORITY), VALID_NAME_AMY,
                Boolean.valueOf(VALID_BOOLEAN));
        FindTaskCommand expectedCommand = new FindTaskCommand(expectedPredicate);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}

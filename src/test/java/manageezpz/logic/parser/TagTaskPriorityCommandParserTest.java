package manageezpz.logic.parser;

import static manageezpz.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static manageezpz.commons.core.Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX;
import static manageezpz.logic.commands.CommandTestUtil.INVALID_TAG_PRIORITY_EMPTY_PREAMBLE;
import static manageezpz.logic.commands.CommandTestUtil.INVALID_TAG_PRIORITY_EMPTY_PRIORITY;
import static manageezpz.logic.commands.CommandTestUtil.INVALID_TAG_PRIORITY_INVALID_INDEX;
import static manageezpz.logic.commands.CommandTestUtil.INVALID_TAG_PRIORITY_INVALID_PRIORITY;
import static manageezpz.logic.commands.CommandTestUtil.INVALID_TAG_PRIORITY_NO_INDEX;
import static manageezpz.logic.commands.CommandTestUtil.INVALID_TAG_PRIORITY_NO_PREFIX;
import static manageezpz.logic.commands.CommandTestUtil.INVALID_TAG_PRIORITY_PREAMBLE;
import static manageezpz.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static manageezpz.logic.commands.CommandTestUtil.VALID_TAG_PRIORITY;
import static manageezpz.logic.commands.CommandTestUtil.VALID_TASK_DESCRIPTION;
import static manageezpz.logic.parser.CommandParserTestUtil.assertParseFailure;
import static manageezpz.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static manageezpz.logic.parser.TagTaskPriorityCommandParser.MESSAGE_EMPTY_PRIORITY;
import static manageezpz.logic.parser.TagTaskPriorityCommandParser.MESSAGE_INVALID_PRIORITY;
import static manageezpz.logic.parser.TagTaskPriorityCommandParser.MESSAGE_TAG_PRIORITY_TO_TASK_INSTRUCTIONS;
import static manageezpz.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import manageezpz.commons.core.index.Index;
import manageezpz.logic.commands.TagTaskPriorityCommand;
import manageezpz.logic.commands.exceptions.CommandException;
import manageezpz.logic.parser.exceptions.ParseException;
import manageezpz.model.Model;
import manageezpz.model.ModelManager;
import manageezpz.model.UserPrefs;
import manageezpz.model.task.Priority;
import manageezpz.model.task.Todo;
import manageezpz.testutil.TodoBuilder;
import manageezpz.testutil.TypicalTasks;


public class TagTaskPriorityCommandParserTest {
    private final TagTaskPriorityCommandParser parser = new TagTaskPriorityCommandParser();

    private final Model model = new ModelManager(TypicalTasks.getTypicalAddressBookTasks(),
            new UserPrefs());

    @Test
    public void parse_allFieldsPresent_success() throws ParseException {

        // Setup Priority
        Priority priority = Priority.valueOf("HIGH");

        Todo expectedTodo = new TodoBuilder().withDescription(VALID_TASK_DESCRIPTION).build();
        expectedTodo.setPriority(priority);

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + VALID_TAG_PRIORITY,
                new TagTaskPriorityCommand(ParserUtil.parseIndex("1"), priority));
    }


    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessageMissingPriorityPrefix = String.format(MESSAGE_TAG_PRIORITY_TO_TASK_INSTRUCTIONS,
                TagTaskPriorityCommand.MESSAGE_USAGE);

        String expectedMessageMissingIndex = MESSAGE_INVALID_COMMAND_FORMAT
                + " \n\n"
                + TagTaskPriorityCommand.MESSAGE_USAGE;

        // missing priority prefix
        assertParseFailure(parser, INVALID_TAG_PRIORITY_NO_PREFIX, expectedMessageMissingPriorityPrefix);

        // missing index
        assertParseFailure(parser, INVALID_TAG_PRIORITY_NO_INDEX, expectedMessageMissingIndex);
    }

    @Test
    public void parse_invalidValue_failure() throws ParseException {

        Index invalidIndex = ParserUtil.parseIndex("999");

        Priority validPriority = Priority.valueOf("HIGH");

        TagTaskPriorityCommand tagTaskPriorityCommandInvalidIndex =
                new TagTaskPriorityCommand(invalidIndex, validPriority);

        String expectedMessageOutOfBoundsIndex = String.format(MESSAGE_INVALID_TASK_DISPLAYED_INDEX,
                TagTaskPriorityCommand.MESSAGE_USAGE);
        String expectedMessageInvalidIndex = ParserUtil.MESSAGE_INVALID_INDEX
                + "\n\n"
                + TagTaskPriorityCommand.MESSAGE_USAGE;
        String expectedMessageInvalidPriority = String.format(MESSAGE_INVALID_PRIORITY,
                TagTaskPriorityCommand.MESSAGE_USAGE);
        String expectedMessageWhitespacePreamble = String.format(MESSAGE_INVALID_COMMAND_FORMAT
                + " " + MESSAGE_TAG_PRIORITY_TO_TASK_INSTRUCTIONS, TagTaskPriorityCommand.MESSAGE_USAGE);
        String expectedMessageEmptyPreamble = MESSAGE_INVALID_COMMAND_FORMAT
                + " \n\n"
                + TagTaskPriorityCommand.MESSAGE_USAGE;
        String expectedMessageEmptyPriority = String.format(MESSAGE_EMPTY_PRIORITY,
                TagTaskPriorityCommand.MESSAGE_USAGE);

        // invalid index (Out of bounds)
        assertThrows(CommandException.class,
                expectedMessageOutOfBoundsIndex, () -> tagTaskPriorityCommandInvalidIndex.execute(model));

        // invalid index (Invalid value)
        assertParseFailure(parser, INVALID_TAG_PRIORITY_INVALID_INDEX, expectedMessageInvalidIndex);

        // invalid Priority (Not Enum Values)
        assertParseFailure(parser, INVALID_TAG_PRIORITY_INVALID_PRIORITY, expectedMessageInvalidPriority);

        // invalid Priority (Empty Priority)
        assertParseFailure(parser, INVALID_TAG_PRIORITY_EMPTY_PRIORITY, expectedMessageEmptyPriority);

        // preamble contains white spaces
        assertParseFailure(parser, INVALID_TAG_PRIORITY_PREAMBLE, expectedMessageWhitespacePreamble);

        // preamble empty
        assertParseFailure(parser, INVALID_TAG_PRIORITY_EMPTY_PREAMBLE, expectedMessageEmptyPreamble);
    }

}

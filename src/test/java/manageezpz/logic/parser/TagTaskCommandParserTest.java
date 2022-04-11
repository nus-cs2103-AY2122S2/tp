package manageezpz.logic.parser;

import static manageezpz.commons.core.Messages.MESSAGE_EMPTY_NAME;
import static manageezpz.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static manageezpz.commons.core.Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX;
import static manageezpz.logic.commands.CommandTestUtil.INVALID_TAG_TASK_EMPTY_NAME;
import static manageezpz.logic.commands.CommandTestUtil.INVALID_TAG_TASK_INVALID_INDEX;
import static manageezpz.logic.commands.CommandTestUtil.INVALID_TAG_TASK_NO_INDEX;
import static manageezpz.logic.commands.CommandTestUtil.INVALID_TAG_TASK_NO_PREFIX;
import static manageezpz.logic.commands.CommandTestUtil.INVALID_TAG_TASK_PREAMBLE;
import static manageezpz.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static manageezpz.logic.commands.CommandTestUtil.VALID_TAG_TASK;
import static manageezpz.logic.commands.CommandTestUtil.VALID_TASK_DESCRIPTION;
import static manageezpz.logic.commands.TagTaskCommand.MESSAGE_NO_SUCH_PERSON;
import static manageezpz.logic.parser.CommandParserTestUtil.assertParseFailure;
import static manageezpz.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static manageezpz.logic.parser.TagTaskCommandParser.MESSAGE_TAG_EMPLOYEE_TO_TASK_INSTRUCTIONS;
import static manageezpz.testutil.Assert.assertThrows;
import static manageezpz.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import manageezpz.commons.core.index.Index;
import manageezpz.logic.commands.TagTaskCommand;
import manageezpz.logic.commands.exceptions.CommandException;
import manageezpz.logic.parser.exceptions.ParseException;
import manageezpz.model.Model;
import manageezpz.model.ModelManager;
import manageezpz.model.UserPrefs;
import manageezpz.model.task.Todo;
import manageezpz.testutil.TodoBuilder;
import manageezpz.testutil.TypicalTasks;


public class TagTaskCommandParserTest {
    private final TagTaskCommandParser parser = new TagTaskCommandParser();

    private final Model model = new ModelManager(TypicalTasks.getTypicalAddressBookTasks(),
            new UserPrefs());

    @Test
    public void parse_allFieldsPresent_success() throws ParseException {

        // Setup Model's UniquePersonList
        model.addPerson(BOB);

        Todo expectedTodo = new TodoBuilder().withDescription(VALID_TASK_DESCRIPTION).build();
        model.tagEmployeeToTask(expectedTodo, BOB);

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + VALID_TAG_TASK,
                new TagTaskCommand(ParserUtil.parseIndex("1"), BOB.getName().toString()));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessageMissingNamePrefix = String.format(MESSAGE_TAG_EMPLOYEE_TO_TASK_INSTRUCTIONS,
                TagTaskCommand.MESSAGE_USAGE);

        String expectedMessageMissingIndex = MESSAGE_INVALID_COMMAND_FORMAT + " \n\n" + TagTaskCommand.MESSAGE_USAGE;

        // missing name prefix
        assertParseFailure(parser, INVALID_TAG_TASK_NO_PREFIX, expectedMessageMissingNamePrefix);

        // missing index
        assertParseFailure(parser, INVALID_TAG_TASK_NO_INDEX, expectedMessageMissingIndex);
    }

    @Test
    public void parse_invalidValue_failure() throws ParseException {

        model.addPerson(BOB);

        Index invalidIndex = ParserUtil.parseIndex("999");
        Index validIndex = ParserUtil.parseIndex("1");

        TagTaskCommand tagTaskCommandInvalidIndex = new TagTaskCommand(invalidIndex, "Bob");
        TagTaskCommand tagTaskCommandInvalidName = new TagTaskCommand(validIndex, "John");

        String expectedMessageOutOfBoundsIndex = String.format(MESSAGE_INVALID_TASK_DISPLAYED_INDEX,
                TagTaskCommand.MESSAGE_USAGE);
        String expectedMessageInvalidIndex = ParserUtil.MESSAGE_INVALID_INDEX + "\n\n" + TagTaskCommand.MESSAGE_USAGE;
        String expectedMessageInvalidName = String.format(MESSAGE_NO_SUCH_PERSON, "John")
                + "\n\n"
                + TagTaskCommand.MESSAGE_USAGE;
        String expectedMessageWhitespacePreamble = String.format(MESSAGE_INVALID_COMMAND_FORMAT + " "
                + MESSAGE_TAG_EMPLOYEE_TO_TASK_INSTRUCTIONS, TagTaskCommand.MESSAGE_USAGE);
        String expectedMessageEmptyName = String.format(MESSAGE_EMPTY_NAME, TagTaskCommand.MESSAGE_USAGE);
        // invalid index (Out of bounds)
        assertThrows(CommandException.class,
                expectedMessageOutOfBoundsIndex, () ->
                        tagTaskCommandInvalidIndex.execute(model));

        // invalid index (Invalid value)
        assertParseFailure(parser, INVALID_TAG_TASK_INVALID_INDEX, expectedMessageInvalidIndex);

        // invalid name (Does not exist in UniquePersonList)
        assertThrows(CommandException.class,
                expectedMessageInvalidName, () ->
                        tagTaskCommandInvalidName.execute(model));

        // invalid name (Empty Name)
        assertParseFailure(parser, INVALID_TAG_TASK_EMPTY_NAME, expectedMessageEmptyName);

        // preamble contains white spaces
        assertParseFailure(parser, INVALID_TAG_TASK_PREAMBLE, expectedMessageWhitespacePreamble);
    }
}

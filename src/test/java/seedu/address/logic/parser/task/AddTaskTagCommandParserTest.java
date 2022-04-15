package seedu.address.logic.parser.task;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.task.AddTaskTagCommand;

class AddTaskTagCommandParserTest {

    private static final String TAG1 = "TAG1";
    private AddTaskTagCommandParser parser = new AddTaskTagCommandParser();


    @Test
    void parse_validArgs_returnsAddTagToTaskCommand() {
        AddTaskTagCommand expectedAddTagToTaskCommand = new AddTaskTagCommand(INDEX_FIRST_PERSON, TAG1);
        assertParseSuccess(parser, " 1 TAG1", expectedAddTagToTaskCommand);
    }

    @Test
    void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser,
                " 1 t/important", // Tags can only be alphanumeric
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskTagCommand.MESSAGE_USAGE));
    }
}

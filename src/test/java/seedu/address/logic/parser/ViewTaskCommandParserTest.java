package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ViewTaskCommand;
import seedu.address.testutil.GroupBuilder;

public class ViewTaskCommandParserTest {
    private ViewTaskCommandParser parser = new ViewTaskCommandParser();
    private final String nonEmptyGroupName = "NUS Fintech Society";

    @Test
    public void parse_groupSpecified_success() {
        // have group name
        String userInput = " " + PREFIX_GROUP_NAME + nonEmptyGroupName;
        ViewTaskCommand expectedCommand = new ViewTaskCommand(new GroupBuilder().build());

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewTaskCommand.MESSAGE_USAGE);

        // no group name
        assertParseFailure(parser, ViewTaskCommand.COMMAND_WORD, expectedMessage);
    }

}

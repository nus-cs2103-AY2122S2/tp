package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ViewContactCommand;
import seedu.address.testutil.GroupBuilder;

public class ViewContactCommandParserTest {
    private ViewContactCommandParser parser = new ViewContactCommandParser();
    private final String nonEmptyGroupName = "NUS Fintech Society";

    @Test
    public void parse_groupSpecified_success() {
        // have group name
        String userInput = " " + PREFIX_GROUP_NAME + nonEmptyGroupName;
        ViewContactCommand expectedCommand = new ViewContactCommand(new GroupBuilder().build());

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewContactCommand.MESSAGE_USAGE);

        // no group name
        assertParseFailure(parser, ViewContactCommand.COMMAND_WORD, expectedMessage);
    }
}

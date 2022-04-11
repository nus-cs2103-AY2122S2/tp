package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindTutorialGroupCommand;
import seedu.address.model.tutorialgroup.TutorialGroupKeywordsPredicate;

public class FindTutorialGroupParserTest {
    private FindTutorialGroupParser parser = new FindTutorialGroupParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindTutorialGroupCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs_throwsPArseException() {
        // no whitespace character to separate module and tutorial group
        assertParseFailure(parser, "cs2100", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindTutorialGroupCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindTutorialGroupCommand expectedFindCommand =
                new FindTutorialGroupCommand(new TutorialGroupKeywordsPredicate("CS2101 G08"));
        assertParseSuccess(parser, "CS2101 G08", expectedFindCommand);

        // with leading and trailing whitespaces
        assertParseSuccess(parser, " \n CS2101 G08 \n", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "CS2101 \n G08", expectedFindCommand);
    }
}

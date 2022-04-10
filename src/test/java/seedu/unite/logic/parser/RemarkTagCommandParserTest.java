package seedu.unite.logic.parser;

import static seedu.unite.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.unite.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.unite.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.unite.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.unite.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.unite.logic.commands.RemarkTagCommand;
import seedu.unite.logic.parser.exceptions.ParseException;
import seedu.unite.model.tag.Remark;
import seedu.unite.model.tag.Tag;

public class RemarkTagCommandParserTest {

    private final RemarkTagCommandParser parser = new RemarkTagCommandParser();
    private final String validTagName = "tagName";
    private final String validRemark = "This is a remark.";
    private final String userInput = " " + PREFIX_TAG + validTagName + " " + PREFIX_REMARK + validRemark;
    private final String userInputWithSpaces = " " + PREFIX_TAG + validTagName + " "
            + PREFIX_REMARK + "     " + validRemark;

    @Test
    public void parse_validArgs_returnsRemarkTagCommand() throws ParseException {
        Tag t = new Tag(validTagName);
        RemarkTagCommand expectedRemarkTagCommand = new RemarkTagCommand(t, new Remark(validRemark));
        parser.parse(" t/tagName r/This is a remark.");
        assertParseSuccess(parser, userInput, expectedRemarkTagCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, userInputWithSpaces, expectedRemarkTagCommand);

    }

    @Test
    public void parse_missingArgs_throwsParseException() {
        //no args
        assertParseFailure(parser, RemarkTagCommand.COMMAND_WORD,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemarkTagCommand.MESSAGE_USAGE));

        //missing tag, valid remark
        assertParseFailure(parser, RemarkTagCommand.COMMAND_WORD + " " + PREFIX_REMARK,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemarkTagCommand.MESSAGE_USAGE));

        //valid tag, missing remark
        assertParseFailure(parser, RemarkTagCommand.COMMAND_WORD + " " + PREFIX_TAG + validTagName,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemarkTagCommand.MESSAGE_USAGE));
    }
}

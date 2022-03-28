package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.COMMENT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMMENT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommentCommand;
import seedu.address.model.person.Comment;

class CommentCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, CommentCommand.MESSAGE_USAGE);
    private CommentCommandParser parser = new CommentCommandParser();

    @Test
    public void parse_invalidArgs_failure() {
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "5 Very rude", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_deleteComment_success() {
        String userInput = "1 c/";
        CommentCommand expectedCommand = new CommentCommand(INDEX_FIRST_PERSON, new Comment(""));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_noIndex_failure() {
        assertParseFailure(parser, " c/Good at leadership", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + COMMENT_DESC;
        CommentCommand expectedCommand = new CommentCommand(targetIndex, new Comment(VALID_COMMENT));
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}

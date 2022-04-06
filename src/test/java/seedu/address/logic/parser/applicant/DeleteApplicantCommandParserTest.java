package seedu.address.logic.parser.applicant;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.FLAG_APPLICANT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPLICANT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.applicant.DeleteApplicantCommand;
import seedu.address.logic.parser.DeleteCommandParser;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteApplicantCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteApplicantCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteApplicantCommandParserTest {

    private DeleteCommandParser parser = new DeleteCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, FLAG_APPLICANT + "1", new DeleteApplicantCommand(INDEX_FIRST_APPLICANT));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, FLAG_APPLICANT + "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteApplicantCommand.MESSAGE_USAGE));
    }
}

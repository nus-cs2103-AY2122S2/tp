package seedu.address.logic.parser.applicant;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_INDEX;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPLICANT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.applicant.DeleteApplicant;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteApplicant code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteApplicant command, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteApplicantParserTest {
    private DeleteApplicantParser parser = new DeleteApplicantParser();

    @Test
    public void parse_validArgs_returnsDeleteApplicant() {
        assertParseSuccess(parser, "1", new DeleteApplicant(INDEX_FIRST_APPLICANT));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_INDEX, DeleteApplicant.MESSAGE_USAGE));
    }
}

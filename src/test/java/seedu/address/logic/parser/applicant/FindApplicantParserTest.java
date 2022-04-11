package seedu.address.logic.parser.applicant;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.applicant.FindApplicant;
import seedu.address.model.applicant.NameApplicantContainsKeywordsPredicate;

class FindApplicantParserTest {

    private FindApplicantParser parser = new FindApplicantParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindApplicant.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindApplicant() {
        // no leading and trailing whitespaces
        FindApplicant expectedFindApplicant =
                new FindApplicant(new NameApplicantContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, "Alice Bob", expectedFindApplicant);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedFindApplicant);
    }

}

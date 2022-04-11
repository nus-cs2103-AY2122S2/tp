package seedu.address.logic.parser.applicant;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SORTING_ATTRIBUTE_DATEAPPLIED;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SORTING_ATTRIBUTE_INTERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SORTING_ATTRIBUTE_JOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.logic.commands.applicant.SortApplicant;

public class SortApplicantParserTest {
    private SortApplicantParser parser = new SortApplicantParser();

    @Test
    void parse_validInput_returnsSortApplicant() {

        String firstUserInput = CommandTestUtil.VALID_INPUT_SORT_BY_DATEAPPLIED;
        String secondUserInput = CommandTestUtil.VALID_INPUT_SORT_BY_INTERVIEW;
        String thirdUserInput = CommandTestUtil.VALID_INPUT_SORT_BY_JOB;

        // Valid applicant sorting attribute: date applied
        assertParseSuccess(parser, firstUserInput, new SortApplicant(VALID_SORTING_ATTRIBUTE_DATEAPPLIED));

        // Valid applicant sorting attribute: interview date
        assertParseSuccess(parser, secondUserInput, new SortApplicant(VALID_SORTING_ATTRIBUTE_INTERVIEW));

        // Valid applicant sorting attribute: job id
        assertParseSuccess(parser, thirdUserInput, new SortApplicant(VALID_SORTING_ATTRIBUTE_JOB));

    }

    @Test
    void parse_invalidInput_throwsParseException() {

        // invalid sorting attribute
        assertParseFailure(parser, CommandTestUtil.INVALID_ATTRIBUTE_SORT_BY_NAME,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortApplicant.MESSAGE_USAGE));

        // invalid prefix
        assertParseFailure(parser, CommandTestUtil.INVALID_PREFIX_SORT_BY_DATE_APPLIED,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortApplicant.MESSAGE_USAGE));

    }

    @Test
    void parse_invalidArgs_throwsParseException() {

        // invalid argument
        assertParseFailure(parser, "invalid argument", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SortApplicant.MESSAGE_USAGE));

        // empty argument
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SortApplicant.MESSAGE_USAGE));

    }
}

package seedu.address.logic.parser.applicant;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.logic.commands.applicant.MarkApplicant;
import seedu.address.model.applicant.ApplicantStatus;
import seedu.address.testutil.TypicalIndexes;

public class MarkApplicantParserTest {
    private MarkApplicantParser parser = new MarkApplicantParser();

    @Test
    void parse_validArgs_returnsMarkApplicant() {
        Index firstTargetIndex = TypicalIndexes.INDEX_THIRD_APPLICANT;
        Index secondTargetIndex = TypicalIndexes.INDEX_FIRST_APPLICANT;
        String firstUserInput = firstTargetIndex.getOneBased() + CommandTestUtil.APPLICANT_STATUS_DESC;
        String secondUserInput = secondTargetIndex.getOneBased() + CommandTestUtil.APPLICANT_STATUS_DESC_TWO;
        ApplicantStatus firstStatus = new ApplicantStatus(CommandTestUtil.VALID_APPLICANT_STATUS);
        ApplicantStatus secondStatus = new ApplicantStatus(CommandTestUtil.VALID_APPLICANT_STATUS_TWO);

        // Valid applicant status accepted
        assertParseSuccess(parser, firstUserInput, new MarkApplicant(firstTargetIndex, firstStatus));
        // Valid applicant status rejected
        assertParseSuccess(parser, secondUserInput, new MarkApplicant(secondTargetIndex, secondStatus));
    }

    @Test
    void parse_invalidStatus_throwsParseException() {
        // invalid applicant status
        assertParseFailure(parser, "1" + CommandTestUtil.INVALID_APPLICANT_STATUS, ApplicantStatus.MESSAGE_CONSTRAINTS);
    }

    @Test
    void parse_invalidArgs_throwsParseException() {
        // invalid argument of command markapplicant
        assertParseFailure(parser, "invalid argument", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                MarkApplicant.MESSAGE_USAGE));
        // empty argument of command markapplicant
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                MarkApplicant.MESSAGE_USAGE));
    }
}

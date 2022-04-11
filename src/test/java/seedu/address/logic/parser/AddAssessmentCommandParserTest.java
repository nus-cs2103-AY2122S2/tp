package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.AssessmentTestUtil.ASSESSMENT_NAME_DESC_OP1;
import static seedu.address.logic.commands.AssessmentTestUtil.ASSESSMENT_NAME_DESC_OP2;
import static seedu.address.logic.commands.AssessmentTestUtil.FULL_MARK_DESC_OP1;
import static seedu.address.logic.commands.AssessmentTestUtil.INVALID_FULL_MARK_DESC;
import static seedu.address.logic.commands.AssessmentTestUtil.INVALID_WEIGHTAGE_DESC;
import static seedu.address.logic.commands.AssessmentTestUtil.WEIGHTAGE_DESC_OP1;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalAssessments.OP1;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddAssessmentCommand;
import seedu.address.model.assessment.Assessment;
import seedu.address.model.assessment.FullMark;
import seedu.address.model.assessment.Weightage;
import seedu.address.testutil.AssessmentBuilder;

public class AddAssessmentCommandParserTest {
    private AddAssessmentCommandParser parser = new AddAssessmentCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Assessment expectedAssessment = new AssessmentBuilder(OP1).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + ASSESSMENT_NAME_DESC_OP1
                + WEIGHTAGE_DESC_OP1 + FULL_MARK_DESC_OP1, new AddAssessmentCommand(expectedAssessment));

        // multiple names - last name accepted
        assertParseSuccess(parser, ASSESSMENT_NAME_DESC_OP2 + ASSESSMENT_NAME_DESC_OP1
                + WEIGHTAGE_DESC_OP1 + FULL_MARK_DESC_OP1, new AddAssessmentCommand(expectedAssessment));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAssessmentCommand.MESSAGE_USAGE);

        // missing assessment name prefix
        assertParseFailure(parser, WEIGHTAGE_DESC_OP1 + FULL_MARK_DESC_OP1, expectedMessage);

        // missing weightage prefix
        assertParseFailure(parser, ASSESSMENT_NAME_DESC_OP1 + FULL_MARK_DESC_OP1, expectedMessage);

        // missing full mark prefix
        assertParseFailure(parser, ASSESSMENT_NAME_DESC_OP1 + WEIGHTAGE_DESC_OP1, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid weightage
        assertParseFailure(parser, ASSESSMENT_NAME_DESC_OP1 + INVALID_WEIGHTAGE_DESC + FULL_MARK_DESC_OP1,
                Weightage.MESSAGE_CONSTRAINTS);

        // invalid full mark
        assertParseFailure(parser, ASSESSMENT_NAME_DESC_OP1 + WEIGHTAGE_DESC_OP1 + INVALID_FULL_MARK_DESC,
                FullMark.MESSAGE_CONSTRAINTS);
    }
}

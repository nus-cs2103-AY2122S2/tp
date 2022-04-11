package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.AssessmentTestUtil.ASSESSMENT_NAME_DESC_OP1;
import static seedu.address.logic.commands.AssessmentTestUtil.INVALID_SCORE_DESC;
import static seedu.address.logic.commands.AssessmentTestUtil.SCORE_DESC_OP1;
import static seedu.address.logic.commands.AssessmentTestUtil.SCORE_DESC_OP2;
import static seedu.address.logic.commands.AssessmentTestUtil.VALID_ASSESSMENT_NAME_OP1;
import static seedu.address.logic.commands.AssessmentTestUtil.VALID_SCORE_OP1;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.StudentTestUtil.NAME_DESC_AARON;
import static seedu.address.logic.commands.StudentTestUtil.VALID_NAME_AARON;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.GradeCommand;
import seedu.address.model.assessment.AssessmentName;
import seedu.address.model.assessment.Score;
import seedu.address.model.person.Name;

public class GradeCommandParserTest {
    private GradeCommandParser parser = new GradeCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        AssessmentName assessmentName = new AssessmentName(VALID_ASSESSMENT_NAME_OP1);
        Name studentName = new Name(VALID_NAME_AARON);

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + ASSESSMENT_NAME_DESC_OP1 + NAME_DESC_AARON
                        + SCORE_DESC_OP1, new GradeCommand(assessmentName, studentName, VALID_SCORE_OP1));

        // multiple scores - last score accepted
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + ASSESSMENT_NAME_DESC_OP1 + SCORE_DESC_OP2
                + NAME_DESC_AARON + SCORE_DESC_OP1, new GradeCommand(assessmentName, studentName, VALID_SCORE_OP1));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, GradeCommand.MESSAGE_USAGE);

        // missing assessment name prefix
        assertParseFailure(parser, NAME_DESC_AARON + SCORE_DESC_OP1, expectedMessage);

        // missing name prefix
        assertParseFailure(parser, ASSESSMENT_NAME_DESC_OP1 + SCORE_DESC_OP1, expectedMessage);

        // missing score prefix
        assertParseFailure(parser, ASSESSMENT_NAME_DESC_OP1 + NAME_DESC_AARON, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid score
        assertParseFailure(parser, ASSESSMENT_NAME_DESC_OP1 + NAME_DESC_AARON + INVALID_SCORE_DESC,
                Score.MESSAGE_CONSTRAINTS);
    }
}

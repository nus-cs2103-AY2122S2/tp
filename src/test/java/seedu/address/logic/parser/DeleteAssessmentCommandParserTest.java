package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.AssessmentTestUtil.ASSESSMENT_NAME_DESC_OP1;
import static seedu.address.logic.commands.AssessmentTestUtil.ASSESSMENT_NAME_DESC_OP2;
import static seedu.address.logic.commands.AssessmentTestUtil.INVALID_ASSESSMENT_NAME_DESC;
import static seedu.address.logic.commands.AssessmentTestUtil.VALID_ASSESSMENT_NAME_OP1;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteAssessmentCommand;
import seedu.address.model.assessment.AssessmentName;

public class DeleteAssessmentCommandParserTest {
    private DeleteAssessmentCommandParser parser = new DeleteAssessmentCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        AssessmentName assessmentName = new AssessmentName(VALID_ASSESSMENT_NAME_OP1);

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + ASSESSMENT_NAME_DESC_OP1,
                new DeleteAssessmentCommand(assessmentName));

        // multiple names - last name accepted
        assertParseSuccess(parser, ASSESSMENT_NAME_DESC_OP2 + ASSESSMENT_NAME_DESC_OP1,
                new DeleteAssessmentCommand(assessmentName));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        // missing assessment name prefix
        assertParseFailure(parser, PREAMBLE_WHITESPACE, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteAssessmentCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid assessment name
        assertParseFailure(parser, INVALID_ASSESSMENT_NAME_DESC, AssessmentName.MESSAGE_CONSTRAINTS);
    }
}

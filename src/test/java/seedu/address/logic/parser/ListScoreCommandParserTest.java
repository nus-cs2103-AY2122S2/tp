package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.AssessmentTestUtil.ASSESSMENT_NAME_DESC_OP1;
import static seedu.address.logic.commands.TutorialTestUtil.TUTORIALNAME_DESC_T01;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalAssessments.OP1;
import static seedu.address.testutil.TypicalTutorials.T01;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ListScoreCommand;
import seedu.address.model.assessment.AssessmentName;
import seedu.address.model.tutorial.TutorialName;
import seedu.address.testutil.AssessmentBuilder;
import seedu.address.testutil.TutorialBuilder;

public class ListScoreCommandParserTest {
    private ListScoreCommandParser parser = new ListScoreCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        TutorialName tutorialName = new TutorialBuilder(T01).build().getTutorialName();
        AssessmentName assessmentName = new AssessmentBuilder(OP1).build().getAssessmentName();

        // all fields present
        assertParseSuccess(parser, TUTORIALNAME_DESC_T01 + ASSESSMENT_NAME_DESC_OP1,
                new ListScoreCommand(tutorialName, assessmentName));
    }

    @Test
    public void parse_tutorialNameFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListScoreCommand.MESSAGE_USAGE);

        // missing tutorial name field
        assertParseFailure(parser, ASSESSMENT_NAME_DESC_OP1, expectedMessage);
    }

    @Test
    public void parse_assessmentNameFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListScoreCommand.MESSAGE_USAGE);

        // missing assessment name field
        assertParseFailure(parser, TUTORIALNAME_DESC_T01, expectedMessage);
    }

    @Test
    public void parse_allFieldsMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListScoreCommand.MESSAGE_USAGE);

        // empty string
        assertParseFailure(parser, "", expectedMessage);

        // white space
        assertParseFailure(parser, " ", expectedMessage);
    }

}

package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.StudentTestUtil.INVALID_STUDENT_ID_ADAM;
import static seedu.address.logic.commands.StudentTestUtil.VALID_STUDENT_ID_AARON;
import static seedu.address.logic.commands.TutorialTestUtil.DAY_DESC_T01;
import static seedu.address.logic.commands.TutorialTestUtil.INVALID_TUTORIAL_NAME;
import static seedu.address.logic.commands.TutorialTestUtil.INVALID_WEEK_DESC;
import static seedu.address.logic.commands.TutorialTestUtil.TIME_DESC_T01;
import static seedu.address.logic.commands.TutorialTestUtil.TUTORIALNAME_DESC_T01;
import static seedu.address.logic.commands.TutorialTestUtil.VENUE_DESC_T01;
import static seedu.address.logic.commands.TutorialTestUtil.WEEKS_DESC_T01;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalStudents.AARON;
import static seedu.address.testutil.TypicalTutorials.T01;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.UnmarkAttendanceCommand;
import seedu.address.model.person.NusNetId;
import seedu.address.model.tutorial.TutorialName;
import seedu.address.testutil.StudentBuilder;
import seedu.address.testutil.TutorialBuilder;

public class UnmarkAttendanceCommandParserTest {
    private UnmarkAttendanceCommandParser parser = new UnmarkAttendanceCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        TutorialName expectedTutorialName = new TutorialBuilder(T01).build().getTutorialName();
        NusNetId expectStudentId = new StudentBuilder(AARON).build().getStudentId();
        int expectedWeeks = 13;

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + TUTORIALNAME_DESC_T01 + " id/" + VALID_STUDENT_ID_AARON
                + WEEKS_DESC_T01,
                new UnmarkAttendanceCommand(expectedTutorialName, expectStudentId, expectedWeeks, false));

        // studentId not given (multiple attendance marking)
        assertParseSuccess(parser, TUTORIALNAME_DESC_T01 + WEEKS_DESC_T01,
                new UnmarkAttendanceCommand(expectedTutorialName, null, expectedWeeks, true));

        // studentId given
        assertParseSuccess(parser, TUTORIALNAME_DESC_T01 + " id/" + VALID_STUDENT_ID_AARON
                        + WEEKS_DESC_T01,
                new UnmarkAttendanceCommand(expectedTutorialName, expectStudentId, expectedWeeks, false));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnmarkAttendanceCommand.MESSAGE_USAGE);

        // missing tutorial name prefix
        assertParseFailure(parser, WEEKS_DESC_T01,
                expectedMessage);

        // missing week prefix
        assertParseFailure(parser, TUTORIALNAME_DESC_T01,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid tutorial name
        assertParseFailure(parser, INVALID_TUTORIAL_NAME + " id/" + VALID_STUDENT_ID_AARON
                + WEEKS_DESC_T01, TutorialName.MESSAGE_CONSTRAINTS);

        // invalid student id
        assertParseFailure(parser, TUTORIALNAME_DESC_T01 + " id/" + INVALID_STUDENT_ID_ADAM
                + WEEKS_DESC_T01, NusNetId.MESSAGE_CONSTRAINTS);

        // invalid week
        assertParseFailure(parser, TUTORIALNAME_DESC_T01 + " id/" + VALID_STUDENT_ID_AARON
                + INVALID_WEEK_DESC, "Weeks should be presented in positive integers between 1-60!");

        // multiple invalid values, only first invalid velue reported
        assertParseFailure(parser, INVALID_TUTORIAL_NAME + " id/" + INVALID_STUDENT_ID_ADAM
                + WEEKS_DESC_T01, TutorialName.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + TUTORIALNAME_DESC_T01 + DAY_DESC_T01 + TIME_DESC_T01
                + VENUE_DESC_T01 + WEEKS_DESC_T01, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                UnmarkAttendanceCommand.MESSAGE_USAGE));

    }
}

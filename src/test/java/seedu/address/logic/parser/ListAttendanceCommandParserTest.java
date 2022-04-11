package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.StudentTestUtil.VALID_STUDENT_ID_AARON;
import static seedu.address.logic.commands.TutorialTestUtil.TUTORIALNAME_DESC_T01;
import static seedu.address.logic.commands.TutorialTestUtil.VALID_WEEK_DESC;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalStudents.AARON;
import static seedu.address.testutil.TypicalTutorials.T01;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ListAttendanceCommand;
import seedu.address.model.person.NusNetId;
import seedu.address.model.tutorial.TutorialName;
import seedu.address.testutil.StudentBuilder;
import seedu.address.testutil.TutorialBuilder;

public class ListAttendanceCommandParserTest {
    private ListAttendanceCommandParser parser = new ListAttendanceCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        TutorialName tutorialName = new TutorialBuilder(T01).build().getTutorialName();
        NusNetId expectStudentId = new StudentBuilder(AARON).build().getStudentId();
        int validWeek = 1;
        int studentRelatedWeek = -1;

        // tutorial fields present
        assertParseSuccess(parser, TUTORIALNAME_DESC_T01 + VALID_WEEK_DESC,
                new ListAttendanceCommand(tutorialName, null, validWeek));

        // student field present
        assertParseSuccess(parser, " id/" + VALID_STUDENT_ID_AARON,
                new ListAttendanceCommand(null, expectStudentId, studentRelatedWeek));
    }

    @Test
    public void parse_tutorialNameFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListAttendanceCommand.MESSAGE_USAGE);

        // student field keyed in
        assertParseFailure(parser, " id/" + VALID_STUDENT_ID_AARON + VALID_WEEK_DESC, expectedMessage);

        // only week keyed in
        assertParseFailure(parser, VALID_WEEK_DESC, expectedMessage);
    }

    @Test
    public void parse_weekFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListAttendanceCommand.MESSAGE_USAGE);

        // student field keyed in
        assertParseFailure(parser, " id/" + VALID_STUDENT_ID_AARON + TUTORIALNAME_DESC_T01, expectedMessage);

        // only tutorialName keyed in
        assertParseFailure(parser, TUTORIALNAME_DESC_T01, expectedMessage);
    }

    @Test
    public void parse_allFieldsMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListAttendanceCommand.MESSAGE_USAGE);

        // empty string
        assertParseFailure(parser, "", expectedMessage);

        // white space
        assertParseFailure(parser, " ", expectedMessage);
    }
}

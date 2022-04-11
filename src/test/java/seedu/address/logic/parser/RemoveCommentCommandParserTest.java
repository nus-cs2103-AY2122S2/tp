package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.StudentTestUtil.INVALID_STUDENT_ID_ADAM;
import static seedu.address.logic.commands.StudentTestUtil.VALID_STUDENT_ID_AARON;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalStudents.AARON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.RemoveCommentCommand;
import seedu.address.model.person.NusNetId;
import seedu.address.testutil.StudentBuilder;

public class RemoveCommentCommandParserTest {
    private RemoveCommentCommandParser parser = new RemoveCommentCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        NusNetId expectStudentId = new StudentBuilder(AARON).build().getStudentId();

        // whitespace only preamble
        assertParseSuccess(parser, " id/" + VALID_STUDENT_ID_AARON,
                new RemoveCommentCommand(expectStudentId));

        // studentId given
        assertParseSuccess(parser, " id/" + VALID_STUDENT_ID_AARON,
                new RemoveCommentCommand(expectStudentId));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RemoveCommentCommand.MESSAGE_USAGE);

        // missing studentId prefix
        assertParseFailure(parser, "",
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid student id
        assertParseFailure(parser, " id/" + INVALID_STUDENT_ID_ADAM,
                NusNetId.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + " id/" + VALID_STUDENT_ID_AARON,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveCommentCommand.MESSAGE_USAGE));

    }
}

package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.StudentTestUtil.INVALID_STUDENT_ID_ADAM;
import static seedu.address.logic.commands.StudentTestUtil.VALID_STUDENT_ID_AARON;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalStudents.AARON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommentCommand;
import seedu.address.model.attendance.Comment;
import seedu.address.model.person.NusNetId;
import seedu.address.testutil.StudentBuilder;

public class AddCommentCommandParserTest {
    private AddCommentCommandParser parser = new AddCommentCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        NusNetId expectedStudentId = new StudentBuilder(AARON).build().getStudentId();
        Comment expectedComment = new Comment("comment");

        // whitespace only preamble
        assertParseSuccess(parser, " id/" + VALID_STUDENT_ID_AARON + " msg/comment",
                new AddCommentCommand(expectedStudentId, expectedComment));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddCommentCommand.MESSAGE_USAGE);

        // missing studentId prefix
        assertParseFailure(parser, " msg/comment", expectedMessage);

        // missing comment prefix
        assertParseFailure(parser, " id/" + VALID_STUDENT_ID_AARON, expectedMessage);

        // missing all prefixes
        assertParseFailure(parser, " msg/comment", expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid student id
        assertParseFailure(parser, " id/" + INVALID_STUDENT_ID_ADAM + " msg/comment",
                NusNetId.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + " id/" + VALID_STUDENT_ID_AARON + " msg/comment",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommentCommand.MESSAGE_USAGE));

    }
}

package seedu.address.logic.parser;



import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.StudentTestUtil.ID_DESC_AARON;
import static seedu.address.logic.commands.StudentTestUtil.INVALID_NAME_DESC_ADAM;
import static seedu.address.logic.commands.StudentTestUtil.INVALID_STUDENT_ID_DESC_ADAM;
import static seedu.address.logic.commands.StudentTestUtil.NAME_DESC_AARON;
import static seedu.address.logic.commands.StudentTestUtil.VALID_NAME_AARON;
import static seedu.address.logic.commands.StudentTestUtil.VALID_STUDENT_ID_AARON;
import static seedu.address.logic.commands.StudentTestUtil.VALID_TUTORIAL_NAME_AARON_DESC;
import static seedu.address.logic.commands.TutorialTestUtil.INVALID_TUTORIAL_NAME;
import static seedu.address.logic.commands.TutorialTestUtil.VALID_TUTORIAL_NAME_TG1;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddStudentCommand;
import seedu.address.model.person.Name;
import seedu.address.model.person.NusNetId;
import seedu.address.model.tutorial.TutorialName;



public class AddStudentCommandParserTest {
    private AddStudentCommandParser parser = new AddStudentCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Name expectedName = new Name(VALID_NAME_AARON);
        NusNetId expectedId = new NusNetId(VALID_STUDENT_ID_AARON);
        TutorialName expectedTutorialName = new TutorialName(VALID_TUTORIAL_NAME_TG1);

        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_AARON + ID_DESC_AARON
                + VALID_TUTORIAL_NAME_AARON_DESC,
                new AddStudentCommand(expectedName, expectedId, expectedTutorialName));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStudentCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, PREAMBLE_WHITESPACE + ID_DESC_AARON + VALID_TUTORIAL_NAME_AARON_DESC,
                expectedMessage);

        // missing student id
        assertParseFailure(parser, PREAMBLE_WHITESPACE + NAME_DESC_AARON + VALID_TUTORIAL_NAME_AARON_DESC,
                expectedMessage);

        // missing tutorial name
        assertParseFailure(parser, PREAMBLE_WHITESPACE + NAME_DESC_AARON + ID_DESC_AARON,
                expectedMessage);
    }


    @Test
    public void parse_nonEmptyPreamble_failure() {
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_AARON + ID_DESC_AARON
                        + VALID_TUTORIAL_NAME_AARON_DESC,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStudentCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidValue_failure() {

        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC_ADAM + ID_DESC_AARON
                        + VALID_TUTORIAL_NAME_AARON_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // invalid student id
        assertParseFailure(parser, PREAMBLE_WHITESPACE + NAME_DESC_AARON + INVALID_STUDENT_ID_DESC_ADAM
                        + VALID_TUTORIAL_NAME_AARON_DESC,
                NusNetId.MESSAGE_CONSTRAINTS);

        // invalid tutorial
        assertParseFailure(parser, PREAMBLE_WHITESPACE + NAME_DESC_AARON + ID_DESC_AARON
                        + INVALID_TUTORIAL_NAME,
                TutorialName.MESSAGE_CONSTRAINTS);
    }
}

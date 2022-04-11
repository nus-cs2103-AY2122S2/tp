package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.StudentTestUtil.INVALID_NAME_ADAM;
import static seedu.address.logic.commands.StudentTestUtil.VALID_TUTORIAL_NAME_AARON_DESC;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalStudents.AARON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ListStudentCommand;
import seedu.address.model.tutorial.TutorialName;
import seedu.address.testutil.StudentBuilder;

public class ListStudentCommandParserTest {
    private ListStudentCommandParser parser = new ListStudentCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        TutorialName expectedTutName = new StudentBuilder(AARON).build().getTutorialName();
        Index expectedIndex = INDEX_FIRST;
        String empty = "";

        // whitespace only preamble - tutorial name
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + VALID_TUTORIAL_NAME_AARON_DESC,
                new ListStudentCommand(null, expectedTutName));

        // whitespace only preamble - index
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + INDEX_FIRST.getOneBased(),
                new ListStudentCommand(expectedIndex, null));

        // valid index
        assertParseSuccess(parser, "" + INDEX_FIRST.getOneBased(),
                new ListStudentCommand(expectedIndex, null));

        // valid tutorial name
        assertParseSuccess(parser, VALID_TUTORIAL_NAME_AARON_DESC,
                new ListStudentCommand(null, expectedTutName));

        // no field passed
        assertParseSuccess(parser, empty, new ListStudentCommand(null, null));

    }

    @Test
    public void parse_wrongFieldsPresent_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListStudentCommand.MESSAGE_USAGE);
        assertParseFailure(parser, INVALID_NAME_ADAM, expectedMessage);
    }
}

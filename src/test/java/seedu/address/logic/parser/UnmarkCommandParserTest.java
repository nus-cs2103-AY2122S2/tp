package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ID_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ID_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INDEX_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ID_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INDEX;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UnmarkCommand;
import seedu.address.model.person.StudentId;
import seedu.address.testutil.TypicalPersons;

public class UnmarkCommandParserTest {

    private static final String MARK_MESSAGE_INVALID_COMMAND_FORMAT = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            UnmarkCommand.MESSAGE_USAGE);

    private final StudentId studentIdAlice = TypicalPersons.ALICE.getStudentId();

    private final Index indexOne = Index.fromOneBased(1);

    private UnmarkCommandParser parser = new UnmarkCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "   ", MARK_MESSAGE_INVALID_COMMAND_FORMAT);
    }

    @Test
    public void parse_missingStudentIdPrefix_throwsParseException() {
        String userInputMissingStudentIdPrefix = VALID_ID_AMY + INDEX_DESC;
        assertParseFailure(parser, userInputMissingStudentIdPrefix, MARK_MESSAGE_INVALID_COMMAND_FORMAT);
    }

    @Test
    public void parse_missingIndexPrefix_throwsParseException() {
        String userInputMissingIndexPrefix = ID_DESC_AMY + VALID_INDEX;
        assertParseFailure(parser, userInputMissingIndexPrefix, MARK_MESSAGE_INVALID_COMMAND_FORMAT);
    }

    @Test
    public void parse_invalidStudentId_throwsParseException() {
        String userInputInvalidStudentId = INVALID_ID_DESC + INDEX_DESC;
        assertParseFailure(parser, userInputInvalidStudentId, MARK_MESSAGE_INVALID_COMMAND_FORMAT);
    }

    @Test
    public void parse_invalidIndex_throwsParseException() {
        String userInputInvalidIndex = ID_DESC_AMY + INVALID_INDEX;
        assertParseFailure(parser, userInputInvalidIndex, MARK_MESSAGE_INVALID_COMMAND_FORMAT);
    }

    @Test
    public void parse_allFieldsPresent_success() {
        UnmarkCommand expectedUnmarkCommand = new UnmarkCommand(studentIdAlice, indexOne);
        String userInputValid = ID_DESC_BOB + INDEX_DESC;
        assertParseSuccess(parser, userInputValid, expectedUnmarkCommand);
    }
}

package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ID_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INDEX_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ID_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INDEX;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MarkCommand;
import seedu.address.model.person.StudentId;
import seedu.address.testutil.TypicalPersons;

public class MarkCommandParserTest {

    private static final String MARK_MESSAGE_INVALID_COMMAND_FORMAT = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            MarkCommand.MESSAGE_USAGE);

    private final StudentId studentIdAlice = TypicalPersons.ALICE.getStudentId();
    private final StudentId studentIdBob = new StudentId(VALID_ID_BOB);
    private final StudentId notPresentStudentId = new StudentId("A0000000Z");

    private final Index indexOne = Index.fromOneBased(1);
    private final Index indexTwo = Index.fromOneBased(2);
    private final Index invalidIndex = Index.fromZeroBased(2000);

    private MarkCommandParser parser = new MarkCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "   ", MARK_MESSAGE_INVALID_COMMAND_FORMAT);
    }

    @Test
    public void parse_missingStudentIdPrefix_throwsParseException() {
        String userInputMissingStudentIdPrefix = VALID_ID_BOB + INDEX_DESC;
        assertParseFailure(parser, userInputMissingStudentIdPrefix, MARK_MESSAGE_INVALID_COMMAND_FORMAT);
    }

    @Test
    public void parse_missingIndexPrefix_throwsParseException() {
        String userInputMissingIndexPrefix = ID_DESC_BOB + VALID_INDEX;
        assertParseFailure(parser, userInputMissingIndexPrefix, MARK_MESSAGE_INVALID_COMMAND_FORMAT);
    }

    @Test
    public void parse_invalidStudentId_throwsParseException() {
        String userInputInvalidStudentId = INVALID_ID_DESC + INDEX_DESC;
        assertParseFailure(parser, userInputInvalidStudentId, MARK_MESSAGE_INVALID_COMMAND_FORMAT);

    }

    @Test
    public void parse_invalidIndex_throwsParseException() {
        String userInputInvalidIndex = ID_DESC_BOB + INVALID_INDEX;
        assertParseFailure(parser, userInputInvalidIndex, MARK_MESSAGE_INVALID_COMMAND_FORMAT);
    }

    @Test
    public void parse_allFieldsPresent_success() {
        MarkCommand expectedMarkCommand = new MarkCommand(studentIdBob, indexOne);
        String userInputValid = ID_DESC_BOB + INDEX_DESC;
        assertParseSuccess(parser, userInputValid, expectedMarkCommand);
    }

}

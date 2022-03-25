package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LAB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LABMARK;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.GradeLabCommand;
import seedu.address.model.lab.LabMark;

public class GradeLabCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, GradeLabCommand.MESSAGE_USAGE);
    private static final String VALID_LABNUMBER = "1 ";
    private static final String VALID_LAB_DESC = PREFIX_LAB + VALID_LABNUMBER;
    private static final String VALID_INDEX = "1 ";

    private static final String VALID_LABMARK = "10 ";
    private static final String VALID_LABMARK_DESC = PREFIX_LABMARK + VALID_LABMARK;

    private GradeLabCommandParser parser = new GradeLabCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // nothing specified
        assertParseFailure(parser, " ", MESSAGE_INVALID_FORMAT);

        // no index specified
        assertParseFailure(parser, VALID_LAB_DESC + VALID_LABMARK_DESC, MESSAGE_INVALID_FORMAT);

        // no lab specified
        assertParseFailure(parser, VALID_INDEX + VALID_LABMARK_DESC, MESSAGE_INVALID_FORMAT);

        // no marks specified
        assertParseFailure(parser, VALID_INDEX + VALID_LAB_DESC, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid index
        assertParseFailure(parser, "0 " + VALID_LAB_DESC + VALID_LABMARK_DESC, MESSAGE_INVALID_FORMAT);

        // invalid lab
        assertParseFailure(parser, VALID_INDEX + "l/a " + VALID_LABMARK_DESC, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, VALID_INDEX + "l/-1 " + VALID_LABMARK_DESC, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, VALID_INDEX + "l/0001 " + VALID_LABMARK_DESC, MESSAGE_INVALID_FORMAT);

        // invalid marks
        assertParseFailure(parser, VALID_INDEX + VALID_LAB_DESC + "m/-1", MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, VALID_INDEX + VALID_LAB_DESC + "m/01", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        GradeLabCommand expectedCommand = new GradeLabCommand(
                Index.fromOneBased(Integer.parseInt(VALID_INDEX.trim())), Integer.parseInt(VALID_LABNUMBER.trim()),
                new LabMark(VALID_LABMARK.trim()));

        assertParseSuccess(parser, VALID_INDEX + VALID_LAB_DESC, expectedCommand);
    }
}

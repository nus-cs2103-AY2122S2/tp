package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LAB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LABMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LABSTATUS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditLabCommand;
import seedu.address.model.lab.Lab;
import seedu.address.model.lab.LabMark;
import seedu.address.model.lab.LabStatus;

public class EditLabCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditLabCommand.MESSAGE_USAGE);
    private static final String VALID_INDEX = "1 ";
    private static final String VALID_LAB = "1 ";
    private static final String VALID_LABMARK = "10 ";

    private static final String LABSTATUS_GRADED = "g ";
    private static final String LABSTATUS_SUBMITTED = "s ";
    private static final String LABSTATUS_UNSUBMITTED = "u ";

    private static final String VALID_LAB_DESC = PREFIX_LAB + VALID_LAB;
    private static final String VALID_LABMARK_DESC = PREFIX_LABMARK + VALID_LABMARK;

    private static final String SUBMITTED_LABSTATUS_DESC = PREFIX_LABSTATUS + LABSTATUS_SUBMITTED;
    private static final String VALID_GRADELAB_DESC =
            PREFIX_LABSTATUS + LABSTATUS_GRADED + PREFIX_LABMARK + VALID_LABMARK;

    private EditLabCommandParser parser = new EditLabCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_LAB_DESC + SUBMITTED_LABSTATUS_DESC, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, VALID_LAB_DESC + VALID_LABMARK_DESC, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, VALID_LAB_DESC + VALID_GRADELAB_DESC, MESSAGE_INVALID_FORMAT);

        // no lab specified
        assertParseFailure(parser, VALID_INDEX + SUBMITTED_LABSTATUS_DESC, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, VALID_INDEX + VALID_LABMARK_DESC, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, VALID_INDEX + VALID_GRADELAB_DESC, MESSAGE_INVALID_FORMAT);

        // no labStatus or labMark specified
        assertParseFailure(parser, VALID_INDEX, MESSAGE_INVALID_FORMAT);

        // nothing specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + VALID_LAB_DESC + SUBMITTED_LABSTATUS_DESC, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + VALID_LAB_DESC + SUBMITTED_LABSTATUS_DESC, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string" + VALID_LAB_DESC + SUBMITTED_LABSTATUS_DESC,
                MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/string" + VALID_LAB_DESC + SUBMITTED_LABSTATUS_DESC, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid labs
        assertParseFailure(parser, VALID_INDEX + "l/a " + SUBMITTED_LABSTATUS_DESC, Lab.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, VALID_INDEX + "l/-1 " + SUBMITTED_LABSTATUS_DESC, Lab.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, VALID_INDEX + "l/0 " + SUBMITTED_LABSTATUS_DESC, Lab.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, VALID_INDEX + "l/0001 " + SUBMITTED_LABSTATUS_DESC, Lab.MESSAGE_CONSTRAINTS);

        // invalid statuses
        assertParseFailure(parser, VALID_INDEX + VALID_LAB_DESC + "s/e", LabStatus.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, VALID_INDEX + VALID_LAB_DESC + "s/1", LabStatus.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, VALID_INDEX + VALID_LAB_DESC + "s/U", LabStatus.MESSAGE_CONSTRAINTS);

        // invalid marks
        assertParseFailure(parser, VALID_INDEX + VALID_LAB_DESC + "m/e", LabMark.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, VALID_INDEX + VALID_LAB_DESC + "m/01", LabMark.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, VALID_INDEX + VALID_LAB_DESC + "m/-1", LabMark.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_editLabStatus_success() {
        EditLabCommand expectedCommand =
                new EditLabCommand(Index.fromOneBased(Integer.parseInt(VALID_INDEX.trim())),
                        Integer.parseInt(VALID_LAB.trim()), LabStatus.SUBMITTED);

        assertParseSuccess(parser, VALID_INDEX + VALID_LAB_DESC + SUBMITTED_LABSTATUS_DESC, expectedCommand);
    }

    @Test
    public void parse_editLabMarks_success() {
        EditLabCommand expectedCommand = new EditLabCommand(Index.fromOneBased(Integer.parseInt(VALID_INDEX.trim())),
                Integer.parseInt(VALID_LAB.trim()), LabStatus.GRADED, new LabMark(VALID_LABMARK.trim()));

        assertParseSuccess(parser, VALID_INDEX + VALID_LAB_DESC + VALID_LABMARK_DESC, expectedCommand);
    }

    @Test
    public void parse_editLabMarksAndStatus_success() {
        EditLabCommand expectedCommand = new EditLabCommand(Index.fromOneBased(Integer.parseInt(VALID_INDEX.trim())),
                Integer.parseInt(VALID_LAB.trim()), LabStatus.GRADED, new LabMark(VALID_LABMARK.trim()));

        assertParseSuccess(parser, VALID_INDEX + VALID_LAB_DESC + VALID_GRADELAB_DESC, expectedCommand);
        // should still work even if order of labMark and labStatus is reversed
        assertParseSuccess(parser, VALID_INDEX + VALID_LAB_DESC + VALID_LABMARK_DESC
                + PREFIX_LABSTATUS + LABSTATUS_GRADED, expectedCommand);
    }
}

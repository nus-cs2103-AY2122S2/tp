package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LAB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LABSTATUS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditLabStatusCommand;
import seedu.address.model.lab.Lab;
import seedu.address.model.lab.LabStatus;

public class EditLabStatusCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditLabStatusCommand.MESSAGE_USAGE);
    private static final String VALID_INDEX = "1 ";
    private static final String VALID_LAB = "1 ";
    private static final String VALID_LABSTATUS = "g";
    private static final String VALID_LAB_DESC = PREFIX_LAB + VALID_LAB;
    private static final String VALID_LABSTATUS_DESC = PREFIX_LABSTATUS + VALID_LABSTATUS;

    private EditLabStatusCommandParser parser = new EditLabStatusCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_LAB_DESC + VALID_LABSTATUS_DESC, MESSAGE_INVALID_FORMAT);

        // no lab specified
        assertParseFailure(parser, VALID_INDEX + VALID_LABSTATUS_DESC, MESSAGE_INVALID_FORMAT);

        // no status specified
        assertParseFailure(parser, VALID_INDEX + VALID_LAB_DESC, MESSAGE_INVALID_FORMAT);

        // missing prefix
        assertParseFailure(parser, VALID_INDEX + VALID_LAB_DESC + VALID_LABSTATUS, MESSAGE_INVALID_FORMAT);

        // nothing specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + VALID_LAB_DESC + VALID_LABSTATUS_DESC, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + VALID_LAB_DESC + VALID_LABSTATUS_DESC, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string" + VALID_LAB_DESC + VALID_LABSTATUS_DESC,
                MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/string" + VALID_LAB_DESC + VALID_LABSTATUS_DESC, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid labs
        assertParseFailure(parser, VALID_INDEX + "l/a " + VALID_LABSTATUS_DESC, Lab.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, VALID_INDEX + "l/-1 " + VALID_LABSTATUS_DESC, Lab.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, VALID_INDEX + "l/0 " + VALID_LABSTATUS_DESC, Lab.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, VALID_INDEX + "l/0001 " + VALID_LABSTATUS_DESC, Lab.MESSAGE_CONSTRAINTS);

        // invalid statuses
        assertParseFailure(parser, VALID_INDEX + VALID_LAB_DESC + "s/e", LabStatus.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, VALID_INDEX + VALID_LAB_DESC + "s/1", LabStatus.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, VALID_INDEX + VALID_LAB_DESC + "s/U", LabStatus.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        EditLabStatusCommand expectedCommand =
                new EditLabStatusCommand(Index.fromOneBased(Integer.parseInt(VALID_INDEX.trim())),
                        Integer.parseInt(VALID_LAB.trim()), LabStatus.GRADED);

        assertParseSuccess(parser, VALID_INDEX + VALID_LAB_DESC + VALID_LABSTATUS_DESC, expectedCommand);
    }
}

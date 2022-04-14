package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ID_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_CODE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TELEGRAM_HANDLE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INDEX_MULTIPLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteModuleCommand;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.ModuleCodeContainsKeywordsPredicate;

public class DeleteModuleCommandParserTest {

    private DeleteModuleCommandParser parser = new DeleteModuleCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteModuleCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_nonemptyPreamble_throwsParseException() {
        assertParseFailure(parser, PREAMBLE_NON_EMPTY, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteModuleCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_multipleFields_throwsParseException() {
        // all 3 fields
        assertParseFailure(parser, PREAMBLE_WHITESPACE + ID_DESC_BOB + NAME_DESC_BOB + MODULE_CODE_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteModuleCommand.MESSAGE_USAGE));

        // student id + name
        assertParseFailure(parser, PREAMBLE_WHITESPACE + ID_DESC_BOB + NAME_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteModuleCommand.MESSAGE_USAGE));

        // student id + mod code
        assertParseFailure(parser, PREAMBLE_WHITESPACE + ID_DESC_BOB + MODULE_CODE_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteModuleCommand.MESSAGE_USAGE));

        // name + mod code
        assertParseFailure(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + MODULE_CODE_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteModuleCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidFields_throwsParseException() {
        // invalid mod code
        assertParseFailure(parser, PREAMBLE_WHITESPACE + INVALID_MODULE_CODE_DESC,
                ModuleCode.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_nonSupportedFields_throwsParseException() {
        // index
        assertParseFailure(parser, PREAMBLE_WHITESPACE + VALID_INDEX,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteModuleCommand.MESSAGE_USAGE));

        // multiple index
        assertParseFailure(parser, PREAMBLE_WHITESPACE + VALID_INDEX_MULTIPLE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteModuleCommand.MESSAGE_USAGE));

        // student id
        assertParseFailure(parser, PREAMBLE_WHITESPACE + VALID_ID_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteModuleCommand.MESSAGE_USAGE));

        // name
        assertParseFailure(parser, PREAMBLE_WHITESPACE + VALID_NAME_AMY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteModuleCommand.MESSAGE_USAGE));

        // telegram handle
        assertParseFailure(parser, PREAMBLE_WHITESPACE + TELEGRAM_HANDLE_DESC_AMY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteModuleCommand.MESSAGE_USAGE));

        // phone number
        assertParseFailure(parser, PREAMBLE_WHITESPACE + PHONE_DESC_AMY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteModuleCommand.MESSAGE_USAGE));

        // email
        assertParseFailure(parser, PREAMBLE_WHITESPACE + EMAIL_DESC_AMY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteModuleCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validModuleCode_returnsDeleteModuleCommand() {
        // valid module code
        DeleteModuleCommand expectedDeleteModuleCommand =
                new DeleteModuleCommand(
                        new ModuleCodeContainsKeywordsPredicate(
                                Arrays.asList(VALID_MODULE_CODE_BOB.split("\\s+"))));

        // no white space before module code
        assertParseSuccess(parser, MODULE_CODE_DESC_BOB, expectedDeleteModuleCommand);

        // whitespace before module code
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + MODULE_CODE_DESC_BOB, expectedDeleteModuleCommand);
    }
}

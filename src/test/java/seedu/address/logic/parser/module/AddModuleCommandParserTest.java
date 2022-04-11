package seedu.address.logic.parser.module;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_DESC_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_DESC_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LECTURE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.typical.TypicalModules.CS2103;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.module.AddModuleCommand;
import seedu.address.model.module.Module;
import seedu.address.testutil.module.ModuleBuilder;

public class AddModuleCommandParserTest {
    private final AddModuleCommandParser parser = new AddModuleCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        final Module expectedModule = new ModuleBuilder(CS2103).withCode(VALID_MODULE_LECTURE).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + MODULE_DESC_LECTURE,
                new AddModuleCommand(expectedModule));

        // multiple names - last name accepted
        assertParseSuccess(parser, MODULE_DESC_TUTORIAL + MODULE_DESC_LECTURE,
                new AddModuleCommand(expectedModule));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        final String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddModuleCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_MODULE_LECTURE, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_MODULE_DESC, Module.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + MODULE_DESC_LECTURE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddModuleCommand.MESSAGE_USAGE));
    }
}

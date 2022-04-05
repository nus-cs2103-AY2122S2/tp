package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_DESC_CS2101;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_DESC_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_SWE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddModulesCommand;
import seedu.address.model.module.Module;

class AddModulesCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddModulesCommand.MESSAGE_USAGE);

    private final AddModulesCommandParser parser = new AddModulesCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_MODULE, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", AddModulesCommandParser.MESSAGE_EMPTY);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + VALID_MODULE, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + VALID_MODULE, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 definitely invalid argument", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_MODULE_DESC, Module.MESSAGE_CONSTRAINTS); // invalid module
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + MODULE_DESC_CS2101
                + MODULE_DESC_CS2103T;
        List<Module> expectedModules = new ArrayList<>();
        expectedModules.add(new Module(VALID_MODULE_SWE));
        expectedModules.add(new Module(VALID_MODULE));
        AddModulesCommand expectedCommand = new AddModulesCommand(targetIndex, expectedModules);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}

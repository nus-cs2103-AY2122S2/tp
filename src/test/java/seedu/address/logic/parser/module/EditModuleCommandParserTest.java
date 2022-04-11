package seedu.address.logic.parser.module;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_DESC_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_DESC_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_TUTORIAL;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.typical.TypicalIndexes.INDEX_FIRST_MODULE;
import static seedu.address.testutil.typical.TypicalIndexes.INDEX_SECOND_MODULE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.module.EditModuleCommand;
import seedu.address.logic.commands.module.EditModuleCommand.EditModuleDescriptor;
import seedu.address.model.module.Module;
import seedu.address.testutil.module.EditModuleDescriptorBuilder;

public class EditModuleCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditModuleCommand.MESSAGE_USAGE);
    private final EditModuleCommandParser parser = new EditModuleCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_MODULE_LECTURE, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditModuleCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + VALID_MODULE_LECTURE, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + VALID_MODULE_LECTURE, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid code
        assertParseFailure(parser, "1" + INVALID_MODULE_DESC, Module.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        final Index targetIndex = INDEX_SECOND_MODULE;
        final String userInput = targetIndex.getOneBased() + MODULE_DESC_LECTURE;

        final EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder().withCode(VALID_MODULE_LECTURE)
                .build();
        final EditModuleCommand expectedCommand = new EditModuleCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        final Index targetIndex = INDEX_FIRST_MODULE;
        final String userInput = targetIndex.getOneBased() + MODULE_DESC_TUTORIAL + MODULE_DESC_LECTURE;

        final EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder().withCode(VALID_MODULE_TUTORIAL)
                .withCode(VALID_MODULE_LECTURE).build();
        final EditModuleCommand expectedCommand = new EditModuleCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        final Index targetIndex = INDEX_FIRST_MODULE;
        final String userInput = targetIndex.getOneBased() + INVALID_MODULE_DESC + MODULE_DESC_LECTURE;
        final EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder()
                .withCode(VALID_MODULE_LECTURE).build();
        final EditModuleCommand expectedCommand = new EditModuleCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

}

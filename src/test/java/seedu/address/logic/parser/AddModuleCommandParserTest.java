package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_CS2101;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_MODULE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_MODULE_SWE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddModuleCommand;
import seedu.address.model.tag.Tag;

class AddModuleCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddModuleCommand.MESSAGE_USAGE);

    private final AddModuleCommandParser parser = new AddModuleCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_TAG_MODULE, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", AddModuleCommandParser.MESSAGE_EMPTY);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + VALID_TAG_MODULE, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + VALID_TAG_MODULE, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 definitely invalid argument", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + TAG_DESC_CS2101
                + TAG_DESC_CS2103T;
        List<Tag> expectedTags = new ArrayList<>();
        expectedTags.add(new Tag(VALID_TAG_MODULE_SWE));
        expectedTags.add(new Tag(VALID_TAG_MODULE));
        AddModuleCommand expectedCommand = new AddModuleCommand(targetIndex, expectedTags);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}

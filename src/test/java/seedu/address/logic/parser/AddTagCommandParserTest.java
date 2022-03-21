package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddTagCommand;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.TagBuilder;

public class AddTagCommandParserTest {
    private AddTagCommandParser parser = new AddTagCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Tag expectedTag = new TagBuilder().withTagName(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + TAG_DESC_FRIEND,
                new AddTagCommand(expectedTag));

        // multiple tags - last tag accepted
        assertParseSuccess(parser, TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                new AddTagCommand(expectedTag));

        // multiple tags with one of them invalid - last tag accepted
        assertParseSuccess(parser, INVALID_TAG_DESC + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                new AddTagCommand(expectedTag));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagCommand.MESSAGE_USAGE);

        // missing tag prefix
        assertParseFailure(parser, VALID_TAG_FRIEND, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid tag
        assertParseFailure(parser, INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalids - first invalid will be reported
        assertParseFailure(parser, INVALID_TAG_DESC + TAG_DESC_FRIEND + INVALID_TAG_DESC,
                Tag.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagCommand.MESSAGE_USAGE));
    }
}

package seedu.contax.logic.parser;


import static seedu.contax.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.contax.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.contax.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.contax.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.contax.logic.commands.AddTagCommand;
import seedu.contax.model.tag.Tag;
import seedu.contax.testutil.TagBuilder;

class AddTagCommandParserTest {
    private AddTagCommandParser parser = new AddTagCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Tag expectedTag = new TagBuilder().withName(VALID_TAG_FRIEND).build();

        String tagName = " " + PREFIX_NAME + VALID_TAG_FRIEND;
        assertParseSuccess(parser, tagName, new AddTagCommand(expectedTag));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagCommand.MESSAGE_USAGE);

        // No token
        assertParseFailure(parser, "", expectedMessage);

        // Token exists but empty field
        assertParseFailure(parser, "n/", expectedMessage);
    }
}

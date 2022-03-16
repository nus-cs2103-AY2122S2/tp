package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_STATUS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_ALICE_IN_WONDERLAND;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_GONE;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.STATUS_DESC_COMPLETED;
import static seedu.address.logic.commands.CommandTestUtil.STATUS_DESC_WATCHING;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIENDS;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_MOVIE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_OWESMONEY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_ALICE_IN_WONDERLAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_COMPLETED;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIENDS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_MOVIE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_OWESMONEY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalShows.ALICE_IN_WONDERLAND;
import static seedu.address.testutil.TypicalShows.GONE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.show.Name;
import seedu.address.model.show.Show;
import seedu.address.model.show.Status;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.ShowBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Show expectedShow = new ShowBuilder(ALICE_IN_WONDERLAND).withTags(VALID_TAG_MOVIE).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_ALICE_IN_WONDERLAND
                + STATUS_DESC_COMPLETED + TAG_DESC_MOVIE, new AddCommand(expectedShow));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_GONE + NAME_DESC_ALICE_IN_WONDERLAND
                + STATUS_DESC_COMPLETED + TAG_DESC_MOVIE, new AddCommand(expectedShow));

        // multiple status - last status accepted
        assertParseSuccess(parser, NAME_DESC_ALICE_IN_WONDERLAND + STATUS_DESC_WATCHING
                + STATUS_DESC_COMPLETED + TAG_DESC_MOVIE, new AddCommand(expectedShow));

        // multiple tags - all accepted
        Show expectedShowMultipleTags = new ShowBuilder(GONE).withTags(VALID_TAG_OWESMONEY, VALID_TAG_FRIENDS)
                .build();
        assertParseSuccess(parser, NAME_DESC_GONE + STATUS_DESC_WATCHING + TAG_DESC_OWESMONEY
                + TAG_DESC_FRIENDS, new AddCommand(expectedShowMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Show expectedShow = new ShowBuilder(ALICE_IN_WONDERLAND).withTags().build();
        assertParseSuccess(parser, NAME_DESC_ALICE_IN_WONDERLAND + STATUS_DESC_COMPLETED,
                new AddCommand(expectedShow));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_ALICE_IN_WONDERLAND + STATUS_DESC_COMPLETED,
                expectedMessage);

        // missing status prefix
        assertParseFailure(parser, NAME_DESC_ALICE_IN_WONDERLAND + VALID_STATUS_COMPLETED,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_ALICE_IN_WONDERLAND + VALID_STATUS_COMPLETED,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + STATUS_DESC_COMPLETED
                + TAG_DESC_MOVIE, Name.MESSAGE_CONSTRAINTS);

        // invalid status
        assertParseFailure(parser, NAME_DESC_ALICE_IN_WONDERLAND + INVALID_STATUS_DESC
                + TAG_DESC_MOVIE, Status.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_ALICE_IN_WONDERLAND + STATUS_DESC_COMPLETED
                + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + INVALID_STATUS_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_ALICE_IN_WONDERLAND
                        + STATUS_DESC_COMPLETED + TAG_DESC_MOVIE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));

    }
}

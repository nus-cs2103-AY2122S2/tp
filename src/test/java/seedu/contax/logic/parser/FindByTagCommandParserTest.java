package seedu.contax.logic.parser;

import static seedu.contax.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.contax.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.contax.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.contax.logic.commands.FindByTagCommand;
import seedu.contax.model.person.TagNameContainsKeywordsPredicate;
import seedu.contax.model.tag.Name;

/**
 * Unit tests which parses input and creates a new FindByTagCommand object.
 */
public class FindByTagCommandParserTest {
    public static final String MESSAGE_ERROR = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            FindByTagCommand.MESSAGE_USAGE);
    private FindByTagCommandParser parser = new FindByTagCommandParser();

    @Test
    public void parse_emptyArg_failure() {
        assertParseFailure(parser, " ", MESSAGE_ERROR);
    }

    @Test
    public void parse_invalidPrefix_failure() {
        // No prefix
        assertParseFailure(parser, " friends", MESSAGE_ERROR);
    }

    @Test
    public void parse_invalidName_failure() {
        // Prefix exists but empty string
        assertParseFailure(parser, " t/", Name.MESSAGE_CONSTRAINTS);

        // Prefix exists but is spacing
        assertParseFailure(parser, " t/ ", Name.MESSAGE_CONSTRAINTS);

        // Invalid keyword
        assertParseFailure(parser, " t/&friends", Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_hasPreamble_failure() {
        assertParseFailure(parser, "hello t/friends", MESSAGE_ERROR);
    }

    @Test
    public void parse_validName_success() {
        TagNameContainsKeywordsPredicate predicate = new TagNameContainsKeywordsPredicate("friends");
        FindByTagCommand command = new FindByTagCommand(predicate);

        assertParseSuccess(parser, " t/friends", command);
    }
}

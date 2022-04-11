package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.FriendName;
import seedu.address.testutil.FriendFilterPredicateBuilder;


public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new FriendFilterPredicateBuilder().withNameSubstring("Alice")
                        .withNameSubstring("Bob").build());

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_NAME + "Alice " + PREFIX_NAME + "Bob ", expectedFindCommand);

        expectedFindCommand =
                new FindCommand(new FriendFilterPredicateBuilder().withTagSubstring("friends")
                        .withTagSubstring("neighbours").withLogTitleSubstring("2013")
                        .withNameSubstring("Bruno").build());

        //many keywords
        assertParseSuccess(parser, " " + PREFIX_TAG
                + "friends " + PREFIX_TAG + "neighbours "
                + PREFIX_TITLE + "2013 " + PREFIX_NAME + "Bruno ", expectedFindCommand);
    }

    @Test
    public void parse_invalidArgs_failure() {

        //no prefixes given
        assertParseFailure(parser, " Alice Bob", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommand.MESSAGE_USAGE));

        //empty prefixes given
        assertParseFailure(parser, " " + PREFIX_NAME + " " + PREFIX_TITLE, FriendName.MESSAGE_CONSTRAINTS);


    }

}

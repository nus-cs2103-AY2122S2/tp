package seedu.address.logic.parser.person;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.person.FindPersonCommand;
import seedu.address.model.name.PersonNameContainsKeywordsPredicate;
import seedu.address.model.tag.PersonTagContainsKeywordsPredicate;

public class FindPersonCommandParserTest {

    private FindPersonCommandParser parser = new FindPersonCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPersonCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        List<String> nameList = Arrays.asList("Alex", "Bob");
        List<String> tagList = Arrays.asList("friends", "neighbours");
        // no leading and trailing whitespaces
        FindPersonCommand expectedFindCommand =
                new FindPersonCommand(new PersonNameContainsKeywordsPredicate(nameList),
                        new PersonTagContainsKeywordsPredicate(tagList));
        assertParseSuccess(parser, " n/Alex n/Bob t/friends t/neighbours", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " n/Alex     n/Bob   t/friends t/neighbours    ", expectedFindCommand);
    }

}

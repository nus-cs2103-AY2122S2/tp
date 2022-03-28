package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.model.person.PersonContainsTagPredicate;
import seedu.address.model.tag.Tag;


public class FilterCommandParserTest {

    private FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFilterCommand() {
        // no leading and trailing whitespaces
        Tag tag = new Tag("friends");
        FilterCommand expectedFilterCommand =
                new FilterCommand(new PersonContainsTagPredicate(tag), tag);
        assertParseSuccess(parser, "friends", expectedFilterCommand);

        // white spaces
        assertParseSuccess(parser, "    friends", expectedFilterCommand);
    }
}

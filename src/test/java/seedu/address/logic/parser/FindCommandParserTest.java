package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindCommand.FindPersonDescriptor;
import seedu.address.model.person.Name;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", FindCommand.MESSAGE_NO_PARAMETERS);
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        FindPersonDescriptor personDescriptor = new FindPersonDescriptor();
        List<Name> names = new ArrayList<>();
        names.add(new Name("Alice"));
        personDescriptor.setNames(names);
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand = new FindOrPredicateParser().parse(personDescriptor);
        //assertParseSuccess(parser, "n/Alice", expectedFindCommand);
        //Test cases will be added once implementation of find is complete

        // multiple whitespaces between keywords
        //assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedFindCommand);
    }

}

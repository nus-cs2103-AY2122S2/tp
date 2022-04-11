package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_NO_TYPE_GIVEN;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindInterviewCommand;
import seedu.address.logic.commands.FindPersonCommand;
import seedu.address.model.interview.InterviewContainsKeywordsPredicate;
import seedu.address.model.person.Name;
import seedu.address.model.person.PersonContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindPersonCommandParser person = new FindPersonCommandParser();
    private FindInterviewCommandParser interview = new FindInterviewCommandParser();

    @Test
    public void parse_emptyString_throwParseException() {
        assertParseFailure(person, "", MESSAGE_NO_TYPE_GIVEN);
        assertParseFailure(interview, "", MESSAGE_NO_TYPE_GIVEN);
    }

    @Test
    public void parse_invalidGroups_throwParseException() {
        // check for no g/ flags
        assertParseFailure(person, " [p]",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPersonCommand.MESSAGE_USAGE));
        assertParseFailure(interview, " [i]",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindInterviewCommand.MESSAGE_USAGE));

        // check for empty g/ flags
        assertParseFailure(person, " [p] g/",
                FindCommandParser.NO_KEYWORDS_ERROR + FindPersonCommand.MESSAGE_USAGE);
        assertParseFailure(interview, " [i] g/",
                FindCommandParser.NO_KEYWORDS_ERROR + FindInterviewCommand.MESSAGE_USAGE);
        assertParseFailure(person, " [p] g/n/test g/",
                FindCommandParser.NO_KEYWORDS_ERROR + FindPersonCommand.MESSAGE_USAGE);
        assertParseFailure(interview, " [i] g/n/test g/",
                FindCommandParser.NO_KEYWORDS_ERROR + FindInterviewCommand.MESSAGE_USAGE);
        assertParseFailure(person, " [p] g/n/test g/ g/j/software",
                FindCommandParser.NO_KEYWORDS_ERROR + FindPersonCommand.MESSAGE_USAGE);
        assertParseFailure(interview, " [i] g/n/test g/ g/j/software",
                FindCommandParser.NO_KEYWORDS_ERROR + FindInterviewCommand.MESSAGE_USAGE);
        assertParseFailure(person, " [p] g/g/",
                FindCommandParser.EXTRA_FLAG_ERROR + FindPersonCommand.MESSAGE_USAGE);
        assertParseFailure(interview, " [i] g/g/",
                FindCommandParser.EXTRA_FLAG_ERROR + FindInterviewCommand.MESSAGE_USAGE);
    }

    @Test
    public void parse_invalidInput_throwParseException() {
        assertParseFailure(person, " [p] g/n/<>",
                String.format("[n/<>] contains invalid name\n" + Name.MESSAGE_CONSTRAINTS));
        assertParseFailure(interview, " [i] g/n/<>",
                String.format("[n/<>] contains invalid name\n" + Name.MESSAGE_CONSTRAINTS));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        List<String> groups = new ArrayList<>();
        groups.add("n/bob tan j/data scientist");
        groups.add("n/connor lee j/accountant");

        // finding interview
        FindInterviewCommand findInterview = new FindInterviewCommand(new InterviewContainsKeywordsPredicate(groups));
        assertParseSuccess(
                interview, " [i] g/n/bob tan j/data scientist g/n/connor lee j/accountant", findInterview);

        // finding applicants
        FindPersonCommand findPerson = new FindPersonCommand(new PersonContainsKeywordsPredicate(groups));
        assertParseSuccess(
                person, " [p] g/n/bob tan j/data scientist g/n/connor lee j/accountant", findPerson);
    }
}

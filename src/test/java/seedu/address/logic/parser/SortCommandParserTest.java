package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORTKEY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortCommand;
import seedu.address.model.person.Person;

public class SortCommandParserTest {

    private static final String SORT_EMPTY = " " + PREFIX_SORTKEY;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE);

    private SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingParts_failure() {
        // no sort key specified
        assertParseFailure(parser, SORT_EMPTY, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidFields_failure() {
        // invalid field specified
        assertParseFailure(parser, PREFIX_SORTKEY + "named", MESSAGE_INVALID_FORMAT);

        // multiple fields specified
        assertParseFailure(parser, PREFIX_SORTKEY + "name course", MESSAGE_INVALID_FORMAT);

        // no whitespace before prefix
        assertParseFailure(parser, PREFIX_SORTKEY + "name", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, PREFIX_TAG + "course", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Comparator<Person> sortComparatorName = Comparator.comparing(l -> l.getName().toString().toLowerCase());
        Comparator<Person> sortComparatorCourse = Comparator.comparing(l -> l.getName().toString().toLowerCase());
        Comparator<Person> sortComparatorStudentId = Comparator.comparing(l -> l.getName().toString().toLowerCase());
        Comparator<Person> sortComparatorPhone = Comparator.comparing(l -> l.getName().toString().toLowerCase());
        Comparator<Person> sortComparatorEmail = Comparator.comparing(l -> l.getName().toString().toLowerCase());

        SortCommand expectedCommandName = new SortCommand(sortComparatorName, "name");
        SortCommand expectedCommandCourse = new SortCommand(sortComparatorCourse, "course");
        SortCommand expectedCommandStudentId = new SortCommand(sortComparatorStudentId, "studentid");
        SortCommand expectedCommandPhone = new SortCommand(sortComparatorPhone, "phone");
        SortCommand expectedCommandEmail = new SortCommand(sortComparatorEmail, "email");

        assertParseSuccess(parser, SORT_EMPTY + "name", expectedCommandName);
        assertParseSuccess(parser, " " + PREFIX_SORTKEY + "name", expectedCommandName);
        assertParseSuccess(parser, SORT_EMPTY + "course", expectedCommandCourse);
        assertParseSuccess(parser, SORT_EMPTY + "studentid", expectedCommandStudentId);
        assertParseSuccess(parser, SORT_EMPTY + "phone", expectedCommandPhone);
        assertParseSuccess(parser, SORT_EMPTY + "email", expectedCommandEmail);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        String userInput = SORT_EMPTY + "name" + SORT_EMPTY + "course" + SORT_EMPTY + "studentid";

        Comparator<Person> sortComparator = Comparator.comparing(l -> l.getStudentID().toString().toLowerCase());
        SortCommand expectedCommand = new SortCommand(sortComparator, "studentid");

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}

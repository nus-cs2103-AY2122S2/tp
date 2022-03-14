package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.FilterCommand;
import seedu.address.model.person.lab.Lab;
import seedu.address.model.person.lab.StudentHasLabPredicate;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

public class FilterCommandParserTest {

    private FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "   ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFilterCommand() {
        Lab expectedLab = (new Lab("1")).of("SUBMITTED");
        FilterCommand expectedFilterCommand =
                new FilterCommand(new StudentHasLabPredicate(expectedLab));

        assertParseSuccess(parser, "l/1 s/s", expectedFilterCommand);

    }

    @Test
    public void parse_missingField_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE);

        // missing LabStatus Prefix
        assertParseFailure(parser, "l/1 ", expectedMessage);

        // missing Lab Prefix
        assertParseFailure(parser, "s/1", expectedMessage);

    }
}

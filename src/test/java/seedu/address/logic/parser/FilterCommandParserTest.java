package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.model.lab.LabMark.MARKS_UNKNOWN;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.model.lab.Lab;
import seedu.address.model.lab.LabMark;
import seedu.address.model.lab.LabStatus;
import seedu.address.model.lab.StudentHasLabPredicate;
import seedu.address.model.util.LabTriplet;

public class FilterCommandParserTest {

    private final FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "   ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFilterCommand() {
        Lab expectedLab1 = new Lab("1").of(LabStatus.SUBMITTED, new LabMark("0"));
        Lab expectedLab2 = new Lab("2").of(LabStatus.UNSUBMITTED, new LabMark("0"));
        Lab expectedLab3 = new Lab("3").of(LabStatus.GRADED, new LabMark("0"));
        FilterCommand expectedFilterCommand1 =
                new FilterCommand(new StudentHasLabPredicate(expectedLab1));
        FilterCommand expectedFilterCommand2 =
                new FilterCommand(new StudentHasLabPredicate(expectedLab2));
        FilterCommand expectedFilterCommand3 =
                new FilterCommand(new StudentHasLabPredicate(expectedLab3));

        assertParseSuccess(parser, " l/1 s/s", expectedFilterCommand1);
        assertParseSuccess(parser, " l/2 s/u", expectedFilterCommand2);
        assertParseSuccess(parser, " l/3 s/g", expectedFilterCommand3);

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

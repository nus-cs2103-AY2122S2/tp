package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindInsuranceCommand;
import seedu.address.model.insurance.TitleContainsKeywordsPredicate;

public class FindInsuranceCommandParserTest {
    private FindInsuranceCommandParser parser = new FindInsuranceCommandParser();
    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindInsuranceCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindInsuranceCommand expectedFindInsuranceCommand =
                new FindInsuranceCommand(new TitleContainsKeywordsPredicate(Arrays.asList("Personal", "Accident")));
        assertParseSuccess(parser, "Personal Accident", expectedFindInsuranceCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Personal \n \t Accident  \t", expectedFindInsuranceCommand);
    }
}

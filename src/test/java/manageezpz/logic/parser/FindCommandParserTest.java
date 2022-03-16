package manageezpz.logic.parser;

import static manageezpz.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static manageezpz.logic.parser.CliSyntax.PREFIX_DATE;
import static manageezpz.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static manageezpz.logic.parser.CliSyntax.PREFIX_TASK;
import static manageezpz.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import manageezpz.logic.commands.FindCommand;
import manageezpz.model.task.Date;

public class FindCommandParserTest {
    private static String EXPECTED_ERROR_MESSAGE =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE);
    private FindCommandParser parser = new FindCommandParser();


    @Test
    void findParser_emptyArguments_parserExceptionThrown() {
        assertParseFailure(parser, "", EXPECTED_ERROR_MESSAGE);
    }

    @Test
    void findParser_invalidArguments_parserExceptionThrown() {
        assertParseFailure(parser, " /someOtherArgument", EXPECTED_ERROR_MESSAGE);
    }

    @Test
    void findParser_noDesc_parserExceptionThrown() {
        String userInput = String.join("", " ", PREFIX_TASK.toString());
        assertParseFailure(parser, userInput, EXPECTED_ERROR_MESSAGE);
    }

    @Test
    void findParser_emptyDesc_parserExceptionThrown() {
        String userInput =
                String.join(" ", "", PREFIX_TASK.toString(), " ", PREFIX_DESCRIPTION.toString());
        assertParseFailure(parser, userInput, EXPECTED_ERROR_MESSAGE);
    }

    @Test
    void findParser_emptyDate_parserExceptionThrown() {
        String userInput = String.join(" ", "", PREFIX_DATE.toString());
        assertParseFailure(parser, userInput, Date.MESSAGE_CONSTRAINTS);
    }

    @Test
    void findParser_invalidDate_parserExceptionThrown() {
        String userInput =
                String.join(" ", "", PREFIX_DATE.toString(), "second january two zero two two");
        assertParseFailure(parser, userInput, Date.MESSAGE_CONSTRAINTS);
    }
}

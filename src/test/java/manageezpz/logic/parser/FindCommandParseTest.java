package manageezpz.logic.parser;

import static manageezpz.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static manageezpz.logic.commands.CommandTestUtil.DESCRIPTION;
import static manageezpz.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static manageezpz.logic.parser.CliSyntax.PREFIX_TODO;
import static manageezpz.logic.parser.CommandParserTestUtil.assertParseFailure;
import static manageezpz.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import manageezpz.logic.commands.FindCommand;
import manageezpz.logic.commands.FindTaskCommand;
import manageezpz.model.task.TaskMultiplePredicate;

public class FindCommandParseTest {
    private static final String EXPECTED_ERROR_MESSAGE =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE);
    private FindCommandParser parser = new FindCommandParser();

    @Test
    void findParse_noArguments_parseExceptionThrown() {
        assertParseFailure(parser, "", EXPECTED_ERROR_MESSAGE);
    }

    @Test
    void findParse_invalidArguments_parserExceptionThrown() {
        assertParseFailure(parser, " /someOtherArgument", EXPECTED_ERROR_MESSAGE);
    }

    @Test
    void findParser_withTaskOption_findTaskCommand() {
        String userInput
                = String.join(" ",
                FindCommand.COMMAND_WORD,
                PREFIX_TODO.toString(),
                PREFIX_DESCRIPTION.toString(), DESCRIPTION);
        List<String> expectedKeywords = new ArrayList<String>();
        expectedKeywords.add(DESCRIPTION);
        TaskMultiplePredicate expectedPredicate
                = new TaskMultiplePredicate(PREFIX_TODO, expectedKeywords, null, null, null, null);
        FindCommand expectedCommand = new FindTaskCommand(expectedPredicate);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}

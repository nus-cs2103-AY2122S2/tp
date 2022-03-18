package manageezpz.logic.parser;

import static manageezpz.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static manageezpz.logic.parser.CliSyntax.PREFIX_DATE;
import static manageezpz.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static manageezpz.logic.parser.CliSyntax.PREFIX_TASK;
import static manageezpz.logic.parser.CommandParserTestUtil.assertParseFailure;
import static manageezpz.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import manageezpz.logic.commands.FindCommand;
import manageezpz.model.task.Date;
import manageezpz.model.task.TaskContainsDatePredicate;
import manageezpz.model.task.TaskContainsKeywordsPredicate;

public class FindCommandParserTest {
    private static final String EXPECTED_ERROR_MESSAGE =
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
    void findParser_taskWithValidDesc_findCommandWithKeywords() {
        List<String> sampleKeywords = Arrays.asList(new String[]{"Genshin", "Ganyu"});
        TaskContainsKeywordsPredicate expectedPredicate = new TaskContainsKeywordsPredicate(sampleKeywords);
        String userInput =
                String.join(" ",
                        "",
                        PREFIX_TASK.toString(),
                        PREFIX_DESCRIPTION.toString(),
                        sampleKeywords.get(0),
                        sampleKeywords.get(1)
                );
        assertParseSuccess(parser, userInput, new FindCommand(expectedPredicate));
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

    @Test
    void findParser_validDate_findCommandWithDate() {
        Date expectedDate = new Date("2022-01-01");
        String userInput =
                String.join(" ", "", PREFIX_DATE.toString(), expectedDate.getDate().toString());
        TaskContainsDatePredicate expectedPredicate = new TaskContainsDatePredicate(expectedDate);
        assertParseSuccess(parser, userInput, new FindCommand(expectedPredicate));
    }
}

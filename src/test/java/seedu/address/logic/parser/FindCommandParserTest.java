package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.FindCommand.NO_PREFIX_MESSAGE;
import static seedu.address.logic.parser.CliSyntax.ARRAY_OF_PREFIX;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.predicate.FindPersonPredicate;

public class FindCommandParserTest {
    private final FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_invalidArg_throwsParseException() {
        // Empty input -> should result in parse failure
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

        // Invalid prefix -> should result in parse failure
        assertParseFailure(parser, "find z/ test1 s/test2", NO_PREFIX_MESSAGE);

        // Stand-alone command -> should result in pare failure
        assertParseFailure(parser, "find ", NO_PREFIX_MESSAGE);

        // Special characters for ContactedDate -> should result in pare failure
        assertParseFailure(parser, "find c/@", FindCommand.MESSAGE_CONTACTED_DATE_INVALID_ARG);

        // Negative integer values for ContactedDate -> should result in pare failure
        assertParseFailure(parser, "find c/-1", FindCommand.MESSAGE_CONTACTED_DATE_INVALID_ARG);

        // Positive value above 2147483647 is invalid -> integer overflow.
        assertParseFailure(parser, "find c/2147483648", FindCommand.MESSAGE_CONTACTED_DATE_INVALID_ARG);

        // First valid arg for the prefix follow by a second invalid arg -> parse failure.
        assertParseFailure(parser, "find e/hi e/", FindCommand.MESSAGE_INVALID_PREFIX_ARGUMENT);

        // First valid arg for the prefix follow by a second invalid arg from a different prefix -> parse failure.
        assertParseFailure(parser, "find e/hi a/", FindCommand.MESSAGE_INVALID_PREFIX_ARGUMENT);

        // Input exceeded 1001 characters -> parse failure due to violation of upper limit.
        String inputExceedingThousandChar = "find n/" + "a".repeat(1001);
        assertParseFailure(parser, inputExceedingThousandChar, FindCommand.MESSAGE_INVALID_PREFIX_ARGUMENT);

        // Input exceeded 1000 digits for contacted date -> parse failure due to violation of upper limit.
        String inputExceedingThousandDigits = "find c/" + "1".repeat(1000);
        assertParseFailure(parser, inputExceedingThousandDigits, FindCommand.MESSAGE_CONTACTED_DATE_INVALID_ARG);
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        String singleValidArg = " n/Alex bob";
        assertParseSuccess(parser, singleValidArg, prepareFindCommand(singleValidArg));

        String validContactedDateArg = " c/1";
        assertParseSuccess(parser, validContactedDateArg, prepareFindCommand(validContactedDateArg));

        // multiple whitespaces between keywords
        String argToBeTested = " n/ Alex bob";
        assertParseSuccess(parser, " n/\n Alex \n \t bob \t", prepareFindCommand(argToBeTested));

        // Max positive integer values -> parse successful.
        String argWithMaxPositiveInteger = " c/2147483647";
        assertParseSuccess(parser, argWithMaxPositiveInteger, prepareFindCommand(argWithMaxPositiveInteger));

        // No arg for contacted date -> parse successful.
        String emptyContactedDateArg = " c/";
        assertParseSuccess(parser, emptyContactedDateArg, prepareFindCommand(emptyContactedDateArg));

        // No arg for memo -> parse successful.
        String emptyMemoArg = " m/";
        assertParseSuccess(parser, emptyMemoArg, prepareFindCommand(emptyMemoArg));

        // Input is 1000 characters -> parse successful. Boundary value.
        String inputExceedingThousandChar = " n/" + "a".repeat(1000);
        assertParseSuccess(parser, inputExceedingThousandChar, prepareFindCommand(inputExceedingThousandChar));
    }

    /**
     * Prepares the FindCommand for testing.
     *
     * @param userFindCommandArgs  User argument that is passed into the FindCommandParser.
     * @return A valid FindCommand that is derived from the user argument.
     */
    private FindCommand prepareFindCommand(String userFindCommandArgs) {
        ArgumentMultimap descriptor = ArgumentTokenizer.tokenize(userFindCommandArgs, ARRAY_OF_PREFIX);
        FindPersonPredicate predicate = new FindPersonPredicate(descriptor);
        return new FindCommand(predicate);
    }
}


package seedu.address.logic.parser.testresult;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.testresult.FindTestResultCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.testresult.TestResultContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindTestResultCommand object
 */
public class FindTestResultCommandParser implements Parser<FindTestResultCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindTestResultCommand
     * and returns a FindTestResultCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindTestResultCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTestResultCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindTestResultCommand(new TestResultContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}

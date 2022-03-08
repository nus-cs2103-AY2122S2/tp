package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FindClassCodeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.ClassCodeContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindClassCodeCommandParser implements Parser<FindClassCodeCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindClassCodeCommand
     * and returns a FindClassCodeCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindClassCodeCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindClassCodeCommand.MESSAGE_USAGE));
        }

        String[] classCodeKeywords = trimmedArgs.split("\\s+");

        return new FindClassCodeCommand(new ClassCodeContainsKeywordsPredicate(Arrays.asList(classCodeKeywords)));
    }

}

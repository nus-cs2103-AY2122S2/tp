package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FindInsuranceCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.insurance.TitleContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindInsuranceCommand object
 */
public class FindInsuranceCommandParser implements Parser<FindInsuranceCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindInsuranceCommand
     * and returns a FindInsuranceCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindInsuranceCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindInsuranceCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindInsuranceCommand(new TitleContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}

package seedu.address.logic.parser.medical;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.medical.FindMedicalCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.medical.MedicalContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindMedicalCommand object
 */
public class FindMedicalCommandParser implements Parser<FindMedicalCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindMedicalCommand
     * and returns a FindMedicalCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindMedicalCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindMedicalCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindMedicalCommand(new MedicalContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}

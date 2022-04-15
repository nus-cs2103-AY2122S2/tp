package seedu.address.logic.parser.prescription;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.prescription.FindPrescriptionCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.prescription.PrescriptionContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindPrescriptionCommand object
 */
public class FindPrescriptionCommandParser implements Parser<FindPrescriptionCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindPrescriptionCommand
     * and returns a FindPrescriptionCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindPrescriptionCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPrescriptionCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindPrescriptionCommand(new PrescriptionContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}

package seedu.address.logic.parser.consultations;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.consultation.FindConsultationCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.consultation.ConsultationContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindConsultationCommand object
 */
public class FindConsultationCommandParser implements Parser<FindConsultationCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindTestResultCommand
     * and returns a FindTestResultCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindConsultationCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindConsultationCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindConsultationCommand(new ConsultationContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}

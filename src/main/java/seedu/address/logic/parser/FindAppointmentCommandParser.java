package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FindAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.DescriptionContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindAppointmentCommand object
 */
public class FindAppointmentCommandParser implements Parser<FindAppointmentCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FindAppointmentCommand
     * and returns a FindAppointmentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindAppointmentCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindAppointmentCommand.MESSAGE_USAGE));
        }

        String[] descKeywords = trimmedArgs.split("\\s+");

        return new FindAppointmentCommand(new DescriptionContainsKeywordsPredicate(Arrays.asList(descKeywords)));
    }
}

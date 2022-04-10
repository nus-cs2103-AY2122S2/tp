package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.SortAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SortAppointmentCommand object
 */
public class SortAppointmentCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the SortAppointmentCommand
     * and returns a SortAppointmentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortAppointmentCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty() || (!trimmedArgs.equals(SortAppointmentCommand.SORT_ASCENDING)
                && !trimmedArgs.equals(SortAppointmentCommand.SORT_DESCENDING))) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortAppointmentCommand.MESSAGE_USAGE));
        }


        return new SortAppointmentCommand(trimmedArgs);
    }
}

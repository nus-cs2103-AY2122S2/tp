package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.SortRecordCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SortRecordCommand object
 */
public class SortRecordCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the SortRecordCommand
     * and returns a SortRecordCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortRecordCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (validateArgs(trimmedArgs)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortRecordCommand.MESSAGE_USAGE));
        }

        return new SortRecordCommand(trimmedArgs);
    }

    private boolean validateArgs(String arg) {
        if (arg.equals(SortRecordCommand.SORT_START_DATE_ASC)
                || arg.equals(SortRecordCommand.SORT_START_DATE_DES)
                || arg.equals(SortRecordCommand.SORT_END_DATE_ASC)
                || arg.equals(SortRecordCommand.SORT_END_DATE_DES)) {
            return false;
        }

        return true;
    }

}

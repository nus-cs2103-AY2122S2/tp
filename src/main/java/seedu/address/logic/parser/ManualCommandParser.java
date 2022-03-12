package seedu.address.logic.parser;

import seedu.address.logic.commands.ManualCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ManualCommand object
 */

public class ManualCommandParser implements Parser<ManualCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ManualCommand
     * and returns a ManualCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ManualCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        return new ManualCommand(trimmedArgs);
    }
}

package seedu.tinner.logic.parser;

import static seedu.tinner.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.tinner.logic.commands.SetReminderWindowCommand;
import seedu.tinner.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new SetReminderWindowCommand object
 */
public class SetReminderWindowCommandParser implements Parser<SetReminderWindowCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SetReminderWindowCommand
     * and returns a SetReminderWindowCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SetReminderWindowCommand parse(String args) throws ParseException {
        try {
            int window = Integer.parseInt(args.trim());
            return new SetReminderWindowCommand(window);
        } catch (NumberFormatException nfe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetReminderWindowCommand.MESSAGE_USAGE), nfe);
        }
    }

}

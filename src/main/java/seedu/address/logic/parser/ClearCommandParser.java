package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.ConfirmedClearCommand;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new ClearCommand object.
 */
public class ClearCommandParser implements Parser<ClearCommand> {
    private static final String FORCED_COMMAND_TAG = "-f";
    /**
     * Checks whether the ClearCommand is a forced clear.
     *
     * @param arguments that comes after the clear COMMAND_WORD.
     * @return true if command is a forced clear.
     */

    public static boolean isNotForcedClear(String arguments) {
        return arguments.trim().isEmpty();
    }

    /**
     * Parses the given {@code String} of user input in the context of the ClearCommand
     * and returns a ClearCommand object for execution.
     *
     * @param userInput the string name of the recipe to be viewed.
     * @return ConfirmedClearCommand object if it's a forced clear and ClearCommand if confirmation is required.
     * @throws ParseException if the user input does not conform the expected format.
     */
    @Override
    public ClearCommand parse(String userInput) throws ParseException {
        if (userInput.trim().equals(FORCED_COMMAND_TAG)) {
            return new ConfirmedClearCommand();
        } else if (userInput.trim().isEmpty()) {
            return new ClearCommand();
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClearCommand.MESSAGE_USAGE));
        }
    }
}

package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.ConfirmedResetCommand;
import seedu.address.logic.commands.ResetCommand;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new ResetCommand object.
 */
public class ResetCommandParser implements Parser<ResetCommand> {
    private static final String FORCED_COMMAND_TAG = "-f";
    /**
     * Checks whether the ResetCommand is a forced Reset.
     *
     * @param arguments that comes after the Reset COMMAND_WORD.
     * @return true if command is a forced Reset.
     */

    public static boolean isNotForcedReset(String arguments) {
        return arguments.trim().isEmpty();
    }

    /**
     * Parses the given {@code String} of user input in the context of the ResetCommand
     * and returns a ResetCommand object for execution.
     *
     * @param userInput the string name of the recipe to be viewed.
     * @return ConfirmedResetCommand object if it's a forced Reset and ResetCommand if confirmation is required.
     * @throws ParseException if the user input does not conform the expected format.
     */
    @Override
    public ResetCommand parse(String userInput) throws ParseException {
        if (userInput.trim().equals(FORCED_COMMAND_TAG)) {
            return new ConfirmedResetCommand();
        } else if (userInput.trim().isEmpty()) {
            return new ResetCommand();
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ResetCommand.MESSAGE_USAGE));
        }
    }
}

package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.ResizeCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ResizeCommand object
 */
public class ResizeCommandParser implements Parser<ResizeCommand> {

    private final int[] resizableWindowSizeList = {1, 2, 3};

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ResizeCommand parse(String args) throws ParseException {
        int resultWindowSize;

        try {
            resultWindowSize = Integer.parseInt(args.trim());
        } catch (NumberFormatException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ResizeCommand.MESSAGE_USAGE));
        }
        for (int d : resizableWindowSizeList) {
            if (resultWindowSize == d) {
                return new ResizeCommand((double) resultWindowSize);
            }
        }
        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ResizeCommand.MESSAGE_USAGE));
    }

}

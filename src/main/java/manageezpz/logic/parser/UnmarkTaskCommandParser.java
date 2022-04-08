package manageezpz.logic.parser;

import static manageezpz.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT_BIND;

import manageezpz.commons.core.index.Index;
import manageezpz.logic.commands.UnmarkTaskCommand;
import manageezpz.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new UnmarkTaskCommand object
 */
public class UnmarkTaskCommandParser implements Parser<UnmarkTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the UnmarkTaskCommand
     * and returns a UnmarkTaskCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public UnmarkTaskCommand parse(String args) throws ParseException {
        // Invalid command if args after trimming is empty or contains whitespaces
        if (args.trim().isEmpty() || args.trim().contains(" ")) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT_BIND,
                    UnmarkTaskCommand.MESSAGE_USAGE));
        }

        try {
            Index index = ParserUtil.parseIndex(args);
            return new UnmarkTaskCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(pe.getMessage() + "\n\n" + UnmarkTaskCommand.MESSAGE_USAGE, pe);
        }
    }
}

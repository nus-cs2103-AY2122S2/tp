package woofareyou.logic.parser;

import woofareyou.commons.core.Messages;
import woofareyou.commons.core.index.Index;
import woofareyou.logic.commands.DeleteCommand;
import woofareyou.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    /**
     * Regex to match charge format.
     * eg: "80.40".
     */
    public static final String VALIDATION_REGEX = "[-?0-9]+";
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        if (!args.trim().matches(VALIDATION_REGEX)) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteCommand.MESSAGE_USAGE));
        }
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(Messages.MESSAGE_INVALID_PET_DISPLAYED_INDEX);
        }
    }

}

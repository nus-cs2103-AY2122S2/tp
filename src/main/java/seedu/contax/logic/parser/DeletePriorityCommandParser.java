package seedu.contax.logic.parser;

import static seedu.contax.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.contax.commons.core.index.Index;
import seedu.contax.logic.commands.DeletePriorityCommand;
import seedu.contax.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new {@code DeletePriorityCommand} object.
 */
public class DeletePriorityCommandParser implements Parser<DeletePriorityCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of DeletePriorityCommand.
     * @param args The argument string to parse.
     * @return DeletePriorityCommand if the argument contains a valid index.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public DeletePriorityCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeletePriorityCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeletePriorityCommand.MESSAGE_USAGE), pe);
        }
    }

}

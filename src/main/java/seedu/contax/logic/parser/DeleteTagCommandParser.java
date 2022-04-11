package seedu.contax.logic.parser;


import static seedu.contax.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.contax.commons.core.index.Index;
import seedu.contax.logic.commands.DeleteTagCommand;
import seedu.contax.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code DeleteTagCommand} object.
 */
public class DeleteTagCommandParser implements Parser<DeleteTagCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of DeleteTagCommand.
     * @param args The argument string to parse.
     * @return DeleteTagCommand if the argument contains a valid index.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public DeleteTagCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteTagCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTagCommand.MESSAGE_USAGE), pe);
        }
    }

}

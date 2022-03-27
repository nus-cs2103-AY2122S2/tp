package seedu.ibook.logic.parser;

import static seedu.ibook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.ibook.commons.core.index.CompoundIndex;
import seedu.ibook.logic.commands.DeleteItemCommand;
import seedu.ibook.logic.parser.exceptions.ParseException;

public class DeleteItemCommandParser implements Parser<DeleteItemCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteItemCommand parse(String args) throws ParseException {
        try {
            CompoundIndex index = ParserUtil.parseCompoundIndex(args);
            return new DeleteItemCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteItemCommand.MESSAGE_USAGE),
                    pe);
        }
    }

}

package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteDriverCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class DeleteDriverCommandParser implements Parser<DeleteDriverCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteDriverCommand
     * and returns a DeleteDriverCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteDriverCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteDriverCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteDriverCommand.MESSAGE_USAGE), pe);
        }
    }
}

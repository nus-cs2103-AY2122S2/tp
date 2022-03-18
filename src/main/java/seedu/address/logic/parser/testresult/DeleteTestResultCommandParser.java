package seedu.address.logic.parser.testresult;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.testresult.DeleteTestResultCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteTestResultCommandParser implements Parser<DeleteTestResultCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteTestResultCommand
     * and returns a DeleteTestResultCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteTestResultCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteTestResultCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTestResultCommand.MESSAGE_USAGE), pe);
        }
    }

}

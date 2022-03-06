package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ViewClassInfoCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ViewClassInfoCommand object.
 */
public class ViewClassInfoParser implements Parser<ViewClassInfoCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ViewStudentInfoCommand
     * and returns a ViewClassInfoCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public ViewClassInfoCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ViewClassInfoCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewClassInfoCommand.MESSAGE_USAGE), pe
            );
        }
    }
}

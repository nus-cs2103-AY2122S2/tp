package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ImagesCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ImagesCommandParser implements Parser<ImagesCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ImagesCommand
     * and returns a ImagesCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ImagesCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ImagesCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ImagesCommand.MESSAGE_USAGE), pe);
        }
    }

}

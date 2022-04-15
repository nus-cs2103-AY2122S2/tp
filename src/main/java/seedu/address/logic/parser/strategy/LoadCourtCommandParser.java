package seedu.address.logic.parser.strategy;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.strategy.LoadCourtCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.image.Image;

/**
 * Parses input arguments and creates a new LoadCourtCommand object
 */
public class LoadCourtCommandParser implements Parser<LoadCourtCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the LoadCourtCommand
     * and returns a LoadCourtCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public LoadCourtCommand parse(String args) throws ParseException {
        try {
            Image image = ParserUtil.parseImage(args);
            return new LoadCourtCommand(image);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, LoadCourtCommand.MESSAGE_USAGE), pe);
        }
    }
}

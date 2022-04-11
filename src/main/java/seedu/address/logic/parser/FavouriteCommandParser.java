package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.FavouriteCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class FavouriteCommandParser implements Parser<FavouriteCommand> {

    /**
     * Parses {@code args} into a FavouriteCommand and returns it.
     *
     * @param args Index of the person to favourite.
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    @Override
    public FavouriteCommand parse(String args) throws ParseException {
        requireNonNull(args);
        try {
            Index index = ParserUtil.parseIndex(args);
            return new FavouriteCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FavouriteCommand.MESSAGE_USAGE), pe);
        }
    }

}

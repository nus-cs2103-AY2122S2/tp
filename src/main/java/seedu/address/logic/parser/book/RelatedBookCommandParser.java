package seedu.address.logic.parser.book;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.book.RelatedBookCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and create a new RelatedBookCommand object
 */
public class RelatedBookCommandParser implements Parser<RelatedBookCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RelatedBookCommand
     * and returns a RelatedBookCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RelatedBookCommand parse(String args) throws ParseException {
        requireNonNull(args);

        Index index;

        try {
            index = ParserUtil.parseIndex(args);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    RelatedBookCommand.MESSAGE_USAGE), pe);
        }

        return new RelatedBookCommand(index);
    }

}

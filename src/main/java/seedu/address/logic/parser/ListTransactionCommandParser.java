package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.ListTransactionCommand;
import seedu.address.logic.parser.exceptions.ParseException;


public class ListTransactionCommandParser implements Parser<ListTransactionCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ListTransaction
     * Command
     * and returns a ListTransactionCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListTransactionCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);

        Index index;
        try {
            index = IndexParser.parse(userInput.trim());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListTransactionCommand.MESSAGE_USAGE), ive);
        }

        return new ListTransactionCommand(index);
    }
}

package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.AddTransactionCommand;
import seedu.address.logic.commands.FindTransactionCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.transaction.util.TransactionWithIdentifierPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindTransactionCommandParser implements Parser<FindTransactionCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindTransactionCommand parse(String args) throws ParseException {
        Index index;
        try {
            index = IndexParser.parse(args.trim());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FindTransactionCommand.MESSAGE_USAGE), ive);
        }

        return new FindTransactionCommand(index, TransactionWithIdentifierPredicate::new);
    }

}

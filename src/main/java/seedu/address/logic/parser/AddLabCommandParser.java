package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LAB;

import seedu.address.logic.commands.AddLabCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lab.Lab;

/**
 * Parses input arguments and creates a new AddLabCommand object
 */
public class AddLabCommandParser implements Parser<AddLabCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddLabCommand
     * and returns a AddLabCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddLabCommand parse(String args) throws ParseException {
        try {
            ArgumentMultimap argMultimap =
                    ArgumentTokenizer.tokenize(args, PREFIX_LAB);
            if (!ArgumentTokenizer.isPrefixPresent(argMultimap, PREFIX_LAB) || !argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddLabCommand.MESSAGE_USAGE));
            }
            Lab toAdd = ParserUtil.parseLab(argMultimap.getValue(PREFIX_LAB).get());
            return new AddLabCommand(toAdd);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddLabCommand.MESSAGE_USAGE), pe);
        }
    }

}

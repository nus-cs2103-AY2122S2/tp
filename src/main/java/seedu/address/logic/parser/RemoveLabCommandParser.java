package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LAB;

import seedu.address.logic.commands.RemoveLabCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lab.Lab;

public class RemoveLabCommandParser implements Parser<RemoveLabCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RemoveLabCommand
     * and returns a RemoveLabCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format.
     */
    @Override
    public RemoveLabCommand parse(String args) throws ParseException {
        try {
            ArgumentMultimap argMultimap =
                    ArgumentTokenizer.tokenize(args, PREFIX_LAB);
            if (!ArgumentTokenizer.isPrefixPresent(argMultimap, PREFIX_LAB) || !argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveLabCommand.MESSAGE_USAGE));
            }
            Lab toRemove = ParserUtil.parseLab(argMultimap.getValue(PREFIX_LAB).get());
            return new RemoveLabCommand(toRemove);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveLabCommand.MESSAGE_USAGE), pe);
        }
    }

}

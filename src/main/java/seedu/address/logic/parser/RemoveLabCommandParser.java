package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LAB;

import seedu.address.logic.commands.RemoveLabCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.lab.Lab;

public class RemoveLabCommandParser implements Parser<RemoveLabCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RemoveLabCommand
     * and returns a RemoveLabCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public RemoveLabCommand parse(String args) throws ParseException {
        try {
            ArgumentMultimap argMultimap =
                    ArgumentTokenizer.tokenize(args, PREFIX_LAB);
            if (!isPrefixPresent(argMultimap) || !argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveLabCommand.MESSAGE_USAGE));
            }
            Lab toRemove = ParserUtil.parseLab(argMultimap.getValue(PREFIX_LAB).get());
            return new RemoveLabCommand(toRemove);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveLabCommand.MESSAGE_USAGE), pe);
        }
    }

    /**
     * Returns true if PREFIX_LAB does not contain {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean isPrefixPresent(ArgumentMultimap argumentMultimap) {
        return argumentMultimap.getValue(PREFIX_LAB).isPresent();
    }
}

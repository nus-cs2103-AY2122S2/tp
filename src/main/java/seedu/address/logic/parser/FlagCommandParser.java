package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FLAG;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.FlagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Flag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class FlagCommandParser implements Parser<FlagCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FlagCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_FLAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FlagCommand.MESSAGE_USAGE), pe);
        }

        Flag newFlag;
        if (argMultimap.getValue(PREFIX_FLAG).isPresent()) {
            newFlag = ParserUtil.parseFlag(argMultimap.getValue(PREFIX_FLAG).get());
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FlagCommand.MESSAGE_USAGE));
        }
        return new FlagCommand(index, newFlag);
    }

}

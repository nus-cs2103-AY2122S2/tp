package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LAB;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.SubmitLabCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class SubmitLabCommandParser implements Parser<SubmitLabCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SubmitLabCommand
     * and returns a SubmitLabCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public SubmitLabCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_LAB);

        if (!ArgumentTokenizer.arePrefixesPresent(argMultimap, PREFIX_LAB)
                || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SubmitLabCommand.MESSAGE_USAGE));
        }

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SubmitLabCommand.MESSAGE_USAGE), pe);
        }

        int labNumber = ParserUtil.parseLab(argMultimap.getValue(PREFIX_LAB).get()).labNumber;

        return new SubmitLabCommand(index, labNumber);
    }

}

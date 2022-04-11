package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IMAGE;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteImageCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class DeleteImageCommandParser implements Parser<DeleteImageCommand>,
        DetailedViewExecutableParser<DeleteImageCommand> {

    /**
     * Parses {@code args} into a command and returns it.
     *
     * @param args the user input to parse
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    @Override
    public DeleteImageCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_IMAGE);

        if (!arePrefixesPresent(argMultimap, PREFIX_IMAGE) || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteImageCommand.MESSAGE_USAGE));
        }

        Index personIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
        Index imageIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_IMAGE).get());

        return new DeleteImageCommand(personIndex, imageIndex);
    }

    @Override
    public DeleteImageCommand parseInDetailedViewContext(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_IMAGE);

        if (!arePrefixesPresent(argMultimap, PREFIX_IMAGE)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteImageCommand.MESSAGE_USAGE));
        }

        Index imageIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_IMAGE).get());

        return new DeleteImageCommand(imageIndex);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

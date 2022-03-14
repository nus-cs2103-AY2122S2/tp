package seedu.contax.logic.parser;

import static seedu.contax.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.stream.Stream;

import seedu.contax.logic.commands.AddTagCommand;
import seedu.contax.logic.parser.exceptions.ParseException;
import seedu.contax.model.tag.Name;
import seedu.contax.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddTagCommand object.
 */
public class AddTagCommandParser implements Parser<AddTagCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of AddTagCommand and returns an AddTagCommand for
     * execution.
     * @param args The {@code String} of arguments to be parsed.
     * @return AddTagCommand with the appropriate parameters.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public AddTagCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagCommand.MESSAGE_USAGE));
        }

        if (!Name.isValidName(argMultimap.getValue(PREFIX_NAME).get())) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }

        Tag tag = ParserUtil.parseTag(argMultimap.getValue(PREFIX_NAME).get());

        return new AddTagCommand(tag);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix ...prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

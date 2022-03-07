package seedu.contax.logic.parser;

import static seedu.contax.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.stream.Stream;

import seedu.contax.logic.commands.AddTagCommand;
import seedu.contax.logic.parser.exceptions.ParseException;
import seedu.contax.model.tag.Tag;

public class AddTagCommandParser implements Parser<AddTagCommand> {
    public AddTagCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException("Invalid command format");
        }

        Tag tag = ParserUtil.parseTag(argMultimap.getValue(PREFIX_NAME).get());

        return new AddTagCommand(tag);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix ...prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

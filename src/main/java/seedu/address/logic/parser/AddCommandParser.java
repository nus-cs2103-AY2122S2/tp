package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Field;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements CommandParser<AddCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        Prefix[] prefixes = Arrays.copyOf(Person.PREFIXES, Person.PREFIXES.length + 1);
        prefixes[prefixes.length - 1] = CliSyntax.PREFIX_TAG;
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, prefixes);
        if (!arePrefixesPresent(argMultimap, Person.REQUIRED_PREFIXES)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Person person = new Person();
        for (Prefix p : Person.PREFIXES) {
            String fieldArgs = argMultimap.getValue(p).orElse("");
            if (fieldArgs.isEmpty()) {
                continue;
            }
            Field field = Person.getParser(p).parse(fieldArgs);
            person.addField(p, field);
        }

        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(CliSyntax.PREFIX_TAG));
        person.addTags(tagList);

        return new AddCommand(person);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}

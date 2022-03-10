package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Field;
import seedu.address.model.person.FieldRegistry;
import seedu.address.model.person.Membership;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;


/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        requireNonNull(args);

        Prefix[] allPrefixes = Arrays.copyOf(FieldRegistry.PREFIXES, FieldRegistry.PREFIXES.length + 1);
        allPrefixes[allPrefixes.length - 1] = Tag.PREFIX;
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, allPrefixes);

        // Ensure that the preamble is empty, and that all required fields are present.
        if (!arePrefixesPresent(argMultimap, FieldRegistry.REQUIRED_PREFIXES)
                    || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        // Parse tags.
        Set<Tag> tags = TagParser.parseCollection(argMultimap.getAllValues(Tag.PREFIX));

        // Parse memberships.
        Set<Membership> memberships = MembershipParser.parseCollection(argMultimap.getAllValues(Membership.PREFIX));

        // Parse all fields.
        ArrayList<Field> fields = new ArrayList<>();
        for (Prefix p : FieldRegistry.PREFIXES) {
            Optional<String> argOpt = argMultimap.getValue(p);
            if (argOpt.isPresent()) {
                FieldParser<? extends Field> parser = FieldRegistry.PARSERS.get(p);
                Field field = parser.parse(argOpt.get());
                fields.add(field);
            }
        }

        Person person = new Person(fields, tags, memberships);
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

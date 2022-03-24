package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HEIGHT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JERSEY_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LINEUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLAYER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEIGHT;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lineup.Lineup;
import seedu.address.model.lineup.LineupName;
import seedu.address.model.person.Email;
import seedu.address.model.person.Height;
import seedu.address.model.person.JerseyNumber;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Weight;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     *
     * @returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PLAYER, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_HEIGHT, PREFIX_JERSEY_NUMBER, PREFIX_TAG, PREFIX_WEIGHT, PREFIX_LINEUP);

        if (arePrefixesPresent(argMultimap, PREFIX_LINEUP)
                && !arePrefixesPresent(argMultimap, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_JERSEY_NUMBER, PREFIX_HEIGHT, PREFIX_WEIGHT)) {
            if (!arePrefixesPresent(argMultimap, PREFIX_NAME)) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        AddCommand.MESSAGE_USAGE_LINEUP));
            } else {
                LineupName name = ParserUtil.parseLineupName(argMultimap.getValue(PREFIX_NAME).get());
                Lineup lineup = new Lineup(name);
                if (arePrefixesPresent(argMultimap, PREFIX_PLAYER)) {
                    // to be added later
                    // need to parse all players and put them into lineup
                }
                return new AddCommand(lineup);
            }
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_PLAYER, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_JERSEY_NUMBER, PREFIX_HEIGHT, PREFIX_WEIGHT)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        JerseyNumber jerseyNumber = ParserUtil.parseJerseyNumber(argMultimap.getValue(PREFIX_JERSEY_NUMBER).get());
        Weight weight = ParserUtil.parseWeight(argMultimap.getValue(PREFIX_WEIGHT).get());
        Height height = ParserUtil.parseHeight(argMultimap.getValue(PREFIX_HEIGHT).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Person person = new Person(name, phone, email, height, jerseyNumber, tagList, weight);

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

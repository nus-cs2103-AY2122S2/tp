package seedu.unite.logic.parser;

import static seedu.unite.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.unite.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.unite.logic.parser.CliSyntax.PREFIX_COURSE;
import static seedu.unite.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.unite.logic.parser.CliSyntax.PREFIX_MATRICCARD;
import static seedu.unite.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.unite.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.unite.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.unite.logic.parser.CliSyntax.PREFIX_TELEGRAM;

import java.util.Set;
import java.util.stream.Stream;

import seedu.unite.logic.commands.AddCommand;
import seedu.unite.logic.parser.exceptions.ParseException;
import seedu.unite.model.person.Address;
import seedu.unite.model.person.Course;
import seedu.unite.model.person.Email;
import seedu.unite.model.person.MatricCard;
import seedu.unite.model.person.Name;
import seedu.unite.model.person.Person;
import seedu.unite.model.person.Phone;
import seedu.unite.model.person.Telegram;
import seedu.unite.model.tag.Tag;


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
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_TAG, PREFIX_COURSE, PREFIX_MATRICCARD, PREFIX_TELEGRAM);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        Course course = ParserUtil.parseCourse(argMultimap.getValue(PREFIX_COURSE).orElse(""));
        MatricCard matricCard = ParserUtil.parseMatricCard(argMultimap.getValue(PREFIX_MATRICCARD).orElse(""));
        Telegram telegram = ParserUtil.parseTelegram(argMultimap.getValue(PREFIX_TELEGRAM).orElse(""));

        Person person = new Person(name, phone, email, address, tagList, course, matricCard, telegram);

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

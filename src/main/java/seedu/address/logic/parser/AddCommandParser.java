package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOBTITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRONOUN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.LabelUtil;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.label.Label;
import seedu.address.model.person.Address;
import seedu.address.model.person.Company;
import seedu.address.model.person.Email;
import seedu.address.model.person.JobTitle;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Pronoun;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer
                        .tokenize(args,
                                PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG, PREFIX_COMPANY,
                                PREFIX_JOBTITLE, PREFIX_PRONOUN);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Company company = null;
        JobTitle jobTitle = null;
        Map<Label, Phone> numbers = ParserUtil.parseNumbers(argMultimap.getAllValues(PREFIX_PHONE));
        numbers = LabelUtil.replacePhonePlaceholders(numbers);

        Map<Label, Email> emails = ParserUtil.parseEmails(argMultimap.getAllValues(PREFIX_EMAIL));
        emails = LabelUtil.replaceEmailPlaceholders(emails);

        Map<Label, Address> addresses = ParserUtil.parseAddresses(argMultimap.getAllValues(PREFIX_ADDRESS));
        addresses = LabelUtil.replaceAddressPlaceholders(addresses);

        if (arePrefixesPresent(argMultimap, PREFIX_COMPANY)) {
            company = ParserUtil.parseCompany(argMultimap.getValue(PREFIX_COMPANY).get());
        }
        if (arePrefixesPresent(argMultimap, PREFIX_JOBTITLE)) {
            jobTitle = ParserUtil.parseJobTitle(argMultimap.getValue(PREFIX_JOBTITLE).get());
        }

        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        Set<Pronoun> pronounList = ParserUtil.parsePronouns(argMultimap.getAllValues(PREFIX_PRONOUN));

        Person person = new Person(name, numbers, emails, addresses,
                company, jobTitle, pronounList, tagList);

        return new AddCommand(person);
    }

}

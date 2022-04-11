package seedu.trackbeau.logic.parser.customer;

import static seedu.trackbeau.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_ALLERGIES;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_BIRTHDATE;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_HAIRTYPE;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_REGDATE;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_SERVICES;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_SKINTYPE;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_STAFFS;

import java.util.Set;

import seedu.trackbeau.logic.commands.customer.AddCustomerCommand;
import seedu.trackbeau.logic.parser.ArgumentMultimap;
import seedu.trackbeau.logic.parser.ArgumentTokenizer;
import seedu.trackbeau.logic.parser.Parser;
import seedu.trackbeau.logic.parser.ParserUtil;
import seedu.trackbeau.logic.parser.exceptions.ParseException;
import seedu.trackbeau.model.customer.Address;
import seedu.trackbeau.model.customer.Birthdate;
import seedu.trackbeau.model.customer.Customer;
import seedu.trackbeau.model.customer.Email;
import seedu.trackbeau.model.customer.HairType;
import seedu.trackbeau.model.customer.Name;
import seedu.trackbeau.model.customer.Phone;
import seedu.trackbeau.model.customer.RegistrationDate;
import seedu.trackbeau.model.customer.SkinType;
import seedu.trackbeau.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCustomerCommand object
 */
public class AddCustomerCommandParser implements Parser<AddCustomerCommand> {
    public static final String EMPTY_HAIR_TYPE = "No data";
    public static final String EMPTY_SKIN_TYPE = "No data";
    public static final String EMPTY_DATE = "01-01-1000"; //impossible date

    /**
     * Parses the given {@code String} of arguments in the context of the AddCustomerCommand
     * and returns an AddCustomerCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCustomerCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_SKINTYPE, PREFIX_HAIRTYPE,
                        PREFIX_STAFFS, PREFIX_SERVICES, PREFIX_ALLERGIES, PREFIX_BIRTHDATE, PREFIX_REGDATE);

        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE,
                PREFIX_EMAIL, PREFIX_REGDATE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCustomerCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        RegistrationDate regDate = ParserUtil
                .parseRegistrationDate(argMultimap.getValue(PREFIX_REGDATE)
                .get());
        SkinType skinType = new SkinType(EMPTY_SKIN_TYPE);
        if (argMultimap.getValue(PREFIX_SKINTYPE).isPresent()) {
            skinType = ParserUtil.parseSkinType(argMultimap.getValue(PREFIX_SKINTYPE).get());
        }
        HairType hairType = new HairType((EMPTY_HAIR_TYPE));
        if (argMultimap.getValue(PREFIX_HAIRTYPE).isPresent()) {
            hairType = ParserUtil.parseHairType(argMultimap.getValue(PREFIX_HAIRTYPE).get());
        }
        Birthdate birthdate = new Birthdate(EMPTY_DATE);
        if (argMultimap.getValue(PREFIX_BIRTHDATE).isPresent()) {
            birthdate = ParserUtil.parseBirthdate(argMultimap.getValue(PREFIX_BIRTHDATE).get());
        }
        Set<Tag> staffList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_STAFFS));
        Set<Tag> serviceList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_SERVICES));
        Set<Tag> allergyList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_ALLERGIES));

        Customer customer = new Customer(name, phone, email,
                address, skinType, hairType, staffList,
                serviceList, allergyList, birthdate, regDate);

        return new AddCustomerCommand(customer);
    }

}

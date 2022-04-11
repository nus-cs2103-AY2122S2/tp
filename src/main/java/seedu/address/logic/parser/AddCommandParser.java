package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PREFERENCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_USERIMAGE;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Preference;
import seedu.address.model.person.UserType;
import seedu.address.model.property.Property;
import seedu.address.model.userimage.UserImage;
import seedu.address.model.util.UserTypeUtil;

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
                        PREFIX_PROPERTY, PREFIX_PREFERENCE, PREFIX_USERIMAGE);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Set<Property> properties = ParserUtil.parseProperties(argMultimap.getAllValues(PREFIX_PROPERTY));
        // preference is optional since it should not be present if the person being added is a not a buyer.
        Optional<String> preferenceArg = argMultimap.getValue(PREFIX_PREFERENCE);
        Optional<Preference> preference = preferenceArg.isPresent()
                ? Optional.of(ParserUtil.parsePreference(preferenceArg.get()))
                : Optional.empty();

        // if the user did not input any properties or preference, throw an error as user needs to be a buyer or seller
        if (properties.isEmpty() && preferenceArg.isEmpty() || !properties.isEmpty() && preferenceArg.isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UserType.MESSAGE_USAGE));
        }
        UserType userType;
        // if the user has at least 1 property, he is a seller
        if (!properties.isEmpty()) {
            userType = UserTypeUtil.createSeller();
        // else the user has a preference, he is a buyer
        } else {
            userType = UserTypeUtil.createBuyer();
        }
        Set<UserImage> userImages = ParserUtil.parseUserImages(argMultimap.getAllValues(PREFIX_USERIMAGE));

        Person person = new Person(name, phone, email, address, properties, preference, userType, userImages);

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

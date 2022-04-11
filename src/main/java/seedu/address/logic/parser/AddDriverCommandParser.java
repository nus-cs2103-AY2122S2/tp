package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.Random;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddDriverCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.customer.AddressCustomer;
import seedu.address.model.customer.Customer;
import seedu.address.model.customer.NameCustomer;
import seedu.address.model.customer.PhoneCustomer;
import seedu.address.model.driver.Driver;
import seedu.address.model.driver.NameDriver;
import seedu.address.model.driver.PhoneDriver;

/**
 * Parses input arguments and creates a new AddDriverCommandParser object
 */
public class AddDriverCommandParser implements Parser<AddDriverCommand> {

    protected String randomString() {
        String nameChar = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder randomString = new StringBuilder();
        Random random = new Random();
        while (randomString.length() < 8) { // length of the random string.
            int index = (int) (random.nextFloat() * nameChar.length());
            randomString.append(nameChar.charAt(index));
        }
        String finalRandomString = randomString.toString();
        return finalRandomString;

    }

    /**
     * Parses the given {@code String} of arguments in the context of the AddDriverCommand
     * and returns an AddDriverCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddDriverCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_PHONE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddDriverCommand.MESSAGE_USAGE));
        }

        NameDriver name = ParserUtil.parseNameDriver(argMultimap.getValue(PREFIX_NAME).get());
        PhoneDriver phone = ParserUtil.parsePhoneDriver(argMultimap.getValue(PREFIX_PHONE).get());

        NameCustomer nameCustomer = ParserUtil.parseNameCustomer(randomString());
        PhoneCustomer phoneCustomer = ParserUtil.parsePhoneCustomer(argMultimap.getValue(PREFIX_PHONE).get());
        AddressCustomer addressCustomer = ParserUtil.parseAddressCustomer(randomString());

        Driver driver = new Driver(name, phone);
        Customer customer = new Customer(nameCustomer, phoneCustomer, addressCustomer);

        return new AddDriverCommand(driver, customer);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}

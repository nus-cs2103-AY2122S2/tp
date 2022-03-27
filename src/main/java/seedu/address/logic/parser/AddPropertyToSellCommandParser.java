package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOUSE_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE_RANGE;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.AddPropertyToSellCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.property.Address;
import seedu.address.model.property.House;
import seedu.address.model.property.HouseType;
import seedu.address.model.property.PriceRange;
import seedu.address.model.property.PropertyToSell;

public class AddPropertyToSellCommandParser implements Parser<AddPropertyToSellCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code AddPropertyToSellCommand}
     * and returns a {@code AddPropertyToSellCommand} object for execution.
     */
    public AddPropertyToSellCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_HOUSE_TYPE, PREFIX_ADDRESS,
                PREFIX_PRICE_RANGE, PREFIX_LOCATION);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddPropertyToSellCommand.MESSAGE_USAGE), ive);
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_ADDRESS, PREFIX_PRICE_RANGE, PREFIX_HOUSE_TYPE, PREFIX_LOCATION)) {
            throw new ParseException(AddPropertyToSellCommand.MESSAGE_USAGE);
        }
        HouseType houseType = ParserUtil.parseHouseType(argMultimap.getValue(PREFIX_HOUSE_TYPE).get());
        String location = ParserUtil.parseLocation(argMultimap.getValue(PREFIX_LOCATION).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        PriceRange priceRange = ParserUtil.parsePriceRange(argMultimap.getValue(PREFIX_PRICE_RANGE).get());
        PropertyToSell propertyToSell = new PropertyToSell(new House(houseType, location), priceRange, address);

        return new AddPropertyToSellCommand(index, propertyToSell);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

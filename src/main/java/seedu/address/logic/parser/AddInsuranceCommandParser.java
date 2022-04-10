package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddInsuranceCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.insurance.Insurance;
import seedu.address.model.insurance.Price;
import seedu.address.model.insurance.Title;


/**
 * Parses input arguments and creates a new AddInsuranceCommand object
 */
public class AddInsuranceCommandParser implements Parser<AddInsuranceCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddInsuranceCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TITLE, PREFIX_PRICE);

        if (!arePrefixesPresent(argMultimap, PREFIX_TITLE, PREFIX_PRICE)
                || !argMultimap.getPreamble().isEmpty()) {
            System.out.println(arePrefixesPresent(argMultimap, PREFIX_TITLE, PREFIX_PRICE));
            System.out.println(argMultimap.getPreamble().isEmpty());
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddInsuranceCommand.MESSAGE_USAGE));
        }

        Title title = InsuranceParserUtil.parseTitle(argMultimap.getValue(PREFIX_TITLE).get());
        Price price = InsuranceParserUtil.parsePrice(argMultimap.getValue(PREFIX_PRICE).get());


        Insurance insurance = new Insurance(title, price);

        return new AddInsuranceCommand(insurance);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}

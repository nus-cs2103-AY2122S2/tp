package seedu.trackbeau.logic.parser.service;

import static seedu.trackbeau.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_PRICE;

import seedu.trackbeau.logic.commands.service.AddServiceCommand;
import seedu.trackbeau.logic.parser.ArgumentMultimap;
import seedu.trackbeau.logic.parser.ArgumentTokenizer;
import seedu.trackbeau.logic.parser.Parser;
import seedu.trackbeau.logic.parser.ParserUtil;
import seedu.trackbeau.logic.parser.exceptions.ParseException;
import seedu.trackbeau.model.service.Duration;
import seedu.trackbeau.model.service.Price;
import seedu.trackbeau.model.service.Service;
import seedu.trackbeau.model.service.ServiceName;


/**
 * Parses input arguments and creates a new AddServiceCommandParser object
 */
public class AddServiceCommandParser implements Parser<AddServiceCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddServiceCommand
     * and returns an AddServiceCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddServiceCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PRICE, PREFIX_DURATION);

        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_PRICE, PREFIX_DURATION)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddServiceCommand.MESSAGE_USAGE));
        }

        ServiceName name = ParserUtil.parseServiceName(argMultimap.getValue(PREFIX_NAME).get());
        Price price = ParserUtil.parsePrice(argMultimap.getValue(PREFIX_PRICE).get());
        Duration duration = ParserUtil.parseDuration(argMultimap.getValue(PREFIX_DURATION).get());

        Service service = new Service(name, price, duration);

        return new AddServiceCommand(service);
    }

}

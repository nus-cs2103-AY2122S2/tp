package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INFORMATION;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddProcCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.procedure.Completion;
import seedu.address.model.procedure.Cost;
import seedu.address.model.procedure.Date;
import seedu.address.model.procedure.Information;
import seedu.address.model.procedure.Procedure;


/**
 * Parses input arguments and creates a new AddProcCommand object
 */
public class AddProcCommandParser implements Parser<AddProcCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddProcCommand
     * and returns an AddProcCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddProcCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_INFORMATION, PREFIX_COST, PREFIX_DATE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddProcCommand.MESSAGE_USAGE), pe);
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_INFORMATION, PREFIX_COST, PREFIX_DATE)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddProcCommand.MESSAGE_USAGE));
        }

        Information information = ParserUtil.parseInformation(argMultimap.getValue(PREFIX_INFORMATION).get());
        Cost cost = ParserUtil.parseCost(argMultimap.getValue(PREFIX_COST).get());
        Date date = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get());
        // New Procedure is not completed by default
        Completion completion = new Completion("false");

        Procedure procedure = new Procedure(information, date, cost, completion);

        return new AddProcCommand(index, procedure);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}

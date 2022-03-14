package seedu.address.logic.parser;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.lab.Lab;
import seedu.address.model.person.lab.LabStatus;
import seedu.address.model.person.lab.StudentHasLabPredicate;

import java.util.stream.Stream;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

public class FilterCommandParser implements Parser<FilterCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterCommand
     * and returns a FilterCommand object for execution
     *
     * @throws ParseException if the user input does not conform the expected input
     */
    public FilterCommand parse(String args) throws ParseException {

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_LAB, PREFIX_LABSTATUS);
        if (!arePrefixesPresent(argMultimap, PREFIX_LAB, PREFIX_LABSTATUS)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }

        Lab lab = ParserUtil.parseLab(argMultimap.getValue(PREFIX_LAB).get());
        LabStatus labStatus = ParserUtil.parseLabStatus(argMultimap.getValue(PREFIX_LABSTATUS).get());

        return new FilterCommand(new StudentHasLabPredicate(lab.thatIs(labStatus)));

    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}

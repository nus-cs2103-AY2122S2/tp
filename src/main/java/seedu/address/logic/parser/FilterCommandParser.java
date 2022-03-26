package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LAB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LABSTATUS;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lab.Lab;
import seedu.address.model.lab.LabStatus;
import seedu.address.model.lab.StudentHasLabPredicate;

/**
 * Parses input arguments and creates a FilterCommand object
 */
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
        if (!ArgumentTokenizer.arePrefixesPresent(argMultimap, PREFIX_LAB, PREFIX_LABSTATUS)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }

        Lab lab = ParserUtil.parseLab(argMultimap.getValue(PREFIX_LAB).get());
        LabStatus labStatus = ParserUtil.parseLabStatus(argMultimap.getValue(PREFIX_LABSTATUS).get());

        return new FilterCommand(new StudentHasLabPredicate(lab.of(labStatus)));

    }

}

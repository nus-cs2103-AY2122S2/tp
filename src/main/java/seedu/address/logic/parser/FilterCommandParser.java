package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BLOCK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COVID_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FACULTY;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.commands.FilterCommand.FilterDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FilterCommand object
 */
public class FilterCommandParser implements Parser<FilterCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterCommand
     * and returns a FilterCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterCommand parse(String args) throws ParseException {

        if (args.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_FACULTY, PREFIX_COVID_STATUS, PREFIX_BLOCK);

        FilterDescriptor filterDescriptor = new FilterDescriptor();
        if (argMultimap.getValue(PREFIX_FACULTY).isPresent()) {
            filterDescriptor.setFaculty(ParserUtil.parseFaculty(
                    argMultimap.getValue(PREFIX_FACULTY).get()));
        }
        if (argMultimap.getValue(PREFIX_COVID_STATUS).isPresent()) {
            filterDescriptor.setCovidStatus(ParserUtil.parseCovidStatus(
                    argMultimap.getValue(PREFIX_COVID_STATUS).get()));
        }
        if (argMultimap.getValue(PREFIX_BLOCK).isPresent()) {
            filterDescriptor.setBlock(ParserUtil.parseBlock(
                    argMultimap.getValue(PREFIX_BLOCK).get()));
        }

        if (filterDescriptor.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }

        return new FilterCommand(filterDescriptor);
    }

}

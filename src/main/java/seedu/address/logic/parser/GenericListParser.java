package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_FILTER_ARGUMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILTER_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_ARGUMENT;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.applicant.ListApplicantCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public abstract class GenericListParser<T extends Command> implements Parser<T> {

    @Override
    public T parse(String userInput) throws ParseException {
        if (userInput.equals("")) {
            return returnFullList();
        }
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(userInput, PREFIX_FILTER_TYPE, PREFIX_FILTER_ARGUMENT,
                PREFIX_SORT_ARGUMENT);
        boolean isFilterTypeExist = argMultimap.getValue(PREFIX_FILTER_TYPE).isPresent();
        boolean isFilterArgumentExist = argMultimap.getValue(PREFIX_FILTER_ARGUMENT).isPresent();
        boolean isSortArgumentExist = argMultimap.getValue(PREFIX_SORT_ARGUMENT).isPresent();

        if (isFilterArgumentExist && isFilterTypeExist && isSortArgumentExist) {
            return parseFilterAndSort(argMultimap);
        } else if (isFilterArgumentExist && isFilterTypeExist) {
            return parseFilter(argMultimap);
        } else if (isSortArgumentExist) {
            return parseSort(argMultimap);
        } else {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    ListApplicantCommand.MESSAGE_USAGE));
        }
    }

    public abstract T parseFilterAndSort(ArgumentMultimap argMultimap) throws ParseException;

    /**
     * Returns {T} command
     * @return
     */
    public abstract T returnFullList();

    /**
     * Parses the given {@code String} of arguments in the context of performing sort feature
     * and returns an {T} object for execution.
     * @param argMultimap The input arguments string
     * @return {T} object with respective sort argument for execution
     * @throws ParseException if the user input does not conform the expected sort format
     */
    public abstract T parseSort(ArgumentMultimap argMultimap) throws ParseException;

    /**
     * Parses the given {@code String} of arguments in the context of performing filter feature
     * and returns an {T} object for execution.
     * @param argMultimap The input arguments string
     * @return {T} object with respective filter type and filter argument for execution
     * @throws ParseException if the user input does not conform the expected filter format
     */
    public abstract T parseFilter(ArgumentMultimap argMultimap) throws ParseException;
}

package manageezpz.logic.parser;

import static manageezpz.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static manageezpz.logic.parser.CliSyntax.PREFIX_DATE;
import static manageezpz.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static manageezpz.logic.parser.CliSyntax.PREFIX_TASK;

import java.util.Arrays;
import java.util.List;

import manageezpz.logic.commands.FindCommand;
import manageezpz.logic.parser.exceptions.ParseException;
import manageezpz.model.person.NameContainsKeywordsPredicate;
import manageezpz.model.task.Date;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {
    private static final Prefix[] VALID_PREFIXES = {PREFIX_DESCRIPTION, PREFIX_DATE, PREFIX_TASK};

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultiMap = ArgumentTokenizer.tokenize(args, VALID_PREFIXES);

        if (argMultiMap.isPrefixExist(PREFIX_TASK) && argMultiMap.isPrefixExist(PREFIX_DATE)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        } else if (argMultiMap.isPrefixExist(PREFIX_TASK)) {
            if (!argMultiMap.isPrefixExist(PREFIX_DESCRIPTION)) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
            String trimmedArgs = argMultiMap.getValue(PREFIX_DESCRIPTION).get().trim();
            if (trimmedArgs.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
            String[] taskKeywords = trimmedArgs.split("\\s+");
            return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(taskKeywords)));
        } else if (argMultiMap.isPrefixExist(PREFIX_DATE)) {
            String dateString = argMultiMap.getValue(PREFIX_DATE).get();
            if (!Date.isValidDate(dateString)) {
                throw new ParseException(Date.MESSAGE_CONSTRAINTS);
            }
            Date date = new Date(dateString);
            return new FindCommand(date);
        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
    }

}

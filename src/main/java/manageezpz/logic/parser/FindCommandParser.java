package manageezpz.logic.parser;

import static manageezpz.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static manageezpz.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static manageezpz.logic.parser.CliSyntax.PREFIX_EVENT;
import static manageezpz.logic.parser.CliSyntax.PREFIX_TASK;
import static manageezpz.logic.parser.CliSyntax.PREFIX_TODO;

import manageezpz.logic.commands.FindCommand;
import manageezpz.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {
    private static final Prefix[] TASK_PREFIXES
            = {PREFIX_TASK, PREFIX_TODO, PREFIX_DEADLINE, PREFIX_EVENT};

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultiMap = ArgumentTokenizer.tokenize(args, TASK_PREFIXES);

        boolean hasAnyTaskTypeOption = checkForTaskTypeOption(argMultiMap);

        if (hasAnyTaskTypeOption) {
            return new FindTaskCommandParser().parse(args);
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
    }

    private boolean checkForTaskTypeOption(ArgumentMultimap argMultiMap) {
        for (Prefix prefix : TASK_PREFIXES) {
            if (argMultiMap.isPrefixExist(prefix)) {
                return true;
            }
        }
        return false;
    }
}

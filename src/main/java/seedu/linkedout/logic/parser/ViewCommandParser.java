package seedu.linkedout.logic.parser;

import static seedu.linkedout.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.linkedout.logic.commands.ViewCommand;
import seedu.linkedout.logic.parser.exceptions.ParseException;
import seedu.linkedout.model.applicant.NameContainsAllKeywordsPredicate;

/**
 * Parses input arguments and creates a new ViewCommand object
 */
public class ViewCommandParser implements Parser<ViewCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ViewCommand
     * and returns a ViewCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
        }

        return new ViewCommand(new NameContainsAllKeywordsPredicate(trimmedArgs));
    }

}
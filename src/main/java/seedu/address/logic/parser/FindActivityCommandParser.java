package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FindActivityCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.activity.ActivityContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindActivityCommandParser implements Parser<FindActivityCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FindActivityCommand
     * and returns a FindActivityCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindActivityCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        //Throw exception when input is empty
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindActivityCommand.MESSAGE_USAGE));
        }

        String[] activityKeywords = trimmedArgs.split("\\s+");

        assert activityKeywords.length > 0 : "Keywords for findactivity command cannot be empty";

        return new FindActivityCommand(new ActivityContainsKeywordsPredicate(Arrays.asList(activityKeywords)));
    }
}

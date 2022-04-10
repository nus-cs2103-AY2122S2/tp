package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FindRecordCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.record.RecordContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindRecordCommand object
 */
public class FindRecordCommandParser implements Parser<FindRecordCommand> {


    /**
     * Parses the given {@code String} of arguments in the context of the FindRecordCommand
     * and returns a FindRecordCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindRecordCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindRecordCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindRecordCommand(new RecordContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}

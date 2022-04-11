package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.FindTutorialGroupCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tutorialgroup.TutorialGroupKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindTutorialGroupCommand object
 */
public class FindTutorialGroupParser implements Parser<FindTutorialGroupCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindTutorialGroupCommand
     * and returns a FindTutorialGroupCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindTutorialGroupCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTutorialGroupCommand.MESSAGE_USAGE));
        }

        // Check for presence of whitespace
        int whitespacePos = trimmedArgs.indexOf(" ");
        if (whitespacePos == -1) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTutorialGroupCommand.MESSAGE_USAGE));
        }

        // remove extra whitespaces
        String moduleCode = trimmedArgs.substring(0, whitespacePos).trim();
        String trimmedGroup = trimmedArgs.substring(whitespacePos).trim();
        String trimmedTutorialGroup = moduleCode + " " + trimmedGroup;
        return new FindTutorialGroupCommand(new TutorialGroupKeywordsPredicate(trimmedTutorialGroup));
    }
}

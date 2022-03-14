package seedu.trackbeau.logic.parser;

import static seedu.trackbeau.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.trackbeau.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.trackbeau.logic.commands.FindCommand;
import seedu.trackbeau.logic.commands.HelpCommand;
import seedu.trackbeau.logic.parser.exceptions.ParseException;
import seedu.trackbeau.model.customer.SearchContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<searchAreaKeyWord>\\S+)(?<arguments>.*)");

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String userInput) throws ParseException {

        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String searchAreaKeyWord = matcher.group("searchAreaKeyWord");
        final String arguments = matcher.group("arguments");

        String trimmedArgs = arguments.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        List argumentList = Arrays.asList(trimmedArgs.split("\\s+"));

        switch (searchAreaKeyWord.toLowerCase()) {
        case "name":
            return new FindCommand(new SearchContainsKeywordsPredicate("getName", argumentList));
        case "phone":
            return new FindCommand(new SearchContainsKeywordsPredicate("getPhone", argumentList));
        case "skintype":
            return new FindCommand(new SearchContainsKeywordsPredicate("getSkinType", argumentList));
        case "hairtype":
            return new FindCommand(new SearchContainsKeywordsPredicate("getHairType", argumentList));
        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}

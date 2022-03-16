package seedu.trackbeau.logic.parser;

import static seedu.trackbeau.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.trackbeau.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
        String[] splitUserInput = matcherParse(userInput);
        List<String> argumentList = Arrays.asList(splitUserInput[1].split("\\s+"))
                .stream().map(String::toLowerCase).collect(Collectors.toList());

        switch (splitUserInput[0].toLowerCase()) {
        case "name":
            return new FindCommand(new SearchContainsKeywordsPredicate("getName",
                    0, argumentList));
        case "phone":
            return new FindCommand(new SearchContainsKeywordsPredicate("getPhone",
                    0, argumentList));
        case "skintype":
            return new FindCommand(new SearchContainsKeywordsPredicate("getSkinType",
                    0, argumentList));
        case "hairtype":
            return new FindCommand(new SearchContainsKeywordsPredicate("getHairType",
                    0, argumentList));
        case "staffpref":
            return new FindCommand(new SearchContainsKeywordsPredicate("getStaffs",
                    1, argumentList));
        case "servicepref":
            return new FindCommand(new SearchContainsKeywordsPredicate("getServices",
                    1, argumentList));
        case "allergies":
            return new FindCommand(new SearchContainsKeywordsPredicate("getAllergies",
                    1, argumentList));
        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    /**
     * Parses the given {@code String} of arguments and returns a filtered String array.
     * @throws ParseException if the user input does not conform the expected format
     */
    public String[] matcherParse(String userInput) throws ParseException {
        String[] splitUserInput = new String[2];

        userInput = userInput.trim().replaceAll("\\n", "");

        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput);
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        splitUserInput[0] = matcher.group("searchAreaKeyWord");
        splitUserInput[1] = matcher.group("arguments").trim();

        if (splitUserInput[1].isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        return splitUserInput;
    }
}

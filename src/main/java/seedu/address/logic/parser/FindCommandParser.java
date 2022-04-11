package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.util.StringUtil.trimExtraWhiteSpaces;
import static seedu.address.logic.commands.FindCommand.NO_PREFIX_MESSAGE;
import static seedu.address.logic.parser.CliSyntax.ARRAY_OF_PREFIX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACTED_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMO;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.predicate.FindPersonPredicate;


/**
 * Parses input arguments and creates a new FindCommand object.
 */
public class FindCommandParser implements Parser<FindCommand> {
    private static final Logger LOGGER = Logger.getLogger(FindCommandParser.class.getName());

    /**
     * Parses the given {@code String} of arguments into the FindCommand. Trims off all leading and trailing white
     * space and returns a FindCommand object for execution.
     *
     * @param args String argument to be parsed into FindCommand.
     * @return FindCommand.
     * @throws ParseException If the user input does not conform the expected format.
     */
    public FindCommand parse(String args) throws ParseException {
        LOGGER.log(Level.INFO, "Parsing user input");
        String argMapInput = makeProperArgMapInput(args);
        ArgumentMultimap argMultimap = createArgMap(argMapInput);
        FindPersonPredicate predicate = new FindPersonPredicate(argMultimap);
        return new FindCommand(predicate);
    }

    /**
     * Cleans up user input and make it appropriate to be parsed into an ArgumentMultimap object.
     *
     * @param args Unmodified user input.
     * @return Valid input string that can be used for the ArgumentMultimap object.
     * @throws ParseException Thrown when the user argument is empty.
     */
    private String makeProperArgMapInput(String args) throws ParseException {
        // Removes trailing whitespace from the user input
        String trimmedArgs = args.stripTrailing();
        // Regex to replace 2 or more consecutive whitespaces with a single whitespace between words
        String modifiedString = trimExtraWhiteSpaces(trimmedArgs);
        if (modifiedString.isEmpty()) {
            LOGGER.log(Level.INFO, "Input is empty");
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        return modifiedString;
    }

    /**
     * Parses the given user argument and check if there is any valid prefix.
     * If no valid prefix is found, a parse exception is thrown. Else, a new ArgumentMultimap is created.
     *
     * @param modifiedString User input that has its trailing and leading whitespaces removed.
     * @return ArgumentMultimap that stores the description to search a person by.
     * @throws ParseException If there are no valid prefix in the user input.
     */
    private ArgumentMultimap createArgMap(String modifiedString) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(modifiedString, ARRAY_OF_PREFIX);
        if (argMultimap.hasNoValidPrefix()) {
            LOGGER.log(Level.INFO, "Input has no valid prefix");
            throw new ParseException(NO_PREFIX_MESSAGE);
        }
        checkPrefixArgsInProperFormat(argMultimap);
        return argMultimap;
    }

    /**
     * Checks if a valid ContactedDate argument is given for the FindCommand. Only non-negative integer values are
     * allowed as argument.Non-negative value is within the range of 0 to 2147483647. If the ContactedDate argument is
     * empty, it would be of the form " c/" and that is a valid user argument for the ContactedDate prefix. See
     * ContactedDateMatchPredicate for more details.
     *
     * @param contactedDateArg The contacted date argument in the FindCommand.
     * @throws ParseException Thrown when an invalid ContactedDate argument is received by the FindCommandParser.
     */
    private void checkValidContactedDateArg(String contactedDateArg) throws ParseException {
        // If contactedDateArg is empty, it is a valid argument.
        if (!contactedDateArg.isEmpty()) {
            try {
                int parsedIntArg = Integer.parseInt(contactedDateArg);
                if (parsedIntArg < 0) {
                    throw new ParseException(FindCommand.MESSAGE_CONTACTED_DATE_INVALID_ARG);
                }
            } catch (NumberFormatException formatException) {
                throw new ParseException(FindCommand.MESSAGE_CONTACTED_DATE_INVALID_ARG);
            }
        }
    }

    /**
     * Checks if a valid memo argument is given for the FindCommand. The number of characters in the memo
     * argument must be between 0 to 1000.
     *
     * @param memoInputArgs The memo argument in the FindCommand.
     * @throws ParseException Thrown when the number of characters in the memo argument is not between 0 to 1000.
     */
    private void checkValidMemo(String memoInputArgs) throws ParseException {
        if (memoInputArgs.toCharArray().length > FindCommand.CHARACTER_LIMIT) {
            String invalidMemoArgument = String.format("Memo argument must be between 0 to %s characters long",
                    FindCommand.CHARACTER_LIMIT);
            throw new ParseException(invalidMemoArgument);
        }
    }

    /**
     * Checks if every prefix argument that is given for the FindCommand is between 1 and 1000 characters.
     *
     * @param multimap ArgumentMultiMap object that stores the description to search a person by.
     * @param prefix Prefix whose argument is being checked.
     * @throws ParseException Thrown when the number of characters in the prefix argument is not between 1 to 1000
     * characters.
     */
    private void checkValidRemainingPrefix(ArgumentMultimap multimap, Prefix prefix) throws ParseException {
        List<String> prefixArgs = multimap.getAllValues(prefix);
        for (String arg : prefixArgs) {
            if (arg.length() > FindCommand.CHARACTER_LIMIT || arg.isEmpty()) {
                throw new ParseException(FindCommand.MESSAGE_INVALID_PREFIX_ARGUMENT);
            }
        }
    }

    /**
     * Checks if the argument specified by the user is in a proper format and meets the constraints specified by
     * each individual attributes.
     *
     * @param multimap ArgumentMultimap object that contains the arguments specified by the user for the find command.
     * @throws ParseException If any of the given arguments are invalid.
     */
    private void checkPrefixArgsInProperFormat(ArgumentMultimap multimap) throws ParseException {
        List<Prefix> prefixes = multimap.getAllAvailablePrefix();
        for (Prefix prefix : prefixes) {
            String inputArg = multimap.getValue(prefix).orElse("");
            if (prefix.equals(PREFIX_CONTACTED_DATE)) {
                checkValidContactedDateArg(inputArg);
            } else if (prefix.equals(PREFIX_MEMO)) {
                checkValidMemo(inputArg);
            } else {
                checkValidRemainingPrefix(multimap, prefix);
            }
        }
    }
}

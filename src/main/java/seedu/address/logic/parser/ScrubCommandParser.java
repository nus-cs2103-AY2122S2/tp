package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.ScrubCommand.NO_VALID_PREFIX;
import static seedu.address.logic.parser.CliSyntax.ARRAY_OF_PREFIX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.ScrubCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.predicate.ScrubPersonPredicate;

/**
 * Parser for the {@link ScrubCommand} class. Ensures that arguments that
 * are passed in the ScrubCommand is in correct format.
 */
public class ScrubCommandParser implements Parser<ScrubCommand> {
    private static final Logger LOGGER = Logger.getLogger(ScrubCommandParser.class.getName());

    /**
     * Parses the given {@code String} of arguments into the ScrubCommand. Trims off all
     * leading and trailing white space and returns a ScrubCommand object for execution.
     *
     * @param args String argument to be parsed into ScrubCommand.
     * @return ScrubCommand.
     * @throws ParseException If the user input does not conform the expected format.
     */
    public ScrubCommand parse(String args) throws ParseException {
        LOGGER.log(Level.INFO, "Parsing user input");
        String modifiedArguments = processArguments(args);
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(modifiedArguments, ARRAY_OF_PREFIX);
        if (argumentMultimap.hasNoValidPrefix()) {
            LOGGER.log(Level.INFO, "Input has no valid prefix");
            throw new ParseException(NO_VALID_PREFIX);
        }

        if (argumentMultimap.contains(PREFIX_EMAIL)) {
            checkCorrectEmailFormat(argumentMultimap);
        }

        if (argumentMultimap.contains(PREFIX_TAG)) {
            checkPrefixArgsInProperFormat(argumentMultimap.getAllValues(PREFIX_TAG), PREFIX_TAG);
        }

        if (argumentMultimap.contains(PREFIX_PHONE)) {
            checkPrefixArgsInProperFormat(argumentMultimap.getAllValues(PREFIX_PHONE), PREFIX_PHONE);
        }

        ScrubPersonPredicate predicate = new ScrubPersonPredicate(argumentMultimap);
        return new ScrubCommand(predicate);
    }

    /**
     * Process arguments to ensure they are in the right format for ScrubCommand. Some checks that
     * include finding if valid prefix (phone, email, tags) are present in the argument
     * and invalid prefix (address, memo, name) are not present in the argument. Email processing
     * is also done here where the email argument is checked if it follows the correct format (only domain name
     * allowed as argument).
     *
     * @param args User input that is processed.
     * @return Processed string that is ready to be parsed into ScrubCommand.
     * @throws ParseException Thrown when the string does not follow a valid format.
     */
    private String processArguments(String args) throws ParseException {
        // Removes trailing whitespace
        String trimmedArgs = args.stripTrailing();
        // Regex to replace 2 or more consecutive whitespaces with a single whitespace between words
        String processedArgs = trimmedArgs.replaceAll("\\s{2,}", " ");
        boolean containsValidPrefix = containsPrefix(processedArgs, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_TAG);
        boolean containsInvalidPrefix = containsPrefix(processedArgs, PREFIX_ADDRESS, PREFIX_MEMO, PREFIX_NAME);
        if (containsInvalidPrefix || !containsValidPrefix || processedArgs.isEmpty()) {
            LOGGER.log(Level.INFO, "Input is invalid");
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ScrubCommand.MESSAGE_USAGE));
        }
        return processedArgs;
    }

    /**
     * Helper method for {@link #processArguments(String)} to check if the given string argument contains any of the
     * prefixes.
     *
     * @param args String argument to be checked.
     * @param prefixes Array of prefixes that are checked against.
     * @return True if any prefix in prefixes is within the args, false otherwise.
     */
    private boolean containsPrefix(String args, Prefix... prefixes) {
        return Arrays.stream(prefixes).anyMatch(prefix -> args.contains(prefix.toString()));
    }

    private void parseDomain(String arg) throws ParseException {
        // regex that takes everything after @
        String regexForDomainName = "(@)(.*)";
        Pattern pattern = Pattern.compile(regexForDomainName);
        Matcher matcher = pattern.matcher(arg);
        boolean argMatches = matcher.matches();
        if (!argMatches) {
            throw new ParseException(ScrubCommand.MESSAGE_WRONG_DOMAIN_FORMAT);
        }
    }

    /**
     * Checks if the email description provided by the user is in a correct format. Email argument in the scrub command
     * has to only contain the domain name, and it has to start with "@". Note that there can be multiple emails in a
     * single scrub query. E.g. "scrub e/ @mail.com @gmail.com".
     *
     * @param argMultimap Email description to be checked.
     * @throws ParseException Thrown when the email description is not in a valid format.
     */
    private void checkCorrectEmailFormat(ArgumentMultimap argMultimap) throws ParseException {
        List<String> emailArgs = argMultimap.getAllValues(PREFIX_EMAIL);
        for (String emailArg : emailArgs) {
            parseDomain(emailArg);
        }
    }

    /**
     * Checks if the argument specified by the user is in a proper format and meets the constraints specified by
     * each individual attributes.
     *
     * @param args List of arguments that belong to the prefix when parsed into the scrub command parser.
     * @param prefix Prefix that is parsed into the scrub command parser.
     * @throws ParseException Thrown if any of the arguments of that prefix is in an invalid format.
     */
    private void checkPrefixArgsInProperFormat(List<String> args, Prefix prefix) throws ParseException {
        if (prefix.equals(PREFIX_PHONE)) {
            for (String phoneStr : args) {
                ParserUtil.parsePhone(phoneStr);
            }
        } else {
            for (String tagStr : args) {
                ParserUtil.parseTag(tagStr);
            }
        }
    }
}

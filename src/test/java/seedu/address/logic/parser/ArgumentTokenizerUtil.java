package seedu.address.logic.parser;

import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Contains helper methods for testing argument tokenizer.
 */
public class ArgumentTokenizerUtil {
    public static final Prefix DASH_A = new Prefix("-a");
    public static final Prefix DASH_E = new Prefix("-e");
    public static final Prefix DASH_N = new Prefix("-n");
    public static final Prefix DASH_P = new Prefix("-p");
    public static final Prefix DASH_T = new Prefix("-t");
    public static final Prefix HAT_Q = new Prefix("^Q");
    public static final String NOT_ALLOWED = " is not allowed";
    public static final Prefix UNKNOWN_PREFIX = new Prefix("--u");

    /**
     * Tokenizes {@code argsString} of prefix {@code prefix} into an ArgumentMultimap.
     *
     * @param argsString the string to be tokenized.
     * @param prefixes the prefixes used to tokenize the argString
     * @return ArgumentMultimap that has the tokenized prefix.
     */
    public static ArgumentMultimap argsTokenize (String argsString, Prefix... prefixes) {
        try {
            return ArgumentTokenizer.tokenize(argsString, prefixes);
        } catch (ParseException pe) {
            throw new IllegalArgumentException("Invalid userInput.", pe);
        }
    }
}

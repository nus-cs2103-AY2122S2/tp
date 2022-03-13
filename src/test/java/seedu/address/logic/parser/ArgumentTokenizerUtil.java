package seedu.address.logic.parser;

import static seedu.address.logic.parser.ArgumentTokenizer.tokenize;

import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Contains helper methods for testing argument tokenizer.
 */
public class ArgumentTokenizerUtil {
    public static final Prefix DASH_A = new Prefix("-a", "DASH_A");
    public static final Prefix DASH_E = new Prefix("-e", "DASH_E");
    public static final Prefix DASH_N = new Prefix("-n", "DASH_N");
    public static final Prefix DASH_P = new Prefix("-p", "DASH_P");
    public static final Prefix DASH_T = new Prefix("-t", "DASH_T");
    public static final Prefix HAT_Q = new Prefix("^Q", "HAT_Q");
    public static final String NOT_ALLOWED = " is not allowed";
    public static final Prefix UNKNOWN_PREFIX = new Prefix("--u", "unknownPrefix");

    /**
     * Tokenizes {@code argsString} of prefix {@code prefix} into an ArgumentMultimap.
     *
     * @param argsString the string to be tokenized.
     * @param prefixes the prefixes used to tokenize the argString
     * @return ArgumentMultimap that has the tokenized prefix.
     */
    public static ArgumentMultimap argsTokenize (String argsString, Prefix... prefixes) {
        try {
            return tokenize(argsString, prefixes);
        } catch (ParseException pe) {
            throw new IllegalArgumentException("Invalid userInput.", pe);
        }
    }
}

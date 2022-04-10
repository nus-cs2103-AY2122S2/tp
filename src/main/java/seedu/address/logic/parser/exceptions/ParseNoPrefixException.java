package seedu.address.logic.parser.exceptions;

/**
 * Represents a parse error encountered by a parser when attempting to parse no prefixes
 */
public class ParseNoPrefixException extends ParseException {
    public ParseNoPrefixException(String message) {
        super(message);
    }
}

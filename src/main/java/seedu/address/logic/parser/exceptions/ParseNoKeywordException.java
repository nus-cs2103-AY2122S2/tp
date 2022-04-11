package seedu.address.logic.parser.exceptions;

/**
 * Represents a parse error encountered by a parser when attempting to parse no keywords provided by user
 */
public class ParseNoKeywordException extends ParseException {
    public ParseNoKeywordException(String message) {
        super(message);
    }
}

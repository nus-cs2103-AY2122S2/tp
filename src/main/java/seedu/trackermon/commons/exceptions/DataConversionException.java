package seedu.trackermon.commons.exceptions;

/**
 * Represents an error during conversion of data from one format to another
 */
public class DataConversionException extends Exception {
    /**
     * @param cause of the main exception.
     */
    public DataConversionException(Exception cause) {
        super(cause);
    }
}

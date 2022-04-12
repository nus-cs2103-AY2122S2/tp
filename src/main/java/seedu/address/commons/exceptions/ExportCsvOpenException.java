package seedu.address.commons.exceptions;

/**
 * Represents an error encountered by export csv.
 */
public class ExportCsvOpenException extends IllegalValueException {

    public ExportCsvOpenException(String message) {
        super(message);
    }

    public ExportCsvOpenException(String message, Throwable cause) {
        super(message, cause);
    }
}

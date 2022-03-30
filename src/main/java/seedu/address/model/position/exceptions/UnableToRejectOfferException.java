package seedu.address.model.position.exceptions;

public class UnableToRejectOfferException extends RuntimeException {
    public UnableToRejectOfferException() {
        super("Number of offers is 0");
    }
}

package seedu.address.model.position.exceptions;

public class UnableToAcceptOfferException extends RuntimeException {
    public UnableToAcceptOfferException() {
        super("there are no outstanding offer in the position");
    }
}

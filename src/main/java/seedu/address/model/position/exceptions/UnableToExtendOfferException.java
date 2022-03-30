package seedu.address.model.position.exceptions;

public class UnableToExtendOfferException extends RuntimeException {
    public UnableToExtendOfferException() {
        super("Number of offers has exceed the number of openings in the position");
    }
}

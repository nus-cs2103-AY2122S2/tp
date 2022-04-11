package seedu.address.model.image.exceptions;

/**
 * Signals that the operation is unable to find the specified image.
 */
public class ImageDetailsNotFoundException extends RuntimeException {
    public ImageDetailsNotFoundException() {
        super("Image not found.");
    }
}

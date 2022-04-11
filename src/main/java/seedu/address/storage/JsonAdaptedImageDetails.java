package seedu.address.storage;

import java.io.File;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.image.ImageDetails;

public class JsonAdaptedImageDetails {

    private final String imageFilePath;

    /**
     * Constructs a {@code JsonAdaptedImage} with the given {@code imageFilePath}.
     */
    @JsonCreator
    public JsonAdaptedImageDetails(String imageFilePath) {
        this.imageFilePath = imageFilePath;
    }

    /**
     * Converts a given {@code source} into this class for Jackson use.
     */
    public JsonAdaptedImageDetails(ImageDetails source) {
        imageFilePath = source.getPath();
    }

    @JsonValue
    public String getImageFilePath() {
        return imageFilePath;
    }

    /**
     * Converts this Jackson-friendly adapted imageDetails object into the model's {@code ImageDetails} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted imageDetails.
     */
    public ImageDetails toModelType() throws IllegalValueException {
        return new ImageDetails(new File(imageFilePath));
    }
}

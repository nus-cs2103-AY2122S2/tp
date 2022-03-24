package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.property.Region;
import seedu.address.model.userimage.FilePath;
import seedu.address.model.userimage.UserImage;


/**
 * Constructs a {@code JsonAdapterUserImage} with the given UserImage details.
 */
public class JsonAdaptedUserImage {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "UserImage's %s field is missing!";

    private final String filePath;
    private final String description;

    /**
     * Constructs a {@code JsonAdapterUserImage} with the given UserImage details.
     */
    @JsonCreator
    public JsonAdaptedUserImage(@JsonProperty("filePath") String filePath,
                               @JsonProperty("description") String description) {
        this.filePath = filePath;
        this.description = description;
    }

    /**
     * Converts a given {@code UserImage} into this class for Jackson use.
     */
    public JsonAdaptedUserImage(UserImage source) {
        filePath = source.getFilePath().value;
        description = source.getDescription();
    }

    /**
     * Converts this Jackson-friendly adapted UserImage object into the model's {@code UserImage} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted UserImage.
     */
    public UserImage toModelType() throws IllegalValueException {
        final FilePath modelFilePath;

        if (filePath == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Region.class.getSimpleName()));
        }
        if (FilePath.isValidFilePath(filePath)) {
            throw new IllegalValueException(FilePath.MESSAGE_CONSTRAINTS);
        }

        modelFilePath = new FilePath(filePath);

        final String modelDescription;
        if (description != null) {
            modelDescription = description;
        } else {
            modelDescription = null;
        }
        return new UserImage(modelFilePath, modelDescription);
    }
}

package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.userimage.FilePath;
import seedu.address.model.userimage.UserImage;

public class JsonAdaptedUserImage {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "UserImage's  %s field is missing!";

    private final String filePath;
    private final String description;

    @JsonCreator
    public JsonAdaptedUserImage(@JsonProperty("filePath") String filePath,
                               @JsonProperty("description") String description) {
        this.filePath = filePath;
        this.description = description;
    }

    public JsonAdaptedUserImage(UserImage source) {
        filePath = source.getFilePath().value;
        description = source.getDescription();
    }

    public UserImage toModelType() throws IllegalValueException {
        if (filePath == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, FilePath.class.getSimpleName()));
        }
        final FilePath modelFilePath = new FilePath(filePath);

        final String modelDescription;
        if (description != null) {
            modelDescription = description;
        } else {
            modelDescription = null;
        }
        return new UserImage(modelFilePath, modelDescription);
    }
}

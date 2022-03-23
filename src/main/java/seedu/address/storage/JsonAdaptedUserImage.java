package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.model.userimage.FilePath;
import seedu.address.model.userimage.UserImage;

public class JsonAdaptedUserImage {

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

    public UserImage toModelType() {
        final FilePath modelFilePath;

        if (filePath != null) {
            modelFilePath = new FilePath(filePath);
        } else {
            modelFilePath = null;
        }


        final String modelDescription;
        if (description != null) {
            modelDescription = description;
        } else {
            modelDescription = null;
        }
        return new UserImage(modelFilePath, modelDescription);
    }
}

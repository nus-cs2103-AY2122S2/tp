package seedu.address.model.person;

import seedu.address.logic.commands.UploadCommand.UploadDescriptor;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class UserImage {
    private final String description;
    private final String filePath;
    private final File newImage;

    public UserImage(UploadDescriptor uploadDescriptor) {
        this.filePath = uploadDescriptor.getFilePath();
        this.description = uploadDescriptor.getDescription().orElse("");
        this.newImage = new File(filePath);
    }

    public String getDescription() {
        return description;
    }

    public String getFilePath(){
        return filePath;
    }

    public boolean isValid() {
        return newImage.exists();
    }

    public boolean isImage() {
        try {
            return ImageIO.read(newImage) != null;
        } catch (IOException E) {
            return false;
        }
    }
}

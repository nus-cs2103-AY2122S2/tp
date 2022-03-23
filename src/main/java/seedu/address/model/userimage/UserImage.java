package seedu.address.model.userimage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class UserImage {

    public static final String MESSAGE_CONSTRAINTS =
                "Image must be of the format: [File Path:Description(optional)]";

    private String description;
    private FilePath filePath;
    private File image;

    public UserImage() {
        this.filePath = null;
        this.description = null;
        this.image = null;
    }

    public UserImage(FilePath filePath, String description) {
        this.filePath = filePath;
        this.description = description;
        this.image = new File(filePath.value);
    }

    public String getDescription() {
        return description;
    }

    public FilePath getFilePath(){
        return filePath;
    }

    public File getImage() {
        return image;
    }

    public void updateContents(UserImage newImage) {
        this.description = newImage.getDescription();
        this.filePath = newImage.getFilePath();
        this.image = newImage.getImage();
    }

    public boolean isImage() {
        try {
            return ImageIO.read(image) != null;
        } catch (IOException E) {
            return false;
        }
    }

    public boolean isPresent() {
        return filePath != null;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof UserImage)) {
            return false;
        }
        UserImage otherUserImage = (UserImage) other;
        return otherUserImage.getFilePath().equals(filePath)
            && otherUserImage.getDescription().equals(description);

    }

    @Override
    public int hashCode() {
        return Objects.hash(filePath, description);
    }
}

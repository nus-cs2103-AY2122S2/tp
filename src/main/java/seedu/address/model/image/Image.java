package seedu.address.model.image;

import static java.util.Objects.requireNonNull;

import java.io.File;
/**
 * Represents an Image in Coach2K22.
 */
public class Image {
    public static final String FILE_PATH = "courts/";

    public static final String MESSAGE_CONSTRAINTS =
            "Image must be a valid .png image and contained in \'" + FILE_PATH + "\'";

    public final String imageName;

    /**
     * Constructor for Image class.
     * @param imageName The name of an image
     */
    public Image(String imageName) {
        requireNonNull(imageName);
        this.imageName = imageName;
    }

    /**
     * Checks if given image name is a valid image.
     * @param imageName The name of an image
     * @return true if the image exists
     */
    public static Boolean isValidFile(String imageName) {
        String filePath = FILE_PATH + imageName + ".png";
        File file = new File(filePath);
        return file.exists();
    }


    /**
     * Returns the File format of the image.
     * @return File object of the given image
     */
    public File getImagePath() {
        String filePath = FILE_PATH + this.imageName + ".png";
        File file = new File(filePath);
        return file;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Image
                && imageName.equals(((Image) other).imageName));
    }

    @Override
    public String toString() {
        return this.imageName;
    }
}

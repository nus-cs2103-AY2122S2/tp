package seedu.address.model.image;

import static java.util.Objects.requireNonNull;

import java.io.File;

public class ImageDetails {
    public final File imageFile;

    /**
     * Creates an image details object that encapsulates the information of an image file.
     *
     * @param imageFile the file that is being encapsulated.
     */
    public ImageDetails(File imageFile) {
        requireNonNull(imageFile);
        this.imageFile = imageFile;
    }

    public String getName() {
        return this.imageFile.getName();
    }

    public File getImageFile() {
        return this.imageFile;
    }

    /**
     * The string representation of the path returned is relative to the project root.
     *
     * @return path of the image file, relative to the project root.
     */
    public String getPath() {
        return this.imageFile.getPath();
    }

    public String getJavaFxImageUrl() {
        return String.format("file:%s", this.imageFile.getPath());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ImageDetails // instanceof handles nulls
                && getName().equals(((ImageDetails) other).getName())); // state check
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + getName() + ']';
    }
}

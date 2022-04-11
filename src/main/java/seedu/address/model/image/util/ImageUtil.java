package seedu.address.model.image.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

import javafx.stage.FileChooser;
import seedu.address.commons.util.FileUtil;
import seedu.address.model.image.ImageDetails;
import seedu.address.model.image.ImageDetailsList;

public class ImageUtil {
    private static final String JPG_EXTENSION = "jpg";
    private static final String PNG_EXTENSION = "png";
    private static final FileChooser.ExtensionFilter IMAGE_FILE_FILTERS = new FileChooser.ExtensionFilter(
            "Image Files", "*." + PNG_EXTENSION, "*." + JPG_EXTENSION);

    private static FileChooser fileChooser;

    /**
     * Opens a FileChooser configured to view and select only image files.
     * @return the FileChooser that can be opened
     */
    public static FileChooser openImageFileChooser() {
        if (fileChooser == null) {
            fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(IMAGE_FILE_FILTERS);
        }
        return fileChooser;
    }

    /**
     * Checks if the {@code file} exists in the {@code directoryDirectory}.
     * @param file the file to check
     * @param directoryPath path of the directory to check if file exists within
     * @return true if the file exists
     */
    public static boolean fileExists(File file, Path directoryPath) {
        File directory = directoryPath.toFile();
        File[] directoryFiles = directory.listFiles();

        if (directoryFiles == null) {
            // Directory doesn't exist yet
            return false;
        }

        for (File f : directoryFiles) {
            if (f.getName().equals(file.getName())) {
                return true;
            }
        }

        return false;
    }

    /**
     * Copies the image specified at the source file, to the destination path specified.
     *
     * @param src the image to be copied
     * @param destPath the location that the newly copied image should be saved to
     * @return the image details of the copied image
     * @throws IOException if reading or writing the image failed
     */
    public static ImageDetails copyTo(File src, Path destPath) throws IOException {
        BufferedImage srcImg = ImageIO.read(src);

        FileUtil.createIfMissing(destPath);
        File destFile = destPath.toFile();

        String imgExtension = getImageExtension(src.getName());

        ImageIO.write(srcImg, imgExtension, destFile);
        return new ImageDetails(destFile);
    }

    private static String getImageExtension(String fileName) {
        String[] splitFileName = fileName.split("\\.");
        // FileChooser should have already ensured that the file must have _some_ valid extension
        assert splitFileName.length > 1;

        String extension = splitFileName[splitFileName.length - 1];
        // FileChooser should have already ensured that the file must have some _valid_ extension
        assert extension.equals(JPG_EXTENSION) || extension.equals(PNG_EXTENSION);

        return extension;
    }

    /**
     * Removes from this list images that no longer exist within the data/images directory.
     *
     * @return a sanitized {@code ImageDetailsList} object.
     */
    public static ImageDetailsList sanitizeList(ImageDetailsList listToSanitize, Path pathToSanitize) {
        List<ImageDetails> sanitizedList = new ArrayList<>();
        for (ImageDetails img : listToSanitize) {
            if (fileExists(img.getImageFile(), pathToSanitize)) {
                sanitizedList.add(img);
            }
        }
        return new ImageDetailsList(sanitizedList);
    }

    /**
     * Deletes the file from the specified filepath.
     *
     * @param imageToDelete the details of the image to delete.
     */
    public static void removeFile(ImageDetails imageToDelete) {
        Path filePath = imageToDelete.getImageFile().toPath();
        if (!FileUtil.isFileExists(filePath)) {
            return;
        }

        FileUtil.deleteFile(filePath);
    }
}

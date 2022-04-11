package seedu.address.model.image.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalSavedImages.TEST_IMAGES_DIRECTORY;
import static seedu.address.testutil.TypicalSavedImages.TEST_IMAGE_1_FILE;
import static seedu.address.testutil.TypicalSavedImages.TEST_IMAGE_2_FILE;
import static seedu.address.testutil.TypicalSavedImages.getTypicalImageDetailsList;
import static seedu.address.testutil.TypicalSavedImages.populateTestImages;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import seedu.address.model.image.ImageDetails;
import seedu.address.model.image.ImageDetailsList;

class ImageUtilTest {

    private static final Path tempDirectory = TEST_IMAGES_DIRECTORY.resolve("temp");

    /**
     * Sets up the temp directory for files to be copied into.
     *
     * @throws IOException if something goes wrong with the creation of the directory.
     */
    @BeforeAll
    static void setUp() throws IOException {
        populateTestImages();
        Files.createDirectory(tempDirectory);
    }

    @Test
    void fileExists_validFile_returnsTrue() {
        assertTrue(ImageUtil.fileExists(TEST_IMAGE_1_FILE, TEST_IMAGES_DIRECTORY));
    }

    @Test
    void fileExists_invalidDirectory_returnsFalse() {
        assertFalse(ImageUtil.fileExists(TEST_IMAGE_1_FILE, Paths.get("does_not_exist")));
    }

    @Test
    void fileExists_invalidFile_returnsFalse() {
        assertFalse(
                ImageUtil.fileExists(TEST_IMAGES_DIRECTORY.resolve("does_not_exist").toFile(), TEST_IMAGES_DIRECTORY));
    }

    @Test
    void fileExists_identicalFilePath_returnsFalse() {
        assertFalse(ImageUtil.fileExists(TEST_IMAGE_1_FILE, TEST_IMAGE_1_FILE.toPath()));
    }

    @Test
    void copyTo_success() throws IOException {
        File fileToCopy = TEST_IMAGE_1_FILE;
        ImageDetails copiedImage = ImageUtil.copyTo(fileToCopy, tempDirectory.resolve(fileToCopy.getName()));

        assertTrue(ImageUtil.fileExists(copiedImage.getImageFile(), tempDirectory));
    }

    @Test
    void copyTo_invalidFile_throwsIoException() {
        File nonExistentFile = new File("does_not_exist");
        assertThrows(IOException.class, () -> ImageUtil.copyTo(
                    nonExistentFile, tempDirectory.resolve(nonExistentFile.getName())));
    }

    @Test
    void sanitizeList_success() {
        File nonExistentFile = new File("non-existent");
        ImageDetailsList unsanitizedList = getTypicalImageDetailsList()
                .appendImageDetails(List.of(new ImageDetails(nonExistentFile)));

        // before sanitize -> size 6
        assertEquals(6, unsanitizedList.size());

        ImageDetailsList sanitizedList = ImageUtil.sanitizeList(unsanitizedList, TEST_IMAGES_DIRECTORY);

        // after sanitize -> size 5
        assertEquals(5, sanitizedList.size());
        assertEquals(getTypicalImageDetailsList(), sanitizedList);
    }

    @Test
    void removeFile_success() throws IOException {
        File fileToCopy = TEST_IMAGE_2_FILE;
        ImageDetails copiedImage = ImageUtil.copyTo(fileToCopy, tempDirectory.resolve(fileToCopy.getName()));

        // newly created file exists
        assertTrue(ImageUtil.fileExists(copiedImage.getImageFile(), tempDirectory));

        ImageUtil.removeFile(copiedImage);

        // newly creted file does not exist after deletion
        assertFalse(ImageUtil.fileExists(copiedImage.getImageFile(), tempDirectory));
    }

    /**
     * Removes the temp folder after the tests.
     *
     * @throws IOException if the delete operation fails.
     */
    @AfterAll
    static void tearDown() throws IOException {
        File[] contents = tempDirectory.toFile().listFiles();
        if (contents != null) {
            for (File f : contents) {
                Files.delete(f.toPath());
            }
        }
        Files.delete(tempDirectory);
    }
}

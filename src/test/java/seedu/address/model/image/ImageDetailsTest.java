package seedu.address.model.image;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalSavedImages.TEST_IMAGES_DIRECTORY;

import java.io.File;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

class ImageDetailsTest {
    private String testFileName = "test_image_1.png";

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ImageDetails(null));
    }

    @Test
    void getName() {
        File file = TEST_IMAGES_DIRECTORY.resolve(testFileName).toFile();
        ImageDetails imageDetails = new ImageDetails(file);
        assertEquals(testFileName, imageDetails.getName());
    }

    @Test
    void getImageFile() {
        File file = TEST_IMAGES_DIRECTORY.resolve(testFileName).toFile();
        ImageDetails imageDetails = new ImageDetails(file);
        assertEquals(file, imageDetails.getImageFile());
    }

    @Test
    void getPath() {
        Path path = TEST_IMAGES_DIRECTORY.resolve(testFileName);
        File file = path.toFile();
        ImageDetails imageDetails = new ImageDetails(file);

        assertEquals(path.toString(), imageDetails.getPath());
    }

    @Test
    void getJavaFxImageUrl() {
        Path path = TEST_IMAGES_DIRECTORY.resolve(testFileName);

        String expected = String.format("file:%s", path);

        File file = path.toFile();
        ImageDetails imageDetails = new ImageDetails(file);

        assertEquals(expected, imageDetails.getJavaFxImageUrl());
    }
}

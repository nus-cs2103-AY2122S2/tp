package seedu.address.testutil;

import java.io.File;
import java.io.IOException;

import seedu.address.model.image.Image;

/**
 * Contains static methods to create sample png files for testing Image-related classes.
 */
public class TestImageCreator {

    public static final String TEST_IMAGE_NAME = "testFile";

    private static Image testImage = new Image("testFile");

    /**
     * Creates a sample png file for testing Image classes
     */
    public static void createTestImage() {
        File testFile = testImage.getImagePath();
        File testDirectory = new File(Image.FILE_PATH);
        try {
            if (!testDirectory.exists()) {
                testDirectory.mkdir(); //in case "courts" directory has not been created
            }
            testFile.createNewFile();
        } catch (IOException e) {
            assert false : "Runtime should not reach here";
        }
    }

    /**
     * Deletes the sample png that was created.
     */
    public static void deleteTestImage() {
        File testFile = testImage.getImagePath();
        testFile.delete();
    }

    public static Image getTestImage() {
        return testImage;
    }
}

package seedu.address.model.userimage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class FilePathTest {
    @Test
    public void isValidFilePath() {
        assertTrue(FilePath.isValidFilePath("./src/test/resources/images/success.png"));

        //test empty string
        assertFalse(FilePath.isValidFilePath(""));

        //test wrong name
        assertFalse(FilePath.isValidFilePath("./src/test/resources/images/succes.png"));
    }

    @Test
    public void equals() {
        FilePath testPath = new FilePath("./src/test/resources/images/success.png");
        assertTrue(testPath.equals(new FilePath("./src/test/resources/images/success.png")));

        //tests different filepath
        assertFalse(testPath.equals(new FilePath("./src/test/resources/images/fail.png")));
    }
}


package seedu.contax.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.contax.testutil.Assert.assertThrows;

import java.io.File;

import org.junit.jupiter.api.Test;

public class IndexedCsvFileTest {
    @Test
    public void constructor_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new IndexedCsvFile(null, 1,
                2, 3, 4, 5));
    }

    @Test
    public void constructor_invalidFilepath_throwsIllegalArgumentException() {
        File invalidFileExtension = new File("invalidFileExtension.txt");
        File invalidFilePath = new File("randomFileName");
        assertThrows(IllegalArgumentException.class, () -> new IndexedCsvFile(invalidFileExtension, 1,
                2, 3, 4, 5));
        assertThrows(IllegalArgumentException.class, () -> new IndexedCsvFile(invalidFilePath, 1,
                2, 3, 4, 5));
    }

    @Test
    public void isValidFilePath() {
        //null filepath
        assertThrows(NullPointerException.class, () -> IndexedCsvFile.isValidFilePath(null));

        //blank filepath
        assertFalse(IndexedCsvFile.isValidFilePath("")); // empty string
        assertFalse(IndexedCsvFile.isValidFilePath(" ")); // spaces only

        //no file extension
        assertFalse(IndexedCsvFile.isValidFilePath("/data/file"));

        //non-csv file extension
        assertFalse(IndexedCsvFile.isValidFilePath("/data/file.txt"));

        //valid file paths
        assertTrue(IndexedCsvFile.isValidFilePath("/data/file.csv"));

        //file with no name has the extension, no such file will exist anyway so it doesn't matter if it is valid
        assertTrue(IndexedCsvFile.isValidFilePath(".csv"));
    }
}

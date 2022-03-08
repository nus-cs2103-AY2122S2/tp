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

    @Test
    public void equals() {
        //same object -> returns true
        IndexedCsvFile csvFile1 = new IndexedCsvFile(new File("/data/file.csv"), 1,
                2, 3, 4, 5);
        assertTrue(csvFile1.equals(csvFile1));

        //same values -> returns true
        IndexedCsvFile csvFile2 = new IndexedCsvFile(new File("/data/file.csv"), 1,
                2, 3, 4, 5);
        assertTrue(csvFile1.equals(csvFile2));

        //null -> returns false
        assertFalse(csvFile1.equals(null));

        //different filename -> returns false
        assertFalse(csvFile1.equals(new IndexedCsvFile(new File("file.csv"), 1,
                2, 3, 4, 5)));

        //different positions -> returns false
        assertFalse(csvFile1.equals(new IndexedCsvFile(new File("file.csv"), 6,
                2, 3, 4, 5)));
        assertFalse(csvFile1.equals(new IndexedCsvFile(new File("file.csv"), 1,
                6, 3, 4, 5)));
        assertFalse(csvFile1.equals(new IndexedCsvFile(new File("file.csv"), 1,
                2, 6, 4, 5)));
        assertFalse(csvFile1.equals(new IndexedCsvFile(new File("file.csv"), 1,
                2, 3, 6, 5)));
        assertFalse(csvFile1.equals(new IndexedCsvFile(new File("file.csv"), 1,
                2, 3, 4, 6)));
    }
}

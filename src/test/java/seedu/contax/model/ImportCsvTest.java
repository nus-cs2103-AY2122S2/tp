package seedu.contax.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.contax.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;
import seedu.contax.model.person.Email;

import java.io.File;

public class ImportCsvTest {
    @Test
    public void constructor_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ImportCsv(null, 1,
                2, 3, 4, 5));
    }

    @Test
    public void constructor_invalidFilepath_throwsIllegalArgumentException() {
        File invalidFileExtension = new File("invalidFileExtension.txt");
        File invalidFilePath = new File("randomFileName");
        assertThrows(IllegalArgumentException.class, () -> new ImportCsv(invalidFileExtension, 1,
                2, 3, 4, 5));
        assertThrows(IllegalArgumentException.class, () -> new ImportCsv(invalidFilePath, 1,
                2, 3, 4, 5));
    }

    @Test
    public void isValidFilePath() {
        //null filepath
        assertThrows(NullPointerException.class, () -> ImportCsv.isValidFilePath(null));

        //blank filepath
        assertFalse(ImportCsv.isValidFilePath("")); // empty string
        assertFalse(ImportCsv.isValidFilePath(" ")); // spaces only

        //no file extension
        assertFalse(ImportCsv.isValidFilePath("/data/file"));

        //non-csv file extension
        assertFalse(ImportCsv.isValidFilePath("/data/file.txt"));

        //valid file paths
        assertTrue(ImportCsv.isValidFilePath("/data/file.csv"));

        //file with no name has the extension, no such file will exist anyway so it doesn't matter if it is valid
        assertTrue(ImportCsv.isValidFilePath(".csv"));
    }
}

package seedu.contax.model;

import static seedu.contax.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ImportCsvTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ImportCsv(null, 0,
                0, 0, 0, 0));
    }
}

package seedu.contax.testutil;

import java.io.File;

import seedu.contax.model.ImportCsv;

public class ImportCsvObjectBuilder {
    public static final String DEFAULT_FILEPATH = "./src/test/data/ImportCsvTest/ValidContaXFormat.csv";
    public static final int DEFAULT_NAMEPOSITION = 1;
    public static final int DEFAULT_PHONEPOSITION = 2;
    public static final int DEFAULT_EMAILPOSITION = 3;
    public static final int DEFAULT_ADDRESSPOSITION = 4;
    public static final int DEFAULT_TAGPOSITION = 5;

    private File filePath;
    private int namePosition;
    private int phonePosition;
    private int emailPosition;
    private int addressPosition;
    private int tagPosition;

    /**
     * Creates a {@code ImportCsvObjectBuilder} with the default details.
     */
    public ImportCsvObjectBuilder() {
        filePath = new File(DEFAULT_FILEPATH);
        namePosition = DEFAULT_NAMEPOSITION;
        phonePosition = DEFAULT_PHONEPOSITION;
        emailPosition = DEFAULT_EMAILPOSITION;
        addressPosition = DEFAULT_ADDRESSPOSITION;
        tagPosition = DEFAULT_TAGPOSITION;
    }

    /**
     * Creates a {@code ImportCsvObjectBuilder} with the specified file path, and default positions.
     */
    public ImportCsvObjectBuilder(String filePath) {
        this.filePath = new File(filePath);
        namePosition = DEFAULT_NAMEPOSITION;
        phonePosition = DEFAULT_PHONEPOSITION;
        emailPosition = DEFAULT_EMAILPOSITION;
        addressPosition = DEFAULT_ADDRESSPOSITION;
        tagPosition = DEFAULT_TAGPOSITION;
    }

    public ImportCsv build() {
        return new ImportCsv(filePath, namePosition, phonePosition, emailPosition, addressPosition, tagPosition);
    }
}

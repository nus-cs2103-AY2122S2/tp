package seedu.contax.testutil;

import static seedu.contax.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_FILE;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_TAG;


import java.io.File;

import seedu.contax.logic.commands.ImportCsvCommand;
import seedu.contax.model.IndexedCsvFile;

public class ImportCsvObjectBuilder {
    public static final String DEFAULT_FILEPATH = "./src/test/data/ImportCsvTest/ValidContaXFormat.csv";
    public static final String SKIP_CSV_FILEPATH = "./src/test/data/ImportCsvTest/SkipLineContaXFormat.csv";
    public static final String EMPTY_CSV_FILEPATH = "./src/test/data/ImportCsvTest/EmptyFile.csv";
    public static final String CUSTOM_COLUMNS_CSV_FILEPATH = "./src/test/data/ImportCsvTest/CustomColumnFormat.csv";

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

    /**
     * Creates a {@code ImportCsvObjectBuilder} with the specified file path, and specified positions.
     */
    public ImportCsvObjectBuilder(String filePath, int namePosition, int phonePosition, int emailPosition,
                                  int addressPosition, int tagPosition) {
        this.filePath = new File(filePath);
        this.namePosition = namePosition;
        this.phonePosition = phonePosition;
        this.emailPosition = emailPosition;
        this.addressPosition = addressPosition;
        this.tagPosition = tagPosition;
    }

    public static String getImportCsvCommand(IndexedCsvFile indexedCsvFile) {
        return ImportCsvCommand.COMMAND_WORD + " " +getIndexedCsvFileDetails(indexedCsvFile);
    }

    public static String getIndexedCsvFileDetails(IndexedCsvFile indexedCsvFile) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_FILE + indexedCsvFile.getFilePath() + " ");
        sb.append(PREFIX_NAME + String.valueOf(indexedCsvFile.getNamePositionIndex() + 1) + " ");
        sb.append(PREFIX_PHONE + String.valueOf(indexedCsvFile.getPhonePositionIndex() + 1) + " ");
        sb.append(PREFIX_EMAIL + String.valueOf(indexedCsvFile.getEmailPositionIndex() + 1) + " ");
        sb.append(PREFIX_ADDRESS + String.valueOf(indexedCsvFile.getAddressPositionIndex() + 1) + " ");
        sb.append(PREFIX_TAG + String.valueOf(indexedCsvFile.getTagPositionIndex() + 1) + " ");

        return sb.toString();
    }

    public IndexedCsvFile build() {
        return new IndexedCsvFile(filePath, namePosition, phonePosition, emailPosition, addressPosition, tagPosition);
    }
}

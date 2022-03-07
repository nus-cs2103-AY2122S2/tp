package seedu.contax.model;

public class ImportCsv {
    public static final String MESSAGE_CONSTRAINTS = "Positions should be integers above 0";

    private final String filePath;
    private final int namePosition;
    private final int phonePosition;
    private final int emailPosition;
    private final int addressPosition;
    private final int tagPosition;

    /**
     * Creates a new ImportCsv object to contain import parameters
     */
    public ImportCsv(String filePath, int namePosition, int phonePosition, int emailPosition,
                     int addressPosition, int tagPosition) {
        this.filePath = filePath;
        this.namePosition = namePosition;
        this.phonePosition = phonePosition;
        this.emailPosition = emailPosition;
        this.addressPosition = addressPosition;
        this.tagPosition = tagPosition;
    }

    public String getFilePath() {
        return filePath;
    }

    public int getNamePositionIndex() {
        return namePosition - 1;
    }

    public int getPhonePositionIndex() {
        return phonePosition - 1;
    }

    public int getEmailPositionIndex() {
        return emailPosition - 1;
    }

    public int getAddressPositionIndex() {
        return addressPosition - 1;
    }

    public int getTagPositionIndex() {
        return tagPosition - 1;
    }
}

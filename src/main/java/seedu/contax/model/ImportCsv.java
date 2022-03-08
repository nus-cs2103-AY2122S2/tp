package seedu.contax.model;

import static seedu.contax.commons.util.AppUtil.checkArgument;
import static seedu.contax.commons.util.CollectionUtil.requireAllNonNull;

import java.io.File;

public class ImportCsv {

    public static final String FILE_PATH_CONSTRAINTS = "File path should point to a .csv file";
    public static final String MESSAGE_CONSTRAINTS = "Positions should be integers above 0";
    public static final String FILE_VALIDATION_REGEX = "(.*?)\\.(csv)$";

    private final File filePath;
    private final int namePosition;
    private final int phonePosition;
    private final int emailPosition;
    private final int addressPosition;
    private final int tagPosition;

    /**
     * Creates a new ImportCsv object to contain import parameters
     */
    public ImportCsv(File filePath, int namePosition, int phonePosition, int emailPosition,
                     int addressPosition, int tagPosition) {
        requireAllNonNull(filePath, namePosition, phonePosition, emailPosition, addressPosition, tagPosition);
        checkArgument(isValidFilePath(filePath.getPath()), FILE_VALIDATION_REGEX);
        this.filePath = filePath;
        this.namePosition = namePosition;
        this.phonePosition = phonePosition;
        this.emailPosition = emailPosition;
        this.addressPosition = addressPosition;
        this.tagPosition = tagPosition;
    }

    public String getFilePath() {
        return filePath.getPath();
    }

    public static boolean isValidFilePath(String test) {
        return test.matches(FILE_VALIDATION_REGEX);
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

    /**
     * Returns true if both ImportCsv have the same filepath and position fields.
     * This defines a stronger notion of equality between two ImportCsv objects.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ImportCsv)) {
            return false;
        }

        ImportCsv otherObject = (ImportCsv) other;
        return otherObject.getFilePath().equals(getFilePath())
                && otherObject.getNamePositionIndex() == getNamePositionIndex()
                && otherObject.getPhonePositionIndex() == getPhonePositionIndex()
                && otherObject.getEmailPositionIndex() == (getEmailPositionIndex())
                && otherObject.getAddressPositionIndex() == (getAddressPositionIndex())
                && otherObject.getTagPositionIndex() == (getTagPositionIndex());
    }
}

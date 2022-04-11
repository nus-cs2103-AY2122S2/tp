package seedu.address.model.userimage;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.io.File;

/**
 * Represents a file path to a file in the user's file system.
 * Guarantees: immutable; is valid as declared in {@link #isValidFilePath(String filepath)}
 */
public class FilePath {

    public static final String MESSAGE_CONSTRAINTS = "File not found. Please check your file path";

    public final String value;

    /**
     * @param filePath filePath to check if it leads to a existing file
     */
    public FilePath(String filePath) {
        requireNonNull(filePath);
        checkArgument(isValidFilePath(filePath), MESSAGE_CONSTRAINTS);
        value = filePath;
    }

    /**
     * Checks to ensure that the given filepath leads to an existing file
     */
    public static boolean isValidFilePath(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }

    public String get() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof FilePath
                && value.equals(((FilePath) other).value));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

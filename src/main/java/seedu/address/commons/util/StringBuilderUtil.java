package seedu.address.commons.util;

/**
 * A container for StringBuilder specific utility functions
 */
public class StringBuilderUtil {
    private StringBuilder stringBuilder;

    /**
     * Default constructor for the OutputFormatter class.
     */
    private StringBuilderUtil() {
        this.stringBuilder = new StringBuilder();
    }

    /**
     * Factory method for the class.
     * @return An instance of the OutputFormatter class.
     */
    public static StringBuilderUtil getInstance() {
        return new StringBuilderUtil();
    }

    /**
     * Appends objects in a bulk manner into a single string.
     * @param objectArray The array of objects to append into the OutputFormatter.
     */
    public void appendAll(Object... objectArray) {
        for (Object o : objectArray) {
            if (o == null) {
                continue;
            }
            this.stringBuilder.append(o);
        }
    }

    /**
     * Appends a single object into the string.
     * @param object The object to append to the OutputFormatter.
     */
    public void append(Object object) {
        this.stringBuilder.append(object.toString());
    }

    /**
     * Gets the formatted output.
     * @return The formatted output in the form of a single string.
     */
    public String getFormattedOutput() {
        return this.stringBuilder.toString();
    }
}

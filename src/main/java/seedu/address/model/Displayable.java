package seedu.address.model;

/**
 * The API of the Displayable component implemented by Person, Student, Tutorial and Assessment.
 */
public interface Displayable {
    /**
     * Returns the display type.
     */
    DisplayType getDisplayType();
}

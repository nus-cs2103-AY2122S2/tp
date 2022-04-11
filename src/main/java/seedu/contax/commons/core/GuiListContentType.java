package seedu.contax.commons.core;

/**
 * Represents the types of models that can be displayed by the UI.
 */
public enum GuiListContentType {
    PERSON,
    APPOINTMENT,
    TAG,
    /* Special value indicating that the content list should retain its current configuration */
    UNCHANGED
}

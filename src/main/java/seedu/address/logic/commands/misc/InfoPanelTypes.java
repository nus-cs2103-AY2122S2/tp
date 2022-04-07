package seedu.address.logic.commands.misc;

/**
 * Enumerator representing the different types of InfoPanels, used in {@code CommandResult}
 */
public enum InfoPanelTypes {
    STUDENT("Student Info Panel"),
    LESSON("Lesson Info Panel"),
    EMPTY("Clear Info Panel"),
    NONE("No change to Info Panel"),
    REFRESH("Refresh Info Panel");

    private final String str;
    InfoPanelTypes(String string) {
        str = string;
    }

    /**
     * Returns the task type in String.
     *
     * @return Task type in String.
     */
    @Override
    public String toString() {
        return str;
    }
}

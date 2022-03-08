package seedu.address.logic.commands.misc;

/**
 * Enumerator representing the different types of InfoPanels, used in {@code CommandResult}
 */
public enum InfoPanelTypes {
    PERSON("Person Info Panel"),
    LESSON("Lesson Info Panel");

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

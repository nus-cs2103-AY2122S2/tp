package seedu.contax.model.appointment;

import static java.util.Objects.requireNonNull;

/**
 * Represents an {@link Appointment}'s priority.
 **/
public enum Priority {
    LOW("Low"),
    MEDIUM("Medium"),
    HIGH("High");

    private final String displayName;

    /**
     * Constructs a Priority level.
     */
    Priority(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return this.displayName;
    }

    /**
     * Returns the Priority Enum with the supplied display name.
     *
     * @param displayName The display name to match for enums against.
     * @return The corresponding enum, or null if no priority levels match the supplied.
     */
    public static Priority getFromDisplayName(String displayName) {
        requireNonNull(displayName);
        if (displayName.equalsIgnoreCase(LOW.displayName)) {
            return LOW;
        } else if (displayName.equalsIgnoreCase(MEDIUM.displayName)) {
            return MEDIUM;
        } else if (displayName.equalsIgnoreCase(HIGH.displayName)) {
            return HIGH;
        } else {
            return null;
        }
    }
}

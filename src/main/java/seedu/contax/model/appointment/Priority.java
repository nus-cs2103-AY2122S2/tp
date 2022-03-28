package seedu.contax.model.appointment;

/**
 * Represents an {@link Appointment}'s priority
 **/

public enum Priority {
    LOW,
    MEDIUM,
    HIGH;

    @Override
    public String toString() {
        switch(this) {
        case LOW: return "Low";
        case MEDIUM: return "Medium";
        case HIGH: return "High";
        default: return "None";
        }
    }
}

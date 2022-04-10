package seedu.address.model.applicant;

/**
 * Represents an Applicant's status in the address book.
 * default value is "Available" and is replaced with a position name when Applicant accepts an offer
 */
public class HiredStatus {
    private final String value;

    /**
     * Constructs a {@code HiredStatus}.
     * Initialised as available as every Applicant added to HireLah is applying for a position.
     */
    public HiredStatus() {
        value = "Available";
    }

    public HiredStatus(String positionName) {
        value = positionName;
    }

    public boolean isHired() {
        return !value.equals("Available");
    }

    @Override
    public String toString() {
        if (value.equals("Available")) {
            return value;
        } else {
            return "Hired as " + value;
        }
    }

    public String getValue() {
        return this.value;
    }

    /**
     * Returns true if both Hired Status have the same value.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof HiredStatus)) {
            return false;
        }

        HiredStatus otherStatus = (HiredStatus) other;
        return otherStatus.value.equals(this.value);
    }
}

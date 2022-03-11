package seedu.address.model.classgroup;

/**
 * Represents a ClassGroupType in the TAssist.
 * Only these 4 types are acceptable.
 */
public enum ClassGroupType {
    LAB("Lab"),
    RECITATION("Recitation"),
    SECTIONAL("Sectional"),
    TUTORIAL("Tutorial");

    private final String value;

    ClassGroupType(String classGroupType) {
        this.value = classGroupType;
    }

    @Override
    public String toString() {
        return value;
    }
}

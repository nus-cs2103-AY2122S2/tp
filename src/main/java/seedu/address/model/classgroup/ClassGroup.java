package seedu.address.model.classgroup;

import java.util.Objects;

import seedu.address.model.tamodule.TaModule;

/**
 * Represents a ClassGroup in the TAssist.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class ClassGroup {
    // Identity fields
    private final ClassGroupId classGroupId;
    private final ClassGroupType classGroupType;
    private final TaModule taModule;

    /**
     * Constructs a {@code ClassGroup}.
     * Every field must be present and not null.
     *
     * @param classGroupId A valid class group ID.
     * @param classGroupType A valid class group type.
     * @param taModule A valid module.
     */
    public ClassGroup(ClassGroupId classGroupId, ClassGroupType classGroupType, TaModule taModule) {
        this.classGroupId = classGroupId;
        this.classGroupType = classGroupType;
        this.taModule = taModule;
    }

    public ClassGroupId getClassGroupId() {
        return classGroupId;
    }

    public ClassGroupType getClassGroupType() {
        return classGroupType;
    }

    public TaModule getModule() {
        return taModule;
    }

    /**
     * Returns true if both class groups have the same identity and fields.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ClassGroup)) {
            return false;
        }

        ClassGroup otherClassGroup = (ClassGroup) other;
        return otherClassGroup.getClassGroupId().equals(getClassGroupId())
                && otherClassGroup.getClassGroupType().equals(getClassGroupType())
                && otherClassGroup.getModule().equals(getModule());
    }

    @Override
    public int hashCode() {
        return Objects.hash(classGroupId, classGroupType, taModule);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getClassGroupId())
                .append("; Type: ")
                .append(getClassGroupType())
                .append("; Module: ")
                .append(getModule());
        return builder.toString();
    }
}

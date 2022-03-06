package seedu.address.model.classgroup;

import java.util.Objects;

import seedu.address.model.tamodule.ModuleCode;

/**
 * Represents a ClassGroup in the TAssist.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class ClassGroup {
    // Identity fields
    private final ClassGroupId classGroupId;
    private final ClassGroupType classGroupType;
    private final ModuleCode moduleCode;

    /**
     * Constructs a {@code ClassGroup}.
     * Every field must be present and not null.
     *
     * @param classGroupId A valid class group ID.
     * @param classGroupType A valid class group type.
     * @param moduleCode A valid module code.
     */
    public ClassGroup(ClassGroupId classGroupId, ClassGroupType classGroupType, ModuleCode moduleCode) {
        this.classGroupId = classGroupId;
        this.classGroupType = classGroupType;
        this.moduleCode = moduleCode;
    }

    public ClassGroupId getClassGroupId() {
        return classGroupId;
    }

    public ClassGroupType getClassGroupType() {
        return classGroupType;
    }

    public ModuleCode getModuleCode() {
        return moduleCode;
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
                && otherClassGroup.getModuleCode().equals(getModuleCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(classGroupId, classGroupType, moduleCode);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getClassGroupId())
                .append("; Type: ")
                .append(getClassGroupType())
                .append("; Module Code: ")
                .append(getModuleCode());
        return builder.toString();
    }
}

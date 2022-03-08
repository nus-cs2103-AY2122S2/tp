package seedu.address.testutil;

import seedu.address.model.classgroup.ClassGroup;
import seedu.address.model.classgroup.ClassGroupId;
import seedu.address.model.classgroup.ClassGroupType;
import seedu.address.model.tamodule.ModuleCode;

/**
 * A utility class to help with building ClassGroup objects.
 */
public class ClassGroupBuilder {
    public static final String DEFAULT_ID = "T13";
    public static final ClassGroupType DEFAULT_TYPE = ClassGroupType.TUTORIAL;
    public static final String DEFAULT_MODULE_CODE = "CS2103T";

    private ClassGroupId classGroupId;
    private ClassGroupType classGroupType;
    private ModuleCode moduleCode;

    /**
     * Creates a {@code ClassGroupBuilder} with the default details.
     */
    public ClassGroupBuilder() {
        classGroupId = new ClassGroupId(DEFAULT_ID);
        classGroupType = DEFAULT_TYPE;
        moduleCode = new ModuleCode(DEFAULT_MODULE_CODE);
    }

    /**
     * Initializes the ClassGroupBuilder with the data of {@code classGroupToCopy}.
     */
    public ClassGroupBuilder(ClassGroup classGroupToCopy) {
        classGroupId = classGroupToCopy.getClassGroupId();
        classGroupType = classGroupToCopy.getClassGroupType();
        moduleCode = classGroupToCopy.getModuleCode();
    }

    /**
     * Sets the {@code ClassGroupId} of the {@code ClassGroup} that we are building.
     */
    public ClassGroupBuilder withClassGroupId(String classGroupId) {
        this.classGroupId = new ClassGroupId(classGroupId);
        return this;
    }

    /**
     * Sets the {@code ClassGroupType} of the {@code ClassGroup} that we are building.
     */
    public ClassGroupBuilder withClassGroupType(ClassGroupType classGroupType) {
        this.classGroupType = classGroupType;
        return this;
    }

    /**
     * Sets the {@code ModuleCode} of the {@code ClassGroup} that we are building.
     */
    public ClassGroupBuilder withModuleCode(String moduleCode) {
        this.moduleCode = new ModuleCode(moduleCode);
        return this;
    }

    public ClassGroup build() {
        return new ClassGroup(classGroupId, classGroupType, moduleCode);
    }
}

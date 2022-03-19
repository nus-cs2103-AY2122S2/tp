package seedu.address.testutil;

import seedu.address.model.classgroup.ClassGroup;
import seedu.address.model.classgroup.ClassGroupId;
import seedu.address.model.classgroup.ClassGroupType;
import seedu.address.model.tamodule.AcademicYear;
import seedu.address.model.tamodule.ModuleCode;
import seedu.address.model.tamodule.ModuleName;
import seedu.address.model.tamodule.TaModule;

/**
 * A utility class to help with building ClassGroup objects.
 */
public class ClassGroupBuilder {

    public static final String DEFAULT_CLASS_GROUP_ID = "G09";
    public static final String DEFAULT_CLASS_GROUP_TYPE = ClassGroupType.SECTIONAL.toString();
    public static final String DEFAULT_MODULE_NAME = "Software Engineering";
    public static final String DEFAULT_MODULE_CODE = "CS2101";
    public static final String DEFAULT_ACADEMIC_YEAR = "21S1";

    private ClassGroupId classGroupId;
    private ClassGroupType classGroupType;
    private TaModule module;

    /**
     * Creates a {@code ClassGroupBuilder} with the default details.
     */
    public ClassGroupBuilder() {
        classGroupId = new ClassGroupId(DEFAULT_CLASS_GROUP_ID);
        classGroupType = ClassGroupType.valueOf(DEFAULT_CLASS_GROUP_TYPE);
        module = new TaModule(new ModuleName(DEFAULT_MODULE_NAME), new ModuleCode(DEFAULT_MODULE_CODE),
                new AcademicYear(DEFAULT_ACADEMIC_YEAR));
    }

    /**
     * Initializes the ClassGroupBuilder with the data of {@code classGroupToCopy}.
     */
    public ClassGroupBuilder(ClassGroup classGroupToCopy) {
        classGroupId = classGroupToCopy.getClassGroupId();
        classGroupType = classGroupToCopy.getClassGroupType();
        module = classGroupToCopy.getModule();
    }

    /**
     * Sets the {@code Address} of the {@code ClassGroup} that we are building.
     */
    public ClassGroupBuilder withClassGroupId(String classGroupId) {
        this.classGroupId = new ClassGroupId(classGroupId);
        return this;
    }

    /**
     * Sets the {@code ClassGroupType} of the {@code ClassGroup} that we are building.
     */
    public ClassGroupBuilder withClassGroupType(String classGroupType) {
        this.classGroupType = ClassGroupType.valueOf(classGroupType);
        return this;
    }

    /**
     * Sets the {@code ModuleCode} of the {@code ClassGroup} that we are building.
     */
    public ClassGroupBuilder withModule(String moduleName, String moduleCode, String academicYear) {
        this.module = new TaModule(new ModuleName(moduleName), new ModuleCode(moduleCode),
                new AcademicYear(academicYear));
        return this;
    }

    /**
     * Sets the {@code ModuleCode} of the {@code ClassGroup} that we are building.
     */
    public ClassGroupBuilder withModule(TaModule module) {
        this.module = module;
        return this;
    }

    public ClassGroup build() {
        return new ClassGroup(classGroupId, classGroupType, module);
    }

}

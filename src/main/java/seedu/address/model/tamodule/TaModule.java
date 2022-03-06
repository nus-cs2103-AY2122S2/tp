package seedu.address.model.tamodule;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a Module in the TAssist.
 * It is named TaModule as it conflicts with the class java.lang.Module.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class TaModule {

    // Identity fields
    private final ModuleName moduleName;
    private final ModuleCode moduleCode;

    /**
     * Every field must be present and not null.
     */
    public TaModule(ModuleName moduleName, ModuleCode moduleCode) {
        requireAllNonNull(moduleName, moduleCode);
        this.moduleName = moduleName;
        this.moduleCode = moduleCode;
    }

    public ModuleName getModuleName() {
        return moduleName;
    }

    public ModuleCode getModuleCode() {
        return moduleCode;
    }

    /**
     * Returns true if both modules have the same moduleCode.
     * This defines a weaker notion of equality between two modules.
     */
    public boolean isSameModule(TaModule otherTaModule) {
        if (otherTaModule == this) {
            return true;
        }

        return otherTaModule != null
                && otherTaModule.getModuleCode().equals(getModuleCode());
    }

    /**
     * Returns true if both modules have the same identity and data fields.
     * This defines a stronger notion of equality between two modules.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof TaModule)) {
            return false;
        }

        TaModule otherTaModule = (TaModule) other;
        return otherTaModule.getModuleName().equals(getModuleName())
                && otherTaModule.getModuleCode().equals(getModuleCode());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(moduleName, moduleCode);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getModuleCode())
                .append("; Module Name: ")
                .append(getModuleName());
        return builder.toString();
    }

}

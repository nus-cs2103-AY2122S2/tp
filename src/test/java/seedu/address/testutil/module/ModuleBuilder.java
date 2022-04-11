package seedu.address.testutil.module;

import seedu.address.model.module.Module;

/**
 * A utility class to help with building Module objects.
 */
public class ModuleBuilder {
    public static final String DEFAULT_MODULE = "CS2103T";

    private String code;

    /**
     * Creates a {@code ModuleBuilder} with the default details.
     */
    public ModuleBuilder() {
        code = DEFAULT_MODULE;
    }

    /**
     * Initializes the ModuleBuilder with the data of {@code moduleToCopy}.
     */
    public ModuleBuilder(Module moduleToCopy) {
        code = moduleToCopy.getCode();
    }

    /**
     * Sets the {@code code} of the {@code Module} that we are building.
     */
    public ModuleBuilder withCode(String code) {
        this.code = code;
        return this;
    }

    /**
     * Creates the {@code Module} represented by this {@code ModuleBuilder} instance.
     */
    public Module build() {
        return new Module(code);
    }
}

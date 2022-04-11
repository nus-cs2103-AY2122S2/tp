package seedu.address.testutil;

import seedu.address.model.person.Mod;

/**
 * A utility class to help with building Person objects.
 */
public class ModuleBuilder {

    public static final String DEFAULT_GROUP = "T01";
    public static final String DEFAULT_MOD = "CS2103T";

    private Mod mod;

    /**
     * Creates a {@code ModuleBuilder} with the default details.
     */
    public ModuleBuilder() {
        mod = new Mod(DEFAULT_MOD, DEFAULT_GROUP);
    }

    /**
     * Initializes the {@link ModuleBuilder} with the data of {@code personToCopy}.
     */
    public ModuleBuilder(Mod modToCopy) {
        this.mod = new Mod(modToCopy.getMod(), modToCopy.getDefaultGroup());
    }

    /**
     * Sets the {@code Mod} of the {@code Person} that we are building.
     */
    public ModuleBuilder withMod(String mod) {
        this.mod = new Mod(mod);
        return this;
    }

    /**
     * Sets the {@code Group} of the {@code Mod} that we are building.
     */
    public ModuleBuilder withGroup(String group) {
        this.mod.setDefaultGroup(group);
        return this;
    }

    public Mod build() {
        return new Mod(mod.toString(), mod.getDefaultGroup());
    }

}


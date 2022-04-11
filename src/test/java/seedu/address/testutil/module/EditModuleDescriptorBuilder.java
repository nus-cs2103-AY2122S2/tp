package seedu.address.testutil.module;

import seedu.address.logic.commands.module.EditModuleCommand.EditModuleDescriptor;
import seedu.address.model.module.Module;

public class EditModuleDescriptorBuilder {
    private EditModuleDescriptor descriptor;

    public EditModuleDescriptorBuilder() {
        descriptor = new EditModuleDescriptor();
    }

    public EditModuleDescriptorBuilder(EditModuleDescriptor descriptor) {
        this.descriptor = new EditModuleDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditModuleDescriptor} with fields containing {@code module}'s details
     */
    public EditModuleDescriptorBuilder(Module module) {
        descriptor = new EditModuleDescriptor();
        descriptor.setCode(module.getCode());
    }

    /**
     * Sets the {@code code} of the {@code EditModuleDescriptor} that we are building.
     */
    public EditModuleDescriptorBuilder withCode(String code) {
        descriptor.setCode(code);
        return this;
    }

    public EditModuleDescriptor build() {
        return descriptor;
    }
}

package seedu.contax.testutil;

import seedu.contax.logic.commands.EditTagCommand.EditTagDescriptor;
import seedu.contax.model.tag.Tag;

/**
 * A utility class to help with building EditTagDescriptor objects.
 */
public class EditTagDescriptorBuilder {
    private EditTagDescriptor descriptor;

    public EditTagDescriptorBuilder() {
        this.descriptor = new EditTagDescriptor();
    }

    public EditTagDescriptorBuilder(EditTagDescriptor descriptor) {
        this.descriptor = descriptor;
    }

    /**
     *  Returns a {@code EditTagDescriptorBuilder} with fields containing {@code tag}'s details
     */
    public EditTagDescriptorBuilder(Tag tag) {
        this.descriptor = new EditTagDescriptor();
        descriptor.setTagName(tag.getTagName());
    }

    /**
     * Sets the tag name of the {@code EditTagDescriptor} that we are building.
     */
    public EditTagDescriptorBuilder withName(String name) {
        descriptor.setTagName(name);
        return this;
    }

    public EditTagDescriptor build() {
        return descriptor;
    }
}

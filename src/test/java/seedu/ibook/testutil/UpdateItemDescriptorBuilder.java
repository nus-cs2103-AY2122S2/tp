package seedu.ibook.testutil;

import seedu.ibook.logic.commands.item.UpdateItemCommand.UpdateItemDescriptor;
import seedu.ibook.model.item.ExpiryDate;
import seedu.ibook.model.item.ItemDescriptor;
import seedu.ibook.model.item.Quantity;

/**
 * A utility class to help with building UpdateItemDescriptor objects.
 */
public class UpdateItemDescriptorBuilder {
    private final UpdateItemDescriptor descriptor;

    public UpdateItemDescriptorBuilder() {
        descriptor = new UpdateItemDescriptor();
    }

    public UpdateItemDescriptorBuilder(UpdateItemDescriptor descriptor) {
        this.descriptor = new UpdateItemDescriptor(descriptor);
    }

    /**
     * Returns an {@code UpdateItemDescriptor} with fields containing {@code product}'s details.
     */
    public UpdateItemDescriptorBuilder(ItemDescriptor itemDescriptor) {
        descriptor = new UpdateItemDescriptor();
        descriptor.setExpiryDate(itemDescriptor.getExpiryDate());
        descriptor.setQuantity(itemDescriptor.getQuantity());
    }

    /**
     * Sets the {@code ExpiryDate} of the {@code UpdateItemDescriptor} that we are building.
     */
    public UpdateItemDescriptorBuilder withExpiryDate(String expiryDate) {
        descriptor.setExpiryDate(new ExpiryDate(expiryDate));
        return this;
    }

    /**
     * Sets the {@code Quantity} of the {@code UpdateItemDescriptor} that we are building.
     */
    public UpdateItemDescriptorBuilder withQuantity(String quantity) {
        descriptor.setQuantity(new Quantity(quantity));
        return this;
    }

    public UpdateItemDescriptor build() {
        return descriptor;
    }
}

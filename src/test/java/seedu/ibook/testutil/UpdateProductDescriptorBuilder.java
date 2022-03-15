package seedu.ibook.testutil;

import seedu.ibook.logic.commands.UpdateCommand.UpdateProductDescriptor;
import seedu.ibook.model.product.Category;
import seedu.ibook.model.product.Description;
import seedu.ibook.model.product.Name;
import seedu.ibook.model.product.Price;
import seedu.ibook.model.product.Product;

/**
 * A utility class to help with building UpdateProductDescriptor objects.
 */
public class UpdateProductDescriptorBuilder {
    private UpdateProductDescriptor descriptor;

    public UpdateProductDescriptorBuilder() {
        descriptor = new UpdateProductDescriptor();
    }

    public UpdateProductDescriptorBuilder(UpdateProductDescriptor descriptor) {
        this.descriptor = new UpdateProductDescriptor(descriptor);
    }

    /**
     * Returns an {@code UpdateProductDescriptor} with fields containing {@code product}'s details.
     */
    public UpdateProductDescriptorBuilder(Product product) {
        descriptor = new UpdateProductDescriptor();
        descriptor.setName(product.getName());
        descriptor.setCategory(product.getCategory());
        descriptor.setDescription(product.getDescription());
        descriptor.setPrice(product.getPrice());
    }

    /**
     * Sets the {@code Name} of the {@code UpdateProductDescriptor} that we are building.
     */
    public UpdateProductDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Category} of the {@code UpdateProductDescriptor} that we are building.
     */
    public UpdateProductDescriptorBuilder withCategory(String category) {
        descriptor.setCategory(new Category(category));
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code UpdateProductDescriptor} that we are building.
     */
    public UpdateProductDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(new Description(description));
        return this;
    }

    /**
     * Sets the {@code Price} of the {@code UpdateProductDescriptor} that we are building.
     */
    public UpdateProductDescriptorBuilder withPrice(String price) {
        descriptor.setPrice(new Price(price));
        return this;
    }

    public UpdateProductDescriptor build() {
        return descriptor;
    }
}

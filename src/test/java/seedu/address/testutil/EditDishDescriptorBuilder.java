package seedu.address.testutil;

import seedu.address.logic.commands.EditDishCommand.EditDishDescriptor;
import seedu.address.model.dish.Dish;
import seedu.address.model.dish.NameDish;
import seedu.address.model.dish.PriceDish;

/**
 * A utility class to help with building EditDishDescriptor objects.
 */
public class EditDishDescriptorBuilder {

    private final EditDishDescriptor descriptor;

    public EditDishDescriptorBuilder() {
        descriptor = new EditDishDescriptor();
    }

    public EditDishDescriptorBuilder(EditDishDescriptor descriptor) {
        this.descriptor = new EditDishDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditDishDescriptor} with fields containing {@code Dish}'s details
     */
    public EditDishDescriptorBuilder(Dish dish) {
        descriptor = new EditDishDescriptor();
        descriptor.setName(dish.getName());
        descriptor.setPrice(dish.getPrice());
    }

    /**
     * Sets the {@code NameDish} of the {@code EditDishDescriptor} that we are building.
     */
    public EditDishDescriptorBuilder withName(String name) {
        descriptor.setName(new NameDish(name));
        return this;
    }

    /**
     * Sets the {@code PhoneDish} of the {@code EditDishDescriptor} that we are building.
     */
    public EditDishDescriptorBuilder withPrice(String price) {
        descriptor.setPrice(new PriceDish(price));
        return this;
    }

    public EditDishDescriptor build() {
        return descriptor;
    }
}

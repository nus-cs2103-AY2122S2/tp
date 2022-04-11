package seedu.address.testutil;

import java.util.ArrayList;

import seedu.address.logic.commands.EditOrderCommand;
import seedu.address.model.dish.Dish;
import seedu.address.model.order.Order;

/**
 * A utility class to help with building EditOrderDescriptor objects.
 */
public class EditOrderDescriptorBuilder {

    private final EditOrderCommand.EditOrderDescriptor descriptor;

    public EditOrderDescriptorBuilder() {
        descriptor = new EditOrderCommand.EditOrderDescriptor();
    }

    /**
     * Returns an {@code EditOrderDescriptor} with fields containing {@code order}'s details
     */
    public EditOrderDescriptorBuilder(Order order) {
        descriptor = new EditOrderCommand.EditOrderDescriptor();
        descriptor.setCustomerPhone(order.getCustomerPhone());

        ArrayList<String> dishNames = new ArrayList<>();
        for (Dish dish : order.getDishes()) {
            dishNames.add(dish.toString());
        }

        descriptor.setDishes(dishNames.toArray(new String[0]));
    }

    /**
     * Sets the {@code CustomerPhone} of the {@code EditOrderDescriptor} that we are building.
     */
    public EditOrderDescriptorBuilder withPhone(String phone) {
        descriptor.setCustomerPhone(phone);
        return this;
    }

    /**
     * Sets the {@code Dish} of the {@code EditOrderDescriptor} that we are building.
     */
    public EditOrderDescriptorBuilder withDish(String[] dishes) {
        descriptor.setDishes(dishes);
        return this;
    }

    public EditOrderCommand.EditOrderDescriptor build() {
        return descriptor;
    }
}

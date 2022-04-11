package seedu.address.model.dish;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a Dish in FoodOnWheels.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Dish {

    // Identity fields
    private final NameDish name;
    private final PriceDish price;

    /**
     * Every field must be present and not null.
     */
    public Dish(NameDish name, PriceDish price) {
        requireAllNonNull(name, price);
        this.name = name;
        this.price = price;
    }

    public NameDish getName() {
        return name;
    }

    public PriceDish getPrice() {
        return price;
    }

    /**
     * Returns true if both dishes have the same name.
     * This defines a weaker notion of equality between two dishes.
     */
    public boolean isSameDish(Dish otherDish) {
        if (otherDish == this) {
            return true;
        }

        return otherDish != null
                && otherDish.getName().equals(getName());
    }

    /**
     * Returns true if both dishes have the same identity and data fields.
     * This defines a stronger notion of equality between two dishes.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Dish)) {
            return false;
        }

        Dish otherDish = (Dish) other;
        return otherDish.getName().equals(getName()) && otherDish.getPrice().equals(getPrice());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName());
        return builder.toString();
    }

}


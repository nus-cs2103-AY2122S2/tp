package seedu.address.model.property;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.person.Address;

/**
 * Represents a Property in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Property {

    public static final String MESSAGE_CONSTRAINTS =
            "Property must have have the format: [region, address, size, price]";

    private final Region region;
    private final Address address;
    private final Size size;
    private final Price price;

    /**
     * Every field must be present and not null.
     */
    public Property(Region region, Address address, Size size, Price price) {
        requireAllNonNull(region, address, size, price);
        this.region = region;
        this.address = address;
        this.size = size;
        this.price = price;
    }

    public Region getRegion() {
        return region;
    }

    public Address getAddress() {
        return address;
    }

    public Size getSize() {
        return size;
    }

    public Price getPrice() {
        return price;
    }

    /**
     * Returns the input command representing the addition of a {@code Property}.
     */
    public String toParseValue() {
        return region + "," + address + "," + size + "," + price.value;
    }

    /**
     * Returns a plain string representation of this {@code Property}
     * which concatenates all attributes with spaces.
     */
    public String toPlainString() {
        return region + " " + address + " " + size + " " + price.value;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getAddress())
                .append("; Region: ")
                .append(getRegion())
                .append("; Size: ")
                .append(getSize())
                .append("; Price: ")
                .append(getPrice());

        return '[' + builder.toString() + ']';
    }

    /**
     * Returns true if both properties have the same address.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameProperty(Property otherProperty) {
        if (otherProperty == this) {
            return true;
        }

        return otherProperty != null
                && otherProperty.getAddress().equals(getAddress());
    }

    /**
     * Returns true if both properties have the same region, address, size and price.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Property)) {
            return false;
        }

        Property otherProperty = (Property) other;
        return otherProperty.getRegion().equals(getRegion())
                && otherProperty.getAddress().equals(getAddress())
                && otherProperty.getSize().equals(getSize())
                && otherProperty.getPrice().equals(getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(region, address, size, price);
    }

}

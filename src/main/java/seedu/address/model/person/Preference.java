package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.property.Price;
import seedu.address.model.property.Property;
import seedu.address.model.property.Region;
import seedu.address.model.property.Size;


public class Preference {

    public static final String MESSAGE_CONSTRAINTS =
            "Preference must have have the format: [region, size, lower price, higher price]";

    private final Region region;
    private final Size size;
    private final Price lowPrice;
    private final Price highPrice;

    /**
     * Every field must be present and not null.
     */
    public Preference(Region region, Size size, Price lowPrice, Price highPrice) {
        requireAllNonNull(region, size, lowPrice, highPrice);
        this.region = region;
        this.size = size;
        this.lowPrice = lowPrice;
        this.highPrice = highPrice;
    }

    /**
     * Returns true if this preference matches with the given property.
     */
    public boolean matches(Property other) {
        return region.equals(other.getRegion())
                && size.equals(other.getSize())
                && lowPrice.compareTo(other.getPrice()) <= 0
                && highPrice.compareTo(other.getPrice()) >= 0;
    }

    public Region getRegion() {
        return region;
    }

    public Size getSize() {
        return size;
    }

    public Price getLowPrice() {
        return lowPrice;
    }

    public Price getHighPrice() {
        return highPrice;
    }

    /**
     * Returns the input command representing the addition of a {@code Preference}.
     */
    public String toParseValue() {
        return region + "," + size + "," + lowPrice.toParseValue() + "," + highPrice.toParseValue();
    }

    @Override
    public String toString() {
        return "[Region: " + region
                + "; Size: " + size
                + "; Lower Price: " + lowPrice
                + "; Higher Price: " + highPrice + "]";
    }

    /**
     * Returns true if both preferences have the same region, size, lowPrice, and highPrice.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Preference)) {
            return false;
        }

        Preference otherPreference = (Preference) other;
        return otherPreference.region.equals(region)
                && otherPreference.size.equals(size)
                && otherPreference.lowPrice.equals(lowPrice)
                && otherPreference.highPrice.equals(highPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(region, size, lowPrice, highPrice);
    }

}

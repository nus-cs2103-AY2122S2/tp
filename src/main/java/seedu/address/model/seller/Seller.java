package seedu.address.model.seller;

import java.util.List;
import java.util.Set;

import seedu.address.model.client.Appointment;
import seedu.address.model.client.Client;
import seedu.address.model.client.Name;
import seedu.address.model.client.Phone;
import seedu.address.model.property.PropertyToSell;
import seedu.address.model.tag.Tag;

/**
 * Represents a client who is selling houses.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Seller extends Client {

    // Data fields
    private final List<PropertyToSell> propertiesToSell;

    /**
     * Constructor of Seller class.
     */
    public Seller(Name name, Phone phone, Appointment appointment, Set<Tag> tags, List<PropertyToSell> properties) {
        super(name, phone, appointment, tags);
        this.propertiesToSell = properties;
    }

    /**
     * Returns the properties the client is willing to sell.
     *
     * @return List of PropertyToSell.
     */
    public List<PropertyToSell> getPropertiesToSell() {
        return propertiesToSell;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Seller)) {
            return false;
        }

        Seller otherSeller = (Seller) other;
        return otherSeller.getName().equals(getName())
                && otherSeller.getPhone().equals(getPhone())
                && otherSeller.getTags().equals(getTags())
                && otherSeller.hasSameProperties(this);
    }

    /**
     * Checks if 2 Sellers have the matching properties.
     *
     * @param other The other Seller.
     * @return Whether their properties match.
     */
    public boolean hasSameProperties(Seller other) {
        return propertiesToSell.containsAll(other.propertiesToSell)
                && other.propertiesToSell.containsAll(propertiesToSell);

    }
}

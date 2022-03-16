package seedu.address.model.seller;

import java.util.List;
import java.util.Set;

import seedu.address.model.client.Address;
import seedu.address.model.client.Appointment;
import seedu.address.model.client.Client;
import seedu.address.model.client.Description;
import seedu.address.model.client.Email;
import seedu.address.model.client.Name;
import seedu.address.model.client.Phone;
import seedu.address.model.client.Remark;
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
    public Seller(Name name, Description description, Phone phone, Email email, Address address, Remark remark,
                 Appointment appointment, Set<Tag> tags, List<PropertyToSell> properties) {
        super(name, description, phone, email, address, remark, appointment, tags);
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
}

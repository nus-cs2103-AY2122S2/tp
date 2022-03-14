package seedu.address.model.buyer;

import java.util.Set;

import seedu.address.model.client.Address;
import seedu.address.model.client.Appointment;
import seedu.address.model.client.Client;
import seedu.address.model.client.Description;
import seedu.address.model.client.Email;
import seedu.address.model.client.Name;
import seedu.address.model.client.Phone;
import seedu.address.model.client.Remark;
import seedu.address.model.tag.Tag;

/**
 * Represents a client who is buying houses.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Buyer extends Client {

    // Data fields
    private final List<PropertyToBuy> propertiesToBuy;

    /**
     * Constructor of Buyer class.
     */
    public Buyer(Name name, Description description, Phone phone, Email email, Address address, Remark remark,
                 Appointment appointment, Set<Tag> tags, List<PropertyToBuy> properties) {
        super(name, description, phone, email, address, remark, appointment, tags);
        this.propertiesToBuy = properties;
    }

    /**
     * Returns the properties the client is willing to buy.
     *
     * @return List of PropertyToBuy.
     */
    public List<PropertyToBuy> getPropertiesToBuy() {
        return propertiesToBuy;
    }
}

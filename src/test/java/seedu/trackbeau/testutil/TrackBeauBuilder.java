package seedu.trackbeau.testutil;

import seedu.trackbeau.model.TrackBeau;
import seedu.trackbeau.model.customer.Customer;

/**
 * A utility class to help with building Trackbeau objects.
 * Example usage: <br>
 *     {@code TrackBeau tb = new TrackBeauBuilder().withCustomer("John", "Doe").build();}
 */
public class TrackBeauBuilder {

    private TrackBeau trackBeau;

    public TrackBeauBuilder() {
        trackBeau = new TrackBeau();
    }

    public TrackBeauBuilder(TrackBeau trackBeau) {
        this.trackBeau = trackBeau;
    }

    /**
     * Adds a new {@code Person} to the {@code TrackBeau} that we are building.
     */
    public TrackBeauBuilder withCustomer(Customer customer) {
        trackBeau.addCustomer(customer);
        return this;
    }

    public TrackBeau build() {
        return trackBeau;
    }
}

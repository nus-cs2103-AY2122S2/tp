package seedu.address.testutil;

import seedu.address.model.customer.AddressCustomer;
import seedu.address.model.customer.Customer;
import seedu.address.model.customer.NameCustomer;
import seedu.address.model.customer.PhoneCustomer;

/**
 * A utility class to help with building Customer objects.
 */
public class CustomerBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private NameCustomer nameCustomer;
    private PhoneCustomer phoneCustomer;
    private AddressCustomer addressCustomer;

    /**
     * Creates a {@code CustomerBuilder} with the default details.
     */
    public CustomerBuilder() {
        nameCustomer = new NameCustomer(DEFAULT_NAME);
        phoneCustomer = new PhoneCustomer(DEFAULT_PHONE);
        addressCustomer = new AddressCustomer(DEFAULT_ADDRESS);
    }

    /**
     * Initializes the CustomerBuilder with the data of {@code customerToCopy}.
     */
    public CustomerBuilder(Customer customerToCopy) {
        nameCustomer = customerToCopy.getName();
        phoneCustomer = customerToCopy.getPhone();
        addressCustomer = customerToCopy.getAddress();
    }

    /**
     * Sets the {@code NameCustomer} of the {@code Customer} that we are building.
     */
    public CustomerBuilder withName(String name) {
        this.nameCustomer = new NameCustomer(name);
        return this;
    }

    /**
     * Sets the {@code AddressCustomer} of the {@code Customer} that we are building.
     */
    public CustomerBuilder withAddress(String address) {
        this.addressCustomer = new AddressCustomer(address);
        return this;
    }

    /**
     * Sets the {@code PhoneCustomer} of the {@code Customer} that we are building.
     */
    public CustomerBuilder withPhone(String phone) {
        this.phoneCustomer = new PhoneCustomer(phone);
        return this;
    }


    public Customer build() {
        return new Customer(nameCustomer, phoneCustomer, addressCustomer);
    }

}

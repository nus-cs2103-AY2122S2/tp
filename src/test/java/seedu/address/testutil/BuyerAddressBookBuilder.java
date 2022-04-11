package seedu.address.testutil;

import seedu.address.model.BuyerAddressBook;
import seedu.address.model.buyer.Buyer;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withclient("John", "Doe").build();}
 */
public class BuyerAddressBookBuilder {

    private BuyerAddressBook buyerAddressBook;

    public BuyerAddressBookBuilder() {
        buyerAddressBook = new BuyerAddressBook();
    }

    public BuyerAddressBookBuilder(BuyerAddressBook buyerAddressBook) {
        this.buyerAddressBook = buyerAddressBook;
    }

    /**
     * Adds a new {@code buyer} to the {@code AddressBook} that we are building.
     */
    public BuyerAddressBookBuilder withBuyer(Buyer buyer) {
        buyerAddressBook.addBuyer(buyer);
        return this;
    }

    public BuyerAddressBook build() {
        return buyerAddressBook;
    }
}

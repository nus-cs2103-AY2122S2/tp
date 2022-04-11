package seedu.address.testutil;

import seedu.address.model.SellerAddressBook;
import seedu.address.model.seller.Seller;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withclient("John", "Doe").build();}
 */
public class SellerAddressBookBuilder {

    private SellerAddressBook sellerAddressBook;

    public SellerAddressBookBuilder() {
        sellerAddressBook = new SellerAddressBook();
    }

    public SellerAddressBookBuilder(SellerAddressBook sellerAddressBook) {
        this.sellerAddressBook = sellerAddressBook;
    }

    /**
     * Adds a new {@code seller} to the {@code AddressBook} that we are building.
     */
    public SellerAddressBookBuilder withSeller(Seller seller) {
        sellerAddressBook.addSeller(seller);
        return this;
    }

    public SellerAddressBook build() {
        return sellerAddressBook;
    }
}

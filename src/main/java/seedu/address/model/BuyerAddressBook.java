package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.buyer.Buyer;
import seedu.address.model.buyer.UniqueBuyerList;


public class BuyerAddressBook implements ReadOnlyBuyerAddressBook {

    private final UniqueBuyerList buyers;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        buyers = new UniqueBuyerList();
    }

    public BuyerAddressBook() {}

    /**
     * Creates an AddressBook using the buyers in the {@code toBeCopied}
     */
    public BuyerAddressBook(ReadOnlyBuyerAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the buyer list with {@code buyers}.
     * {@code sellers} must not contain duplicate buyers.
     */
    public void setBuyers(List<Buyer> buyers) {
        this.buyers.setBuyers(buyers);
    }

    /**
     * Resets the existing data of this {@code SellerAddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyBuyerAddressBook newData) {
        requireNonNull(newData);
        setBuyers(newData.getBuyerList());
    }

    /**
     * Checks if the buyer list contains the buyer.
     *
     * @param buyer The buyer.
     * @return Whether buyer is already in list.
     */
    public boolean hasBuyer(Buyer buyer) {
        requireNonNull(buyer);
        return buyers.contains(buyer);
    }

    /**
     * Adds a new buyer to the buyers list.
     *
     * @param buyer The buyer.
     */
    public void addBuyer(Buyer buyer) {
        buyers.add(buyer);
    }

    /**
     * Replaces the given buyers {@code target} in the list with {@code editedBuyers}.
     * {@code target} must exist in the address book.
     * The buyer identity of {@code editedBuyers} must not be the same as another existing buyer in the address book.
     */
    public void setBuyer(Buyer target, Buyer editedBuyer) {
        requireNonNull(editedBuyer);
        buyers.setBuyer(target, editedBuyer);
    }

    public void sortPersons() {
        buyers.sortPersons();
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeBuyer(Buyer key) {
        buyers.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return buyers.asUnmodifiableObservableList().size() + " buyers";
        // TODO: refine later
    }

    @Override
    public ObservableList<Buyer> getBuyerList() {
        return buyers.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && buyers.equals(((BuyerAddressBook) other).buyers));
    }

    @Override
    public int hashCode() {
        return buyers.hashCode();
    }
}

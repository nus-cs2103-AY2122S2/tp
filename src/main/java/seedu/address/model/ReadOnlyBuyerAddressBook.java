package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.buyer.Buyer;

public interface ReadOnlyBuyerAddressBook {
    /**
     * Returns an unmodifiable view of the clients list.
     * This list will not contain any duplicate clients.
     */
    ObservableList<Buyer> getBuyerList();
}

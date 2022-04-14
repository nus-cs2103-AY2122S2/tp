package seedu.address.model;

import java.util.ArrayList;

/**
 * Stores a list of address books as they are updated throughout the current user session.
 */
public class AddressBookHistory {
    private final ArrayList<AddressBook> addressBooks;

    /**
     * Creates an AddressBookHistory which stores AddressBooks after each
     * successful user command.
     */
    public AddressBookHistory(ReadOnlyAddressBook addressBook) {
        addressBooks = new ArrayList<>();
        addressBooks.add(new AddressBook(addressBook));
    }

    public ArrayList<AddressBook> getAddressBooks() {
        return addressBooks;
    }

    /**
     * Adds an AddressBook to the list of AddressBooks in AddressBookHistory.
     */
    public void addAddressBook(AddressBook addressBook) {
        addressBooks.add(new AddressBook(addressBook));
    }

    /**
     * Returns true if AddressBookHistory is empty.
     */
    public boolean isEmpty() {
        return addressBooks.isEmpty();
    }

    /**
     * Returns the AddressBook that was saved in AddressBookHistory
     * before the execution of previous command.
     */
    public AddressBook getPreviousAddressBook() {
        if (addressBooks.size() == 1) {
            return addressBooks.get(0);
        }

        // AB state after previous command must be removed first.
        int undoneCommandAddressBookIndex = addressBooks.size() - 1;
        addressBooks.remove(undoneCommandAddressBookIndex);

        // AB state before previous command is restored.
        int previousAddressBookIndex = addressBooks.size() - 1;
        AddressBook previousAddressBook = addressBooks.get(previousAddressBookIndex);
        return previousAddressBook;
    }

    /**
     * Clears address book history and sets the initial address book in history to an empty address book.
     */
    public void clearHistory() {
        addressBooks.clear();
        addressBooks.add(new AddressBook());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof AddressBookHistory)) { //this handles null as well.
            return false;
        }

        AddressBookHistory otherAddressBookHistory = (AddressBookHistory) obj;
        return addressBooks.equals(otherAddressBookHistory.addressBooks);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int counter = 0;
        for (AddressBook ab : addressBooks) {
            counter++;
            sb.append(counter + ". ");
            sb.append(ab.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
}

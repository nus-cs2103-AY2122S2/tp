package seedu.address.model;

import java.util.ArrayList;

/**
 * Stores a list of address books as they are updated throughout the current user session.
 */
public class AddressBookHistory {
    private final ArrayList<AddressBook> addressBooks;

    public AddressBookHistory(ReadOnlyAddressBook addressBook) {
        addressBooks = new ArrayList<>();
        addressBooks.add(new AddressBook(addressBook));
    }

    public void addAddressBook(AddressBook addressBook) {
        addressBooks.add(new AddressBook(addressBook));
    }

    public boolean isEmpty() {
        return addressBooks.isEmpty();
    }

    /**
     * Returns AddressBook before execution of previous command.
     */
    public AddressBook getPreviousAddressBook() {
        if (addressBooks.size() == 1) {
            return addressBooks.get(0);
        }

        //AB state after previous command must be removed first.
        int undoneCommandAddressBookIndex = addressBooks.size() - 1;
        addressBooks.remove(undoneCommandAddressBookIndex);

        //AB state before previous command is restored.
        int previousAddressBookIndex = addressBooks.size() - 1;
        AddressBook previousAddressBook = addressBooks.remove(previousAddressBookIndex);
        return previousAddressBook;
    }

    /**
     * Returns AddressBook before execution of previous command (that was not undo), if undo was the previous command.
     */
    public AddressBook getPreviousAddressBookAfterChainedUndo() {
        if (addressBooks.size() == 1) {
            return addressBooks.get(0);
        }

        //AB state before previous command (that was not undo) is restored.
        int previousAddressBookIndex = addressBooks.size() - 1;
        AddressBook previousAddressBook = addressBooks.remove(previousAddressBookIndex);
        return previousAddressBook;
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

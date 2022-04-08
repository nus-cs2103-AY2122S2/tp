package woofareyou.model;

import static java.util.logging.Level.INFO;

import java.util.ArrayList;
import java.util.logging.Logger;

public class VersionedPetBook extends PetBook {

    private static final ArrayList<ReadOnlyPetBook> addressBookStateList = new ArrayList<>();
    private Integer currentStatePointer = 0;
    private final Logger logger = Logger.getLogger(VersionedPetBook.class.getName());


    public VersionedPetBook() {}

    /**
     * Creates a VersionedAddressBook using the Pets in the {@code toBeCopied}
     */
    public VersionedPetBook(ReadOnlyPetBook toBeCopied) {
        this();
        addressBookStateList.add(new PetBook(toBeCopied));
    }

    /**
     * Commits a new addressBook state to the addressBookStateList
     * @param currentAddressBookState current address book state
     */
    public void commit(ReadOnlyPetBook currentAddressBookState) {
        addressBookStateList.add(new PetBook(currentAddressBookState));
        this.currentStatePointer = addressBookStateList.size() - 1;
    }

    /**
     * Shifts the current state pointer to the left.
     * @return The previous state of the address book.
     */
    public ReadOnlyPetBook undo() throws Exception {
        if (addressBookStateList.size() <= 0) {
            throw new Exception("No commands to undo");
        }

        logger.log(INFO, String.valueOf(addressBookStateList.size()));
        this.currentStatePointer -= 1;
        addressBookStateList.remove(this.currentStatePointer + 1);
        return addressBookStateList.get(this.currentStatePointer);
    }
}

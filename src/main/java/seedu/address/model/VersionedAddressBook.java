package seedu.address.model;

import java.util.LinkedList;

import seedu.address.model.person.UniquePersonList;

public class VersionedAddressBook extends AddressBook {
    private LinkedList<UniquePersonList> addressBookStateList = new LinkedList<>();
    private int currentState = -1;

    public VersionedAddressBook() {
        super();
    }

    /** Initializes an instance of VersionedAddressBook with the given AddressBook. */
    public VersionedAddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
        commit();
        this.currentState = 0;
    }

    /** Saves the current state of the AddressBook into its history. */
    public void commit() {
        while (addressBookStateList.size() > currentState + 1) {
            // Clears all history in front of this commit
            addressBookStateList.removeLast();
        }
        UniquePersonList history = new UniquePersonList();
        history.setPersons(this.persons);
        this.addressBookStateList.add(history);
        currentState++;
    }

    /**
     * Undoes the last command that changed the AddressBook.
     * Does nothing if there are no commands in history to undo.
     */
    public void undo() {
        if (canUndo()) {
            currentState--;
            assert (currentState >= 0 && currentState < this.addressBookStateList.size());
            this.persons.setPersons(this.addressBookStateList.get(currentState));
        }
    }

    /**
     * Redo the last command that was undone.
     * Does nothing if there are no commands in history to redo.
     */
    public void redo() {
        if (canRedo()) {
            currentState++;
            assert (currentState >= 0 && currentState < this.addressBookStateList.size());
            this.persons.setPersons(this.addressBookStateList.get(currentState));
        }
    }

    /**
     * Returns whether there are any commands in history that can be undone.
     *
     * @return True if there are commands that can be undone, False otherwise.
     */
    public boolean canUndo() {
        if (this.addressBookStateList.size() < 1) {
            return false;
        }
        return this.currentState != 0;
    }

    /**
     * Returns whether there are any commands in history that can be redone.
     *
     * @return True if there are commands that can be redone, False otherwise.
     */
    public boolean canRedo() {
        return this.addressBookStateList.size() - 1 > currentState;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof VersionedAddressBook // instanceof handles nulls
                && persons.equals(((VersionedAddressBook) other).persons) // state check
                && addressBookStateList.equals(((VersionedAddressBook) other).addressBookStateList)
                && currentState == ((VersionedAddressBook) other).currentState);
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}

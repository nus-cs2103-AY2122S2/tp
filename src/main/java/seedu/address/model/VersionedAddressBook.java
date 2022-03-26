package seedu.address.model;

import seedu.address.model.person.UniquePersonList;

import java.util.LinkedList;

public class VersionedAddressBook extends AddressBook{
    private LinkedList<UniquePersonList> addressBookStateList;
    private int currentState = -1;

    public VersionedAddressBook(){
        super();
        this.addressBookStateList = new LinkedList<>();
        this.currentState = -1;
    }

    public VersionedAddressBook(ReadOnlyAddressBook toBeCopied){
        this();
        resetData(toBeCopied);
        commit();
        this.currentState = 0;
    }

    public void commit(){
        while(addressBookStateList.size() > currentState + 1){
            // Clears all history in front of this commit
            addressBookStateList.removeLast();
        }
        UniquePersonList history = new UniquePersonList();
        history.setPersons(this.persons);
        this.addressBookStateList.add(history);
        currentState++;
    }

    public void undo(){
        if (canUndo()) {
            currentState--;
            assert (currentState >= 0  && currentState < this.addressBookStateList.size());
            this.persons.setPersons(this.addressBookStateList.get(currentState));
        }
    }

    public void redo(){
        if (canRedo()){
            currentState++;
            assert (currentState >= 0  && currentState < this.addressBookStateList.size());
            this.persons.setPersons(this.addressBookStateList.get(currentState));
        }
    }

    public boolean canUndo(){
        if (this.addressBookStateList.size() < 1){
            return false;
        }
        return this.currentState != 0;
    }

    public boolean canRedo(){
        return this.addressBookStateList.size() - 1 > currentState;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof VersionedAddressBook // instanceof handles nulls
                && persons.equals(((VersionedAddressBook) other).persons) // state check
                && addressBookStateList.equals(((VersionedAddressBook) other).addressBookStateList));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}

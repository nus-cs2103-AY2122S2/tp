package seedu.address.model;

import com.sun.javafx.collections.UnmodifiableListSet;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

import java.util.LinkedList;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class VersionedAddressBook extends AddressBook{
    private LinkedList<UniquePersonList> addressBookStateList;
    private UniquePersonList persons;
    private int currentState = -1;

    public VersionedAddressBook(){
        this.addressBookStateList = new LinkedList<>();
        this.persons = new UniquePersonList();
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

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);
        setPersons(newData.getPersonList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        String s = "\n";
        s += persons.asUnmodifiableObservableList().size() + " persons";
        s += "\n";
        return s;
        //return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof VersionedAddressBook // instanceof handles nulls
                && persons.equals(((VersionedAddressBook) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}

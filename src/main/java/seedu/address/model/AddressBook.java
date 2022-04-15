package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;

    private UniquePersonList personsHistory;

    private UniquePersonList personsOriginal;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        personsHistory = null;
        personsOriginal = null;
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

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
        resetHistory();
        resetOriginal();
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
     * Returns a string of person attributes that already exists in the address book.
     */
    public String getNonUniquePersonAttributeType(Person person) {
        requireNonNull(person);
        return persons.getNonUniqueAttributeType(person);
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

    /**
     * Saves the previous list of persons in the address book.
     */
    public void saveHistory() {
        if (this.personsHistory == null) {
            this.personsHistory = new UniquePersonList();
        }
        this.personsHistory.setPersons(this.persons);
    }

    /**
     * Restores the previous list of persons in the address book.
     */
    public void restoreHistory() {
        this.persons.setPersons(this.personsHistory);
        resetHistory();
    }

    /**
     * Retrieves the previous list of persons in the address book.
     */
    public UniquePersonList getHistory() {
        return this.personsHistory;
    }

    /**
     * Resets personsOriginal to null.
     */
    public void resetHistory() {
        this.personsHistory = null;
    }

    /**
     * Saves the original list of persons in the address book.
     */
    public void saveOriginal() {
        this.personsOriginal = new UniquePersonList();
        this.personsOriginal.setPersons(this.persons);
    }

    /**
     * Restores the current list of person in the address book.
     */
    public void restoreOriginal() {
        this.persons.setPersons(this.personsOriginal);
        resetOriginal();
    }

    /**
     * Retrieves the original list of persons in the address book.
     */
    public UniquePersonList getOriginal() {
        return this.personsOriginal;
    }

    /**
     * Resets personsOriginal to null.
     */
    public void resetOriginal() {
        this.personsOriginal = null;
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && persons.equals(((AddressBook) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}

package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.candidate.Candidate;
import seedu.address.model.candidate.UniquePersonList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList candidates;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        candidates = new UniquePersonList();
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
    public void setPersons(List<Candidate> candidates) {
        this.candidates.setPersons(candidates);
    }

    /**
     * Reorders the contents of the person list by comparator on {@code sortKey}.
     * {@code persons} must not contain duplicate persons.
     */
    public void sortPersons(List<Candidate> persons, Comparator<Candidate> sortComparator) {
        requireNonNull(persons);
        requireNonNull(sortComparator);
        List<Candidate> personsCopy = new ArrayList<Candidate>(persons);
        personsCopy.sort(sortComparator);
        this.candidates.setPersons(personsCopy);
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
    public boolean hasPerson(Candidate candidate) {
        requireNonNull(candidate);
        return candidates.contains(candidate);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Candidate p) {
        candidates.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Candidate target, Candidate editedCandidate) {
        requireNonNull(editedCandidate);

        candidates.setPerson(target, editedCandidate);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Candidate key) {
        candidates.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return candidates.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Candidate> getPersonList() {
        return candidates.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && candidates.equals(((AddressBook) other).candidates));
    }

    @Override
    public int hashCode() {
        return candidates.hashCode();
    }
}

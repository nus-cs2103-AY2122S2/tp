package seedu.trackbeau.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.trackbeau.model.customer.Customer;
import seedu.trackbeau.model.customer.UniquePersonList;

/**
 * Wraps all data at the trackbeau level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class TrackBeau implements ReadOnlyTrackBeau {

    private final UniquePersonList persons;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
    }

    public TrackBeau() {}

    /**
     * Creates an TrackBeau using the Persons in the {@code toBeCopied}
     */
    public TrackBeau(ReadOnlyTrackBeau toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the customer list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Customer> customers) {
        this.persons.setPersons(customers);
    }

    /**
     * Resets the existing data of this {@code TrackBeau} with {@code newData}.
     */
    public void resetData(ReadOnlyTrackBeau newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
    }

    //// customer-level operations

    /**
     * Returns true if a customer with the same identity as {@code customer} exists in the trackbeau book.
     */
    public boolean hasPerson(Customer customer) {
        requireNonNull(customer);
        return persons.contains(customer);
    }

    /**
     * Adds a customer to the trackbeau book.
     * The customer must not already exist in the trackbeau book.
     */
    public void addPerson(Customer p) {
        persons.add(p);
    }

    /**
     * Replaces the given customer {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the trackbeau book.
     * The customer identity of {@code editedPerson} must not be the same as another existing customer in trackbeau.
     */
    public void setPerson(Customer target, Customer editedCustomer) {
        requireNonNull(editedCustomer);

        persons.setPerson(target, editedCustomer);
    }

    /**
     * Removes {@code key} from this {@code TrackBeau}.
     * {@code key} must exist in the trackbeau book.
     */
    public void removePerson(Customer key) {
        persons.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Customer> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TrackBeau // instanceof handles nulls
                && persons.equals(((TrackBeau) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}

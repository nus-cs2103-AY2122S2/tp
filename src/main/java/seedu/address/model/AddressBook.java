package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;

import javafx.collections.ObservableList;
import seedu.address.model.entry.Event;
import seedu.address.model.entry.Person;
import seedu.address.model.entry.UniqueEntryList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameEntry comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniqueEntryList<Person> persons;
    private final UniqueEntryList<Event> events;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniqueEntryList<Person>();
        events = new UniqueEntryList<Event>();
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
        this.persons.setEntries(persons);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setEvents(newData.getEventList());
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

        persons.setEntry(target, editedPerson);
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
        return persons.asUnmodifiableObservableList().size() + " persons "
                + events.asUnmodifiableObservableList().size() + " events";
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
                && persons.equals(((AddressBook) other).persons)
                && events.equals(((AddressBook) other).events));
    }

    @Override
    public int hashCode() {
        return Objects.hash(persons, events);
    }


    public void setEvents(List<Event> events) {
        this.events.setEntries(events);
    }
    public boolean hasEvent(Event event) {
        requireNonNull(event);
        return events.contains(event);
    }
    public void addEvent(Event e) {
        events.add(e);
    }
    public void setEvent(Event target, Event editedEvent) {
        requireNonNull(editedEvent);

        events.setEntry(target, editedEvent);
    }
    public void removeEvent(Event key) {
        events.remove(key);
    }
    @Override
    public ObservableList<Event> getEventList() {
        return events.asUnmodifiableObservableList();
    }

}

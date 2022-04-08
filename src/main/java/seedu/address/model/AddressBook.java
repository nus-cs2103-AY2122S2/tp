package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.event.Event;
import seedu.address.model.event.UniqueEventList;
import seedu.address.model.person.FriendName;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.person.insights.PersonInsight;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed for both persons and events (by .isSamePerson/.isSameEvent comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final UniqueEventList events;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        events = new UniqueEventList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the data in {@code toBeCopied}.
     * Assumes that the given data is valid.
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //=========== List Override Operations ================================================

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    private void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Replaces the contents of the event list with {@code events}.
     * {@code events} must not contain duplicate events.
     */
    private void setEvents(List<Event> events) {
        this.events.setEvents(events);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     * Assumptions: Given data is valid.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setEvents(newData.getEventList());
    }

    //=========== Person/Event Level Operations ================================================

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

        if (!(target.hasSameName(editedPerson))) {
            events.changeFriendName(target.getName(), editedPerson.getName());
        }
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
        events.removeFriendName(key.getName());
    }

    /**
     * Returns true if a event with the same identity as {@code event} exists in the address book.
     */
    public boolean hasEvent(Event event) {
        requireNonNull(event);
        return events.contains(event);
    }

    /**
     * Adds an event to the address book.
     * The event must not already exist in the address book and its friend names must be already in the address book.
     */
    public void addEvent(Event toAdd) {
        requireNonNull(toAdd);
        assert(areFriendNamesValid(toAdd));
        alignFriendNames(toAdd);

        events.add(toAdd);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeEvent(Event key) {
        events.remove(key);
    }

    /**
     * Replaces the given event {@code target} in the list with {@code editedEvent}.
     * {@code target} must exist in the address book.
     * The identity of {@code editedEvent} must not be the same as another existing event in the address book.
     * Also, the friend names of {@code editedEvent} must be already in the address book.
     */
    public void setEvent(Event target, Event editedEvent) {
        requireNonNull(editedEvent);
        assert(areFriendNamesValid(editedEvent));
        alignFriendNames(editedEvent);

        events.setEvent(target, editedEvent);
    }

    //=========== Person-Event Consistency Methods ================================================

    /**
     * Aligns event's friend names to be exactly the same as actual friend names, including capitalisation.
     * Assumes that all event friend names are valid.
     *
     * @param event Event object containing friend names to be aligned.
     */
    private void alignFriendNames(Event event) {
        requireNonNull(event);
        List<FriendName> originalNames = new ArrayList<>(event.getFriendNames());

        for (FriendName originalName : originalNames) {
            FriendName actualName = persons.getExactName(originalName);
            event.changeFriendNameIfPresent(originalName, actualName);
        }
    }

    /**
     * Returns true if all friend names in the given event correspond to actual Friends in the AddressBook.
     *
     * @param event Event to check friend names for validity.
     * @return true if all friend names correspond to actual Friends in the AddressBook.
     */
    public boolean areFriendNamesValid(Event event) {
        for (FriendName name : event.getFriendNames()) {
            if (!persons.containsPersonWithName(name)) {
                return false;
            }
        }
        return true;
    }

    //=========== Utility Methods ================================================

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(persons.asUnmodifiableObservableList().size()).append(" persons");
        builder.append(events.asUnmodifiableObservableList().size()).append(" events");
        return builder.toString();
        // TODO: refine later
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Event> getEventList() {
        return events.asUnmodifiableObservableList();
    }


    @Override
    public ObservableList<PersonInsight> getInsightsList(Model model) {
        return FXCollections.observableArrayList(this.getPersonList()
                .stream()
                .map(person -> new PersonInsight(person, model))
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) { // short circuit if same object
            return true;
        }
        if (!(other instanceof AddressBook)) { // handles nulls
            return false;
        }
        AddressBook otherBook = (AddressBook) other;
        return persons.equals(otherBook.persons) && events.equals(otherBook.events);
    }

    @Override
    public int hashCode() {
        return Objects.hash(persons, events);
    }
}

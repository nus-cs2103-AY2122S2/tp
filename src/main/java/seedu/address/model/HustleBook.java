package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.ScheduledMeeting;
import seedu.address.model.person.UniquePersonList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class HustleBook implements ReadOnlyHustleBook {

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

    public HustleBook() {}

    /**
     * Creates an HustleBook using the Persons in the {@code toBeCopied}
     */
    public HustleBook(ReadOnlyHustleBook toBeCopied) {
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
     * Resets the existing data of this {@code HustleBook} with {@code newData}.
     */
    public void resetData(ReadOnlyHustleBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the hustle book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the hustle book.
     * The person must not already exist in the hustle book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the hustle book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the hustle book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Returns true if any person with the same scheduled meeting
     * as {@code scheduledMeeting} exists in the hustle book.
     * @param scheduledMeeting The meeting to be scheduled.
     * @return true if meeting clashes.
     */
    public boolean hasSameMeeting(ScheduledMeeting scheduledMeeting) {
        requireNonNull(scheduledMeeting);
        return persons.anyMeetingClash(scheduledMeeting);
    }

    /**
     * Removes {@code key} from this {@code HustleBook}.
     * {@code key} must exist in the hustle book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    /**
     * Sorts the persons in the hustle book.
     */
    public void sortPersonBy(Comparator<Person> sortComparator) {
        persons.sortBy(sortComparator);
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
                || (other instanceof HustleBook // instanceof handles nulls
                && persons.equals(((HustleBook) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}

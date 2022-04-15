package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import javafx.collections.ObservableList;
import seedu.address.model.entry.Company;
import seedu.address.model.entry.Event;
import seedu.address.model.entry.Person;
import seedu.address.model.entry.UniqueEntryList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameEntry comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniqueEntryList<Person> persons;
    private final UniqueEntryList<Company> companies;
    private final UniqueEntryList<Event> events;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniqueEntryList<>();
        companies = new UniqueEntryList<>();
        events = new UniqueEntryList<>();
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
     * Replaces the contents of the companies list with {@code companies}.
     * {@code companies} must not contain duplicate companies.
     */
    public void setCompanies(List<Company> companies) {
        this.companies.setEntries(companies);
    }

    /**
     * Replaces the contents of the event list with {@code events}.
     * {@code events} must not contain duplicate events.
     */
    public void setEvents(List<Event> events) {
        this.events.setEntries(events);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setCompanies(newData.getCompanyList());
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

    /**
     * Sets archived state of {@code key} in the person list.
     * {@code key} must exist in the person list.
     */
    public void setArchivePerson(Person key, boolean isArchived) {
        persons.setArchived(key, isArchived);
    }

    //// company-level operations

    /**
     * Returns true if a company with the same identity as {@code company} exists in the address book.
     */
    public boolean hasCompany(Company company) {
        requireNonNull(company);
        return companies.contains(company);
    }

    /**
     * Adds a company to the address book.
     * The company must not already exist in the address book.
     */
    public void addCompany(Company c) {
        companies.add(c);
    }

    /**
     * Replaces the given company {@code target} in the list with {@code editedCompany}.
     * {@code target} must exist in the address book.
     * The company identity of {@code editedCompany} must not be the same as another existing company in the
     * address book.
     */
    public void setCompany(Company target, Company editedCompany) {
        requireNonNull(editedCompany);

        companies.setEntry(target, editedCompany);
    }

    /**
     * Removes {@code key} from the company list.
     * {@code key} must exist in the company list.
     */
    public void removeCompany(Company key) {
        companies.remove(key);
    }

    /**
     * Sets archived state of {@code key} in the company list.
     * {@code key} must exist in the company list.
     */
    public void setArchiveCompany(Company key, boolean isArchived) {
        companies.setArchived(key, isArchived);
    }

    //// event-level operations

    /**
     * Returns true if an event with the same identity as {@code event} exists in the address book.
     */
    public boolean hasEvent(Event event) {
        requireNonNull(event);
        return events.contains(event);
    }

    /**
     * Adds an event to the address book.
     * The event must not already exist in the address book.
     */
    public void addEvent(Event e) {
        events.add(e);
    }

    /**
     * Replaces the contents of the event list with {@code events}.
     * {@code events} must not contain duplicate events.
     */
    public void setEvent(Event target, Event editedEvent) {
        requireNonNull(editedEvent);

        events.setEntry(target, editedEvent);
    }

    /**
     * Removes {@code key} from the event list.
     * {@code key} must exist in the event list.
     */
    public void removeEvent(Event key) {
        events.remove(key);
    }

    /**
     * Sort list of {@code Company}s in the address book.
     */
    public void sortCompanies(Comparator<? super Company> comparator) {
        companies.sort(comparator);
    }

    /**
     * Sort list of {@code Person}s in the address book.
     */
    public void sortPersons(Comparator<? super Person> comparator) {
        persons.sort(comparator);
    }

    /**
     * Sort list of {@code Event}s in the address book.
     */
    public void sortEvents(Comparator<? super Event> comparator) {
        events.sort(comparator);
    }

    /**
     * Sets archived state of {@code key} in the event list.
     * {@code key} must exist in the event list.
     */
    public void setArchiveEvent(Event key, boolean isArchived) {
        events.setArchived(key, isArchived);
    }

    @Override
    public void updateCompanyNames(String oldName, String newName) {
        persons.updateCompanyNames(oldName, newName);
        events.updateCompanyNames(oldName, newName);
    }

    /**
     * Removes all entries whose companyName attribute has the same string as the given {@code companyName}.
     * Note: Thie function should only be called for Person and Event lists.
     */
    public void removeMatchingCompanyName(String companyName) {
        persons.removeMatchingCompanyName(companyName);
        events.removeMatchingCompanyName(companyName);
    }

    @Override
    public ObservableList<Event> getEventList() {
        return events.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Company> getCompanyList() {
        return companies.asUnmodifiableObservableList();
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons,"
                + companies.asUnmodifiableObservableList().size() + " companies, "
                + events.asUnmodifiableObservableList().size() + " events";
        // TODO: refine later
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AddressBook)) {
            return false;
        }

        AddressBook otherAddressBook = (AddressBook) other;
        return persons.equals(otherAddressBook.persons)
                && companies.equals(otherAddressBook.companies)
                && events.equals(otherAddressBook.events);
    }

    @Override
    public int hashCode() {
        return Objects.hash(persons, companies, events);
    }

}

package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.LinkedHashMap;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentId;
import seedu.address.model.person.Task;
import seedu.address.model.person.UniquePersonList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

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

    /**
     * Assigns {@code task} to {@code Person} with {@code studentId}.
     *
     * @param studentId the student id of the person to be assigned.
     * @param task the task to be assigned.
     */
    public void assignTaskToPerson(StudentId studentId, Task task) {
        requireNonNull(studentId);
        requireNonNull(task);

        persons.assignTaskToPerson(studentId, task);
    }

    /**
     * Marks task with {@code index} belonging to {@code Person} with {@code studentId} as done.
     *
     * @param studentId the student id of the person who's task is to be marked.
     * @param index the task to be marked as done.
     */
    public void markTaskOfPerson(StudentId studentId, Index index) {
        requireNonNull(studentId);
        requireNonNull(index);

        persons.markTaskOfPerson(studentId, index);
    }

    /**
     * Marks task with {@code index} belonging to {@code Person} with {@code studentId} as undone.
     *
     * @param studentId the student id of the person who's task is to be marked.
     * @param index the task to be marked as undone.
     */
    public void unmarkTaskOfPerson(StudentId studentId, Index index) {
        requireNonNull(studentId);
        requireNonNull(index);

        persons.unmarkTaskOfPerson(studentId, index);
    }

    /**
     * Sorts the list of persons in alphabetical order by their name.
     */
    public void sortPersonList() {
        persons.sortList();
    }

    /**
     * Returns a key-value pair between each {@code person} and the completion status of a task,
     * if the person is taking the specified module and is being assigned with the specified task.
     *
     *
     * @param moduleCode target moduleCode to be compared with.
     * @param task target task to be compared with
     * @return LinkedHashMap containing valid person/completion status pair.
     */
    public LinkedHashMap<Person, Boolean> checkProgress(ModuleCode moduleCode, Task task) {
        requireNonNull(moduleCode);
        requireNonNull(task);
        return persons.checkProgress(moduleCode, task);
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

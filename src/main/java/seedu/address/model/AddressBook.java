package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;

import javafx.collections.ObservableList;
import seedu.address.model.group.Group;
import seedu.address.model.group.UniqueGroupList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.task.Task;
import seedu.address.model.task.UniqueTaskList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final UniqueGroupList groups;
    private final UniqueTaskList tasks;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        groups = new UniqueGroupList();
        tasks = new UniqueTaskList();
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
     * Replaces the contents of the group list with {@code groups}.
     * {@code groups} must not contain duplicate groups.
     */
    public void setGroups(List<Group> groups) {
        this.groups.setGroups(groups);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setGroups(newData.getGroupList());
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
     * Returns true if a group with the same identity as {@code group} exists in the address book.
     */
    public boolean hasGroup(Group group) {
        requireNonNull(group);
        return groups.contains(group);
    }

    /**
     * Returns true if a tasks with the same identity as {@code task} exists in the
     * specified group in the address book.
     */
    public boolean hasTask(Task task, Group group) {
        requireNonNull(group);
        requireNonNull(task);
        return groups.getGroup(group).getTaskList().contains(task);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Adds a group to the address book.
     * The group must not already exist in the address book.
     */
    public void addGroup(Group g) {
        groups.add(g);
    }

    /**
     * Adds a task to a specified group from the address book.
     * The group must already exist in the address book.
     * The task must not already exist in the specified group in the address book.
     */
    public void addTask(Task task, Group group) {
        groups.getGroup(group).addTask(task);
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
     * Replaces the given group {@code target} in the list with {@code editedGroup}.
     * {@code target} must exist in the address book.
     * The group identity of {@code editedGroup} must not be the same as another existing group in the address book.
     */
    public void setGroup(Group target, Group editedGroup) {
        requireNonNull(editedGroup);
        groups.setGroup(target, editedGroup);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeGroup(Group key) {
        groups.remove(key);
    }

    /**
     * Removes a task from a specified group from the address book.
     * The group must already exist in the address book.
     * The task must already exist in the specified group in the address book.
     */
    public void removeTask(Task task, Group g) {
        groups.getGroup(g).removeTask(task);
    }

    /**
     * Views tasks from a specified group from the address book.
     * The group must already exist in the address book.
     */
    public String viewTask(Group group) {
        return groups.getGroup(group).viewTask();
    }

    /**
     * Views student contacts from a specified group from the address book.
     * The group must already exist in the address book.
     */
    public String viewContact(Group g) {
        return groups.getGroup(g).viewContact();
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Group> getGroupList() {
        return groups.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Task> getTaskList() {
        return tasks.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && persons.equals(((AddressBook) other).persons)
                && groups.equals(((AddressBook) other).groups));
    }

    @Override
    public int hashCode() {
        return Objects.hash(persons.hashCode(), groups.hashCode());
    }

    /**
     * Assigns a {@code Person} to a {@code Group} in this {@code AddressBook}.
     *
     * @param personToAssign The {@code Person} being assigned.
     * @param group The {@code Group} that the {@code Person} is being assigned.
     */
    public void assignPerson(Person personToAssign, Group group) {
        group.assignPerson(personToAssign);
        setGroup(new Group(group.getGroupName()), group);
    }

    /**
     * Deassigns a {@code Person} from a {@code Group} in this {@code AddressBook}.
     *
     * @param personToDeassign The {@code Person} being deassigned.
     * @param group The {@code Group} that the {@code Person} is being deassigned.
     */
    public void deassignPerson(Person personToDeassign, Group group) {
        group.deassignPerson(personToDeassign);
        setGroup(new Group(group.getGroupName()), group);
    }
}

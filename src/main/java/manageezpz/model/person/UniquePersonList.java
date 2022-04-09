package manageezpz.model.person;

import static java.util.Objects.requireNonNull;
import static manageezpz.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import manageezpz.model.person.exceptions.DuplicatePersonException;
import manageezpz.model.person.exceptions.PersonNotFoundException;

/**
 * A list of persons that enforces uniqueness between its elements and does not allow nulls.
 * A person is considered unique by comparing using {@code Person#isSamePerson(Person)}. As such, adding and updating of
 * persons uses Person#isSamePerson(Person) for equality to ensure that the person being added or updated is
 * unique in terms of identity in the UniquePersonList. However, the removal of a person uses Person#equals(Object)
 * to ensure that the person with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Person#isSamePerson(Person)
 */
public class UniquePersonList implements Iterable<Person> {

    private final ObservableList<Person> internalList = FXCollections.observableArrayList();
    private final ObservableList<Person> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Checks if the list contains an equivalent person as the given argument.
     * @param toCheck the person to be checked.
     * @return true if the list contains the specified person, false otherwise.
     */
    public boolean contains(Person toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSamePerson);
    }

    /**
     * Adds a person to the list.
     * The person must not already exist in the list.
     * @param toAdd the person to be added.
     */
    public void add(Person toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the list.
     * @param target the person to be replaced.
     * @param editedPerson the new person to replace the target.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        if (!target.isSamePerson(editedPerson) && contains(editedPerson)) {
            throw new DuplicatePersonException();
        }

        internalList.set(index, editedPerson);
    }

    /**
     * Increases the number of tasks by one.
     * @param person the person to increment the number of tasks.
     */
    public void increaseNumOfTasks(Person person) {
        requireNonNull(person);

        Person updatedPerson = new Person(person.getName(), person.getPhone(),
                person.getEmail(), person.getNumOfTasks());
        updatedPerson.increaseTaskCount();

        setPerson(person, updatedPerson);
    }

    /**
     * Decreases the number of tasks by one.
     * @param person the person to decrement the number of tasks.
     */
    public void decreaseNumOfTasks(Person person) {
        requireNonNull(person);

        Person updatedPerson = new Person(person.getName(), person.getPhone(),
                person.getEmail(), person.getNumOfTasks());
        updatedPerson.decreaseTaskCount();

        setPerson(person, updatedPerson);
    }

    /**
     * Removes the equivalent person from the list.
     * The person must exist in the list.
     * @param toRemove the person to be removed.
     */
    public void remove(Person toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PersonNotFoundException();
        }
    }

    /**
     * Replaces the current person list with the provided list.
     * @param replacement the list to replace the current person list.
     */
    public void setPersons(UniquePersonList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     * @param persons the list to replace the old list.
     */
    public void setPersons(List<Person> persons) {
        requireAllNonNull(persons);
        if (!personsAreUnique(persons)) {
            throw new DuplicatePersonException();
        }

        internalList.setAll(persons);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     * @return the unmodifiable persons list.
     */
    public ObservableList<Person> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<Person> iterator() {
        return internalList.iterator();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniquePersonList // instanceof handles nulls
                        && internalList.equals(((UniquePersonList) other).internalList));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    private boolean personsAreUnique(List<Person> persons) {
        for (int i = 0; i < persons.size() - 1; i++) {
            for (int j = i + 1; j < persons.size(); j++) {
                if (persons.get(i).isSamePerson(persons.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}

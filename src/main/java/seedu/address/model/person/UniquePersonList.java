package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.lineup.Lineup;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * A list of persons that enforces uniqueness between its elements and does not allow nulls.
 * A person is considered unique by comparing using {@code Person#isSamePerson(Person)}. As such, adding and updating of
 * persons uses Person#isSamePerson(Person) for equality so as to ensure that the person being added or updated is
 * unique in terms of identity in the UniquePersonList. However, the removal of a person uses Person#equals(Object) so
 * as to ensure that the person with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Person#isSamePerson(Person)
 */
public class UniquePersonList implements Iterable<Person> {
    private static final int MAXIMUM_CAPACITY = 100;

    private final ObservableList<Person> internalList = FXCollections.observableArrayList();
    private final ObservableList<Person> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(Person toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSamePerson);
    }

    /**
     * Return true if some person with {@code targetName}
     * @param targetName
     * @return
     */
    public boolean containsName(Name targetName) {
        requireNonNull(targetName);
        return internalList.stream().anyMatch(person -> person.isMatchName(targetName));
    }

    /**
     * Adds a person to the list.
     * The person must not already exist in the list.
     */
    public void add(Person toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonException();
        }
        internalList.add(toAdd);
    }

    /**
     * Refreshes the observable list so that update can be reflected in GUI.
     */
    public void refresh() {
        List<Person> playersCopy = new ArrayList<>(internalList);
        internalList.setAll(playersCopy);
    }

    /**
     * Replaces the person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the list.
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
     * Returns the person with {@code targetName};
     */
    public Person getPerson(Name targetName) {
        requireNonNull(targetName);
        return internalList.stream()
                .filter(person -> person.isMatchName(targetName))
                .collect(Collectors.toList()).get(0);
    }

    /**
     * Removes all player in the target lineup
     *
     * @param lineup The lineup to be cleared
     */
    public void removeAllPlayerFromLineup(Lineup lineup) {
        requireNonNull(lineup);
        for (Person person : this.internalList) {
            if (person.isInLineup(lineup)) {
                person.removeFromLineup(lineup);
                //System.out.printf("%s has been removed from lineup %s\n", person.getName(), lineup.getLineupName());
            }
        }
    }

    /**
     * Returns true if the person's jersey number is already taken.
     */
    public boolean containsJerseyNumber(JerseyNumber jerseyNumber) {
        requireNonNull(jerseyNumber);
        return internalList.stream()
                .anyMatch(person -> person.isSameJerseyNumber(jerseyNumber));
    }

    /**
     * Returns true if MyGM has reached maximum capacity.
     */
    public boolean isFull() {
        return internalList.size() == MAXIMUM_CAPACITY;
    }

    /**
     * Removes the equivalent person from the list.
     * The person must exist in the list.
     */
    public void remove(Person toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PersonNotFoundException();
        }
    }

    public void setPersons(UniquePersonList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        requireAllNonNull(persons);
        if (!personsAreUnique(persons)) {
            throw new DuplicatePersonException();
        }

        internalList.setAll(persons);
    }

    /**
     * Returns a string representation of a list of available Jersey Number.
     * @return
     */
    public String getAvailableJerseyNumber() {
        Stream<Integer> stream = IntStream.range(0, MAXIMUM_CAPACITY).boxed();
        List<Integer> ls = stream
                .filter(x -> !this.containsJerseyNumber(new JerseyNumber(((Integer) x).toString())))
                .collect(Collectors.toList());
        return ls.toString();
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Person> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Person> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniquePersonList // instanceof handles nulls
                        && internalList.equals(((UniquePersonList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code persons} contains only unique persons.
     */
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

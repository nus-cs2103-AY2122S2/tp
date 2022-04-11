package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.person.exceptions.StudentNotFoundException;
import seedu.address.model.tutorial.TutorialName;

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
     * Returns the Student that has the same student id as {@code id}
     */
    public Student getStudentWithId(NusNetId id) {
        requireNonNull(id);
        for (int i = 0; i < internalList.size(); i++) {
            Person p = internalList.get(i);
            if (!(p instanceof Student)) {
                continue;
            }
            Student s = (Student) p;
            if (s.getStudentId().equals(id)) {
                return s;
            }
        }
        throw new StudentNotFoundException();
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

    /**
     * Returns true if the list contains an equivalent person with
     * the same name as the given argument.
     */
    public boolean hasPersonWithName(Name name) {
        requireNonNull(name);
        return internalList.stream().anyMatch(person -> person.getName().equals(name));
    }

    /**
     * Returns true if the list contains a person with the same email as {@code email}.
     */
    public boolean hasPersonWithEmail(Email email) {
        requireNonNull(email);
        return internalList.stream().anyMatch(person -> person.getEmail().equals(email));
    }

    /**
     * Returns true if the list contains a person with the same phone as {@code phone}.
     */
    public boolean hasPersonWithPhone(Phone phone) {
        requireNonNull(phone);
        return internalList.stream().anyMatch(person -> person.getPhone().equals(phone));
    }

    /**
     * Runs through the all contents in this list to find the person with
     * name matching given {@code name}.
     */
    public Person getPersonWithName(Name name) {
        requireNonNull(name);
        Person person = internalList.stream()
                .filter(p -> p.getName().equals(name))
                .findAny()
                .orElse(null);

        if (person == null) {
            throw new PersonNotFoundException();
        }
        return person;

    }

    public NusNetId getIdOfStudent(Name studentName) {
        requireNonNull(studentName);
        Person person = getPersonWithName(studentName);
        if (!(person instanceof Student)) {
            throw new StudentNotFoundException();
        }
        return ((Student) person).getStudentId();
    }

    public TutorialName getTutorialNameOfStudent(Name studentName) {
        requireNonNull(studentName);
        Person person = getPersonWithName(studentName);
        if (!(person instanceof Student)) {
            throw new StudentNotFoundException();
        }
        return ((Student) person).getTutorialName();
    }

}

package unibook.model.person;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import unibook.commons.util.CollectionUtil;
import unibook.model.module.ModuleCode;
import unibook.model.module.group.Group;
import unibook.model.person.exceptions.DuplicatePersonException;
import unibook.model.person.exceptions.PersonNotFoundException;

/**
 * A list of persons that enforces uniqueness between its elements and does not allow nulls.
 * A person is considered unique by comparing using {@code Person#isSamePerson(Person)}. As such, adding and updating of
 * persons uses Person#isSamePerson(Person) for equality so as to ensure that the person being added or updated is
 * unique in terms of identity in the UniquePersonList. However, the removal of a person uses Person#equals(Object) so
 * as to ensure that the person with exactly the same fields will be removed.
 * <p>
 * Supports a minimal set of list operations.
 *
 * @see Person#isSamePerson(Person)
 */
public class UniquePersonList implements Iterable<Person> {

    private final ObservableList<Person> internalList = FXCollections.observableArrayList();
    private final ObservableList<Person> internalUnmodifiableList =
        FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent person (using equals) as the given argument.
     */
    public boolean contains(Person toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Returns true if any person in the list contains same email or phone as checked person.
     */
    public boolean hasSameEmailOrPhoneInList(Person toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::hasSameEmailOrPhone);
    }

    /**
     * Adds a person to the list.
     * The person must not already exist in the list.
     */
    public void add(Person toAdd) {
        requireNonNull(toAdd);
        if (hasSameEmailOrPhoneInList(toAdd)) {
            throw new DuplicatePersonException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the list.
     */
    public void setPerson(int idx, Person target, Person editedPerson) {
        CollectionUtil.requireAllNonNull(target, editedPerson);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        if (!target.isSamePerson(editedPerson) && contains(editedPerson)) {
            throw new DuplicatePersonException();
        }

        internalList.set(idx, editedPerson);
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

    /**
     * Remove module that matches with moduleCode from every person in UniquePersonList
     *
     * @param moduleCode
     */
    public void removeModuleFromAllPersons(ModuleCode moduleCode) {
        for (Person person : internalList) {
            person.removeModule(moduleCode);
        }
    }

    /**
     * Delete all students and professors from unique person list if they exist in the 2 list provided
     *
     * @param listOfStudents
     * @param listOfProfessors
     */
    public void deleteStudentsAndProfs(List<Student> listOfStudents, List<Professor> listOfProfessors) {
        for (Student student: listOfStudents) {
            internalList.remove(student);
        }
        for (Professor professor: listOfProfessors) {
            internalList.remove(professor);
        }
    }

    /**
     * Delete all professors from unique person list if they exist in the list provided
     *
     * @param listOfProfessors
     */
    public void deleteProfs(List<Professor> listOfProfessors) {
        for (Professor professor: listOfProfessors) {
            internalList.remove(professor);
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
        CollectionUtil.requireAllNonNull(persons);
        if (!personsPhoneAndEmailsAreUnique(persons)) {
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
     * Returns true if {@code persons} contains no duplicate phones or emails.
     */
    private boolean personsPhoneAndEmailsAreUnique(List<Person> persons) {
        for (int i = 0; i < persons.size() - 1; i++) {
            for (int j = i + 1; j < persons.size(); j++) {
                if (persons.get(i).hasSameEmailOrPhone(persons.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns true if {@code persons} contains person with given phone number.
     */
    public boolean phoneNumberBeingUsed(Phone phone) {
        if (phone.isEmpty()) {
            return false;
        }
        for (int i = 0; i < internalList.size(); i++) {
            if (internalList.get(i).getPhone().equals(phone)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if {@code persons} contains person with given phone number.
     */
    public int getIdxOfPhoneNumberBeingUsed(Phone phone) {

        for (int i = 0; i < internalList.size(); i++) {
            if (internalList.get(i).getPhone().equals(phone)) {
                return i;
            }
        }
        return -1;
    }


    /**
     * Returns true if {@code persons} contains person with given email.
     */
    public boolean emailBeingUsed(Email email) {
        if (email.isEmpty()) {
            return false;
        }
        for (int i = 0; i < internalList.size(); i++) {
            if (internalList.get(i).getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if {@code persons} contains person with given email.
     */
    public int getIdxOfEmailBeingUsed(Email email) {

        for (int i = 0; i < internalList.size(); i++) {
            if (internalList.get(i).getEmail().equals(email)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Delete the specified Group from all persons in person list
     *
     * @param moduleCode
     * @param group
     */
    public void deleteGroupFromAllPersons(ModuleCode moduleCode, Group group) {
        for (Person person : internalList) {
            if (person instanceof Student && person.hasModule(moduleCode)) {
                Student student = (Student) person;
                student.removeGroup(moduleCode, group);
            }
        }
    }

    /**
     * Remove all groups from all students that are associated with this module code
     *
     * @param moduleCode
     */
    public void removeGroupFromAllStudents(ModuleCode moduleCode) {
        for (Person person : internalList) {
            if (person instanceof Student) {
                Student student = (Student) person;
                student.removeGroup(moduleCode);
            }
        }
    }
}

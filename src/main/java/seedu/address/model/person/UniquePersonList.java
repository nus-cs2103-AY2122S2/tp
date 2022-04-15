package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;

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
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(Person toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSamePerson);
    }

    /**
     * Returns a string of the type of attributes that is unique to a {@code person} that already exist
     * in the address book.
     */
    public String getNonUniqueAttributeType(Person toCheck) {
        requireNonNull(toCheck);
        ArrayList<String> nonUniqueAttributeTypes = new ArrayList<>();
        for (int i = 0; i < internalList.size(); i++) {
            Person otherPerson = internalList.get(i);
            if (otherPerson.isSamePerson(toCheck)) { // Unique attributes already exist in the address book.
                if (otherPerson.getPhone().equals(toCheck.getPhone())) {
                    addNonUniqueAttributeType(nonUniqueAttributeTypes, 0);
                }
                if (otherPerson.getEmail().equals(toCheck.getEmail())) {
                    addNonUniqueAttributeType(nonUniqueAttributeTypes, 1);
                }
                if (otherPerson.getMatriculationNumber().equals(toCheck.getMatriculationNumber())) {
                    addNonUniqueAttributeType(nonUniqueAttributeTypes, 2);
                }
            }
        }
        return getNonUniqueAttributeTypesMessage(nonUniqueAttributeTypes);
    }

    /**
     * Adds a non-unique attribute type to the list if it is not yet added.
     *
     * @param nonUniqueAttributeTypes   The list containing the non-unique attribute types
     * @param uniqueAttributeTypesIndex The index of the unique attributes specified by the {@code Person} class
     */
    private void addNonUniqueAttributeType(ArrayList<String> nonUniqueAttributeTypes, int uniqueAttributeTypesIndex) {
        assert uniqueAttributeTypesIndex >= 0 && uniqueAttributeTypesIndex <= Person.UNIQUE_ATTRIBUTE_TYPES.length
            : false;
        String attributeType = Person.UNIQUE_ATTRIBUTE_TYPES[uniqueAttributeTypesIndex];
        if (!nonUniqueAttributeTypes.contains(attributeType)) {
            nonUniqueAttributeTypes.add(attributeType);
        }
    }

    /**
     * Returns the message for non-unique attribute types.
     *
     * @param nonUniqueAttributeTypes The types of non-unique attribute
     */
    public String getNonUniqueAttributeTypesMessage(ArrayList<String> nonUniqueAttributeTypes) {
        if (nonUniqueAttributeTypes.isEmpty()) {
            return "";
        }
        StringBuilder message = new StringBuilder(); // to store all the non-unique attribute types
        if (nonUniqueAttributeTypes.size() == 1) { // only have one non-unique attribute types
            message.append(nonUniqueAttributeTypes.get(0));
            return message.toString();
        } else if (nonUniqueAttributeTypes.size() == 2) {
            message.append(nonUniqueAttributeTypes.get(0) + " and ");
            message.append(nonUniqueAttributeTypes.get(1));
        } else {
            for (int i = 0; i < nonUniqueAttributeTypes.size(); i++) {
                if (i == 0) { // first attribute type in the list
                    message.append(nonUniqueAttributeTypes.get(0));
                } else if (i + 1 == nonUniqueAttributeTypes.size()) { // last attribute type in the list
                    message.append(" and ");
                    message.append(nonUniqueAttributeTypes.get(i));
                } else { // neither first nor last
                    message.append(", ");
                    message.append(nonUniqueAttributeTypes.get(i));
                }
            }
        }
        return message.toString();
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
}

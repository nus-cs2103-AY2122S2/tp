package seedu.contax.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.contax.model.person.Person;
import seedu.contax.model.person.UniquePersonList;
import seedu.contax.model.tag.Tag;
import seedu.contax.model.tag.UniqueTagList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final UniqueTagList tags;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        tags = new UniqueTagList();
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

    public void setTags(List<Tag> tags) {
        this.tags.setTags(tags);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setTags(newData.getTagList());
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
        addTagsNotInList(p);
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);
        addTagsNotInList(editedPerson);
        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    //// tag-level operations

    /**
     * Returns true if a matching {@code tag} exists in the address book.
     * @param tag The tag specified tag to search.
     * @return true if the tag exists in the tags list.
     */
    public boolean hasTag(Tag tag) {
        requireNonNull(tag);
        return tags.contains(tag);
    }

    /**
     * Adds the given tag into the address book.
     * Tag must not already exist in the address book.
     */
    public void addTag(Tag tag) {
        tags.add(tag);
    }

    /**
     * Deletes the given tag from the address book.
     * The supplied tag must already exist in the address book.
     */
    public void removeTag(Tag tagToDelete) {
        removeTagFromPersons(tagToDelete);
        tags.remove(tagToDelete);
    }

    /**
     * Replaces the given {@code target} with {@code editedTag}.
     * {@code target} must exist in the address book.
     * {@code editedTag} must not be the same as another existing tag in the address book.
     */
    public void setTag(Tag target, Tag editedTag) {
        tags.setTag(target, editedTag);
        setPersonsWithTag(target, editedTag);
    }

    /**
     * Disassociates the specified tag from all persons in the address book.
     */
    private void removeTagFromPersons(Tag tagToDelete) {
        requireNonNull(tagToDelete);
        List<Person> persons = getPersonList().filtered(person -> person.hasTag(tagToDelete));
        for (int i = 0; i < persons.size(); i++) {
            Person oldPerson = persons.get(i);
            setPerson(oldPerson, oldPerson.withoutTag(tagToDelete));
        }
    }

    /**
     * Adds tags in {@code persons} that do not exist in the address book.
     */
    private void addTagsNotInList(Person person) {
        requireNonNull(person);
        Set<Tag> tagSet = person.getTags();
        for (Tag personTag : tagSet) {
            if (!tags.contains(personTag)) {
                addTag(personTag);
            }
        }
    }

    /**
     * Replaces {@code target} with {@code editedTag} in all persons of the address book.
     */
    private void setPersonsWithTag(Tag target, Tag editedTag) {
        requireNonNull(target);
        requireNonNull(editedTag);

        List<Person> persons = getPersonList();
        for (int i = 0; i < persons.size(); i++) {
            Person person = persons.get(i);

            if (!person.hasTag(target)) {
                continue;
            }

            Person editedPerson = person.withoutTag(target).withTag(editedTag);
            setPerson(person, editedPerson);
        }
    }

    //// util methods

    @Override
    public String toString() {
        String numPersons = persons.asUnmodifiableObservableList().size() + " persons";
        String numTags = tags.asUnmodifiableObservableList().size() + " tags";
        return numPersons + " | " + numTags;
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Tag> getTagList() {
        return tags.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && persons.equals(((AddressBook) other).persons)
                && tags.equals(((AddressBook) other).tags));
    }

    @Override
    public int hashCode() {
        return Objects.hash(persons, tags);
    }
}

package seedu.unite.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.unite.model.person.Person;
import seedu.unite.model.person.UniquePersonList;
import seedu.unite.model.tag.Tag;
import seedu.unite.model.tag.UniqueTagList;

/**
 * Wraps all data at the unite level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class Unite implements ReadOnlyUnite {

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

    public Unite() {}

    /**
     * Creates an Unite using the Persons in the {@code toBeCopied}
     */
    public Unite(ReadOnlyUnite toBeCopied) {
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
     * Replaces the contents of the tag list with {@code tags}.
     * {@code tags} must not contain duplicate tags.
     */
    public void setTags(List<Tag> tags) {
        this.tags.setTags(tags);
    }

    /**
     * Resets the existing data of this {@code Unite} with {@code newData}.
     */
    public void resetData(ReadOnlyUnite newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setTags(newData.getTagList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the unite.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Returns true if a tag with the same identity as {@code tag} exists in the unite.
     */
    public boolean hasTag(Tag tag) {
        requireNonNull(tag);
        return tags.contains(tag);
    }

    /**
     * Adds a person to the unite.
     * The person must not already exist in the unite.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Adds a tag to the unite.
     * The tag must not already exist in the unite.
     */
    public void addTag(Tag t) {
        tags.add(t);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the unite.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the unite.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Replaces the given tag {@code target} in the list with {@code editedTag}.
     * {@code target} must exist in the unite.
     * The tag identity of {@code editedTag} must not be the same as another existing tag in the unite.
     */
    public void setTag(Tag target, Tag editedTag) {
        requireNonNull(editedTag);

        tags.setTag(target, editedTag);
    }

    /**
     * Removes {@code key} from this {@code Unite}.
     * {@code key} must exist in the unite.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    /**
     * Removes {@code key} from this {@code Unite}.
     * {@code key} must exist in the unite.
     */
    public void removeTag(Tag key) {
        tags.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons "
                + tags.asUnmodifiableObservableList().size() + " tags";
        // TODO: refine later
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
                || (other instanceof Unite // instanceof handles nulls
                && persons.equals(((Unite) other).persons))
                && tags.equals(((Unite) other).tags);
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
